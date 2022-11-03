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

        naermeste_gress = self.naermeste_gress()
        if naermeste_gress != None:
            retninger.append(self.retninger_mot_objekt(naermeste_gress)[0])
        
        if self.avstand_til_objekt(self._spillbrett.ulv()) <=6:
            retninger_vekk_fra_stein = self.retninger_fra_objekt(self._spillbrett.ulv())
            retninger_vekk_fra_stein *= 2
            for retning in retninger_vekk_fra_stein:
                retninger.append(retning)
        
        if self._sau.rute_venstre() == 17 and finn_vanligste_element_i_liste(retninger) == "hoeyre":
            for i in range(3):
                retninger.append("ned")
        elif self._sau.rute_topp() == 0 and finn_vanligste_element_i_liste(retninger) == "opp":
            for i in range(3):
                retninger.append("hoeyre")
        elif self._sau.rute_venstre() == 0 and finn_vanligste_element_i_liste(retninger) == "venstre":
            for i in range(3):
                retninger.append("opp")
        elif self._sau.rute_topp() == 13 and finn_vanligste_element_i_liste(retninger) == "ned":
            for i in range(3):
                retninger.append("venstre")
        
        mulige_retninger = ["venstre", "opp", "hoeyre", "ned"]
        if self.stein_finnes_i_retning(finn_vanligste_element_i_liste(retninger)):
            for retning in mulige_retninger:
                if not self.hinder_finnes_i_retning(retning):
                    mulige_retninger[mulige_retninger.index(retning)] = 0
            for retning in mulige_retninger:
                if retning != 0:
                    for i in range(4):
                        retninger.append(retning)
            print(mulige_retninger)
            
        print(retninger)
        return finn_vanligste_element_i_liste(retninger)

        pass #Gjør denne på nytt, Oppgave: 2, 3, 5, 6, 7, 8

    def avstand_til_objekt(self, objekt):
        return abs(self._sau.rute_venstre() - objekt.rute_venstre()) + abs(self._sau.rute_topp() - objekt.rute_topp())
    
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
        stein_posisjon = []
        for stein in self._spillbrett.stein():
            stein_posisjon.append([stein.rute_venstre(), stein.rute_topp()])
        
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
    
#    def stein_rett_ved(self, retning):
#        stein_posisjon = [self._spillbrett.stein().rute_venstre(), self._spillbrett.stein()]
#        
#        if retning == "venstre":
#            if [self._sau.rute_venstre() - 1, self._sau.rute_topp()] in stein_posisjon:
#                return True
#        if retning == "hoeyre":
#            if [self._sau.rute_venstre() + 1, self._sau.rute_topp()] in stein_posisjon:
#                return True
#        if retning == "opp":
#            if [self._sau.rute_venstre(), self._sau.rute_topp() - 1] in stein_posisjon:
#                    return True
#        if retning == "ned":
#            if [self._sau.rute_venstre(), self._sau.rute_topp() + 1] in stein_posisjon:
#                    return True
#        return False

    def hinder_finnes_i_retning(self, retning):
        if self.er_utenfor_banen(retning) or self.stein_finnes_i_retning(retning): # or self.stein_rett_ved(retning)
            return False
        return True   
