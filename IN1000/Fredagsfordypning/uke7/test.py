from lag import Lag
from kamp import Kamp

mål1 = 0
mål2 = 0
for i in range(1,10000001):
    lag1 = Lag("SIF", 1.5, 1)
    lag2 = Lag("IDK", 1, 1.5)

    kamp = Kamp(lag1, lag2)
    kamp.spill()
    mål1 += kamp.mål_hjemme()
    mål2 += kamp.mål_borte()
    print((mål1 + mål2)/i)
