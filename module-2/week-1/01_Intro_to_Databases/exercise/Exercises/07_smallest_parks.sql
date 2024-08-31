-- 7. The name, date established, and area of the 10 smallest parks.
-- (10 rows)
select park_name, date_established, area
from park
order by area ASC
limit 10;
