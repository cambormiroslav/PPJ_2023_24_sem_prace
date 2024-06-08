package org.app.data;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@IdClass(CompositeKey.class)
@Table(name="Seven_Days_Weather")
public class Seven_Days_Weather {
    @Id
    @Column(name = "date")
    private Date date;
    @Id
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "town_name")
    private Town town;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "temperature_min")
    private double temperature_min;
    @Column(name = "temperature_max")
    private double temperature_max;
    @Column(name = "feel_like_temperature")
    private double feel_like_temperature;
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

    public void setAll(Date date, Town town, double temperature_min, double temperature_max, double temperature,
                       double feel_like_temperature, int pressure, int humidity, double wind_speed,
                       int wind_degrees, double wind_gust, int clouds){
        this.date = date;
        this.town = town;
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
        this.temperature = temperature;
        this.feel_like_temperature = feel_like_temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.wind_degrees = wind_degrees;
        this.wind_gust = wind_gust;
        this.clouds = clouds;
    }

    public void setAllNotNull(Date date, Town town, double temperature_min, double temperature_max,
                              double temperature, double feel_like_temperature){
        this.date = date;
        this.town = town;
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
        this.temperature = temperature;
        this.feel_like_temperature = feel_like_temperature;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTemperature_min() {
        return temperature_min;
    }

    public double getTemperature_max() {
        return temperature_max;
    }

    public double getFeel_like_temperature() {
        return feel_like_temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_degrees() {
        return wind_degrees;
    }

    public void setWind_degrees(int wind_degrees) {
        this.wind_degrees = wind_degrees;
    }

    public double getWind_gust() {
        return wind_gust;
    }

    public void setWind_gust(double wind_gust) {
        this.wind_gust = wind_gust;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    @Override
    public String toString(){
        return String.format("Seven Days Weather AVG: \n{\n\tdate : %s,\n\ttown name : %s,\n\ttemperature : %f" +
                        ",\n\tfeel like temperature : %f,\n\ttemperature min : %f,\n\ttemperature max : %f,\n\t" +
                        "pressure : %d,\n\thumidity : %d,\n\twind speed : %f,\n\twind degrees : %d,\n\twind gust :" +
                        " %f,\n\tclouds percentage : %d\n}",
                date.toString(), town.getName(), temperature, feel_like_temperature, temperature_min, temperature_max,
                pressure, humidity, wind_speed, wind_degrees, wind_gust, clouds);
    }
}
