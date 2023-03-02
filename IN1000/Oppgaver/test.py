liste = [4,1,6,7,4,8,9,2,1,4,6]

for i in range(0, len(liste)):
  for l in range(0, len(liste)-1):
    if liste[l] > liste[l+1]:
      liste[l+1], liste[l] = liste[l], liste[l+1]

print(liste)