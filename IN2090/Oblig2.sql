--Oppgave 2 
--a)
SELECT navn
FROM planet
WHERE stjerne = 'Proxima Centauri';

--b)
SELECT DISTINCT oppdaget
FROM planet
WHERE sjerne = 'TRAPPIST-1' OR stjerne = 'Kepler-154';

--c)
SELECT DISTINCT count(navn)
FROM planet
WHERE masse IS NULL;

--d)
SELECT navn, masse
FROM planet
WHERE oppdager = 2020 AND masse > (SELECT avg(masse)) FROM planet);

--e)
SELECT max(oppdaget) - min(oppdager) AS max_diff
FROM planet;

--Oppgave 3
--a)
SELECT p.navn
FROM planet AS p INNER JOIN materie AS m ON (p.navn = m.planet)
WHERE p.masse > 3 AND p.masse < 10 AND m.molekyl = 'H2O';

--b)
SELECT DISTINCT p.navn
FROM planet AS p 
INNER JOIN stjerne AS s ON (p.stjerne = s.navn) 
INNER JOIN materie AS m ON (m.planet = p.navn)
WHERE s.avstand < s.masse * 12 AND m.molekyl LIKE '%H%';

--c)
SELECT DISTINCT p.navn, p.stjerne, p.masse
FROM (SELECT s.navn, count(*) OVER (PARTITION BY s.navn) AS antall 
    FROM planet AS p 
    INNER JOIN stjerne AS s ON (s.navn = p.stjerne) 
    WHERE p.masse > 10 AND s.avstand < 50) AS t
INNER JOIN planet AS p ON (p.stjerne = t.navn) 
WHERE t.antall > 1 AND p.masse > 10;

SELECT p.navn
FROM planet AS p1 INNER JOIN planet AS p2 AS p1.navn != p2.navn
INNER JOIN stjerne AS s ON p1.stjerne = s.navn AND p2.stjerne = s.navn AND p2.stjerne = s.navn
WHERE s.avstand < 50 AND p1.masse > 10 AND p2.masse > 10

--Oppgave 4
-- Hvis man ser på de to forskjellige tabellene, stjerne og planet, kan man se at de har like navn på 2 
-- attributter, navn og masse. Dermed vil NATURAL JOIN ikke fungere i denne situasjonen siden da prøver 
-- man å kombinere de radene der hvor både masse og navn er like. Jeg antar at ingen planeter heter det 
-- samme som noen stjerner. 

--Oppgave 5
--a)
INSERT INTO stjerne VALUES('Sola', 0, 1);

--b)
INSERT INTO planet VALUES('Jorda', 0.003146, NULL, 'Sola');

--Oppgave 6
CREATE TABLE observasjon (
    observasjons_id SERIAL,
    oppdaget timestamp NOT NULL,
    planet text NOT NULL,
    kommentar text,
    PRIMARY KEY(observasjons_id),
    CONSTRAINT planet_fk FOREIGN KEY(planet) REFERENCES planet(navn)
);


