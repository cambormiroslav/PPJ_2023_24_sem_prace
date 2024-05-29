package org.app.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Weather_Hourly")
public class Weather_Hourly {
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
    @Column(name = "precipitation_of_rain")
    private int precipitation_of_rain;
    @Column(name = "rain_volume_1h")
    private double rain_volume_1h;

    public void setAll(Date date, Town town, String main_description, String alongside_description,
                       String icon, double temperature, double feel_like_temperature, double temperature_min,
                       double temperature_max, int pressure, int humidity, int sea_level, int ground_level,
                       int visibility, double wind_speed, int wind_degrees, double wind_gust, int clouds,
                       int precipitation_of_rain, double rain_volume_1h){

    }
}
