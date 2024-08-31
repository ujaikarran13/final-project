-- 16. The name, date established, and area of parks that contain the string "Canyon" anywhere in the name.
-- Order the results by date established, oldest first.
-- (5 rows)

select park_name, date_established, area
from park
where park_name LIKE '%Canyon%'
order by date_established ASC;