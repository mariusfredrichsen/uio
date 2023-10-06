--Oppgave 1
--a)
--Student(studentNr, land, populasjon)
--FD: studentNr -> ... , land --> populasjon

--b)
--Student(navn, personnr, døfseslsdato, kurskode, fagkode, fagnummer, kursnavn)
--personnr+ -> {navn, fødseslsdato}
--{personnr, fagkode, fagnummer}+ -> {navn, fødselsdato, kurskode, kursnavn, fagkode, fagnummer}

--Oppgave 2
--a) 
--Bok(isbn, tittel, forfatter, forlagsnr, forlagsnavn, utgitt, sjanger, aldersgrense)
--forlagsnr+ -> {forlagsnavn}

--b)
--{forlagsnr, sjanger, forfatter} -> {forlagsnavn, aldersgrense}

--c)
--KN: {isbn, utgitt}
