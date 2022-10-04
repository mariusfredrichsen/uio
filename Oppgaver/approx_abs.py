from math import pi, cos
from tkinter import N
import numpy as np
import matplotlib.pyplot as plt

def abs_approx(x, N):
    sum = 0
    for k in range(1,N+1):
        sum += (cos((2*k-1)*x))/((2*k-1)**2)
    return (pi/2)-((4*sum)/pi)

x = np.linspace(-pi,pi)
y = 0
N = 4

for x1 in x:
    print(y)
    y += abs_approx(x1,N)

print(y)