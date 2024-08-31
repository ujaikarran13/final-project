-- 20. The state abbreviation, and population of the city with the largest population (name column 'city_population')
-- for all states, territories, and districts.
-- Order the results from highest to lowest populations.
-- (56 rows)

select state_abbreviation, MAX(population) AS city_population
from city
GROUP BY state_abbreviation
order by city_population DESC;

