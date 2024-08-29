-- PARK TABLE
-----------------------------------------------

-- 1. Select name and description of all parks ordered by the park's establish date in descending order. (expected: 3 rows, starting with "Cuyahoga Valley")

-- 2. For all parks, select the name and location as a single value that looks like this: "Park Name (Location)". (expected: 3 rows)

-- 3. Select name and description of all parks in Ohio. (expected: 1 row)

-- 4. Select name and description of all parks NOT in Ohio. (expected: 2 rows)

-- 5. Select the total number of visitors for all parks. (expected value: around 6 million)

-- 6. Select the average number of visitors for all parks. (expected value: around 2 million)

-- 7. Potential guests want to know how secluded they may be in a park. To show this we can calculate the visitor density as (visitors / area). 
--		Select the park name and visitor density for all parks. Call the computed visitor density "visitors_per_sqkm". (expected: 3 rows)


-- CAMPGROUND TABLE
-----------------------------------------------

-- 8. Select name and daily fee of all campgrounds. (expected: 7 rows)

-- 9. Select name and daily fee of all campgrounds ordered by campground name. (expected: 7 rows, starting with "Blackwoods")

-- 10. Select name, open from month, open to month, and daily fee of the campgrounds where daily fee is less than $100. (expected: 5 rows)

-- 11. Select name and daily fee of the campgrounds where the campground is open all year long. (expected: 4 rows)

-- You need to get all the campgrounds in the park named 'Arches'. You can do this with the following two queries:
-- 12. First, select the park id of the park with name 'Arches'. (expected: 1 row)

-- 13. Then, select the campground id and name of all the campgrounds for the park with the park_id you just selected. (expected: 3 rows)

-- 14. Now, can you do the same work of the previous two queries in one query? 
--		HINT: The first query will become a subquery of the second. (expected: 3 rows)

-- 15. Select the park id and the number of campgrounds at each park (expected: 3 rows)


-- SITE TABLE
-----------------------------------------------

-- 16. Select all columns from site where utilities is true sorted by max RV length with the largest coming first.
--		(expected: 26 rows, starting with 35-foot max_rv_length)

-- 17. Select the total number of sites that have utilities hook up. (expected value: around 25)

-- 18. List the site id and max_occupancy of the 10 highest-occupancy sites sorted from highest occupancy to lowest. 
--		(expected: 10 rows, starting with site 46)

-- 19. Select site id, site number, and campground id of the sites where the site number is in the list (1, 4, 8, 11).
--		(expected: 19 rows)
	
-- 20. A group of primitive campers called, and they want you to list the site number for all sites which 
--		don't allow RV's, aren't accessible, and don't have utilities. (expected: 14 rows)

-- 21. A physically challenged customer called, and you need to list the site number, max RV length, accessible and utilities 
--		for all sites which either allow RV's, or are both accessible and have utilities. (expected: 27 rows)

-- 22. The customer from the previous call decided to take their RV, and is camping in February. List the campground id, site number and max RV length 
--		of all sites that allow RV's and are in a campground which is open in February. (expected: 8 rows)
-- 		HINT: Use a subquery to do this in a single SQL statement. The subquery should find all the campgrounds open in February.


-- RESERVATION TABLE
-----------------------------------------------

-- 23. Select reservation id, site id, name, from date, to date of the reservations where the start date 
--		is between the first and last day of the current month (hard coded month is ok).
-- 		(expected row count may vary, should be no more than 44)

-- 24. Select all columns from reservation where name includes 'Reservation'. (expected: 23 rows)

-- 25. Select the total number of reservations in the reservation table. (expected value: around 40)

-- 26. Select the date and number of reservations for each date ordered by from date in ascending order.
--		(expected: 24 rows, starting from the earliest date)

-- 27. Select the site_id, name, and number of nights for each reservation. You can get the number of nights by using the
--		expression "to_date - from_date". Call the computed column "number_of_nights". (expected: 44 rows)

-- 28. List the site_id, name, and number of nights for each reservation, and call the computed column "number_of_nights", as in the preceding query. 
--		Sort the list from the longest stay to shortest. (expected: 44 rows, starting with "Kawasaki Family Reservation" for 11 nights)

-- 29. List the site_id, name, and number of nights of all reservation, and call the computed column "number_of_nights", as in the preceding query. 
--		Show only the 3 longest stays. (expected: 3 rows, starting with "Kawasaki Family Reservation")

-- 30. The park system is trying to get a profile of a typical camp stay. Determine the average number of nights across all reservations. 
--		(expected value: between 4 and 5)

-- 31. The park system IT department needs to know which days will put the most load on the system in terms of check-ins. Select the number of
-- 		check-ins per date sorted most to fewest. (expected: 24 rows, starting with 9 check-ins)

-- 32. Select the average stay length for each reservation start date, ordered by start (from) date. The stay length is the number of nights (to_date - from_date).
--		(expected: 24 rows, starting with the earliest start date)
