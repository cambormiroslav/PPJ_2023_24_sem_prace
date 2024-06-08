package org.app.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(CompositeKey.class)
@Table(name="Fourteen_Days_Weather")
public class Fourteen_Days_Weather {
    @Id
    @Column(name = "date")
    private Date date;
    @Id
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
    private double precipitation_of_rain;
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
                       double precipitation_of_rain, double rain, Date sunrise, Date sunset){
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

    public void setAllNotNull(Date date, Town town, String main_description, String alongside_description,
                       String icon, double temperature_min, double temperature_max, double temperature_day,
                       double temperature_night, double temperature_eve, double temperature_morning,
                       double feel_like_temperature_day, double feel_like_temperature_night,
                       double feel_like_temperature_eve, double feel_like_temperature_morning){
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
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    public void setTown(Town town){
        this.town = town;
    }

    public Town getTown(){
        return town;
    }

    public void setMain_description(String main_description){
        this.main_description = main_description;
    }

    public String getMain_description(){
        return main_description;
    }

    public void setAlongside_description(String alongside_description){
        this.alongside_description = alongside_description;
    }

    public String getAlongside_description(){
        return alongside_description;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return icon;
    }

    public void setTemperature_min(double temperature_min){
        this.temperature_min = temperature_min;
    }

    public double getTemperature_min(){
        return temperature_min;
    }

    public void setTemperature_max(double temperature_max){
        this.temperature_max = temperature_max;
    }

    public double getTemperature_max(){
        return temperature_max;
    }

    public void setTemperature_day(double temperature_day){
        this.temperature_day = temperature_day;
    }

    public double getTemperature_day(){
        return temperature_day;
    }

    public void setTemperature_night(double temperature_night){
        this.temperature_night = temperature_night;
    }

    public double getTemperature_night(){
        return temperature_night;
    }

    public void setTemperature_eve(double temperature_eve){
        this.temperature_eve = temperature_eve;
    }

    public double getTemperature_eve(){
        return temperature_eve;
    }

    public void setTemperature_morning(double temperature_morning){
        this.temperature_morning = temperature_morning;
    }

    public double getTemperature_morning(){
        return temperature_morning;
    }

    public void setFeel_like_temperature_day(double feel_like_temperature_day){
        this.feel_like_temperature_day = feel_like_temperature_day;
    }

    public double getFeel_like_temperature_day(){
        return feel_like_temperature_day;
    }

    public void setFeel_like_temperature_night(double feel_like_temperature_night){
        this.feel_like_temperature_night = feel_like_temperature_night;
    }

    public double getFeel_like_temperature_night(){
        return feel_like_temperature_night;
    }

    public void setFeel_like_temperature_eve(double feel_like_temperature_eve){
        this.feel_like_temperature_eve = feel_like_temperature_eve;
    }

    public double getFeel_like_temperature_eve(){
        return feel_like_temperature_eve;
    }

    public void setFeel_like_temperature_morning(double feel_like_temperature_morning){
        this.feel_like_temperature_morning = feel_like_temperature_morning;
    }

    public double getFeel_like_temperature_morning(){
        return feel_like_temperature_morning;
    }

    public void setPressure(int pressure){
        this.pressure = pressure;
    }

    public int getPressure(){
        return pressure;
    }

    public void setHumidity(int humidity){
        this.humidity = humidity;
    }

    public int getHumidity(){
        return humidity;
    }

    public void setWind_speed(double wind_speed){
        this.wind_speed = wind_speed;
    }

    public double getWind_speed(){
        return wind_speed;
    }

    public void setWind_degrees(int wind_degrees){
        this.wind_degrees = wind_degrees;
    }

    public int getWind_degrees(){
        return wind_degrees;
    }

    public void setWind_gust(double wind_gust){
        this.wind_gust = wind_gust;
    }

    public double getWind_gust(){
        return wind_gust;
    }

    public void setClouds(int clouds){
        this.clouds = clouds;
    }

    public int getClouds(){
        return clouds;
    }

    public void setPrecipitation_of_rain(double precipitation_of_rain){
        this.precipitation_of_rain = precipitation_of_rain;
    }

    public double getPrecipitation_of_rain(){
        return precipitation_of_rain;
    }

    public void setRain(double rain){
        this.rain = rain;
    }

    public double getRain(){
        return rain;
    }

    public void setSunrise(Date sunrise){
        this.sunrise = sunrise;
    }

    public Date getSunrise(){
        return sunrise;
    }

    public void setSunset(Date sunset){
        this.sunset = sunset;
    }

    public Date getSunset(){
        return sunset;
    }
}
