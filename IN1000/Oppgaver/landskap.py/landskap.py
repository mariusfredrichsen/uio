import numpy as np
from pygame import Rect
import matplotlib.pyplot as plt
import math

class Landskap:
    def __init__(self, bredde, hoyde, skjermstorrelse=None, antall_peaks = 12, random_seed=1, maks_score_sjekk=1000):
        np.random.seed(random_seed)
        self._skjermstorrelse = skjermstorrelse  #  brukes hvis man skal tegne med pygame zero
        self._bredde = bredde
        self._hoyde = hoyde
        self._landskap = np.zeros((self._hoyde, self._bredde))
        self._individer = []
        self._max_hoyde = None
        self._antall_peaks = antall_peaks
        self._antall_score_sjekk = 0
        self._maks_score_sjekk = maks_score_sjekk

    def hent_score(self, x, y):
        if self._antall_score_sjekk >= self._maks_score_sjekk:
            print("Ikke lov å sjekke scoren mer")
            return 0
        else:
            self._antall_score_sjekk += 1
            return self._landskap[x, y]

    def _lag_peak(self, x, y, hoyde):
        # sett peaken til å være høyden, fyll inn "skråning" rundt
        self._landskap[y, x] = hoyde
        peak_radius = self._bredde // 5
        # fyll inn ruter med avstand radius fra peaken
        for i in range(x-peak_radius, x+peak_radius):
            for j in range(y-peak_radius, y+peak_radius):
                avstand_fra_peak = math.sqrt((x-i)**2 + (y-j)**2)
                if avstand_fra_peak <= peak_radius:
                    if i >= 0 and i < self._bredde and j >= 0 and j < self._hoyde:
                        self._landskap[j, i] = hoyde - (avstand_fra_peak * hoyde/peak_radius)

    def _legg_til_stoy(self):
        self._landskap += np.random.randint(0, 10, size=(self._hoyde, self._bredde)) / 10

    def plott_3d(self):
        fig = plt.figure()
        ax = plt.axes(projection='3d')
        x = np.arange(0, self._bredde)
        y = np.arange(0, self._hoyde)
        x, y = np.meshgrid(x, y)
        ax.plot_surface(x, y, self._landskap, rstride=1, cstride=1,
                        cmap='viridis', edgecolor='none')
        plt.show()

    def lag_landskap(self):
        for peak in range(self._antall_peaks):
            pos_x = np.random.randint(0, self._bredde)
            pos_y = np.random.randint(0, self._hoyde)
            peak_hoyde = np.random.normal(10, 10) + np.random.randint(0, 10)
            self._lag_peak(pos_x, pos_y, peak_hoyde)

        self._legg_til_stoy()
        self._max_hoyde = np.max(self._landskap)

    # fungerer med pygame zero
    def tegn(self, skjerm):
        rutestorrelse_x = self._skjermstorrelse / self._bredde
        rutestorrelse_y = self._skjermstorrelse / self._hoyde
        for x in range(self._bredde):
            for y in range(self._hoyde):
                farge = 128 * self._landskap[y, x] / self._max_hoyde
                skjerm.draw.filled_rect(Rect((x*rutestorrelse_x, y*rutestorrelse_y), ((x+1)*rutestorrelse_x, (y+1)*rutestorrelse_y)),
                                        (farge, farge, farge))

    def oppdater(self):
        pass

if __name__ == "__main__":
    landskap = Landskap(70, 70, maks_score_sjekk=200)
    landskap.lag_landskap()
    landskap.plott_3d()

    landskap.sjekk_score(5, 20)