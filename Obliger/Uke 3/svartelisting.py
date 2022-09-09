def bestem_laan():
    id = int(input("Hva er din kune-ID? "))
    idlist = {23894, 29741, 10961, 22768, 22803, 11993, 24409, 9312, 29405, 6638, 738, 29964, 11967, 13443, 11534, 26228, 6867, 23027, 29137, 14084, 452, 15594, 22765, 25487}
    if id in idlist:
        print("Kan ikke få lån.")
    else:
        print("Kan få lån.")

bestem_laan()