X_1 = {8,6,7,5,3,10,9}
X_2 = {11,6,5,1,7,13,12}
T_1 = 15

def subset_sum(X, T):
    if T == 0:
        return 1
    elif T < 0 or len(X) == 0:
        return 0
    else:
        x = next(iter(X))
        X_r = X.copy()
        X_r.remove(x)

        w = subset_sum(X_r, T)
        wo = subset_sum(X_r, T-x)
        return w + wo

print(subset_sum(X_1, T_1))