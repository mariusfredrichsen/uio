"""class Barnehage:
	def __init__(self, barn):
		self._barn = barn
	def bursdag(self):
		self._barn.bursdag()
	def bytte(self, nytt_barn):
		self._barn = nytt_barn
class Person:
	def __init__(self, alder):
		self._alder = alder
	def bursdag(self):
		self._alder += 1
	def hent_alder(self):
		return self._alder
per = Person(2)
palle = Person(5)
maurtua = Barnehage(per)
per.bursdag()
maurtua.bursdag()
print("A:", per.hent_alder())
maurtua.bytte(palle)
palle.bursdag()
print("B:", per.hent_alder())
print("C:", palle.hent_alder())
maurtua.bytte(Person(1))
print("D:", palle.hent_alder())"""

"""def bordsetting(introverte, ekstroverte):
    i = introverte
    e = ekstroverte
    ny_liste = []
    while e != []:
        ny_liste.append(i[0])
        ny_liste.append(e[0])
        
        i.pop(0)
        e.pop(0)
    
    return ny_liste

print(bordsetting([1,1,1,1,1],[0,0,0,0,0]))"""

"""# Oppgave 4g
# Grensesnittet til klassen Reservasjonssystem er metodene som ligger i selve klassen.

# Oppgave 4a
class Gruppe:
    def __init__(self, liste_med_krav):
        self._liste_med_personer = []
        self._liste_med_krav = liste_med_krav
        
    def legg_til_personer(self, liste_ned_nye):
        for person in liste_med_nye:
            self._liste_med_personer.append(person)
    
    def hent_personer(self):
        return self._liste_med_personer
    
    def hent_krav(self):
        return self._liste_med_krav
    

# Oppgave 4b
class Rom:
    def __init__(self, romnummer, antall_senger, fasiliteter):
        self._romnummer = romnummer
        self._antall_senger = antall_senger
        self._fasiliteter = fasiliteter
        
        self._opptatt = False
        self._navn = None
    
    def reserver(self, navn):
        self._opptatt = True
        self._navn = navn
    
    def hent_ant_senger(self):
        return self._antall_senger
    
    def passer(self, liste_med_krav):
        for krav in liste_med_krav:
            if krav not in self._fasiliteter:
                return False
        return True
    
    def __str__(self):
        ny_streng = ""
        for fasilitet in self._fasiliteter:
            ny_streng += fasilitet + "\n"
        return f"Romnummer: {self._romnummer}\nAntall senger: {self._antall_senger}\nFasiliteter:\n"


# Oppgave 4c
class Hotell:
    def __init__(self, navn):
        f = open(navn + ".txt")
        ordbok = {}
        
        for linje in f:
            linje.strip().split()
            if len(linje) == 2:
                ordbok[int(linje[0])] = Rom(int(linje[0]), int(linje[1]))
            else:
                fasiliteter = []
                for i in range(len(linje)):
                    if i != 0 and i != 1:
                        fasiliteter.append(linje[i])
                ordbok[int(linje[0])] = Rom(int(linje[0]), int(linje[1]), fasiliteter)
        
        self._rom_ordbok = ordbok
    
    def reserver_rom(self, romnummer, navn):
        self._rom_ordbok[int(romnummer)].reserver(navn)
    
    def finn_passende_rom(self, liste_med_krav):
        ny_liste = []
        for rom in self._rom_ordbok:
            if self._rom_ordbok[rom].passer(liste_med_krav):
                ny_liste.append(self._rom_ordbok[rom])
        
        return ny_liste


# Oppgave 4d
class Reservasjonssystem:
    def __init__(self, liste_med_navn):
        ordbok = {}
        for navn in liste_med_navn:
            ordbok[navn] = Hotell(navn)
        
        self._hotell_ordbok = ordbok

    def reserver_rom_i_hotell(self, hotellnavn, romnummer, navn):
        self._hotell_ordbok[hotellnavn].reserver_rom(romnummer, navn)

# Oppgave 4e    
    def _finn_alle_passende_rom(self, liste_med_krav):
        ordbok = {}
        for hotell in self._hotell_ordbok:
            if self._hotell_ordbok[hotell].finn_passende_rom(liste_med_krav) != []:
                ordbok[hotell] = self._hotell_ordbok[hotell].finn_passende_rom(liste_med_krav)
        
        return ordbok

# Oppgave 4f
    def gruppe_reservasjon(self, gruppe):
        liste_med_krav = gruppe.hent_krav()
        liste_med_personer = gruppe.hent_personer()
        ordbok = self._finn_alle_passende_rom(liste_med_krav)
        
        if ordbok == {}:
            return "Ingen passende rom"
        
        ferdig = False
        hotell_ordbok = {}
        for hotell in ordbok:
            if ferdig:
                break
            hotell_ordbok[hotell] = []
            for rom in ordbok:
                if rom.hent_ant_senger() < len(liste_med_personer):
                    ferdig = True
                    break
                navn_liste = []
                for i in range(rom.hent_ant_senger()):
                    if liste_med_personer != []:
                        navn_liste.append(liste_med_personer[0])
                        liste_med_personer.pop(0)
                    else:
                        ferdig = True
                        break
                rom.reserver(navn_liste)
                hotell_ordbok[hotell].append(rom)
        
        ny_streng = ""
        for hotell in hotell_ordbok:
            ny_streng += hotell + ":"
            for rom in hotell_ordbok[hotell]:
                ny_streng += f"\n{str(rom)}"
        if len(liste_med_personer) > 0:
            ny_streng += f"\nADVARSEL, {len(liste_med_personer)} FÅR IKKE PLASS"
        
        return ny_streng"""

"""def stigespill(terningkast, stiger):
    index = 0
    for kast in terningkast:
        index += kast
        if index in stiger:
            index = stiger[index]
    
    return index

def hvilke_tre_kast(slutt_rute, stiger):
    liste_med_kast = []
    for x in range(1,7):
        for y in range(1,7):
            for z in range(1,7):
                terningkast = [x,y,z]
                if stigespill(terningkast, stiger) == slutt_rute:
                    liste_med_kast.append(terningkast)
    
    return liste_med_kast

print(hvilke_tre_kast(5,{3:15, 17:4}))"""

"""def lag_interessegrupper(personers_interesse):
    ny_ordbok = {}
    
    for person in personers_interesse:
        if personers_interesse[person] not in ny_ordbok:
            ny_ordbok[personers_interesse[person]] = [person]
        elif personers_interesse[person] in ny_ordbok:
            ny_ordbok[personers_interesse[person]].append(person)
    
    return ny_ordbok

print(lag_interessegrupper({"Per":"Mat", "Palle":"Film", "Espen":"Mat"}))"""

"""def heie(tabellplass_ordbok):
    if tabellplass_ordbok["Brann"] > 4:
        return "Brann"
    
    for elem in tabellplass_ordbok:
        if int(tabellplass_ordbok[elem]) == 1:
            return elem

print(heie({"Rosenborg":2, "Odd":1, "Molde":3, "Brann":4}))"""


"""ny_ordbok = {}

bokstav_liste = ["a", "b", "c", "d", "e", "f", "g"]
tall_liste = [1,2,3,4,5,6,7]

index = 0
for bokstav in bokstav_liste:
	ny_ordbok[bokstav_liste] = index
	index += 1"""

asd = (0,0)
efg = (asd[0],asd[1]-1)
print(efg)