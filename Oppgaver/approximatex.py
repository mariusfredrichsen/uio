from math import pi, cos
import numpy as np
import matplotlib.pyplot as plt

def abs_approx(x, k):
    sum = 0
    for i in x:
        sum += (cos((2*N-1)*i))/((2*k-1)**2)
    return (pi/2) - (4/pi)*sum

sum = 0
N = 4
x = np.linspace(-pi,pi)

for n in range(1,N+1):
    sum += abs_approx(x, n)

print(sum)