import wave
import numpy as np

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

def les_sang_fra_fil(filnavn):
    fil = open(filnavn)
    note = []
    for linje in fil:
        note.append(linje.strip().split())
    for i in note:
        i[0] = noter[i[0]]
        i[1] = float(i[1])

    return note

def lag_sang_fra_noter(note_liste):
    data = []
    for i in note_liste:
        for l in lag_tone(i[1],i[0]):
            data.append(l)

    return data


skriv_lyd_til_fil(lag_sang_fra_noter(les_sang_fra_fil("sang.txt")),44100,"sang.wav")