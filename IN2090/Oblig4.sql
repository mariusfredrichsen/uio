-- Oppgave 1

SELECT p.firstname || ' ' || p.lastname AS fullname, fp.parttype
FROM film AS f
INNER JOIN filmparticipation AS fp ON (f.filmid = fp.filmid)
INNER JOIN person AS p ON (p.personid = fp.personid)
WHERE f.title = 'Star Wars' 
AND fp.parttype = 'cast';
-- (108)



-- Oppgave 2

SELECT country, count(country)
FROM filmcountry
GROUP BY country
ORDER BY count(country) DESC;
-- (190)



-- Oppgave 3

SELECT t.country, t.avg AS average_playtime 
FROM (SELECT country, avg(cast(time AS INTEGER)), count(*)
    FROM runningtime
    WHERE time ~ '^\d+$' 
    AND country IS NOT NULL 
    GROUP by country) AS t 
WHERE t.count >= 200 
ORDER BY t.avg DESC;
-- (44)



-- Oppgave 4

SELECT f.filmid, f.title, t.count
FROM (SELECT fi.filmid, f.title, count(*)
    FROM filmitem AS fi
    INNER JOIN filmgenre AS fg ON (fi.filmid = fg.filmid)
    INNER JOIN film AS f ON (f.filmid = fi.filmid)
    WHERE fi.filmtype = 'C'
    GROUP BY fi.filmid, f.title
    ORDER BY count(*) DESC, f.title
    LIMIT 10) AS t
INNER JOIN film AS f ON (t.filmid = f.filmid)
ORDER BY t.count DESC, f.title;
-- (10)



-- Oppgave 5

SELECT * 
FROM (SELECT fc.country, avg(fr.rank) AS avg_rating
    FROM filmrating AS fr
    INNER JOIN filmcountry AS fc USING (filmid)
    GROUP BY fc.country) AS t1
FULL JOIN 
    (SELECT fc.country, count(*) AS n_movies
    FROM filmcountry AS fc
    GROUP BY fc.country) AS t2 
USING (country)
FULL JOIN 
    (SELECT DISTINCT ON(fc.country) fc.country, fg.genre
    FROM filmgenre AS fg
    INNER JOIN filmcountry AS fc USING (filmid)
    GROUP BY fc.country, fg.genre
    ORDER BY fc.country, count(*) DESC) AS t3
USING (country);
-- (190)



-- Oppgave 6

SELECT fc1.country, fc2.country, count(*)
FROM filmcountry AS fc1
INNER JOIN filmcountry AS fc2 ON (fc1.country < fc2.country)
WHERE fc1.filmid = fc2.filmid
GROUP BY fc1.country, fc2.country HAVING count(*) > 150
ORDER BY count(*) DESC;
-- (44)



-- Oppgave 7

SELECT DISTINCT f.title, f.prodyear
FROM film AS f FULL JOIN filmgenre AS fg USING (filmid) 
FULL JOIN filmcountry AS fc USING (filmid)
WHERE (f.title LIKE '%Dark%' OR f.title LIKE '%Night%')
AND (fg.genre = 'Horror' OR fc.country = 'Romania');
-- (457)



-- Oppgave 8

SELECT f.title, f.prodyear, t.count AS participants
FROM (SELECT f.filmid, count(parttype)
    FROM film AS f
    FULL JOIN filmparticipation USING (filmid) 
    WHERE f.prodyear > 2009
    GROUP BY f.filmid
    HAVING count(*) < 3) AS t 
INNER JOIN film AS f USING (filmid);
-- (28)




-- Oppgave 9

SELECT count(filmid) 
FROM (SELECT DISTINCT filmid 
    FROM filmgenre) AS t 
    EXCEPT 
    (SELECT filmid 
    FROM filmgenre 
    WHERE genre = 'Horror' 
    OR genre = 'Sci-Fi');
-- n(435091)



-- Oppgave 10

WITH mi AS (SELECT *
FROM filmrating AS fr
INNER JOIN filmitem AS fi USING (filmid)
WHERE fr.rank >= 8
AND fr.votes > 1000
AND fi.filmtype = 'C'
ORDER BY rank DESC, votes DESC)

SELECT DISTINCT f.title
FROM (SELECT * FROM
    (SELECT filmid 
    FROM mi 
    LIMIT 10) AS t1
-- (10)
UNION

SELECT DISTINCT filmid 
FROM mi
INNER JOIN filmparticipation AS fp USING (filmid)
INNER JOIN person AS p USING (personid)
WHERE p.firstname || ' ' || p.lastname = 'Harrison Ford'
-- (9)
UNION

SELECT DISTINCT filmid 
FROM mi 
INNER JOIN filmgenre AS fg USING (filmid)
WHERE fg.genre = 'Romance' 
OR fg.genre = 'Comedy') AS t1
-- (157)

INNER JOIN film AS f USING (filmid);
-- (171) ???

SELECT * FROM
(select fc.country, count(*) 
from filmcountry as fc
group by fc.country           
having count(distinct filmid) > 0 ) AS t1

FULL JOIN

(select country, avg(rank) 
from filmrating
join filmcountry using (filmid)
group by country) AS t2
USING (country)

FULL JOIN

(select distinct on (q.country)* 
from (select country, genre, count(*)
from filmgenre inner join filmcountry using (filmid)
group by country, genre) as q
group by q.country, q.genre, q.count
order by country, max(q.count) desc) AS t3
USING (country);



--Oppgave 10

-- Start med å først finne filmid til alle interessante filmer, og så skriv
-- delspørringer som gjør uttrekk fra disse i henhold til listen over (altså skriv en
-- delspørring som finner filmid til de 10 høyest rangerte interessante filmene


-- Start med å først finne filmid til alle interessante filmer,
WITH  interessantefilmer AS(
SELECT filmitem.filmid, filmrating.rank, filmrating.votes
FROM filmitem
JOIN filmrating USING (filmid)
WHERE filmrating.rank >= 8 AND filmrating.votes > 1000 AND filmtype = 'C'), 

tihoyest AS

-- delspørring som finner filmid til de 10 høyest rangerte interessante filmene
    (SELECT filmid
    FROM interessantefilmer
    ORDER BY rank DESC, votes DESC
    LIMIT 10), 

    HarrisonFord AS 

    (
        SELECT filmid
        FROM tihoyest
        JOIN filmparticipation USING (filmid)
        JOIN person ON person.personid = filmparticipation.personid
        WHERE person.firstname = 'Harrison' AND person.lastname ='Ford'
    ), 

    ROMCOM AS (
        SELECT DISTINCT filmid
        FROM interessantefilmer
        JOIN filmgenre USING (filmid)
        WHERE filmgenre.genre IN ('Comedy', 'Romance')
    ),
    Utvalgtefilmer AS (
        SELECT filmid FROM tihoyest
        UNION
        SELECT filmid FROM ROMCOM
        UNION
        SELECT filmid FROM HarrisonFord
    )
    SElECT film.title
    FROM Utvalgtefilmer
    JOIN film USING (filmid) 
    ;