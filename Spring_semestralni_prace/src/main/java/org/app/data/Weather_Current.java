package org.app.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Weather_Current")
public class Weather_Current {
    @Id
    @Column(name = "date")
    private Date date;
    @OneToOne
    @JoinColumn(name = "name")
    private Town town;
    @Column(name = "main_description")
    private String main_description;
    @Column(name = "alongside_description")
    private String alongside_description;
    @Column(name = "icon")
    private String icon;
    @Column(name = "base")
    private String base;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "feel_like_temperature")
    private double feel_like_temperature;
    @Column(name = "temperature_min")
    private double temperature_min;
    @Column(name = "temperature_max")
    private double temperature_max;
    @Column(name = "pressure")
    private int pressure;
    @Column(name = "humidity")
    private int humidity;
    @Column(name = "sea_level")
    private int sea_level;
    @Column(name = "ground_level")
    private int ground_level;
    @Column(name = "visibility")
    private int visibility;
    @Column(name = "wind_speed")
    private double wind_speed;
    @Column(name = "wind_degrees")
    private int wind_degrees;
    @Column(name = "wind_gust")
    private double wind_gust;
    @Column(name = "clouds")
    private int clouds;
    @Column(name = "rain_volume_1h")
    private double rain_volume_1h;
    @Column(name = "sunrise")
    private Date sunrise;
    @Column(name = "sunset")
    private Date sunset;
    @Column(name = "timezone")
    private int timezone;
    public void setAll(Date date, Town town, String main_description, String alongside_description,
                       String icon, String base, double temperature, double feel_like_temperature,
                       double temperature_min, double temperature_max, int pressure, int humidity,
                       int sea_level, int ground_level, int visibility, double wind_speed,
                       int wind_degrees, double wind_gust, int clouds, double rain_volume_1h,
                       Date sunrise, Date sunset, int timezone){
        this.date = date;
        this.town = town;
        this.main_description = main_description;
        this.alongside_description = alongside_description;
        this.icon = icon;
        this.base = base;
        this.temperature = temperature;
        this.feel_like_temperature = feel_like_temperature;
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
        this.pressure = pressure;
        this.humidity = humidity;
        this.sea_level = sea_level;
        this.ground_level = ground_level;
        this.visibility = visibility;
        this.wind_speed = wind_speed;
        this.wind_degrees = wind_degrees;
        this.wind_gust = wind_gust;
        this.clouds = clouds;
        this.rain_volume_1h = rain_volume_1h;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.timezone = timezone;
    }
}
