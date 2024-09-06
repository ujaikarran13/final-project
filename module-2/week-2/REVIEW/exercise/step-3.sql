-- select the park name, campground name, open_from_mm, open_to_mm & daily_fee 
-- order by park name and then by campground name
-- (expected: 7 rows, starting with "Blackwoods")


-- select the park name and the total number of campgrounds for each park
-- order alphabetically by park name
-- (expected: 3 rows, starting with "Acadia")


-- select the park name, campground name, site number, max occupancy, accessible, max rv length, utilities where the campground name is 'Blackwoods'
-- order alphabetically by park name
-- (expected: 12 rows)


-- select site number, reservation name, reservation from and to date 
-- order by reservation from date
-- (expected: 44 rows, starting with the earliest date)
Select re.name, from_date, to_date, si.site_number
	From reservation As re
	Join site As si On re.site_id = si.site_id
	Order by re.from_date;

-- select the park name, animal name, and estimated population for all animals in all parks. 
-- order the results by park name then by animal name
-- (expected: 31 rows, starting with Big Brown Bat in Acadia and ending with White-Tailed Deer in Cuyahoga Valley)
select pk.name, an.name, est_population
from park AS pk
join park_animal AS pa ON pa.park_id = pk.park_id
join animal AS an ON pa.animal_id = an.animal_id
order by pk.name, an.name;

-- select the name of all parks that have a Common Snapping Turtle population followed by their estimated population.
-- order the results by population (highest to lowest)
-- (expected: 2 rows, Acadia 1000 and Cuyahoga Valley 400)
select pk.name, est_population
from park AS pk
join park_animal AS pa ON pk.park_id = pa.park_id
where pa.animal_id = 11
order by est_population DESC;
-- select the name of each park that contains Black Bears followed by the 'area_per_bear' (number of acres per bear) of all parks that contain bears.
-- order the results by area_per_bear
-- (expected: 3 rows, starting with Cuyahoga at 1095 and ending with Acadia at 4738)
select pk.name, area / est_population AS area_per_bear
from park AS pk
join park_animal AS pa ON pk.park_id = pa.park_id
join animal AS an ON an.animal_id = pa.animal_id
where an.name = 'Black Bear'
order by area_per_bear;
-- select the name and Largemouth Bass population of all parks that have a Largemouth Bass population greater than 1500. 
-- order the results by population (highest to lowest).
-- (expected: 2 rows, Cuyahoga 10000 followed by Acadia 3000)
select pk.name, est_population
from park AS pk
join park_animal AS pa ON pk.park_id = pa.park_id
join animal AS an ON an.animal_id = pa.animal_id
where an.name = 'Largemouth Bass' AND est_population > 1500
order by est_population DESC;