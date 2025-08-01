-- 19. The name, population, area, and population density (name the column 'population_density') 
-- of all states, territories, and districts.
-- Population density is expressed as people per square kilometer. In other words, population divided by area.
-- Order the results by population density, lowest first.
-- (56 rows)
select state_name, population, area, population/area AS population_density
from state
order by population_density ASC;
