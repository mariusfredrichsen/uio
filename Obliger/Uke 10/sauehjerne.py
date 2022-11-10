
# Bruker en noested for loop for å finne ut hvilke av elementene i listen som er vanligst
def finn_vanligste_element_i_liste(liste):
    vanligst_index = 0
    vanligst_teller = 0

    for i in liste:
        teller = 0
        for l in liste:
            if i == l:
                teller += 1
        if teller > vanligst_teller:
            vanligst_teller = teller
            vanligst_index = liste.index(i)
    return liste[vanligst_index]

class Sauehjerne:
    def __init__(self, sau, spillbrett):
        self._sau = sau
        self._spillbrett = spillbrett

    def velg_retning(self):
        retninger = []

        # Finner det naermeste_gresset og gir sauen en ide om aa gaa i retningen
        naermeste_gress = self.naermeste_gress()
        if naermeste_gress != None:
            retninger.append(self.retninger_mot_objekt(naermeste_gress)[0])
        
        # Gjoer det samme med ulven som med gress, men gaar i motsatt retning hvis den er innenfor 6 ruters avstand
        if self.avstand_til_objekt(self._spillbrett.ulv()) <=6:
            retninger_vekk_fra_ulv = self.retninger_fra_objekt(self._spillbrett.ulv())
            retninger_vekk_fra_ulv *= 2
            for retning in retninger_vekk_fra_ulv:
                retninger.append(retning)
        
        # Finner den retningen sauen vil gaa mest
        bestemt_retning = finn_vanligste_element_i_liste(retninger)

        # Hvis sauen er paa kanten vil den bytte retningen helt til en annen retning
        if self._sau.rute_venstre() == 17 and bestemt_retning == "hoeyre":
            bestemt_retning = "ned"
        elif self._sau.rute_topp() == 0 and bestemt_retning == "opp":
            bestemt_retning = "hoeyre"
        elif self._sau.rute_venstre() == 0 and bestemt_retning == "venstre":
            bestemt_retning = "opp"
        elif self._sau.rute_topp() == 13 and bestemt_retning == "ned":
            bestemt_retning = "venstre"
        
        # Lager en liste med alle retningene og fjerner retningene som har et hinder i seg
        mulige_retninger = ["ned", "hoeyre", "opp", "venstre"]
        if self.stein_finnes_i_retning(bestemt_retning):
            for retning in mulige_retninger:
                if not self.hinder_finnes_i_retning(retning):
                    mulige_retninger[mulige_retninger.index(retning)] = 0
            print(mulige_retninger)
            for retning in mulige_retninger:
                if retning != 0:
                    bestemt_retning = retning

        return bestemt_retning

    # Avstand mellom 2 objekter i et rutenett
    def avstand_til_objekt(self, objekt):
        return abs(self._sau.rute_venstre() - objekt.rute_venstre()) + abs(self._sau.rute_topp() - objekt.rute_topp())
    
    # Sjekker hvor det andre objektet er i forhold til sauen for baade vertikalt og horisontalt
    def retninger_mot_objekt(self, objekt):
        retning = []
        if objekt.rute_venstre() - self._sau.rute_venstre() < 0:
            retning.append("venstre")
        elif objekt.rute_venstre() - self._sau.rute_venstre() > 0:
            retning.append("hoeyre")
        if objekt.rute_topp() - self._sau.rute_topp() < 0:
            retning.append("opp")
        elif objekt.rute_topp() - self._sau.rute_topp() > 0:
            retning.append("ned")
        return retning
    
    # Gjoer det samme som metoden over, men gjoer det motsatt
    def retninger_fra_objekt(self, objekt):
        retning = []
        if objekt.rute_venstre() - self._sau.rute_venstre() > 0:
            retning.append("venstre")
        elif objekt.rute_venstre() - self._sau.rute_venstre() < 0:
            retning.append("hoeyre")
        if objekt.rute_topp() - self._sau.rute_topp() > 0:
            retning.append("opp")
        elif objekt.rute_topp() - self._sau.rute_topp() < 0:
            retning.append("ned")
        return retning
    
    # Finner det gresset som er naermest ved bruk av metoden avstand_til_objekt()
    def naermeste_gress(self):
        levende_gress = []
        for gress in self._spillbrett.hent_gress():
            if not gress.er_spist():
                levende_gress.append(gress)

        if levende_gress == []:
            return

        naermeste_gress = levende_gress[0]
        for gress in levende_gress:
            if self.avstand_til_objekt(gress) < self.avstand_til_objekt(naermeste_gress):
                naermeste_gress = gress
        return naermeste_gress

    def stein_finnes_i_retning(self, retning):
        # Lager en liste med posisjonene til absolutt alle steiner paa brettet
        stein_posisjon = []
        for stein in self._spillbrett.stein():
            stein_posisjon.append([stein.rute_venstre(), stein.rute_topp()])
        
        # Sjekker om det er en stein der hvor sauen tenker aa gaa neste gang
        if retning == "venstre":
            if [self._sau.rute_venstre() - 1, self._sau.rute_topp()] in stein_posisjon:
                return True
        if retning == "hoeyre":
            if [self._sau.rute_venstre() + 1, self._sau.rute_topp()] in stein_posisjon:
                return True
        if retning == "opp":
            if [self._sau.rute_venstre(), self._sau.rute_topp() - 1] in stein_posisjon:
                    return True
        if retning == "ned":
            if [self._sau.rute_venstre(), self._sau.rute_topp() + 1] in stein_posisjon:
                    return True
        return False

    # Sjekker om retningen sauen vil i er utenfor brettet
    def er_utenfor_banen(self, retning):
        if retning == "venstre" and self._sau.rute_venstre() == 0:
            return True
        if retning == "hoeyre" and self._sau.rute_venstre() == 17:
            return True
        if retning == "opp" and self._sau.rute_topp() == 0:
            return True
        if retning == "ned" and self._sau.rute_topp() == 13:
            return True
        return False

    def hinder_finnes_i_retning(self, retning):
        if self.er_utenfor_banen(retning) or self.stein_finnes_i_retning(retning): # or self.stein_rett_ved(retning)
            return False
        return True   
