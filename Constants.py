OUTCOME2LABEL = {"Adoption": 0,
                 "Transfer": 1,
                 "Return_to_owner": 2,
                 "Euthanasia": 3,
                 "Died": 4}

LABEL2OUTCOME = {v: k for k, v in OUTCOME2LABEL.items()}

TIME2DAYS = {'year': 365,
             'years': 365,
             'month': 30,
             'months': 30,
             'week': 7,
             'weeks': 7,
             'day': 1,
             'days': 1}

AnimalType = ['Cat', 'Dog']
random_state = 42
