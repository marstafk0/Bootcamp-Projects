INSERT INTO grade(grade_name, active) 
VALUES("K", true),
("1st", true),
("2nd", true),
("3rd", true),
("4th", true),
("5th", true),
("6th", true),
("7th", true),
("8th", true),
("9th", true),
("10th", true),
("11th", true),
("12th", true);

INSERT INTO person_type(status_name, active)
VALUES("Student", true),
("Teacher", true),
("Member", true);

INSERT INTO classroom(class_name, active)
VALUES("St. John", true),
("St. Mary", true),
("St. Dominic Sovio", true),
("St. Peter", true),
("St. Pius X", true),
("St. Jude", true),
("St. Theresa", true),
("St. Padre Pio", true),
("St. Thomas Aquinas", true),
("St. Benedict", true),
("St. John Vienny", true);

INSERT INTO person(fname, lname, contact, active)
VALUES("Charlie", "Stafki", "parents", true),
("Jacob", "Stafki", "None", true),
("Margaret", "Stafki", "None", true),
("Annette", "Stafki", "None", true),
("Nancy", "Budzien", "None", true),
("Lori", "Aubuchon", "None", true),
("Stephanie", "Holeksa", "None", true),
("Madeleine", "Mackin", "None", true),
("Jenna", "Simones", "None", true),
("Kenneth", "Simones", "None", true),
("Laura", "Simones", "None", true),
("Philomena", "Hering", "None", true),
("Amy", "Stafki", "None", true),
("Mary", "Stafki", "None", true),
("Mary", "Hering", "None", true),
("Dominic", "Hering", "None", true),
("Ivy", "Somdahl", "None", true),
("William", "Fitzpatrick", "None", true),
("Victoria", "Mailand", "None", true);

INSERT INTO person_type_person(person_id, person_type_id)
VALUES(1,1),
(2,1),
(3,3),
(4,2),
(5,2),
(6,2),
(7,2),
(8,2),
(9,2),
(10,1),
(11,1),
(12,1),
(13,1),
(14,1),
(15,3),
(16,1),
(17,1),
(18,1),
(19,1);

INSERT INTO grade_person(person_id, grade_id)
VALUES(1,2),
(2,10),
(10,11),
(11,13),
(12,9),
(13,10),
(14,6),
(16,10),
(17,10),
(18,11),
(19,10);

INSERT INTO classroom_grade(classroom_id, grade_id)
VALUES(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(10,11),
(11,12),
(11,13);

INSERT INTO run(laps)
VALUES(10),
(12),
(15),
(30),
(18);

INSERT INTO person_run(person_id, run_id)
VALUES(1,1),
(2,2),
(11,3),
(12,4),
(13,5);

INSERT INTO jogathon_master(active, comments, deletion, run_date)
VALUES(false, "good year", false, "09/21/2019"),
(true, "Walmarts are open to collaborating this year.", false, "09/21/2021");

INSERT INTO jogathon_master_run(jogathon_master_id, run_id)
VALUES(1, 1),
(1, 2),
(2, 3),
(2, 4),
(2, 5);

INSERT INTO pledge_type(pledge_name, active)
VALUES("Door to door", true),
("Walmart", true);

INSERT INTO sponsor(address_one, address_two, fname, lname, city, phone, state_of, zip)
VALUES("20022 Shelby Ave", "none", "Bob", "Seegar", "Hotshot", "61243495494", "NY", "66043"),
("32452 Simaon ST", "none", "John", "Smith", "Smithville", "6124342654", "MN", "550332"),
("Anonymous", "", "", "", "", "", "", "");

INSERT INTO pledge(active, collected, deletion, receipt, one_time, per_lap, total, week)
VALUES(true, true, false, false, 24.99, 0, 24.99, 1),
(false, true, false, false, 50, 0, 50, 1),
(true, true, false, false, 15, 1.50, 30, 2),
(true, true, false, false, 2, 0, 2, 3);

INSERT INTO person_pledge(person_id, pledges_id)
VALUES(2,1),
(1,3),
(11,2),
(12,4);

INSERT INTO jogathon_master_pledge(jogathon_master_id, pledge_id)
VALUES(1, 2),
(2, 1),
(2, 3),
(2, 4);

INSERT INTO pledge_type_pledge(pledge_id, pledge_type_id)
VALUES(1, 1),
(2, 2),
(3, 1),
(4, 2);

INSERT INTO sponsor_pledge(sponsor_id, pledge_id)
VALUES(1, 1),
(2, 2),
(3, 3),
(2, 4);

INSERT INTO family(address_one, address_two, fam_name, city, phone, state_of, zip, active)
VALUES("20022 Shelby Ave", "none", "Bob Seegar", "Hotshot", "61243495494", "NY", "66043", true),
("24545 Crooked Ave", "none", "John Smith", "Smithville", "6124342654", "ND", "223445", true),
("32452 Simaon St", "none", "Bob Seegar", "Hotshot", "2338939202", "MN", "66043", true);

INSERT INTO family_person(family_id, person_id)
VALUES(1, 1),
(1, 2),
(2, 11),
(3, 12),
(1, 10),
(2, 8);