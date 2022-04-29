USE MASTER 
GO

if exists (select * from sys.databases where name = N'HotelDB')
begin
	EXEC msdb.dbo.sp_delete_database_backuphistory @database_name = N'HotelDB';
	ALTER DATABASE HotelDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
	DROP DATABASE HotelDB;
end

CREATE DATABASE HotelDB;
GO 


USE HotelDB
GO

CREATE TABLE dbo.Room (
	RoomId INT PRIMARY KEY,
	RoomType VARCHAR(10) NOT NULL,
	ADAAccessible BIT NOT NULL DEFAULT(0),
	StOccupancy INT NOT NULL,
	MaxOccupancy INT NOT NULL,
	BasePrice SMALLMONEY NOT NULL,
	ExtraGuest SMALLMONEY NULL
);

CREATE TABLE dbo.Guest (
	GuestId INT PRIMARY KEY IDENTITY(1, 1),
	FirstName VARCHAR(200) NOT NULL,
	LastName VARCHAR(200) NOT NULL,
	[Address] VARCHAR(200) NOT NULL,
	City VARCHAR(20) NOT NULL,
	[State] CHAR(2) NOT NULL,
	Zip CHAR(10) NOT NULL,
	Phone CHAR(20) NOT NULL
);

CREATE TABLE dbo.Amenity (
	AmenityId INT PRIMARY KEY IDENTITY(1, 1),
	AmenityName VARCHAR(50) NOT NULL,
);

CREATE TABLE dbo.RoomAmenity (
	RoomAmenityId INT NOT NULL,
		CONSTRAINT PK_RoomAmenityId PRIMARY KEY (RoomAmenityId),
	RoomId INT FOREIGN KEY REFERENCES Room(RoomID),
	AmenityId INT FOREIGN KEY REFERENCES Amenity(AmenityId)
);

CREATE TABLE dbo.Reservation (
	ReservationId INT PRIMARY KEY IDENTITY(1, 1),
	RoomId INT FOREIGN KEY REFERENCES Room(RoomId),
	GuestId INT FOREIGN KEY REFERENCES Guest(GuestId),
	Adults INT NOT NULL,
	Children INT NULL,
	StartDate DATE NOT NULL,
	EndDate DATE NOT NULL,
	TotalCost SMALLMONEY NOT NULL
);