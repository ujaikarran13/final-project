-- 5. The titles and release dates of all the movies that are in the Comedy genre.
-- Order the results by release date, earliest to latest.
-- (220 rows)

SELECT mo.title, mo.release_date
FROM movie AS mo
JOIN movie_genre AS mg ON mo.movie_id = mg.movie_id
JOIN genre AS gr ON mg.genre_id = gr.genre_id
WHERE gr.genre_name = 'Comedy'
ORDER BY mo.release_date ASC;
