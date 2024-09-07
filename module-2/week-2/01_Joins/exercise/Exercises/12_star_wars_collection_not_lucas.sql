-- 12. The titles of the movies in the "Star Wars Collection" that weren't directed by George Lucas, sorted alphabetically.
-- (5 rows)

SELECT mo.title
FROM movie AS mo
JOIN collection AS co ON mo.collection_id = co.collection_id
JOIN person AS pe ON mo.director_id = pe.person_id
WHERE co.collection_name = 'Star Wars Collection' AND pe.person_name <> 'George Lucas'
ORDER BY mo.title ASC;
