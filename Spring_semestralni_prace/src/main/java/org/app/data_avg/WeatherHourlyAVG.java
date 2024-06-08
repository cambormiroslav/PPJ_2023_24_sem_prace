package org.app.data_avg;

import org.app.data.Weather_Hourly;
import org.app.service.Weather_HourlyService;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.List;

public class WeatherHourlyAVG {
    private Date date;
    private String town_name;
    private double temperature;
    private double feel_like_temperature;
    private double temperature_min;
    private double temperature_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int ground_level;
    private int visibility;
    private double wind_speed;
    private int wind_degrees;
    private double wind_gust;
    private int clouds;
    private double precipitation_of_rain;
    private double rain_volume_1h;


    public void setAll(ApplicationContext ctx, String town){
        Weather_HourlyService weather_hourly_service = ctx.getBean(Weather_HourlyService.class);
        List<Weather_Hourly> weather_hourly_list = weather_hourly_service.getWeatherCurrentForTown(town);
        double temperature_tmp = 0.0, feel_like_temperature_tmp = 0.0, temperature_min_tmp = 0.0,
                temperature_max_tmp = 0.0, wind_speed_tmp = 0.0, wind_gust_tmp = 0.0,
                precipitation_of_rain_tmp = 0.0, rain_volume_1h_tmp = 0.0;
        int pressure_tmp = 0, humidity_tmp = 0, sea_level_tmp = 0, ground_level_tmp = 0, visibility_tmp = 0,
                wind_degrees_tmp = 0, clouds_tmp = 0;
        this.date = weather_hourly_list.getLast().getDate();
        this.town_name = weather_hourly_list.getLast().getTown().getName();
        int list_size = weather_hourly_list.size();
        for(Weather_Hourly weather : weather_hourly_list){
            temperature_tmp += weather.getTemperature();
            feel_like_temperature_tmp += weather.getFeel_like_temperature();
            temperature_min_tmp += weather.getTemperature_min();
            temperature_max_tmp += weather.getTemperature_max();
            wind_speed_tmp += weather.getWind_speed();
            wind_gust_tmp += weather.getWind_gust();
            precipitation_of_rain_tmp += weather.getPrecipitation_of_rain();
            rain_volume_1h_tmp += weather.getRain_volume_1h();
            pressure_tmp += weather.getPressure();
            humidity_tmp += weather.getHumidity();
            sea_level_tmp += weather.getSea_level();
            ground_level_tmp += weather.getGround_level();
            visibility_tmp += weather.getVisibility();
            wind_degrees_tmp += weather.getWind_degrees();
            clouds_tmp += weather.getClouds();
        }
        this.temperature = temperature_tmp / list_size;
        this.feel_like_temperature = feel_like_temperature_tmp / list_size;
        this.temperature_min = temperature_min_tmp / list_size;
        this.temperature_max = temperature_max_tmp / list_size;
        this.wind_speed = wind_speed_tmp / list_size;
        this.wind_gust = wind_gust_tmp / list_size;
        this.precipitation_of_rain = precipitation_of_rain_tmp / list_size;
        this.rain_volume_1h = rain_volume_1h_tmp / list_size;
        this.pressure = pressure_tmp / list_size;
        this.humidity = humidity_tmp / list_size;
        this.sea_level = sea_level_tmp / list_size;
        this.ground_level = ground_level_tmp / list_size;
        this.visibility = visibility_tmp / list_size;
        this.wind_degrees = wind_degrees_tmp / list_size;
        this.clouds = clouds_tmp / list_size;
    }

    @Override
    public String toString(){
        return String.format("Weather Hourly AVG: \n{\n\tdate (last measurement): %s,\n\ttown name : %s,\n\ttemperature :" +
                        " %f,\n\tfeel like temperature : %f,\n\ttemperature min : %f,\n\ttemperature max : %f,\n\t" +
                        "pressure : %d,\n\thumidity : %d,\n\tsea level : %d,\n\tground level : %d,\n\tvisibility : %d," +
                        "\n\twind speed : %f,\n\twind degrees : %d,\n\twind gust : %f,\n\tclouds percentage : %d,\n\t" +
                        "precipitation of rain : %f,\n\train volume : %f\n}",
                date.toString(), town_name, temperature,
                feel_like_temperature, temperature_min, temperature_max, pressure, humidity, sea_level, ground_level,
                visibility, wind_speed, wind_degrees, wind_gust, clouds, precipitation_of_rain, rain_volume_1h);
    }
}
