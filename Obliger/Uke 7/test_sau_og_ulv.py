from sau import Sau
from ulv import Ulv

sau1 = Sau("Knut", "Norge")
ulv1 = Ulv("Ulf", "Norge")

assert sau1.lever() == True

assert ulv1.hent_vekt() == 20

ulv1.spis_sau(sau1)

assert sau1.lever() == False

assert ulv1.hent_vekt() == 25