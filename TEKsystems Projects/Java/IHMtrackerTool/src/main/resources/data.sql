INSERT INTO grade(grade_name, active) VALUES('K', true);
INSERT INTO grade(grade_name, active) VALUES('1st', true);
INSERT INTO grade(grade_name, active) VALUES('2nd', true);
INSERT INTO grade(grade_name, active) VALUES('3rd', true);
INSERT INTO grade(grade_name, active) VALUES('4th', true);
INSERT INTO grade(grade_name, active) VALUES('5th', true);
INSERT INTO grade(grade_name, active) VALUES('6th', true);
INSERT INTO grade(grade_name, active) VALUES('7th', true);
INSERT INTO grade(grade_name, active) VALUES('8th', true);
INSERT INTO grade(grade_name, active) VALUES('9th', true);
INSERT INTO grade(grade_name, active) VALUES('10th', true);
INSERT INTO grade(grade_name, active) VALUES('11th', true);
INSERT INTO grade(grade_name, active) VALUES('12th', true);

INSERT INTO person_type(status_name, active) VALUES('Student', true);
INSERT INTO person_type(status_name, active) VALUES('Teacher', true);
INSERT INTO person_type(status_name, active) VALUES('Member', true);

INSERT INTO classroom(class_name, active) VALUES('St. John', true);
INSERT INTO classroom(class_name, active) VALUES('St. Mary', true);
INSERT INTO classroom(class_name, active) VALUES('St. Dominic Sovio', true);
INSERT INTO classroom(class_name, active) VALUES('St. Peter', true);
INSERT INTO classroom(class_name, active) VALUES('St. Pius X', true);
INSERT INTO classroom(class_name, active) VALUES('St. Jude', true);
INSERT INTO classroom(class_name, active) VALUES('St. Theresa', true);
INSERT INTO classroom(class_name, active) VALUES('St. Padre Pio', true);
INSERT INTO classroom(class_name, active) VALUES('St. Thomas Aquinas', true);
INSERT INTO classroom(class_name, active) VALUES('St. Benedict', true);
INSERT INTO classroom(class_name, active) VALUES('St. John Vienny', true);

INSERT INTO person(fname, lname, contact, active) VALUES('Charlie', 'Stafki', 'parents', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Jacob', 'Stafki', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Margaret', 'Stafki', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Annette', 'Stafki', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Nancy', 'Budzien', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Lori', 'Aubuchon', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Stephanie', 'Holeksa', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Madeleine', 'Mackin', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Jenna', 'Simones', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Kenneth', 'Simones', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Laura', 'Simones', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Philomena', 'Hering', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Amy', 'Stafki', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Mary', 'Stafki', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Mary', 'Hering', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Dominic', 'Hering', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Ivy', 'Somdahl', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('William', 'Fitzpatrick', 'None', true);
INSERT INTO person(fname, lname, contact, active) VALUES('Victoria', 'Mailand', 'None', true);

INSERT INTO person_type_person(person_id, person_type_id) VALUES(1,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(2,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(3,3);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(4,2);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(5,2);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(6,2);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(7,2);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(8,2);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(9,2);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(10,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(11,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(12,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(13,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(14,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(15,3);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(16,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(17,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(18,1);
INSERT INTO person_type_person(person_id, person_type_id) VALUES(19,1);

INSERT INTO grade_person(person_id, grade_id) VALUES(1,2);
INSERT INTO grade_person(person_id, grade_id) VALUES(2,10);
INSERT INTO grade_person(person_id, grade_id) VALUES(10,11);
INSERT INTO grade_person(person_id, grade_id) VALUES(11,13);
INSERT INTO grade_person(person_id, grade_id) VALUES(12,9);
INSERT INTO grade_person(person_id, grade_id) VALUES(13,10);
INSERT INTO grade_person(person_id, grade_id) VALUES(14,6);
INSERT INTO grade_person(person_id, grade_id) VALUES(16,10);
INSERT INTO grade_person(person_id, grade_id) VALUES(17,10);
INSERT INTO grade_person(person_id, grade_id) VALUES(18,11);
INSERT INTO grade_person(person_id, grade_id) VALUES(19,10);

INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(1,1);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(2,2);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(3,3);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(4,4);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(5,5);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(6,6);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(7,7);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(8,8);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(9,9);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(10,10);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(10,11);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(11,12);
INSERT INTO classroom_grade(classroom_id, grade_id) VALUES(11,13);

INSERT INTO run(laps) VALUES(10);
INSERT INTO run(laps) VALUES(12);
INSERT INTO run(laps) VALUES(15);
INSERT INTO run(laps) VALUES(30);
INSERT INTO run(laps) VALUES(18);

INSERT INTO person_run(person_id, run_id) VALUES(1,1);
INSERT INTO person_run(person_id, run_id) VALUES(2,2);
INSERT INTO person_run(person_id, run_id) VALUES(11,3);
INSERT INTO person_run(person_id, run_id) VALUES(12,4);
INSERT INTO person_run(person_id, run_id) VALUES(13,5);

INSERT INTO jogathon_master(active, comments, deletion, run_date) VALUES(false, 'good year', false, '09/21/2019');
INSERT INTO jogathon_master(active, comments, deletion, run_date) VALUES(true, 'Walmarts are open to collaborating this year.', false, '09/21/2021');

INSERT INTO jogathon_master_run(jogathon_master_id, run_id) VALUES(1, 1);
INSERT INTO jogathon_master_run(jogathon_master_id, run_id) VALUES(1, 2);
INSERT INTO jogathon_master_run(jogathon_master_id, run_id) VALUES(2, 3);
INSERT INTO jogathon_master_run(jogathon_master_id, run_id) VALUES(2, 4);
INSERT INTO jogathon_master_run(jogathon_master_id, run_id) VALUES(2, 5);

INSERT INTO pledge_type(pledge_name, active) VALUES('Door to door', true);
INSERT INTO pledge_type(pledge_name, active) VALUES('Walmart', true);

INSERT INTO sponsor(address_one, address_two, fname, lname, city, phone, state_of, zip) VALUES('20022 Shelby Ave', 'none', 'Bob', 'Seegar', 'Hotshot', '61243495494', 'NY', '66043');
INSERT INTO sponsor(address_one, address_two, fname, lname, city, phone, state_of, zip) VALUES('32452 Simaon ST', 'none', 'John', 'Smith', 'Smithville', '6124342654', 'MN', '550332');
INSERT INTO sponsor(address_one, address_two, fname, lname, city, phone, state_of, zip) VALUES('Anonymous', '', '', '', '', '', '', '');

INSERT INTO pledge(active, collected, deletion, receipt, one_time, per_lap, total, week) VALUES(true, true, false, false, 24.99, 0, 24.99, 1);
INSERT INTO pledge(active, collected, deletion, receipt, one_time, per_lap, total, week) VALUES(false, true, false, false, 50, 0, 50, 1);
INSERT INTO pledge(active, collected, deletion, receipt, one_time, per_lap, total, week) VALUES(true, true, false, false, 15, 1.50, 30, 2);
INSERT INTO pledge(active, collected, deletion, receipt, one_time, per_lap, total, week) VALUES(true, true, false, false, 2, 0, 2, 3);

INSERT INTO person_pledge(person_id, pledges_id) VALUES(2,1);
INSERT INTO person_pledge(person_id, pledges_id) VALUES(1,3);
INSERT INTO person_pledge(person_id, pledges_id) VALUES(11,2);
INSERT INTO person_pledge(person_id, pledges_id) VALUES(12,4);

INSERT INTO jogathon_master_pledge(jogathon_master_id, pledge_id) VALUES(1, 2);
INSERT INTO jogathon_master_pledge(jogathon_master_id, pledge_id) VALUES(2, 1);
INSERT INTO jogathon_master_pledge(jogathon_master_id, pledge_id) VALUES(2, 3);
INSERT INTO jogathon_master_pledge(jogathon_master_id, pledge_id) VALUES(2, 4);

INSERT INTO pledge_type_pledge(pledge_id, pledge_type_id) VALUES(1, 1);
INSERT INTO pledge_type_pledge(pledge_id, pledge_type_id) VALUES(2, 2);
INSERT INTO pledge_type_pledge(pledge_id, pledge_type_id) VALUES(3, 1);
INSERT INTO pledge_type_pledge(pledge_id, pledge_type_id) VALUES(4, 2);

INSERT INTO sponsor_pledge(sponsor_id, pledge_id) VALUES(1, 1);
INSERT INTO sponsor_pledge(sponsor_id, pledge_id) VALUES(2, 2);
INSERT INTO sponsor_pledge(sponsor_id, pledge_id) VALUES(3, 3);
INSERT INTO sponsor_pledge(sponsor_id, pledge_id) VALUES(2, 4);

INSERT INTO family(address_one, address_two, fam_name, city, phone, state_of, zip, active) VALUES('20022 Shelby Ave', 'none', 'Bob Seegar', 'Hotshot', '61243495494', 'NY', '66043', true);
INSERT INTO family(address_one, address_two, fam_name, city, phone, state_of, zip, active) VALUES('24545 Crooked Ave', 'none', 'John Smith', 'Smithville', '6124342654', 'ND', '223445', true);
INSERT INTO family(address_one, address_two, fam_name, city, phone, state_of, zip, active) VALUES('32452 Simaon St', 'none', 'Bob Seegar', 'Hotshot', '2338939202', 'MN', '66043', true);

INSERT INTO family_person(family_id, person_id) VALUES(1, 1);
INSERT INTO family_person(family_id, person_id) VALUES(1, 2);
INSERT INTO family_person(family_id, person_id) VALUES(2, 11);
INSERT INTO family_person(family_id, person_id) VALUES(3, 12);
INSERT INTO family_person(family_id, person_id) VALUES(1, 10);
INSERT INTO family_person(family_id, person_id) VALUES(2, 8);