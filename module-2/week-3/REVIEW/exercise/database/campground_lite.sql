-- database name: campground_lite;
DROP TABLE IF EXISTS campground;
DROP TABLE IF EXISTS park;

BEGIN TRANSACTION;

CREATE TABLE park (
  park_id serial,
  name varchar(80) NOT NULL,          -- Name of the park
  location varchar(50) NOT NULL,      -- State name(s) where park is located
  establish_date date NOT NULL,       -- Date park was established
  area integer NOT NULL,              -- Area in acres
  visitors integer NOT NULL,          -- Latest recorded number of annual visitors
  description varchar(500) NOT NULL,
  CONSTRAINT pk_park_park_id PRIMARY KEY (park_id)
);

CREATE TABLE campground (
  campground_id serial,
  park_id integer NOT NULL,           -- Parent park
  name varchar(80) NOT NULL,          -- Name of the campground
  open_from_mm integer NOT NULL,      -- Campground is open from month: 1=January, 2=February, ... 12=December
  open_to_mm integer NOT NULL,        -- Campground is open to month: 1=January, 2=February, ... 12=December
  daily_fee decimal(10,2) NOT NULL,
  CONSTRAINT pk_campground_campground_id PRIMARY KEY (campground_id)
);


-- Acadia
INSERT INTO park (name, location, establish_date, area, visitors, description)
VALUES ('Acadia', 'Maine', '1919-02-26', 47389, 2563129, 'Covering most of Mount Desert Island and other coastal islands, Acadia features the tallest mountain on the Atlantic coast of the United States, granite peaks, ocean shoreline, woodlands, and lakes. There are freshwater, estuary, forest, and intertidal habitats.');

-- Arches
INSERT INTO park (name, location, establish_date, area, visitors, description)
VALUES ('Arches',	'Utah', '1929-04-12', 76518,	1284767, 'This site features more than 2,000 natural sandstone arches, including the famous Delicate Arch. In a desert climate, millions of years of erosion have led to these structures, and the arid ground has life-sustaining soil crust and potholes, which serve as natural water-collecting basins. Other geologic formations are stone columns, spires, fins, and towers.');

-- Cuyahoga
INSERT INTO park (name, location, establish_date, area, visitors, description)
VALUES ('Cuyahoga Valley', 'Ohio', '2000-10-11',32860,	2189849, 'This park along the Cuyahoga River has waterfalls, hills, trails, and exhibits on early rural living. The Ohio and Erie Canal Towpath Trail follows the Ohio and Erie Canal, where mules towed canal boats. The park has numerous historic homes, bridges, and structures, and also offers a scenic train ride.');

-- Acadia Campgrounds
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (1, 'Blackwoods', 1, 12, 35.00);
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (1, 'Seawall', 5, 9, 30.00);
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (1, 'Schoodic Woods', 5, 10, 30.00);

-- Arches Campgrounds
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (2, 'Devil''s Garden', 1, 12, 25.00);
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (2, 'Canyon Wren Group Site', 1, 12, 160.00);
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (2, 'Juniper Group Site', 1, 12, 250.00);

-- Cuyahoga Campgrounds
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (3, 'The Unnamed Primitive Campsites', 5, 11, 20.00);


ALTER TABLE campground ADD FOREIGN KEY (park_id) REFERENCES park(park_id);

COMMIT;
