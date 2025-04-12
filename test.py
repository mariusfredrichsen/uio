


def findMax(l):
    max_value = float('-inf')
    other_max = float('-inf')

    for e in l:
        if type(e) == list:
            other_max = max(findMax(e), other_max)
        else:
            max_value = max(max_value, e)
    return max(other_max, max_value)

a = findMax([[2,1,5], [4,21, [40]],[3,5,6,-1],[0], 100])
print(a)

def crack(code, key):
    out = ""
    char_map = {k: i for i, k in enumerate(key)}
    n = len(key)
    print(n)
    for c in code:
        out += key[(char_map[c]-1)%n]
    return out

a = crack("ROFFXPZAOZPXKFÆRZNÆPPÆFEZPZP", "OSYÅKQGEZHØBLVRPXUCJMAÆWDTNFI")
print(a)