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
("Teacher", true);

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

INSERT INTO classroom_grade(grade_id, classroom_id)
VALUES(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 9),
(11, 10),
(12, 10);