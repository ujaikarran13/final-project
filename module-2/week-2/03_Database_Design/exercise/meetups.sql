BEGIN TRANSACTION;

CREATE TABLE Members (

	MemberNumber INT PRIMARY KEY,

    LastName VARCHAR(50) NOT NULL,

    FirstName VARCHAR(50) NOT NULL,

    EmailAddress VARCHAR(100) UNIQUE NOT NULL,

    PhoneNumber VARCHAR(20),

    DateOfBirth DATE NOT NULL,

    ReminderEmails BOOLEAN DEFAULT FALSE

);



CREATE TABLE InterestGroup (

    GroupNumber INT PRIMARY KEY,

    GroupName VARCHAR(100) UNIQUE NOT NULL

);



CREATE TABLE MemberGroup (

    MemberNumber INT NOT NULL,

    GroupNumber INT NOT NULL,

    PRIMARY KEY (MemberNumber, GroupNumber),

    FOREIGN KEY (MemberNumber) REFERENCES Members (MemberNumber) ON DELETE CASCADE,

    FOREIGN KEY (GroupNumber) REFERENCES InterestGroup(GroupNumber) ON DELETE CASCADE

);



CREATE TABLE Events (

    EventNumber INT PRIMARY KEY,

    Name VARCHAR(100) NOT NULL,

    Description VARCHAR(200) NOT NULL,

    StartDate VARCHAR(20) NOT NULL,

    Duration INT NOT NULL, 

    GroupNumber INT NOT NULL,

    FOREIGN KEY (GroupNumber) REFERENCES InterestGroup(GroupNumber) ON DELETE CASCADE

);


CREATE TABLE EventAttendance (

    MemberNumber INT NOT NULL,

    EventNumber INT NOT NULL,

    PRIMARY KEY (MemberNumber, EventNumber),

    FOREIGN KEY (MemberNumber) REFERENCES Members (MemberNumber) ON DELETE CASCADE,

    FOREIGN KEY (EventNumber) REFERENCES Events (EventNumber) ON DELETE CASCADE

);



------------------------------ Test Data ---------------------------------


INSERT INTO Members (MemberNumber, LastName, FirstName, EmailAddress, PhoneNumber, DateOfBirth, ReminderEmails)
	VALUES (1, 'Smith', 'John', 'john.smith@example.com', '555-1234', '1980-06-15', TRUE),
(2, 'Doe', 'Jane', 'jane.doe@example.com', '555-5678', '1990-11-22', FALSE),
(3, 'Brown', 'Mike', 'mike.brown@example.com', '555-8765', '1985-03-30', TRUE),
(4, 'Johnson', 'Emily', 'emily.johnson@example.com', '555-4321', '1978-09-10', FALSE),
(5, 'Smith', 'Henry', 'Henry.smith@example.com', '555-1234', '1980-06-15', TRUE),
(6, 'Mary', 'Jane', 'jane.mary@example.com', '555-5678', '1990-11-22', FALSE),
(7, 'Brown', 'Mike', 'mike.bown@example.com', '555-8765', '1985-03-30', TRUE),
(8, 'Jonson', 'Emily', 'emily.jonson@example.com', '555-4321', '1978-09-10', FALSE);
	
INSERT INTO InterestGroup ( GroupNumber, GroupName)
values(1, 'baby'), (2, 'college grads'), (3, 'adults');

INSERT INTO MemberGroup (MemberNumber, GroupNumber)
Values (2, 1), (3, 2), (4, 3);

INSERT INTO Events (EventNumber, Name, Description, StartDate, Duration, GroupNumber)
VALUES
(1, 'Baby Shower', 'A party welcoming a baby.', '2024-09-10', 60, 1), 
(2, 'Wedding', 'A party celebrating the newly weds.', '2024-09-12', 45, 2),  
(3, 'Graduation', 'A party celebrating accomplishments.', '2024-09-15', 120, 3), 
(4, 'Bridal Shower', 'A party celebrating the bride-to-be.', '2024-09-20', 90, 2);  

INSERT INTO EventAttendance (MemberNumber, EventNumber)
VALUES (1, 1),  (1, 2),  (2, 2),  (3, 3),  (4, 4);  
	
COMMIT TRANSACTION;
