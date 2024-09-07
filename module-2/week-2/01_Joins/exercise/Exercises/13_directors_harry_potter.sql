-- 13. The directors of the movies in the "Harry Potter Collection", sorted alphabetically.
-- (4 rows)

SELECT DISTINCT pe.person_name
FROM movie AS mo
JOIN collection AS co ON mo.collection_id = co.collection_id
JOIN person AS pe ON mo.director_id = pe.person_id
WHERE co.collection_name = 'Harry Potter Collection'
ORDER BY pe.person_name ASC;
