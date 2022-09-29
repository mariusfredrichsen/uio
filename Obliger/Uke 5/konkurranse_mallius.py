import numpy as np
import matplotlib.pyplot as plt
import pickle
import wave

def skriv_lyd_til_fil(lydliste):
    # dette er funksjonen du har fra før ...
    pass
    
def les_lyd_fra_fil():
    lyd = pickle.load(open("kode.pickle", "rb"))
    return lyd


hemmelig_kode = les_lyd_fra_fil()
# hemmelig_kode er nå en liste som inneholder lyden du skal jobbe med

# Start gjerne med å skrive lyden til fil og spille den av å få en fealing av hva du har å jobbe med:
skriv_lyd_til_fil(hemmelig_kode)

# Det kan være lurt å plotte lyden for å se etter mulige problemer og ting du kan gjøre for å forbedre signalet
plt.plot(hemmelig_kode)
plt.show()

# tips: Det kan være lurt å zoome inn på deler av plottet for å studere det nærmere

def fiks_lyd(lyd):
    for i in range(len(lyd)):
        lyd[i]*=5
    for l in range(10):
        for i in range(len(lyd)):
            if i < len(lyd)-1:
                if lyd[i+1] > lyd[i]*1.5:
                    lyd[i+1] = (lyd[i+1]+lyd[i])/2
                if lyd[i+1] < lyd[i]/1.5:
                    lyd[i+1] = (lyd[i+1]+lyd[i])/2
        
    return lyd

plt.plot(fiks_lyd(hemmelig_kode))
plt.show()

def skriv_lyd_til_fil(data, sample_rate, filnavn):
    audio = np.array([data, data]).T
    audio = audio.astype("<h")

    with wave.open(filnavn, "w") as f:
        f.setnchannels(2)
        f.setsampwidth(2)
        f.setframerate(sample_rate)
        f.writeframes(audio.tobytes())

skriv_lyd_til_fil(fiks_lyd(hemmelig_kode),44100,"konk.wav")