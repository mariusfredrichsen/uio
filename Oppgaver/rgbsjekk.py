def rgbinput():
    rgb = input("Skriv inn en rgb verdi (000 000 000):\n")

    r = int(rgb.split(' ')[0])
    g = int(rgb.split(' ')[1])
    b = int(rgb.split(' ')[2])

    for i in rgb.split(' '):
        if len(i) != 3:
            print("Feil input")
            exit(0)

    rgblist = [r,g,b]
    for i in rgblist:
        if i >= 0 and i <= 256:
            pass
        else:
            print("Noe gikk galt, for stort tall")
            exit(0)
    print("Alt gikk bra!")


rgbinput()
