-- 4. The titles and taglines of all the movies that are in the Fantasy genre.
-- Order the results by title (A-Z).
-- (81 rows)


SELECT mo.title, mo.tagline
FROM movie AS mo
JOIN movie_genre AS mg ON mo.movie_id = mg.movie_id
JOIN genre AS gr ON mg.genre_id = gr.genre_id
WHERE gr.genre_name = 'Fantasy'
ORDER BY mo.title ASC;
