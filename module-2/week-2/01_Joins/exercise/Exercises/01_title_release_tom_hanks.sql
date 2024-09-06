-- 1. The titles and release dates of movies that Tom Hanks has appeared in.
-- Order the results by release date, newest to oldest.
-- (47 rows)

select title, release_date
from movie AS mo
join movie_actor AS ma ON mo.movie_id = ma.movie_id
where ma.actor_id = '31'
order by mo.release_date DESC;