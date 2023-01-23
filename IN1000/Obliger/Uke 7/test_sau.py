from sau import Sau

sau1 = Sau("Knut", 2) #Lager sauen Knut i posisjon 2
assert sau1.lever() == True #Ser om Knut lever

sau1.blir_spist() #Blir spist og det at sauen lever er nå usann
assert sau1.lever() == False #Tester om den ikke lever