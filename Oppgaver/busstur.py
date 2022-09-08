stop1 = int(input("Stop1, hvor mange går på bussen?\n"))

if stop1 > 30:
    print(f"Bussen er full! {stop1-30} må gå til fots.\n")
elif stop1 <= 30 and stop1 > 0:
    stop2 = int(input("Stop2, hvor mange går på bussen?\n"))
    if stop2+stop1 >= 30:
        print(f"Bussen er full! {stop1+stop2-30} må gå til fots.\n")
    elif stop1+stop2 <= 30:
        stop3 = int(input("Stop3, hvor mange går på bussen?\n"))
        if stop1+stop2+stop3 >= 30:
            print(f"Bussen er full! {stop1+stop2+stop3-30} må gå til fots.\n")
        else:
            print(f"Siste stop! Det er {stop1+stop2+stop3-30} ledige plasser.")
