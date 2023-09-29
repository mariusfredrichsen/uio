import numpy as np
import wave

def skriv_lyd_til_fil(data, sample_rate, filnavn):
    # tatt fra https://stackoverflow.com/a/64376061
    audio = np.array([data, data]).T
    audio = audio.astype("<h")

    with wave.open(filnavn, "w") as f:
        f.setnchannels(2)
        f.setsampwidth(2)
        f.setframerate(sample_rate)
        f.writeframes(audio.tobytes())

def lag_tone(antall_sekunder, antall_svingninger_i_sekundet):
    lyd = []
    for i in range(int(44100 * antall_sekunder)):
        lyd.append(16000 * (1 + np.sin(antall_svingninger_i_sekundet * i/44100 * 2 * np.pi)))
    return lyd

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
    out_list = []
    with open(filnavn) as f:
        for linje in f:
            linje = linje.strip().split(" ")
            out_list.append([noter[linje[0]], float(linje[1])])
    return out_list

def lag_sang_fra_noter(noested_liste):
    out_data = []
    for liste in noested_liste:
        for x in lag_tone(liste[1], liste[0]):
            out_data.append(x)
    return out_data

skriv_lyd_til_fil(lag_sang_fra_noter(les_sang_fra_fil("test.txt")), 44100, "test.wav")