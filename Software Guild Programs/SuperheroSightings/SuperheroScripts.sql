USE SuperheroDB;

Insert into organizations(name, description, address, contact)
values ("The Justice League", "The DC world's greatest heroes", "Hall of Justice, Washington, D.C.", "unknown");

Insert into superhero(name, description)
values ("Wonder Woman", "An Amazonian Goddess");

Insert into superhero(name, description)
values ("SuperMan", "He's from Krypton");

Insert into superhero(name, description)
values ("Batman", "The Dark Knight");

Insert into superhero(name, description)
values ("Robin", "The Dark Knight's side-kick");

INSERT into superhero_organization(superheroId, organizationId)
Values (1, 1);

INSERT into superhero_organization(superheroId, organizationId)
Values (2, 1);

INSERT into superhero_organization(superheroId, organizationId)
Values (3, 1);

INSERT into superhero_organization(superheroId, organizationId)
Values (4, 1);

insert into locations(name, description, address, longitude, latitude)
values ("Times Square", "major commercial intersection", "Manhattan, NY 10036", "40.758005", "-73.985626");

Insert into organizations(name, description, address, contact)
values ("Teen Titans", "Teenage heroes", "Titans Tower, Jump City Bay", "unknown");

INSERT into superhero_organization(superheroId, organizationId)
Values (4, 2);

insert into sightings(superheroId, locationId, dateOf)
values (1, 1, '2020-08-08');

insert into power(name)
values ("Super Speed");

insert into power(name)
values ("Super Strength");

insert into power(name)
values ("Flight");

insert into power(name)
values ("Laser Eyes");

insert into superhero_power(superheroId, powerId)
values (1, 1); 

select * from power;
select * from superhero_power;
select image from superhero;
select * from superhero_organization;
select * from locations;
select * from sightings;
SELECT * FROM sightings WHERE dateOf = "2020-08-08";

DELETE so.* FROM superhero_organization so WHERE superheroId = 4;

SELECT p.* FROM power p JOIN 
superhero_power sp ON sp.powerId = p.id WHERE sp.superheroId = 2;

SELECT s.* FROM superhero s JOIN
superhero_organization so ON so.superheroId = s.Id WHERE so.organizationId = 1;

SELECT l.* FROM locations l JOIN
sightings st ON st.locationId = l.Id WHERE st.superheroId = 1;

SELECT s.* FROM superhero s JOIN
sightings st ON st.superheroId = s.Id WHERE st.locationId = 1;



SELECT l.* FROM superhero_location l JOIN
sightings st ON st.locationId = l.locationId WHERE st.id = 2;

SELECT o.* FROM organizations o JOIN
superhero_organization so ON so.organizationId = o.Id WHERE so.superheroId = 4;

SELECT st.id, s.name, l.name, st.dateOf
FROM superhero s
JOIN sightings st ON st.superheroId = s.id 
JOIN locations l ON st.locationId = l.id 
WHERE st.id = 1;

SELECT s.* FROM superhero s
JOIN sightings st ON st.superheroId = s.id 
WHERE st.id = 3;

SELECT l.* FROM locations l
JOIN sightings st ON st.locationId = l.id 
WHERE st.id = 3;