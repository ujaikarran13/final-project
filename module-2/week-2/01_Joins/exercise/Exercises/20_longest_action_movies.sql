-- 20. The titles, lengths, and release dates of the 5 longest movies in the "Action" genre.
-- Order the movies by length (highest first), then by release date (latest first).
-- (5 rows, expected lengths around 180 - 200)

SELECT mo.title, mo.length_minutes, mo.release_date
FROM movie AS mo
JOIN movie_genre AS mg ON mo.movie_id = mg.movie_id
JOIN genre AS gr ON mg.genre_id = gr.genre_id
WHERE gr.genre_name = 'Action'
ORDER BY mo.length_minutes DESC, mo.release_date DESC
LIMIT 5;
