from sau import Sau

sau1 = Sau("Knut", 2)
assert sau1.lever() == True

sau1.blir_spist()
assert sau1.lever() == False