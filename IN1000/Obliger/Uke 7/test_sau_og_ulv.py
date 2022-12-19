from sau import Sau
from ulv import Ulv

sau1 = Sau("Knut", "Norge") #Lager sauen Knut
ulv1 = Ulv("Ulf", "Norge") #Lager ulven Ulf

assert sau1.lever() == True #Tester om den lever

assert ulv1.hent_vekt() == 20 #Tester om vekten til ulven er lik 20

ulv1.spis_sau(sau1) #Påkaller metoden at ulven spiser sauen

assert sau1.lever() == False

assert ulv1.hent_vekt() == 25