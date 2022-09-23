import numpy as np
import wave
from random import randint

def skriv_lyd_til_fil(data, sample_rate, filnavn):
    # tatt fra https://stackoverflow.com/a/64376061
    audio = np.array([data, data]).T
    audio = audio.astype("<h")

    with wave.open(filnavn, "w") as f:
        f.setnchannels(2)
        f.setsampwidth(2)
        f.setframerate(sample_rate)
        f.writeframes(audio.tobytes())

def tilfeldig_lyd(antall_sekunder):
    lyd = []
    for i in range(44100 * antall_sekunder):
        lyd.append(randint(0, 32767))

    return lyd

def firkant():
    lyd = []
    for i in range(800):
        for i in range(50):
            lyd.append(0)
        for i in range(50):
            lyd.append(32000)

    return lyd

def trekant(antall_sekunder):
    lyd = []
    for l in range(antall_sekunder*1000):
        for i in range(0,32100,int(32100/50)):
            lyd.append(i)
        for i in range(32100,0,int(-32100/50)):
            lyd.append(i)
    print(lyd)
    return lyd

def lyd_sinus(antall_sekunder, frekvens):
    lyd = []
    for i in range(44100 * antall_sekunder):
        lyd.append(16000 * (1 + np.sin(frekvens * i/44100 * 2 * np.pi)))
    return lyd

skriv_lyd_til_fil(lyd_sinus(1, 310), 44100, "test.wav")