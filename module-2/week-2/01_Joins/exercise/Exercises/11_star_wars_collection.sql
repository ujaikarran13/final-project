-- 11. The titles of the movies in the "Star Wars Collection" ordered by release date, most recent first.
-- (9 rows)

SELECT mo.title, mo.release_date
FROM movie AS mo
JOIN collection AS co ON mo.collection_id = co.collection_id
WHERE co.collection_name = 'Star Wars Collection'
ORDER BY mo.release_date DESC;
