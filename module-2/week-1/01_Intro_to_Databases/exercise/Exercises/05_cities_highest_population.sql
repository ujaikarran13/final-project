-- 5. The name, state abbreviation, and population of the 5 cities with the highest population.
-- (5 rows)

select city_name, state_abbreviation, population
from city
ORDER by population DESC
LIMIT 5;
