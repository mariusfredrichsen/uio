n = 0
tall = []
while n < 5:
    tall.append(int(input("Skriv inn et helttall:\n")))
    n+=1

def summer(tall):
    sum = 0
    for i in tall:
        sum+=i
    print(sum)

def underti(tall):
    for i in tall:
        if i < 10:
            print(i)

def talletfem(tall):
    if 5 in tall:
        print("Tallet 5 er i listen.")
    else:
        print("Tallet 5 finnes ikke i listen.")

summer(tall)
underti(tall)
talletfem(tall)