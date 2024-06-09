-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-06-08 22:01:54.656

-- tables
-- Table: Country
CREATE TABLE Country (
    shortcut char(2)  NOT NULL,
    CONSTRAINT Country_pk PRIMARY KEY (shortcut)
);

-- Table: Fourteen_Days_Weather
CREATE TABLE Fourteen_Days_Weather (
    date datetime  NOT NULL,
    town_name nvarchar(20)  NOT NULL,
    temperature double(4,2)  NOT NULL,
    temperature_min double(4,2)  NOT NULL,
    temperature_max double(4,2)  NOT NULL,
    feel_like_temperature double(4,2)  NOT NULL,
    pressure int  NULL,
    humidity int  NULL,
    wind_speed double(5,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(5,2)  NULL,
    clouds int  NULL,
    CONSTRAINT Fourteen_Days_Weather_pk PRIMARY KEY (date,town_name)
);

-- Table: Seven_Days_Weather
CREATE TABLE Seven_Days_Weather (
    date datetime  NOT NULL,
    town_name nvarchar(20)  NOT NULL,
    temperature double(4,2)  NOT NULL,
    temperature_min double(4,2)  NOT NULL,
    temperature_max double(4,2)  NOT NULL,
    feel_like_temperature double(4,2)  NOT NULL,
    pressure int  NULL,
    humidity int  NULL,
    wind_speed double(5,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(5,2)  NULL,
    clouds int  NULL,
    CONSTRAINT Seven_Days_Weather_pk PRIMARY KEY (date)
);

-- Table: Town
CREATE TABLE Town (
    name nvarchar(20)  NOT NULL,
    location nvarchar(10)  NOT NULL,
    lat double(9,7)  NOT NULL,
    lon double(9,7)  NOT NULL,
    state_shortcut char(2)  NOT NULL,
    CONSTRAINT Town_pk PRIMARY KEY (name)
);

-- Table: Weather_Current
CREATE TABLE Weather_Current (
    date datetime  NOT NULL,
    town_name nvarchar(20)  NOT NULL,
    main_description nvarchar(10)  NOT NULL,
    alongside_description nvarchar(20)  NOT NULL,
    icon nvarchar(10)  NOT NULL,
    base nvarchar(10)  NOT NULL,
    temperature double(4,2)  NOT NULL,
    feel_like_temperature double(4,2)  NOT NULL,
    temperature_min double(4,2)  NOT NULL,
    temperature_max double(4,2)  NOT NULL,
    pressure int  NULL,
    humidity int  NULL,
    sea_level int  NULL,
    ground_level int  NULL,
    visibility int  NULL,
    wind_speed double(5,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(5,2)  NULL,
    clouds int  NULL,
    rain_volume_1h double(4,2)  NULL,
    sunrise datetime  NULL,
    sunset datetime  NULL,
    CONSTRAINT Weather_Current_pk PRIMARY KEY (date,town_name)
);

-- Table: Weather_Day
CREATE TABLE Weather_Day (
    date datetime  NOT NULL,
    town_name nvarchar(20)  NOT NULL,
    temperature double(4,2)  NOT NULL,
    feel_like_temperature double(4,2)  NOT NULL,
    temperature_min double(4,2)  NOT NULL,
    temperature_max double(4,2)  NOT NULL,
    pressure int  NULL,
    humidity int  NULL,
    wind_speed double(5,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(5,2)  NULL,
    clouds int  NULL,
    CONSTRAINT Weather_Day_pk PRIMARY KEY (date,town_name)
);

-- foreign keys
-- Reference: Copy_of_Fourteen_Days_Weather_Town (table: Seven_Days_Weather)
ALTER TABLE Seven_Days_Weather ADD CONSTRAINT Copy_of_Fourteen_Days_Weather_Town FOREIGN KEY Copy_of_Fourteen_Days_Weather_Town (town_name)
    REFERENCES Town (name)
    ON DELETE CASCADE;

-- Reference: FK_Current_Weather (table: Weather_Current)
ALTER TABLE Weather_Current ADD CONSTRAINT FK_Current_Weather FOREIGN KEY FK_Current_Weather (town_name)
    REFERENCES Town (name)
    ON DELETE CASCADE;

-- Reference: FK_Fouteen_Day_Weather (table: Fourteen_Days_Weather)
ALTER TABLE Fourteen_Days_Weather ADD CONSTRAINT FK_Fouteen_Day_Weather FOREIGN KEY FK_Fouteen_Day_Weather (town_name)
    REFERENCES Town (name)
    ON DELETE CASCADE;

-- Reference: FK_Hourly_Weather (table: Weather_Day)
ALTER TABLE Weather_Day ADD CONSTRAINT FK_Hourly_Weather FOREIGN KEY FK_Hourly_Weather (town_name)
    REFERENCES Town (name)
    ON DELETE CASCADE;

-- Reference: FK_Town (table: Town)
ALTER TABLE Town ADD CONSTRAINT FK_Town FOREIGN KEY FK_Town (state_shortcut)
    REFERENCES Country (shortcut)
    ON DELETE CASCADE;

-- End of file.

