quiz = 0
while quiz == 0:
    ans = input("Hva er hovedstaten i Filippinene? ")
    if ans.lower() == "manila":
        print("Helt rikgit!\n")
        quiz += 1
    else:
        print("Feil, prøv igjen.\n")

while quiz == 1:
    ans = input("Hva er (2 + 8) / 5 * (1 + 2)? ")
    if int(ans) == 6:
        print("Helt rikgit!\n")
        quiz += 1
    else:
        print("Feil, prøv igjen.\n")

while quiz == 2:
    ans = input("Hva er hovedstaten i Tuvalu? ")
    if ans.lower() == "funafuti":
        print("Helt riktig!\n")
        quiz += 1
    else:
        print("Feil, prøv igjen.\n")
