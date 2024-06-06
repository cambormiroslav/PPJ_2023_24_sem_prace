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
    @JoinColumn(name = "town_name")
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

    public void setAll(Date date, Town town, String main_description, String alongside_description,
                       String icon, String base, double temperature, double feel_like_temperature,
                       double temperature_min, double temperature_max, int pressure, int humidity,
                       int sea_level, int ground_level, int visibility, double wind_speed,
                       int wind_degrees, double wind_gust, int clouds, double rain_volume_1h,
                       Date sunrise, Date sunset){
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
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Town getTown() {
        return town;
    }

    public void setMain_description(String main_description) {
        this.main_description = main_description;
    }

    public String getMain_description() {
        return main_description;
    }

    public void setAlongside_description(String alongside_description) {
        this.alongside_description = alongside_description;
    }

    public String getAlongside_description() {
        return alongside_description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setFeel_like_temperature(double feel_like_temperature) {
        this.feel_like_temperature = feel_like_temperature;
    }

    public double getFeel_like_temperature() {
        return feel_like_temperature;
    }

    public void setTemperature_min(double temperature_min) {
        this.temperature_min = temperature_min;
    }

    public double getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_max(double temperature_max) {
        this.temperature_max = temperature_max;
    }

    public double getTemperature_max() {
        return temperature_max;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getPressure() {
        return pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setSea_level(int sea_level) {
        this.sea_level = sea_level;
    }

    public int getSea_level() {
        return sea_level;
    }

    public void setGround_level(int ground_level) {
        this.ground_level = ground_level;
    }

    public int getGround_level() {
        return ground_level;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_degrees(int wind_degrees) {
        this.wind_degrees = wind_degrees;
    }

    public int getWind_degrees() {
        return wind_degrees;
    }

    public void setWind_gust(double wind_gust) {
        this.wind_gust = wind_gust;
    }

    public double getWind_gust() {
        return wind_gust;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getClouds() {
        return clouds;
    }

    public void setRain_volume_1h(double rain_volume_1h) {
        this.rain_volume_1h = rain_volume_1h;
    }

    public double getRain_volume_1h() {
        return rain_volume_1h;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public Date getSunset() {
        return sunset;
    }
}
