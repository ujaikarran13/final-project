-- 9. The titles of movies directed by James Cameron, sorted alphabetically.
-- (6 rows)

SELECT mo.title
FROM movie AS mo
JOIN person AS pe ON mo.director_id = pe.person_id
WHERE pe.person_name = 'James Cameron'
ORDER BY mo.title ASC;
