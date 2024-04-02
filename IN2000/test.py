import numpy as np
import matplotlib.pyplot as plt
from random import randint


x = [x for x in range(100)]
y = [y for y in range(100)]

rgb_values = [[0, 0, ()] for i in range(100)]



plt.scatter(x, y, c=rgb_values)

plt.show()