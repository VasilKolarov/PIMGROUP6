-- Create a sample note
INSERT INTO NOTES
(NOTE_TITLE, NOTE_CONTENT, NOTE_CREATION_DATE)
VALUES
("Test Note 1", "Note Content 1", NOW()),
("Test Note 2", "Note Content 2", NOW()),
("Test Note 3", "Note Content 3", NOW()),
("Test Note 4", "Note Content 4", NOW()),
("Test Note 5", "Note Content 5", NOW()),
("Test Note 6", "Note Content 6", NOW()),
("Test Note 7", "Note Content 7", NOW());

-- Create a sample contact
INSERT INTO CONTACTS
(CONTACT_FORENAME, CONTACT_SURNAME, CONTACT_PHONE, CONTACT_EMAIL)
VALUES
("Jonathon", "Butterworth", "12345678910", "jb2253@bath.ac.uk"),
("Jack", "Evans", "12345678910", "je387@bath.ac.uk"),
("Adam", "Hobbs", "12345678910", "ah945@bath.ac.uk"),
("Vasil", "Kolarov", "12345678910", "vk313@bath.ac.uk"),
("Caroline", "Moir", "12345678910", "clm52@bath.ac.uk"),
("Tommy", "Preston", "12345678910", "tip27@bath.ac.uk"),
("Harry", "Sergeant", "12345678910", "has38@bath.ac.uk"),
("Petar", "Tonev", "12345678910", "pt386@bath.ac.uk");

-- Create a sample appointment
INSERT INTO APPOINTMENTS
(APPOINTMENT_TITLE, APPOINTMENT_CATEGORY, APPOINTMENT_DESCRIPTION, APPOINTMENT_LOCATION, APPOINTMENT_DATE)
VALUES
("Maths Lecture", "CM10197", "Impossibly boring", "East Building", 02/08/2015),
("Programming Lecture", "CM10228", "Marginally boring", "East Building", 02/08/2015),
("System Architecture Lecture", "CM10195", "Really boring", "East Building", 02/08/2015),
("Software Engineering Lecture", "CM10193", "Exceptionally boring", "East Building", 02/08/2015);
