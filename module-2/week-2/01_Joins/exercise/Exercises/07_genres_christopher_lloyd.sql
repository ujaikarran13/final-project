-- 7. The genres of movies that Christopher Lloyd has appeared in, sorted alphabetically.
-- (8 rows) Hint: DISTINCT will prevent duplicate values in your query results.

SELECT mo.title, gr.genre_name
FROM movie AS mo
JOIN movie_actor AS ma ON mo.movie_id = ma.movie_id
JOIN person AS pe ON ma.actor_id = pe.person_id
JOIN movie_genre AS mg ON mo.movie_id = mg.movie_id
JOIN genre AS gr ON mg.genre_id = gr.genre_id
WHERE pe.person_name = 'Christopher Lloyd'
ORDER BY mo.title ASC;
