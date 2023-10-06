--Oppgave 1
CREATE TABLE kunde (
    kundenummer int PRIMARY KEY,
    kundenavn text NOT NULL,
    kundeadresse text,
    postnr int,
    poststed text
)

CREATE TABLE ansatt (
    ansattnr int PRIMARY KEY,
    navn text NOT NULL,
    fødselsdato date,
    ansattdato date
)

CREATE TABLE prosjekt (
    prosjektnummer int PRIMARY KEY,
    prosjektleder int REFERENCES ansatt(ansattnr),
    prosjektnavn text NOT NULL,
    kundenummer int REFERENCES kunde(kundenummer),
    status text CHECK (status = 'planlagt' OR status = 'aktiv' OR status = 'ferdig')
)

CREATE TABLE ansattdeltariprosjekt (
    ansattnr int REFERENCES prosjekt(prosjektnummer), 
    prosjektnr int REFERENCES ansatt(ansattnr),
    CONSTRAINT deltar_pk PRIMARY KEY (ansattnr, prosjektnr)
)

--Oppgave 2
--a)
--PN (ansatt): {ansattnr}
--PN (ansattdeltariprosjekt): {ansattnr, prosjektnr}

--b)
--NA (ansatt): {ansattnr}, {ansattnr, navn}
--NA (ansattdeltariprosjekt): {ansattnr, prosjektnr}

--c)
--Ja, KN (ansatt): {ansattnr}

--d)
--SN (ansatt): {ansattnr}, {ansattnr, navn}