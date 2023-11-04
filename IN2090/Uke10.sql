
-- Oppgave 1

SELECT p.personid, p.firstname || ' ' || p.lastname AS fullname, count(filmid)
FROM filmparticipation AS fp
RIGHT JOIN person AS p USING (personid)
WHERE p.lastname = 'Abbott'
GROUP BY p.personid, p.firstname || ' ' || p.lastname
ORDER BY count(filmid) DESC;
-- (243)



-- Oppgave 2
-- a
SELECT f.title
FROM film AS f
INNER JOIN filmgenre AS fg USING (filmid)
WHERE prodyear > 2007 
AND genre = 'Western' 
AND filmid NOT IN (SELECT filmid FROM filmrating);

-- b
SELECT f.title
FROM film AS f
INNER JOIN filmgenre AS fg USING (filmid)
LEFT JOIN filmrating USING (filmid)
WHERE prodyear > 2007 
AND genre = 'Western';

-- c ???
SELECT filmid, title
FROM film AS f
INNER JOIN filmgenre AS fg USING (filmid)
WHERE genre = 'Western' AND prodyear > 2007
EXCEPT
SELECT filmid, title FROM film AS f
INNER JOIN filmgenre AS fg USING (filmid)
INNER JOIN filmrating AS fr USING (filmid);

-- d ???
SELECT title
FROM film AS f 
INNER JOIN filmgenre AS fg USING (filmid)
WHERE genre = 'Western' 
AND prodyear > 2007 
AND NOT EXISTS (SELECT * FROM filmrating AS fr WHERE fr.filmid = f.filmid);
-- (14)


-- Oppgave 3

SELECT count(DISTINCT filmid)
FROM filmgenre AS fg
INNER JOIN filmparticipation AS fp USING (filmid)
INNER JOIN person AS p USING (personid)
WHERE p.firstname || ' ' || p.lastname = 'Jim Carrey'
OR fg.genre = 'Comedy' AND filmid IN (SELECT filmid FROM film);
-- n(79587)



-- Oppgave 4
SELECT title FROM film 
WHERE filmid IN (SELECT DISTINCT filmid
FROM filmparticipation AS fr 
INNER JOIN person AS p USING (personid)
WHERE p.firstname = 'Jim' AND p.lastname = 'Carrey'
EXCEPT
SELECT filmid FROM filmgenre WHERE genre = 'Comedy');
-- (62)



-- Oppgave 5

SELECT company_name FROM suppliers WHERE country = 'Norway' OR country = 'Sweden'
UNION 
SELECT company_name FROM customers WHERE country = 'Norway' OR country = 'Sweden';
-- (6)



-- Oppgave 6

