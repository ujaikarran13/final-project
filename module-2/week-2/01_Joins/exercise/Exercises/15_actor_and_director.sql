-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie.
-- Order the results by movie title (A-Z)
-- (73 rows)
SELECT DISTINCT mo.title, per.person_name AS director_name
FROM movie AS mo
JOIN person AS per ON mo.director_id = per.person_id
JOIN movie_actor AS ma ON mo.movie_id = ma.movie_id
JOIN person AS pe ON ma.actor_id = pe.person_id
WHERE per.person_id = pe.person_id
ORDER BY mo.title ASC;

