from copy import deepcopy
import pandas as pd
from Constants import TIME2DAYS
from sklearn.preprocessing import StandardScaler
from sklearn.feature_extraction.text import CountVectorizer
from scipy.sparse import coo_matrix
import numpy as np


class Normalizer:
    def __init__(self):
        self.mean = None
        self.std = None

    def fit_transform(self, a: pd.Series):
        self.mean = a.mean()
        self.std = a.std()
        return self.transform(a)

    def transform(self, a: pd.Series):
        assert self.mean is not None, 'Before transform fit must be executed.'
        assert self.std is not None, 'Before transform fit must be executed.'
        return (a - self.mean) / self.std


class OneHotEncoderMultipleLabels:
    def __init__(self, prefix, sparse):
        self.columns = None
        self.columns_set = None
        self.sparse = sparse
        self.prefix = prefix

    def fit_transform(self, a: pd.Series):
        self.columns_set = set()

        for s in a:
            for label in s.split('/'):
                self.columns_set.add(self.prefix + label)

        self.columns = list(self.columns_set)
        return self.transform(a)

    def transform(self, a):
        df = pd.DataFrame(np.zeros((a.shape[0], len(self.columns)), dtype=int), columns=self.columns)

        for i, s in enumerate(a):
            for label in s.split('/'):
                prefixed_label = self.prefix + label
                if prefixed_label in self.columns_set:
                    df.at[i, prefixed_label] += 1
        if not self.sparse:
            return df
        else:
            return coo_matrix(df)


class Process:
    def __init__(self):
        self.vectorizer = None
        self.scaler = None

    def _transform(self, df, fit):
        df = deepcopy(df)
        df = df.drop(['ID', 'Name'], axis=1)

        # Time preprocessing
        df['DateTime'] = pd.to_datetime(df['DateTime']).values.astype('int64')

        def transform_age(s):
            if s is np.nan:
                return s
            arr = s.split(' ')
            return int(arr[0]) * TIME2DAYS[arr[1]]

        df['AgeuponOutcome'] = df['AgeuponOutcome'].apply(transform_age)

        df['AgeuponOutcome'].fillna(df['AgeuponOutcome'].mean(), inplace=True)

        # Color preprocessing
        if fit:
            self.vectorizer = CountVectorizer()
            vectorized_color = self.vectorizer.fit_transform(df["Color"])
        else:
            vectorized_color = self.vectorizer.transform(df["Color"])

        columns = ["" for i in range(len(self.vectorizer.vocabulary_))]

        for key, value in self.vectorizer.vocabulary_.items():
            columns[value] = "color_" + key

        colors = pd.DataFrame(vectorized_color.todense(), columns=columns)
        df = df.drop(['Color'], axis=1)
        df = pd.concat([df, colors], axis=1)

        # AnimalType preprocessing
        df['AnimalType'] = df['AnimalType'].apply(lambda s: int(s == 'Dog'))

        # Sex
        df['Sex'] = df['SexuponOutcome'].apply(lambda s: int(s.find('Male') != -1))
        df['Intact'] = df['SexuponOutcome'].apply(lambda s: int(s.find('Intact') != -1))
        df = df.drop(['SexuponOutcome'], axis=1)

        # Breed
        df['breed_mix'] = df['Breed'].apply(lambda s: int(s.find('/') != -1))

        if fit:
            self.BreedEncoder = OneHotEncoderMultipleLabels(sparse=False, prefix='breed_')
            df = pd.concat([df, self.BreedEncoder.fit_transform(df['Breed'])], axis=1)
        else:
            df = pd.concat([df, self.BreedEncoder.transform(df['Breed'])], axis=1)
        df = df.drop(['Breed'], axis=1)

        if fit:
            self.scaler = StandardScaler()
            self.scaler.fit(df)
        df = pd.DataFrame(self.scaler.transform(df), columns=df.columns)
        return df

    def fit_transform(self, df):
        return self._transform(df, fit=True)

    def transform(self, df):
        return self._transform(df, fit=False)


