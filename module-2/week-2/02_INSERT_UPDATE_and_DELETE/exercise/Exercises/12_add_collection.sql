-- 12. Create a "Bill Murray Collection" in the collection table. For the movies that have Bill Murray in them, set
--     their collection ID to the "Bill Murray Collection". (1 row, 6 rows)

INSERT INTO collection (collection_name)
VALUES ('Bill Murray Collection');

UPDATE movie
SET collection_id = 1795483
WHERE movie_id IN (
    SELECT movie_id
    FROM movie_actor
    JOIN person ON movie_actor.actor_id = person.person_id
    WHERE person.person_name = 'Bill Murray'
);
