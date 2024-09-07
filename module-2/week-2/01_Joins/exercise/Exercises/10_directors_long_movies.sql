-- 10. The names of directors who have directed a movie over 3 hours [180 minutes], sorted alphabetically.
-- (15 rows)


SELECT pe.person_name
FROM movie AS mo
JOIN person AS pe ON mo.director_id = pe.person_id
WHERE mo.length_minutes > 180
Order BY pe.person_name ASC;