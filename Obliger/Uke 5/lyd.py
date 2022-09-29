import wave
import numpy as np
import matplotlib.pyplot as plt

noter = {
    "A": 440,
    "G": 392,
    "F": 349,
    "E": 330,
    "D": 294,
    "B": 247,
    "C": 261,
    "-": 0
} #ordbok med noter og frekvensene til hver note

def lag_tone(antall_sekunder, antall_svingninger_i_sekundet):
    lyd = []
    for i in range(int(44100 * antall_sekunder)):
        lyd.append(16000 * (1 + np.sin(antall_svingninger_i_sekundet * i/44100 * 2 * np.pi)))
    return lyd
#prosedyre som produserer sinusbølger ut ifra notene/tidene


def skriv_lyd_til_fil(data, sample_rate, filnavn):
    # tatt fra https://stackoverflow.com/a/64376061
    audio = np.array([data, data]).T
    audio = audio.astype("<h")

    with wave.open(filnavn, "w") as f:
        f.setnchannels(2)
        f.setsampwidth(2)
        f.setframerate(sample_rate)
        f.writeframes(audio.tobytes())
#prosedyre som skal lage lydfilen ut ifra en liste med bølgeverdier

def les_sang_fra_fil(filnavn):
    fil = open(filnavn)
    note = []
    for linje in fil:
        note.append(linje.strip().split())
    for i in note:
        i[0] = noter[i[0]]
        i[1] = float(i[1])

    return note
#prosedyre som leser av en .txt fil og lager en nøsted liste med hvor lenge en frekvens skal bli spilt 


def lag_sang_fra_noter(note_liste):
    data = []
    for i in note_liste:
        for l in lag_tone(i[1],i[0]):
            data.append(l)

    return data
#en prosedyre som går igjennom den nøstede listen og lager en liste med bølgeverdier


def fade_out(data):
    fstart = 0.75
    startpoint = fstart*len(data)
    for i in range(int(startpoint), len(data)):
        data[i] = data[i]*(i-len(data))/(startpoint-len(data))
    
    return data
#bestemmer et punkt i sangen der hvor volumet skal gå fra 100% til 0%

def forenkle_lyd(data):
    for i in range(len(data)):
        if data[i] < 16000:
            data[i] = 0
        elif data[i] > 16000:
            data[i] = 32000
        
    return data
#går igjennom listen med bølgeverdiene og gjør dem lik 0 hvis de er under 16000 og 32000 hvis de er over 16000


skriv_lyd_til_fil(fade_out(lag_sang_fra_noter(les_sang_fra_fil("sang.txt"))),44100,"sang.wav")