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

def subset(seq, tar, check):
    if tar == 0:
        return check
    elif tar < 0 or len(seq) == 0:
        return None
    else:
        x = seq[-1]
        seq_mod = seq.copy()
        seq_mod.pop()
        
        w = subset(seq_mod, tar, check.copy())
        check = check.copy()
        check.append(x)
        wo = subset(seq_mod, tar-x, check)
        
        if w:
           return w
        if wo:
            return wo
        return None
asd = [8,6,7,5,3,10,9]
print(subset(asd, 15, []))