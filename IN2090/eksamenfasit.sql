WITH
    poeng AS (
        SELECT tid, sid, 1 AS poeng
        FROM resultat JOIN parti USING (pid)
        WHERE utfall = 'vant'
        UNION
        SELECT tid, sid, 0.5 AS poeng
        FROM resultat JOIN parti USING (pid)
        WHERE utfall = 'remi'
    -- Lager en tabell som ramser opp alle sid som vant og gir dem 1 poeng
    -- I tillegg legger den til sid som fikk remis og gir dem 0.5 poeng
    -- sid er koblet til tilsvarende tid'er
    ),
        sum_poeng AS (
        SELECT tid, sid, sum(poeng) AS total
        FROM poeng
        GROUP BY tid, sid
    -- grupperer opp alle sid og tid og velger ut summen for hver sid i hver tid
    )
SELECT t.navn, s.navn AS vinner
FROM turnering AS t,
    spiller AS s
    -- Kryssproduktet av alle spillere i enhver turnering
WHERE s.sid = (
        SELECT p.sid
        FROM sum_poeng AS p
        WHERE p.tid = t.tid
        ORDER BY total DESC
        LIMIT 1
    -- Velger ut alle tilfeller der hvor poeng.tid matcher med turnering.tid
    -- Sorterer og begrenser med 1 (enhver) og får kun top spilleren 
);





SELECT navn
FROM turnering
WHERE spilltype = 'lynsjakk'
AND startdato >= '2022-11-01' AND startdato <= '2022-11-30';


1. Berlin

3. Kristiansand

4. Paris

9. Kloster

10. Termer

SELECT b.navn, v.nedbør, v.vind
FROM by AS b 
INNER JOIN værmelding AS v USING (bid)
INNER JOIN land AS l USING (lid)
WHERE l.navn = 'Italia' AND v.dato = '2020-12-17'
ORDER BY b.navn;


SELECT b.navn, sum(v.nedbør) AS total_nedbør, avg(v.vind) AS gjennomsnitt_vindstyrke
FROM by AS b
INNER JOIN værmelding AS v USING (bid)
WHERE v.dato >= '2020-12-24' AND v.dato <= '2020-12-31';
GROUP BY b.navn;

SELECT b.navn
FROM værmelding AS v
INNER JOIN by AS b USING (bid)
WHERE v.dato >= '2020-12-24' AND v.dato < '2021-01-01'
GROUP BY b.navn
HAVING sum(v.nedbør) = 0 AND sum(vind) = 0;

CREATE VIEW steder (navn text, posisjon text, nedbør float, vind int)
AS
    SELECT b.navn, l.navn AS posisjon, v.nedbør, v.vind
    FROM by AS b
    INNER JOIN land AS l USING (lid)
    INNER JOIN værmelding AS v USING (bid)
    WHERE v.dato = current_date

    UNION

    SELECT p.navn, p.adresse AS posisjon, v.nedbør, v.vind
    FROM pos AS p
    INNER JOIN værmelding AS v USING (bid)
    WHERE v.dato = current_date AND p.adresse IS NOT NULL;

WITH null_vær AS (
    SELECT b.bid
    FROM værmelding AS v
    INNER JOIN by AS b USING (bid)
    WHERE v.dato = '2020-12-17'
    GROUP BY b.bid
    HAVING sum(v.nedbør) = 0 AND sum(vind) = 0
),  kafeer AS (
    SELECT bid
    FROM poi AS
    WHERE type = 'Kafé'
    GROUP BY bid
    HAVING count(*) >= 3
)


SELECT DISTINCT b.navn
FROM null_vær 
INTERSECT kafeer
INNER JOIN by AS b USING (bid);