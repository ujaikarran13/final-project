-- 16. The names and birthdays of actors born in the 1950s who acted in movies that were released in 1985.
-- Order the results by actor from oldest to youngest.
-- (20 rows)

SELECT DISTINCT pe.person_name, pe.birthday
FROM person AS pe
JOIN movie_actor AS ma ON pe.person_id = ma.actor_id
JOIN movie AS mo ON ma.movie_id = mo.movie_id
WHERE EXTRACT(YEAR FROM pe.birthday) BETWEEN 1950 AND 1959
  AND EXTRACT(YEAR FROM mo.release_date) = 1985
ORDER BY pe.birthday ASC;



