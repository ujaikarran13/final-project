-- 3. For all actors with the last name of "Jones", display the actor's name and movie titles they appeared in.
-- Order the results by the actor names (A-Z) and then by movie title (A-Z).
-- (48 rows)


SELECT pe.person_name, mo.title
FROM person AS pe
JOIN movie_actor AS ma ON pe.person_id = ma.actor_id
JOIN movie AS mo ON ma.movie_id = mo.movie_id
WHERE pe.person_name LIKE '% Jones'
ORDER BY pe.person_name ASC, mo.title ASC;
