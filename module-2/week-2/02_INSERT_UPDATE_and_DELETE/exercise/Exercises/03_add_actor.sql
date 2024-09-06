-- 3. Did you know Eric Stoltz was originally cast as Marty McFly 
--in "Back to the Future"? Add Eric Stoltz to the list of actors for "Back to the Future" (1 row)

insert into person (person_name)
values ('Eric Stoltz');
insert into movie (title)
values ('Back to the Future');

SELECT person_id
FROM person 
WHERE person_name = 'Eric Stoltz';

INSERT INTO movie_actor (actor_id, movie_id)
VALUES (3984917, 105);

