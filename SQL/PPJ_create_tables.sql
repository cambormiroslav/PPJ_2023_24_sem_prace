-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-06-05 13:18:49.896

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
    main_description nvarchar(10)  NOT NULL,
    alongside_description nvarchar(20)  NOT NULL,
    icon nvarchar(10)  NOT NULL,
    temperature_min double(2,2)  NOT NULL,
    temperature_max double(2,2)  NOT NULL,
    temperature_day double(2,2)  NOT NULL,
    temperature_night double(2,2)  NOT NULL,
    temperature_eve double(2,2)  NOT NULL,
    temperature_morning double(2,2)  NOT NULL,
    feel_like_temperature_day double(2,2)  NOT NULL,
    feel_like_temperature_night double(2,2)  NOT NULL,
    feel_like_temperature_eve double(2,2)  NOT NULL,
    feel_like_temperature_morning double(2,2)  NOT NULL,
    pressure int  NULL,
    humidity int  NULL,
    wind_speed double(3,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(3,2)  NULL,
    clouds int  NULL,
    precipitation_of_rain int  NULL,
    rain double(2,2)  NULL,
    sunrise datetime  NULL,
    sunset datetime  NULL,
    CONSTRAINT Fourteen_Days_Weather_pk PRIMARY KEY (date)
);

-- Table: Town
CREATE TABLE Town (
    name nvarchar(20)  NOT NULL,
    location nvarchar(10)  NOT NULL,
    lat double(7,7)  NOT NULL,
    lon double(7,7)  NOT NULL,
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
    temperature double(2,2)  NOT NULL,
    feel_like_temperature double(2,2)  NOT NULL,
    temperature_min double(2,2)  NOT NULL,
    temperature_max double(2,2)  NOT NULL,
    pressure int  NULL,
    humidity int  NULL,
    sea_level int  NULL,
    ground_level int  NULL,
    visibility int  NULL,
    wind_speed double(3,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(3,2)  NULL,
    clouds int  NULL,
    rain_volume_1h double(2,2)  NULL,
    sunrise datetime  NULL,
    sunset datetime  NULL,
    CONSTRAINT Weather_Current_pk PRIMARY KEY (date)
);

-- Table: Weather_Hourly
CREATE TABLE Weather_Hourly (
    date datetime  NOT NULL,
    town_name nvarchar(20)  NOT NULL,
    main_description nvarchar(10)  NOT NULL,
    alongside_description nvarchar(20)  NOT NULL,
    icon nvarchar(10)  NOT NULL,
    temperature double(2,2)  NOT NULL,
    feel_like_temperature double(2,2)  NOT NULL,
    temperature_min double(2,2)  NOT NULL,
    temperature_max double(2,2)  NOT NULL,
    pressure double(2,2)  NULL,
    humidity int  NULL,
    sea_level int  NULL,
    ground_level int  NULL,
    visibility int  NULL,
    wind_speed double(3,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(3,2)  NULL,
    clouds int  NULL,
    precipitation_of_rain int  NULL,
    rain_volume_1h double(2,2)  NULL,
    CONSTRAINT Weather_Hourly_pk PRIMARY KEY (date)
);

-- foreign keys
-- Reference: FK_Current_Weather (table: Weather_Current)
ALTER TABLE Weather_Current ADD CONSTRAINT FK_Current_Weather FOREIGN KEY FK_Current_Weather (town_name)
    REFERENCES Town (name);

-- Reference: FK_Fouteen_Day_Weather (table: Fourteen_Days_Weather)
ALTER TABLE Fourteen_Days_Weather ADD CONSTRAINT FK_Fouteen_Day_Weather FOREIGN KEY FK_Fouteen_Day_Weather (town_name)
    REFERENCES Town (name);

-- Reference: FK_Hourly_Weather (table: Weather_Hourly)
ALTER TABLE Weather_Hourly ADD CONSTRAINT FK_Hourly_Weather FOREIGN KEY FK_Hourly_Weather (town_name)
    REFERENCES Town (name);

-- Reference: FK_Town (table: Town)
ALTER TABLE Town ADD CONSTRAINT FK_Town FOREIGN KEY FK_Town (state_shortcut)
    REFERENCES Country (shortcut);

-- End of file.

