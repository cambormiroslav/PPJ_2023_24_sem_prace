package org.app.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Fourteen_Days_Weather")
public class Fourteen_Days_Weather {
    @Id
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "name")
    private Town town;
    @Column(name = "main_description")
    private String main_description;
    @Column(name = "alongside_description")
    private String alongside_description;
    @Column(name = "icon")
    private String icon;
    @Column(name = "temperature_min")
    private double temperature_min;
    @Column(name = "temperature_max")
    private double temperature_max;
    @Column(name = "temperature_day")
    private double temperature_day;
    @Column(name = "temperature_night")
    private double temperature_night;
    @Column(name = "temperature_eve")
    private double temperature_eve;
    @Column(name = "temperature_morning")
    private double temperature_morning;
    @Column(name = "feel_like_temperature_day")
    private double feel_like_temperature_day;
    @Column(name = "feel_like_temperature_night")
    private double feel_like_temperature_night;
    @Column(name = "feel_like_temperature_eve")
    private double feel_like_temperature_eve;
    @Column(name = "feel_like_temperature_morning")
    private double feel_like_temperature_morning;
    @Column(name = "pressure")
    private int pressure;
    @Column(name = "humidity")
    private int humidity;
    @Column(name = "wind_speed")
    private double wind_speed;
    @Column(name = "wind_degrees")
    private int wind_degrees;
    @Column(name = "wind_gust")
    private double wind_gust;
    @Column(name = "clouds")
    private int clouds;
    @Column(name = "precipitation_of_rain")
    private int precipitation_of_rain;
    @Column(name = "rain")
    private double rain;
    @Column(name = "sunrise")
    private Date sunrise;
    @Column(name = "sunset")
    private Date sunset;
}
