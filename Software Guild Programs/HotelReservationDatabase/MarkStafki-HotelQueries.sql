USE HotelDB
GO

-- 1. Write a query that returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), and the reservation dates.

SELECT g.FirstName, g.LastName, r.RoomId, r.StartDate, r.EndDate
FROM Guest g
INNER JOIN Reservation r ON r.GuestId = g.GuestId
WHERE r.EndDate BETWEEN '2023-7-1' AND '2023-7-31';

--RESULT 
--Mark		Stafki	 205	2023-06-28	 2023-07-02
--Walter	Holaway	 204	2023-07-13	 2023-07-14
--Wilfred	Vise	 401	2023-07-18	 2023-07-21
--Bettyann	Seery	 303	2023-07-28	 2023-07-29

-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, the room number, and the dates of the reservation.

SELECT g.FirstName, g.LastName, res.RoomId, res.StartDate, res.EndDate
FROM Reservation res
INNER JOIN Guest g ON res.GuestId = g.GuestId
INNER JOIN Room r ON res.RoomId = r.RoomId
INNER JOIN RoomAmenity ra ON r.RoomId = ra.RoomId
INNER JOIN Amenity a ON ra.AmenityId = a.AmenityId
WHERE a.AmenityName = 'Jacuzzi';

--RESULT 
--Bettyann	Seery		203	2023-02-05	 2023-02-10
--Duane		Cullison	305	2023-02-22	 2023-02-24
--Karie		Yang		201	2023-03-06	 2023-03-07
--Mark		Stafki		307	2023-03-17	 2023-03-20
--Walter	Holaway		301	2023-04-09	 2023-04-13
--Wilfred	Vise		207	2023-04-23	 2023-04-24
--Mark		Stafki		205	2023-06-28	 2023-07-02
--Bettyann	Seery		303	2023-07-28 	 2023-07-29
--Bettyann	Seery		305	2023-08-30	 2023-09-01
--Karie		Yang		203	2023-09-13	 2023-09-15
--Mack		Simmer		301	2023-11-22	 2023-11-25

-- 3. Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting date of the reservation, 
--    and how many people were included in the reservation. (Choose a guest's name from the existing data.)

SELECT g.FirstName, g.LastName, res.RoomId, res.StartDate, res.Adults + ISNULL(res.Children, 0) AS TotalPeople
FROM Reservation res
INNER JOIN Guest g ON res.GuestId = g.GuestId
WHERE g.FirstName = 'Mark' AND g.LastName = 'Stafki';

--RESULT 
--Mark	Stafki	402	 2022-08-24	 1
--Mark	Stafki	307	 2023-03-17	 2
--Mark	Stafki	205	 2023-06-28	 2

-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
--    The results should include all rooms, whether or not there is a reservation associated with the room.

SELECT r.RoomId, res.ReservationId, res.TotalCost, r.BasePrice
FROM Room r
LEFT OUTER JOIN Reservation res ON r.RoomId = res.RoomId

--RESULT 
--201	5		$199.99		$199.99
--202	8		$349.98		$174.99
--203	3		$999.95		$199.99
--203	22		$399.98		$199.99
--204	17		$184.99		$174.99
--205	16		$699.96		$174.99
--206	13		$599.96		$149.99
--206	24		$449.97		$149.99
--207	11		$174.99		$174.99
--208	14		$599.96		$149.99
--208	21		$149.99		$149.99
--301	10		$799.96		$199.99
--301	25		$599.97		$199.99
--302	7		$924.95		$174.99
--302	26		$699.96		$174.99
--303	19		$199.99		$199.99
--304	15		$184.99		$174.99
--305	4		$349.98		$174.99
--305	20		$349.98		$174.99
--306	NULL	NULL		$149.99
--307	6		$524.97		$174.99
--308	2		$299.98		$149.99
--401	12		$1,199.97	$399.99
--401	18		$1,259.97	$399.99
--401	23		$1,199.97	$399.99
--402	1		$100		$399.99

-- 5. Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.

SELECT *
FROM Room r
INNER JOIN Reservation res ON r.RoomId = res.RoomId
WHERE res.StartDate BETWEEN '2023-4-1' AND '2023-4-30' 
AND r.MaxOccupancy >= 3;

--RESULT 
--301	Double	0	2	4	$199.99	$10    10	301	 9	1	NULL	2023-04-09	2023-04-13	$799.96

-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting with the guest with the most reservations and then by the guest's last name.

SELECT g.FirstName, g.LastName, COUNT(res.GuestId) AS NumberOfReservations
FROM Guest g
INNER JOIN Reservation res ON g.GuestId = res.GuestId
GROUP BY g.FirstName, g.LastName
ORDER BY COUNT(res.GuestId) DESC, g.LastName;

--RESULT
--Mack		Simmer		4
--Bettyann	Seery		3
--Mark		Stafki		3
--Duane		Cullison	2
--Walter	Holaway		2
--Aurore	Lipton		2
--Maritza	Tilton		2
--Joleen	Tison		2
--Wilfred	Vise		2
--Karie		Yang		2
--Zachery	Luechtefeld	1

-- 7. Write a query that displays the name, address, and phone number of a guest based on their phone number. (Choose a phone number from the existing data.)

SELECT g.FirstName, g.LastName, g.[Address], g.Phone
FROM Guest g
WHERE Phone = '(651) 888-8888';

--RESULT
--Mark	Stafki	Data St NW	(651) 888-8888      