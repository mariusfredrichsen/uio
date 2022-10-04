from math import pi, cos
from tkinter import N, Y
import numpy as np
import matplotlib.pyplot as plt

def abs_approx(x, N):
    y = 0
    for k in range(1,N+1):
        y += (cos((2*k-1)*x))/((2*k-1)**2)
    return pi/2-4/pi*y

N = 4
y = []
x = list(np.linspace(-pi,pi))
for x1 in x:
    y.append(abs_approx(x1, N))

absverdi = 0
for i in y:
    absverdi += i
print(absverdi)

plt.plot(x,y)
plt.show()