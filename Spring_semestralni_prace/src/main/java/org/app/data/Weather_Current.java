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
}
