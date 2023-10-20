--Oppgave 1
SELECT filmtype, count(*) AS n_films
FROM filmitem
GROUP BY filmtype;

--Oppgave 2
SELECT s.maintitle, s.firstprodyear, count(*) AS episodes
FROM series AS s INNER JOIN episode AS e ON (s.seriesid = e.seriesid)
GROUP BY s.maintitle, s.firstprodyear
ORDER BY s.firstprodyear
LIMIT 15;

--Oppgave 3
SELECT DISTINCT f.title, t.count
FROM (SELECT title, count(*) FROM film GROUP BY title) AS t 
INNER JOIN film AS f ON (t.title = f.title)
WHERE t.count > 30
ORDER BY t.count DESC;

--Oppgave 4
SELECT f.title, t.count
FROM (SELECT f.title, count(*) 
    FROM film AS f 
    INNER JOIN filmgenre AS fg ON (f.filmid = fg.filmid) 
    WHERE f.title LIKE '%Pirates of the Caribbean%' 
    GROUP BY f.title) AS t 
INNER JOIN film AS f ON (f.title = t.title)
WHERE t.count > 3;

--Oppgave 5
