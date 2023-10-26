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
FROM (SELECT country, AVG(cast(time AS INTEGER)), count(*)
    FROM runningtime
    WHERE time ~ '^\d+$' 
    AND country IS NOT NULL 
    GROUP by country) AS t 
WHERE t.count > 200 
ORDER BY t.avg DESC;
-- (44)



-- Oppgave 4
SELECT f.filmid, f.title, t.count 
FROM (SELECT fi.filmid, count(*)
    FROM filmitem AS fi
    INNER JOIN filmgenre AS fg ON (fi.filmid = fg.filmid)
    WHERE fi.filmtype = 'C' 
    GROUP BY fi.filmid
    ORDER BY count(*) 
    DESC LIMIT 10) AS t 
INNER JOIN film AS f ON (t.filmid = f.filmid) 
ORDER BY t.count DESC, f.title;
-- (10)



-- Oppgave 5

SELECT *
FROM (SELECT country, count(*)
FROM filmcountry
GROUP BY country) AS c LEFT JOIN (SELECT fc.country, avg(fr.rank)
FROM filmrating AS fr INNER JOIN filmcountry AS fc ON (fr.filmid = fc.filmid)
GROUP BY fc.country) AS r ON (r.country = c.country);





SELECT * 
FROM filmgenre AS fg
INNER JOIN filmcountry AS fc ON (fc.filmid = fg.filmid) 
WHERE fg.genre = (SELECT t.genre, max(t.count) 
                FROM (SELECT fc.country, fg.genre, count(genre)
                    FROM filmcountry AS fc 
                    INNER JOIN filmgenre AS fg ON (fg.filmid = fc.filmid)
                    GROUP BY fc.country, fg.genre
                    ORDER BY fc.country, count(genre) DESC) AS t);


-- bruk with for den siste biten