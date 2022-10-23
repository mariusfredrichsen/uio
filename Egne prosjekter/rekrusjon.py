global sum = 0
x = 2
def en_metode(n, x):
    if n-x >= 0:
        return sum
    else:
        x *= 2
        sum += n
        return n + en_metode(n, x)

print(en_metode(10, x))