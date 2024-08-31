-- 18. The count of the number of cities (name column 'num_cities') and the state abbreviation 
-- for each state and territory (exclude state abbreviation DC).
-- Order the results by state abbreviation.
-- (55 rows)

select COUNT (city_name) AS num_cities
from city
where state_abbreviation IN (
select state_abbreviation
from state
where state_abbreviation <> 'DC'
order by state_abbreviation ASC);
