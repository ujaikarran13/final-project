-- 6. The genres of "The Wizard of Oz" sorted in alphabetical order (A-Z).
-- (3 rows)

SELECT mo.title, gr.genre_name
FROM movie AS mo
JOIN movie_genre AS mg ON mo.movie_id = mg.movie_id
JOIN genre AS gr ON mg.genre_id = gr.genre_id
WHERE mo.title = 'The Wizard of Oz'
ORDER BY gr.genre_name;
