from Constants import random_state
from sklearn.linear_model import LogisticRegression


def select(X_train, y_train, num_feat=50):
    clf = LogisticRegression(random_state=random_state, class_weight='balanced', penalty='l1', solver='saga',
                             C=10,
                             max_iter=100)
    clf.fit(X_train, y_train)

    feat = set()
    for i in range(5):
        a = list(zip(X_train.columns, clf.coef_[i]))
        a = sorted(a, key=lambda p: -abs(p[1]))
        feat_added = 0
        for p in a:
            if feat_added == num_feat // 5:
                break
            if not p[0] in feat:
                feat.add(p[0])
                feat_added += 1

    return list(feat)
