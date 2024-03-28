import numpy as np
from netCDF4 import Dataset
import matplotlib.pyplot as plt

ncfile = Dataset('norway_oceandata.nc', 'r')

lats = ncfile.variables['lat'][:]
longs = ncfile.variables['lon'][:]

elevation = ncfile.variables['elevation'][:, :]


points = []
for i in range(0, len(lats), 3):
    for j in range(0, len(longs), 3):
        color = 'blue'
        if elevation[i][j] > 0:
            color = 'green'
        points.append((longs[j], lats[i], color))

x = [p[0] for p in points]
y = [p[1] for p in points]
colors = [p[2] for p in points]
plt.scatter(x, y, c=colors)

plt.show()