def bestem_laan():
    id = int(input("Hva er din kunde-ID? "))
    idlist = {23894, 29741, 10961, 22768, 22803, 11993, 24409, 9312, 29405, 6638, 738, 29964, 11967, 13443, 11534, 26228, 6867, 23027, 29137, 14084, 452, 15594, 22765, 25487}
    if id in idlist: #Sjekker hvis kunde-ID'en er i listen med ider som er svartelista
        print("Kan ikke få lån.")
    else:
        print("Kan få lån.")

bestem_laan()

#Det spiller ingen rolle om det er flere av samme ID'en der fordi alle av samme id vil bli sperret ut. 
#Dermed vil det være poengtløs å ha flere av samme id, men kan funke helt fint med en liste. 