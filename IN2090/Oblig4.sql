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
WHERE t.count > 200 
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

WITH top_genre AS (SELECT fc.country, fg.genre, count(*)
FROM filmgenre AS fg
INNER JOIN filmcountry AS fc USING (filmid)
GROUP BY fc.country, fg.genre
ORDER BY fc.country, count(*) DESC)

SELECT t1.country, t1.count AS movies_made, t1.avg AS avg_rating, t2.genre AS most_common_genre 
FROM (SELECT c.country, c.count, r.avg
    FROM (SELECT country, count(*)
        FROM filmcountry
        GROUP BY country) AS c 
    LEFT JOIN (SELECT fc.country, avg(fr.rank)
            FROM filmrating AS fr 
            INNER JOIN filmcountry AS fc ON (fr.filmid = fc.filmid)
            GROUP BY fc.country) AS r ON (r.country = c.country)) AS t1
INNER JOIN (SELECT tg.country, tg.genre
        FROM (SELECT tg.country, max(tg.count)
            FROM top_genre AS tg
            GROUP BY tg.country) AS t
        INNER JOIN top_genre AS tg ON (t.country = tg.country AND t.max = tg.count)) AS t2 ON (t1.country = t2.country)
ORDER BY t1.country;
-- (228) Tar med ALLE vanligste spørringene



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
AND fr.votes >= 1000
AND fi.filmtype = 'C')
SELECT DISTINCT * FROM (SELECT filmid FROM (SELECT filmid FROM mi ORDER BY rank DESC LIMIT 10) AS t1 -- Top 10
UNION
(SELECT DISTINCT filmid FROM mi
INNER JOIN filmparticipation AS fp USING (filmid)
INNER JOIN person AS p USING (personid)
WHERE p.firstname || ' ' || p.lastname = 'Harrison Ford')) AS t1 -- Harrison Ford
UNION
SELECT DISTINCT filmid FROM mi INNER JOIN filmgenre AS fg USING (filmid)
WHERE fg.genre = 'Romance' OR fg.genre = 'Comedy'; -- Comedy & Romance
-- (170-172)???



