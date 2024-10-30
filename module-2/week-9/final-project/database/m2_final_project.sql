-- database m2_final_project
BEGIN TRANSACTION;
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
-- Table for Facilities
CREATE TABLE Facilities (
    FacilityID INT AUTO_INCREMENT PRIMARY KEY,
    Address VARCHAR(255) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL,
    OfficeHours VARCHAR(100) NOT NULL,
    CostPerHour DECIMAL(10, 2) NOT NULL
);

-- Table for Doctor-Facility Association
CREATE TABLE DoctorFacilities (
    DoctorID INT,
    FacilityID INT,
    PRIMARY KEY (DoctorID, FacilityID),
    FOREIGN KEY (DoctorID) REFERENCES users(user_id),
    FOREIGN KEY (FacilityID) REFERENCES Facilities(FacilityID)
);

-- Table for Availability Schedule
CREATE TABLE Availability (
    AvailabilityID INT AUTO_INCREMENT PRIMARY KEY,
    DoctorID INT,
    DayOfWeek ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    FOREIGN KEY (DoctorID) REFERENCES users(user_id)
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


-- Insert Facilities
INSERT INTO Facilities (Address, PhoneNumber, OfficeHours, CostPerHour) VALUES
('123 Health St, Wellness City', '555-1234', 'Mon-Fri: 9 AM - 5 PM', 150.00),
('456 Care Ave, Health City', '555-5678', 'Mon-Fri: 10 AM - 6 PM', 200.00);

-- Associate Doctors with Facilities
INSERT INTO DoctorFacilities (DoctorID, FacilityID) VALUES
(1, 1),  -- Doc Smith in Facility 1
(2, 1),  -- Doc Jones in Facility 1
(1, 2);  -- Doc Smith in Facility 2

-- Insert Availability for Doctors
INSERT INTO Availability (DoctorID, DayOfWeek, StartTime, EndTime) VALUES
(1, 'Monday', '09:00:00', '17:00:00'),
(1, 'Tuesday', '09:00:00', '17:00:00'),
(2, 'Wednesday', '10:00:00', '18:00:00'),
(2, 'Thursday', '10:00:00', '18:00:00'),
(1, 'Friday', '09:00:00', '17:00:00');

COMMIT TRANSACTION;
