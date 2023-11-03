
-- Oppgave 1

SELECT p.personid, p.firstname || ' ' || p.lastname, count(filmid)
FROM filmparticipation AS fp
RIGHT JOIN person AS p USING (personid)
WHERE p.lastname = 'Abbott'
GROUP BY p.personid, p.firstname || ' ' || p.lastname
ORDER BY count(filmid) DESC;
-- (243)



-- Oppgave 2

