package org.app.data_avg;

import java.util.Date;

public class DataAVGTransfer {
    private Date datetime;
    private double temperature;
    private double feel_like_temperature;
    private double min_temperature;
    private double max_temperature;
    private double wind_speed;
    private double wind_gust;
    private int pressure;
    private int humidity;
    private int clouds_percentage;
    private int wind_degrees;

    public void setAll(Date datetime, double temperature, double feel_like_temperature, double min_temperature, double max_temperature,
                  double wind_speed, double wind_gust, int pressure, int humidity, int clouds_percentage, int wind_degrees){
        this.datetime = datetime;
        this.temperature = temperature;
        this.feel_like_temperature = feel_like_temperature;
        this.min_temperature = min_temperature;
        this.max_temperature = max_temperature;
        this.wind_speed = wind_speed;
        this.wind_gust = wind_gust;
        this.pressure = pressure;
        this.humidity = humidity;
        this.clouds_percentage = clouds_percentage;
        this.wind_degrees = wind_degrees;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeel_like_temperature() {
        return feel_like_temperature;
    }

    public double getMin_temperature() {
        return min_temperature;
    }

    public double getMax_temperature() {
        return max_temperature;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_gust() {
        return wind_gust;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getClouds_percentage() {
        return clouds_percentage;
    }

    public int getWind_degrees() {
        return wind_degrees;
    }

    public Date getDatetime() {
        return datetime;
    }
}
