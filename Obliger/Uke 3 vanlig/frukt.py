"""I denne oppgaven skal du spørre brukeren om hvor mange frukter de husker navnet på. 
Svarene skal du lagre i en liste og etterpå skal du finne ut hvor mange av fruktene som har bokstaven 'e' i seg og hvor mange av dem som starter på 'a'."""

svar = input("Hvor mange frukter husker du navnet på? (banan,eple,appelsin)\n")
svar = svar.split(',') #Lager en liste av stringen der hvor hvert element er bitene mellom komma

n = 0
n1 = 0
for i in svar:
    if "e" in i:
        n+=1
#Går igjennom listen og sjekker om det finnes en "e" i ordet, hvis det er så plusser den 1 på telleren
for i in svar:
    if i[0] == "a":
        n1+=1
#Gjør det samme som forrige for loop bare at den sjekker første indexen av stringen er lik "a"
print(f"{n} av fruktene du nevnte hadde boksteven 'e' i seg.")
print(f"{n1} av fruktene du nevnte hadde bokstaven 'a' som første bokstav.")