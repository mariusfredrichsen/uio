--Oppgave 1
--a)
--EksamensResultat(emnekode, studentId, semester, emnenavn, karakter)
--emnekode -> {emnenavn}
-- Følgende relasjonstabell bryter med 2NF ettersom emnenavn er avhengig av en attributt som er en del av en kandidatnøkkel

--b)
--KN: {emnekode, studentID, semester}

--FD: emnekode -> {emnenavn}
--Man får følgende relasjoner:
--Emne(emnekode,emnenavn)
--Karakter(emnekode, studentId, semester, karakter)

--Opgave 2
--a)
--R(A,B,C,D,E,F)
--FD: B,C -> D
--    E -> F

--KN: {A,B,C,E}
--Bryter med 2NF fordi D og F er avhengig av en del av kandidatnøkkelen

--b)
--KN: {A,B,C,E}
--FD: B,C -> D
--    E -> F

--Men får følgende dekomponering:
--R(A,B,C,D,E,F)
--S1(B,C,D)
--S2(A,B,C,E,F) ->
--S21(E,F)
--S22(A,B,C,E)

--R1(B,C,D), R2(E,F), R3(A,B,C,E)


--Oppgave 3
--a)
--timeliste(ansattnr, uke, år, navn, antTimer)
--FD: ansattnr -> navn
--KN: {ansattnr, uke, år}

--Den bryter med 2NF ettersom navn og antTimer er avhengig av en attributt som er kun en del av KN


--Oppgave 4
--a)
--ordre(ordre, kundenr, kundenavn, antall, sum, mva)
--FD: kundenr -> kundenavn
--    sum -> mva
--    ordre -> kundenr, antall, sum

--b)
--Relasjonen har normalform 3NF fordi alt er avhengig av ordre som er den eneste KN, men noen av attributtene er avhengig transitivt


--Oppgave 5
--filmgenre(filmid, title, prodyear, genre)
--FD: filmid -> title
--    filmid -> prodyear
--KN: {filmid, genre}

--a)
--SN: {filmid, genre}, {filmid, genre, title}, {filmid, genre, prodyear}, {filmid, genre, title, prodyear}

--b)
--Skrevet ovenfor

--c)
--1NF ettersom title og prodyear er avhengig av filmid som er kun en del av en kandidatnøkkel
