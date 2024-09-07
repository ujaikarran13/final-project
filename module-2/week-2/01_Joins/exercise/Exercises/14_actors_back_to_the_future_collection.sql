-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection", sorted alphabetically.
-- (28 rows)

SELECT DISTINCT pe.person_name
FROM movie AS mo
JOIN collection AS co ON mo.collection_id = co.collection_id
JOIN movie_actor AS ma ON mo.movie_id = ma.movie_id
JOIN person AS pe ON ma.actor_id = pe.person_id
WHERE co.collection_name = 'Back to the Future Collection'
ORDER BY pe.person_name ASC;
