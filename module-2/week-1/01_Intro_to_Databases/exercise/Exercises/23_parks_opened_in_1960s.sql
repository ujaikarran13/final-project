-- 23. The name and date established of parks opened in the 1960s.
-- Order the results by date established, oldest first.
-- (6 rows)

select park_name, date_established
from park
where date_established BETWEEN '1960-01-01' AND '1969-12-31'
order by date_established ASC;