-- 17. The titles and taglines of movies that are in the "Family" genre that Samuel L. Jackson has acted in.
-- Order the results alphabetically by movie title.
-- (4 rows)

SELECT mo.title, mo.tagline
FROM movie AS mo
JOIN movie_actor AS ma ON mo.movie_id = ma.movie_id
JOIN person AS pe ON ma.actor_id = pe.person_id
JOIN movie_genre AS mg ON mo.movie_id = mg.movie_id
JOIN genre AS gr ON mg.genre_id = gr.genre_id
WHERE pe.person_name = 'Samuel L. Jackson' AND gr.genre_name = 'Family' 
ORDER BY mo.title ASC;