-- 2. The names and birthdays of actors in "The Fifth Element"
-- Order the results alphabetically (A-Z) by name.
-- (15 rows)

select person_name, birthday
from person AS pe
join movie_actor AS ma ON pe.person_id = ma.actor_id
join movie AS mo ON ma.movie_id = mo.movie_id
where mo.title = 'The Fifth Element'
order by pe.person_name ASC;

