CREATE TABLE Country (
    shortcut char(2)  NOT NULL,
    CONSTRAINT Country_pk PRIMARY KEY (shortcut)
);

CREATE TABLE Fourteen_Days_Weather (
    date datetime  NOT NULL,
    town_name nvarchar(20)  NOT NULL,
    main_description nvarchar(10)  NOT NULL,
    alongside_description nvarchar(20)  NOT NULL,
    icon nvarchar(10)  NOT NULL,
    temperature_min double(4,2)  NOT NULL,
    temperature_max double(4,2)  NOT NULL,
    temperature_day double(4,2)  NOT NULL,
    temperature_night double(4,2)  NOT NULL,
    temperature_eve double(4,2)  NOT NULL,
    temperature_morning double(4,2)  NOT NULL,
    feel_like_temperature_day double(4,2)  NOT NULL,
    feel_like_temperature_night double(4,2)  NOT NULL,
    feel_like_temperature_eve double(4,2)  NOT NULL,
    feel_like_temperature_morning double(4,2)  NOT NULL,
    pressure int  NULL,
    humidity int  NULL,
    wind_speed double(5,2)  NULL,
    wind_degrees int  NULL,
    wind_gust double(5,2)  NULL,
    clouds int  NULL,
    precipitation_of_rain double(3,2)  NULL,
    rain double(4,2)  NULL,
    sunrise datetime  NULL,
    sunset datetime  NULL,
    CONSTRAINT Fourteen_Days_Weather_pk PRIMARY KEY (date)
);

CREATE TABLE Town (
    name nvarchar(20)  NOT NULL,
    location nvarchar(10)  NOT NULL,
    lat double(9,7)  NOT NULL,
    lon double(9,7)  NOT NULL,
    state_shortcut char(2)  NOT NULL,
    CONSTRAINT Town_pk PRIMARY KEY (name)
);

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
    CONSTRAINT Weather_Current_pk PRIMARY KEY (date)
);

CREATE TABLE Weather_Hourly (
    date datetime  NOT NULL,
    town_name nvarchar(20)  NOT NULL,
    main_description nvarchar(10)  NOT NULL,
    alongside_description nvarchar(20)  NOT NULL,
    icon nvarchar(10)  NOT NULL,
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
    precipitation_of_rain double(3,2)  NULL,
    rain_volume_1h double(4,2)  NULL,
    CONSTRAINT Weather_Hourly_pk PRIMARY KEY (date)
);

ALTER TABLE Weather_Current ADD CONSTRAINT FK_Current_Weather FOREIGN KEY FK_Current_Weather (town_name)
    REFERENCES Town (name);

ALTER TABLE Fourteen_Days_Weather ADD CONSTRAINT FK_Fouteen_Day_Weather FOREIGN KEY FK_Fouteen_Day_Weather (town_name)
    REFERENCES Town (name);

ALTER TABLE Weather_Hourly ADD CONSTRAINT FK_Hourly_Weather FOREIGN KEY FK_Hourly_Weather (town_name)
    REFERENCES Town (name);

ALTER TABLE Town ADD CONSTRAINT FK_Town FOREIGN KEY FK_Town (state_shortcut)
    REFERENCES Country (shortcut);
