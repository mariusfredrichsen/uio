#Det vil kjøre den første linjen, men kjøre det andre så lenge det blir skrevet
#inn et heltall. Den tredje linjen vil kjøre uansett (så lenge andre linjen
#kjører), men den fjerde vil ikke kunne kjøre fordi den sammenlikner et heltall
#med en string.

#Hvis brukeren svarer noe annet enn et heltall så vil den ikke kunne kjøre
#"int" funksjonen på den i b = int(a). Hvis vi kommer så langt som til "print
#(b + "Hei!")" så vil den stoppe fordi den sammenlikner to forskjellige
#"verdi-klasser".

a = input("Tast inn et heltall! ")
b = int(a)
if b < 10:
    print (b + "Hei!")
