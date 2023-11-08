--Oppgave 1

SELECT filmtype, count(*) AS n_films
FROM filmitem
GROUP BY filmtype;
-- (7)



--Oppgave 2

SELECT s.maintitle, s.firstprodyear, count(*) AS episodes
FROM series AS s 
INNER JOIN episode AS e ON (s.seriesid = e.seriesid)
GROUP BY s.maintitle, s.firstprodyear
ORDER BY s.firstprodyear
LIMIT 15;
-- (15)



--Oppgave 3

SELECT DISTINCT f.title, t.count
FROM (SELECT title, count(*) 
    FROM film 
    GROUP BY title) AS t 
INNER JOIN film AS f ON (t.title = f.title)
WHERE t.count > 30
ORDER BY t.count DESC;
-- (26)



--Oppgave 4

SELECT f.title, t.count
FROM (SELECT f.title, count(*) 
    FROM film AS f 
    INNER JOIN filmgenre AS fg ON (f.filmid = fg.filmid) 
    WHERE f.title LIKE '%Pirates of the Caribbean%' 
    GROUP BY f.title) AS t 
INNER JOIN film AS f ON (f.title = t.title)
WHERE t.count > 3;
-- (4)



--Oppgave 5

SELECT firstname, count(*)
FROM person
WHERE firstname != ''
GROUP BY firstname
ORDER BY count DESC;
-- ()
--Hvorfor funker ikke IS NOT NULL?



--Oppgave 6

SELECT f.filmid, f.title, t.count
FROM (SELECT filmid, count(*) 
    FROM filmgenre 
    GROUP BY filmid 
    ORDER BY count DESC) AS t 
INNER JOIN film AS f ON (t.filmid = f.filmid)
LIMIT 25;
-- (25)
--Finnes ikke alle filmidene i filmgenre i film tabellene?



--Oppgave 7 

SELECT p.firstname || ' ' || p.lastname AS fullname
FROM (SELECT fp.personid, count(DISTINCT filmid)
    FROM filmparticipation AS fp
    INNER JOIN filmcountry AS fc USING (filmid) 
    INNER JOIN film AS f USING (filmid)
    WHERE fp.parttype = 'director' 
    AND fc.country = 'Norway'
    GROUP BY fp.personid
    HAVING count(DISTINCT filmid) > 5) AS t1
INNER JOIN person AS p USING (personid);
-- (60) Tåpelig dritt som å inner joine med film selvom det ikke er relevant



--Oppgave 8

SELECT s.seriesid, s.maintitle, s.firstprodyear
FROM filmitem AS fi 
INNER JOIN series AS s ON (fi.filmid = s.seriesid) 
WHERE fi.filmtype = 'TVS' 
AND s.firstprodyear IS NOT NULL 
ORDER BY s.firstprodyear 
LIMIT 50;
-- (50)



--Oppgave 9

SELECT avg(rank) 
FROM filmrating 
WHERE votes > 100000;
-- a(8,43)



--Oppgave 10

SELECT f.title, fr.rank
FROM film AS f 
INNER JOIN filmrating AS fr ON (f.filmid = fr.filmid 
                                AND fr.votes > 100000)
WHERE fr.rank > (SELECT avg(rank) 
                FROM filmrating 
                WHERE votes > 100000);
-- (20)



--Oppgave 11

SELECT firstname, count(*)
FROM person
WHERE firstname != '' 
GROUP BY firstname
ORDER BY count DESC
LIMIT 100;
-- (100)



--Oppgave 12

SELECT p1.firstname, p1.count
FROM (SELECT firstname, count(*) 
    FROM person 
    WHERE firstname != '' 
    GROUP BY firstname) AS p1 
INNER JOIN (SELECT firstname, count(*) 
    FROM person 
    WHERE firstname != '' 
    GROUP BY firstname) AS p2 ON (p1.firstname != p2.firstname 
                                AND p1.count > 6000 AND p2.count > 6000) 
WHERE p1.count = p2.count;
-- r(Paul og Peter)



--Oppgave 13

SELECT count(*) 
FROM filmparticipation 
WHERE personid = (SELECT personid 
                FROM person 
                WHERE firstname || ' ' || lastname = 'Tancred Ibsen') 
AND parttype = 'director';
-- n(24)



--Oppgave 14

SELECT DISTINCT f.title, t.count 
FROM (SELECT fp.filmid, count(*) 
    FROM filmparticipation AS fp 
    INNER JOIN filmcountry AS fc ON (fp.filmid = fc.filmid 
                                    AND fc.country = 'Norway' 
                                    AND fp.parttype = 'director') 
    GROUP BY fp.filmid) AS t 
INNER JOIN film AS f ON (f.filmid = t.filmid) 
WHERE t.count > 1;
-- (135)



--Oppgave 15 !!!

SELECT p.firstname || ' ' || p.lastname AS fullname 
FROM (SELECT fp.personid 
    FROM (SELECT fp.filmid, count(*)
        FROM film AS f
        INNER JOIN filmparticipation AS fp USING (filmid)
        INNER JOIN person AS p USING (personid)
        INNER JOIN filmcountry AS fc USING (filmid)
        WHERE fc.country = 'Norway' 
        AND fp.parttype = 'director'
        GROUP BY fp.filmid
        HAVING count(*) = 1) AS t1 
    INNER JOIN filmparticipation AS fp USING (filmid)
    WHERE fp.parttype = 'director'
    GROUP BY fp.personid 
    HAVING count(*) > 5) AS t2
INNER JOIN person AS p USING (personid);
-- (49)



--Oppgave 16

SELECT f.title, f.prodyear, fi.filmtype
FROM filmitem AS fi 
INNER JOIN film AS f USING (filmid)
WHERE f.prodyear = 1893 
AND fi.filmtype = 'C';
-- (4)



--Oppgave 17

SELECT p.firstname || ' ' || p.lastname AS fullname
FROM person AS p 
INNER JOIN filmparticipation AS fp USING (personid)
INNER JOIN film AS f USING (filmid)
WHERE f.title = 'Baile Perfumado' 
AND fp.parttype = 'cast';
-- (14)



--Oppgave 18

SELECT f.title, f.prodyear
FROM filmparticipation AS fp 
INNER JOIN person AS p ON (fp.personid = p.personid) 
INNER JOIN film AS f ON (f.filmid = fp.filmid)
WHERE p.personid = (SELECT personid 
                    FROM person 
                    WHERE firstname || ' ' || lastname = 'Ingmar Bergman') 
AND fp.parttype = 'director';
-- (62)



--Oppgave 19

SELECT min(f.prodyear), max(f.prodyear)
FROM filmparticipation AS fp 
INNER JOIN person AS p ON (fp.personid = p.personid) 
INNER JOIN film AS f ON (f.filmid = fp.filmid)
WHERE p.personid = (SELECT personid 
                    FROM person 
                    WHERE firstname || ' ' || lastname = 'Ingmar Bergman') 
AND fp.parttype = 'director';
-- (1)



--Oppgave 20

SELECT f.title, f.prodyear
FROM (SELECT filmid, count(*) 
    FROM filmparticipation 
    GROUP BY filmid) AS t 
INNER JOIN film AS f ON (f.filmid = t.filmid) 
INNER JOIN filmitem AS fi ON (fi.filmid = f.filmid)
WHERE t.count > 300 
AND (fi.filmtype = 'C' OR fi.filmtype = 'TV');
-- (11)



--Oppgave 21 !!!

SELECT fp.personid, min(f.prodyear) AS first, max(f.prodyear) AS last, count(f.filmid)
FROM filmparticipation AS fp
INNER JOIN person AS p USING (personid)
INNER JOIN film AS f USING (filmid)
INNER JOIN filmitem AS fi USING (filmid)
WHERE fi.filmtype = 'C' 
AND fp.parttype = 'director'
GROUP BY fp.personid
HAVING (max(f.prodyear) - min(f.prodyear)) > 49
ORDER BY (max(f.prodyear) - min(f.prodyear)) DESC;
-- (188)



--Oppgave 22

SELECT fp.filmid, f.title, t.count-1 AS co_director 
FROM (SELECT filmid, count(*)
    FROM filmparticipation
    WHERE parttype = 'director' 
    GROUP BY filmid) AS t
INNER JOIN filmparticipation AS fp ON (fp.filmid = t.filmid)
INNER JOIN person AS p ON (fp.personid = p.personid) 
INNER JOIN film AS f ON (fp.filmid = f.filmid)
WHERE p.firstname || ' ' || p.lastname = 'Ingmar Bergman' 
AND fp.parttype = 'director';
-- (62)



--Oppgave 23

SELECT f.filmid, f.title, t.count AS involved, f.prodyear, fr.rank 
FROM (SELECT fp.filmid, count(*) 
    FROM (SELECT fp.filmid
        FROM filmparticipation AS fp 
        INNER JOIN person AS p ON (fp.personid = p.personid)
        WHERE p.firstname || ' ' || p.lastname = 'Ingmar Bergman' 
        AND fp.parttype = 'director') AS t
    INNER JOIN filmparticipation AS fp ON (t.filmid = fp.filmid)
    GROUP BY fp.filmid) AS t
INNER JOIN film AS f ON (t.filmid = f.filmid) 
INNER JOIN filmrating AS fr ON (fr.filmid = f.filmid)
ORDER BY f.prodyear;
-- (56)




--Oppgave 24 !!!







--Oppgave 25 ???
SELECT DISTINCT f.title, p.firstname || ' ' || p.lastname AS fullname, fp.parttype 
FROM (SELECT DISTINCT personid, filmid, count(*)
    FROM filmparticipation
    GROUP BY personid, filmid) AS t
INNER JOIN filmparticipation AS fp ON (fp.filmid = t.filmid)
INNER JOIN film AS f ON (f.filmid = t.filmid)
INNER JOIN person AS p ON (p.personid = t.personid) 
INNER JOIN filmitem AS fi ON (fi.filmid = t.filmid)
WHERE t.count > 1 
AND f.prodyear = 2003
AND fi.filmtype = 'C';



--Oppgave 26

--Spørring for filmer der hvor folk har deltatt i mer en 15filmer i årene 2008 - 2010 og filtrer ut personene som har spilt filmer i 2005, IS IN eller IN idk





--Oppgave 27

SELECT p.firstname || ' ' || p.lastname AS fullname, f.title 
FROM (SELECT filmid, count(*)
    FROM filmparticipation
    GROUP BY filmid
    HAVING count(*) > 200) AS t1 
INNER JOIN film AS f USING (filmid) 
INNER JOIN filmparticipation AS fp USING (filmid) 
INNER JOIN person AS p USING (personid)
WHERE filmid IN (SELECT filmid
                FROM filmparticipation
                WHERE parttype = 'director'
                GROUP BY filmid
                HAVING count(*) = 1) 
AND parttype = 'director';
-- (33)