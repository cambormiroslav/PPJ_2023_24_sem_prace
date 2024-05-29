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

    public void setAll(Date date, Town town, String main_description, String alongside_description,
                       String icon, double temperature_min, double temperature_max, double temperature_day,
                       double temperature_night, double temperature_eve, double temperature_morning,
                       double feel_like_temperature_day, double feel_like_temperature_night,
                       double feel_like_temperature_eve, double feel_like_temperature_morning, int pressure,
                       int humidity, double wind_speed, int wind_degrees, double wind_gust, int clouds,
                       int precipitation_of_rain, double rain, Date sunrise, Date sunset){
        this.date = date;
        this.town = town;
        this.main_description = main_description;
        this.alongside_description = alongside_description;
        this.icon = icon;
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
        this.temperature_day = temperature_day;
        this.temperature_night = temperature_night;
        this.temperature_eve = temperature_eve;
        this.temperature_morning = temperature_morning;
        this.feel_like_temperature_day = feel_like_temperature_day;
        this.feel_like_temperature_night = feel_like_temperature_night;
        this.feel_like_temperature_eve = feel_like_temperature_eve;
        this.feel_like_temperature_morning = feel_like_temperature_morning;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.wind_degrees = wind_degrees;
        this.wind_gust = wind_gust;
        this.clouds = clouds;
        this.precipitation_of_rain = precipitation_of_rain;
        this.rain = rain;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}
