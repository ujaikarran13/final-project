-- database m2_final_project
BEGIN TRANSACTION;


CREATE TABLE physicians_office (
    office_id INT PRIMARY KEY,
    address VARCHAR,
    phone_number VARCHAR,
    from_hour INT,
    to_hour INT
);

CREATE TABLE services (
    services_id INT PRIMARY KEY,
    service_description VARCHAR,
    cost_per_hour INT
);

CREATE TABLE physician_availability (
    physician_id INT PRIMARY KEY,
    date_available DATE,
	date_unavailable DATE
);



-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');


-- Inserting data into physicians_office
INSERT INTO physicians_office (office_id, address, phone_number, from_hour, to_hour) VALUES
(1, '123 Health St, Cityville', '555-1234', 9, 17),
(2, '456 Wellness Ave, Townsville', '555-5678', 10, 18);

-- Inserting data into services
INSERT INTO services (services_id, service_description, cost_per_hour) VALUES
(1, 'General Consultation', 100),
(2, 'Urgent Call', 150),
(3, 'Drug Information Request', 80);

-- Inserting data into staff_availability
INSERT INTO physician_availability (physician_id, date_available, date_unavailable) VALUES
(1, '2024-10-01', '2024-12-25'),
(2, '2024-10-02', '2024-11-21'),
(3, '2024-10-03', '2024-11-11');
COMMIT TRANSACTION;
