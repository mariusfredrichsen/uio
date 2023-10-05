import numpy as np
import wave
import matplotlib.pyplot as plt

def lag_tone(antall_sekunder, antall_svingninger_i_sekundet):
    lyd = []
    for i in range(int(44100 * antall_sekunder)):
        lyd.append(16000 * (1 + np.sin(antall_svingninger_i_sekundet * i/44100 * 2 * np.pi)))

    return lyd

def skriv_lyd_til_fil(data, sample_rate, filnavn):
    # tatt fra https://stackoverflow.com/a/64376061
    audio = np.array([data, data]).T
    audio = audio.astype("<h")

    with wave.open(filnavn, "w") as f:
        f.setnchannels(2)
        f.setsampwidth(2)
        f.setframerate(sample_rate)
        f.writeframes(audio.tobytes())

#oppg 1
noter = {
    "A": 440,
    "G": 392,
    "F": 349,
    "E": 330,
    "D": 294,
    "B": 247,
    "C": 261,
    "-": 0
}

def les_sang_fra_fil(filnavn):
    liste =[]
    fil = open(filnavn)
    for linje in fil:
        kolonner = linje.split()
        if kolonner[0] in noter:
            liste1 =[]
            liste1.append(noter[kolonner[0]])
            liste1.append(float(kolonner[1]))
            liste.append(liste1)

    liste.append(liste1)
    return liste
frekvenser = []
liste = les_sang_fra_fil("sang.txt")
for i in liste:
    frekvenser += lag_tone(i[1], i[0])
x = []
y = []
for i in range(len(frekvenser)):
    x.append(i)
    y.append(frekvenser[i])
    
plt.plot(x,y)
plt.show()

