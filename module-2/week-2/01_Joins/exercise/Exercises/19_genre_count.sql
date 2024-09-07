-- 19. The genre name and the number of movies in each genre. Name the count column 'num_of_movies'.
-- Order the results from the highest movie count to the lowest.
-- (19 rows, the highest expected count is around 400).

SELECT gr.genre_name, COUNT(mo.movie_id) AS num_of_movies
FROM movie AS mo
JOIN movie_genre AS mg ON mo.movie_id = mg.movie_id
JOIN genre AS gr ON mg.genre_id = gr.genre_id
GROUP BY gr.genre_name
ORDER BY num_of_movies DESC;
