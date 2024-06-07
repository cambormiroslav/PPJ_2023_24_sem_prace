package org.app;

import jakarta.validation.constraints.Null;
import org.app.data.*;
import org.app.data.Weather_Current;
import org.app.data_loader.DataLoader;
import org.app.repositories.CountryRepository;
import org.app.service.*;
import org.decimal4j.util.DoubleRounder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
//@EnableAutoConfiguration
@EnableJpaRepositories("org.app.repositories")
public class Main {
    private static DataLoader data_loader = new DataLoader();

    /*@Bean
    public Weather_CurrentService weatherCurrentService(){
        return new Weather_CurrentService();
    }*/

    /*@Bean
    public Weather_HourlyService weatherHourlyService(){
        return new Weather_HourlyService();
    }*/

    /*@Bean
    public Fourteen_Days_WeatherService fourteenDaysWeatherService(){
        return new Fourteen_Days_WeatherService();
    }*/

    @Bean
    public CountryService countryService() {
        return new CountryService();
    }

    @Bean
    public TownService townService(){
        return new TownService();
    }

    @Bean
    public Country country(){
        return new Country();
    }

    @Bean
    public Town town(){
        return new Town();
    }

    @Bean
    public Weather_Current weather_current(){
        return new Weather_Current();
    }

    @Bean
    Weather_Hourly weather_hourly(){
        return new Weather_Hourly();
    }

    @Bean
    Fourteen_Days_Weather fourteen_days_weather(){
        return new Fourteen_Days_Weather();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        downloadCurrentWeather(ctx, "Liberec", "CZ");
        //downloadHourlyWeather(ctx, "Liberec", "CZ");
    }

    private static Town downloadTownAndCountry(ApplicationContext ctx, String town, String country){
        CountryService country_service = ctx.getBean(CountryService.class);
        TownService town_service = ctx.getBean(TownService.class);
        try{
            JSONObject json_obj_lat_lon = data_loader.load_town_and_country(town, country);

            String country_get = json_obj_lat_lon.get("country").toString();
            String town_get = json_obj_lat_lon.get("name").toString();
            String location = json_obj_lat_lon.get("state").toString();
            double lon = json_obj_lat_lon.getDouble("lon");
            double lat = json_obj_lat_lon.getDouble("lat");

            Country country_class = ctx.getBean(Country.class);
            country_class.setShortcut(country_get);
            Town town_class = ctx.getBean(Town.class);
            town_class.setAll(town_get, location, lat, lon, country_class);

            country_service.createOrUpdate(country_class);
            town_service.createOrUpdate(town_class);

            return town_class;
        }catch (IOException e){
            System.out.println("Weather Current can not be downloaded.");
        }
        return null;
    }

    private static void downloadCurrentWeather(ApplicationContext ctx, String town, String country){
        Weather_CurrentService weather_current_service = ctx.getBean(Weather_CurrentService.class);
        TownService town_service = ctx.getBean(TownService.class);
        Town town_class;

        try{
            if(town_service.exists(town))
                town_class = town_service.getTownById(town);
            else
                town_class = downloadTownAndCountry(ctx, town, country);

            List<Weather_Current> weather_current_list = weather_current_service.getWeathersCurrent();
            for(Weather_Current weather : weather_current_list){
                if(weather.getTown().getName().equals(town)) {
                    System.out.println(weather.getTown().getName());
                    weather_current_service.delete(weather);
                }
            }

            double lon = town_class.getLon();
            double lat = town_class.getLat();

            JSONObject json_current_wheather = data_loader.load_current_weather(town, lat, lon);

            Date datetime = data_loader.get_datetime(json_current_wheather, "dt");

            //town

            JSONArray weather_json_array = json_current_wheather.getJSONArray("weather");
            JSONObject weather_json = weather_json_array.getJSONObject(0);
            String main_description = data_loader.get_string_part(weather_json, "main");
            String alongside_description = data_loader.get_string_part(weather_json, "description");
            String icon = data_loader.get_string_part(weather_json, "icon");

            String base = data_loader.get_string_part(json_current_wheather, "base");

            JSONObject main = json_current_wheather.getJSONObject("main");
            double temperature = data_loader.get_double_part(main, "temp");
            double feel_like_temperature = data_loader.get_double_part(main, "feels_like");
            double min_temperature = data_loader.get_double_part(main, "temp_min");
            double max_temperature = data_loader.get_double_part(main, "temp_max");
            int pressure = data_loader.get_int_part(main, "pressure");
            int humidity = data_loader.get_int_part(main, "humidity");
            int sea_level = data_loader.get_int_part(main, "sea_level");
            int ground_level = data_loader.get_int_part(main, "grnd_level");

            int visibility = data_loader.get_int_part(json_current_wheather, "visibility");

            JSONObject wind_json = json_current_wheather.getJSONObject("wind");
            double wind_speed = data_loader.get_double_part(wind_json, "speed");
            int wind_degrees = data_loader.get_int_part(wind_json, "deg");
            double wind_gust = data_loader.get_double_part(wind_json, "gust");

            int clouds_percentage = data_loader.get_clouds_percentage(json_current_wheather);
            double rain_volume = data_loader.get_rain_volume(json_current_wheather);

            JSONObject sys_json = json_current_wheather.getJSONObject("sys");
            Date sunrise = data_loader.get_datetime(sys_json, "sunrise");
            Date sunset = data_loader.get_datetime(sys_json, "sunset");

            //weather_current_service.delete();
            Weather_Current weather_current_class = ctx.getBean(Weather_Current.class);

            weather_current_class.setAllNotNull(datetime, town_class, main_description, alongside_description,
                    icon, base, temperature, feel_like_temperature, min_temperature, max_temperature);

            if(pressure != -20000)
                weather_current_class.setPressure(pressure);
            if(humidity != -20000)
                weather_current_class.setHumidity(humidity);
            if(sea_level != -20000)
                weather_current_class.setSea_level(sea_level);
            if(ground_level != -20000)
                weather_current_class.setGround_level(ground_level);
            if(visibility != -20000)
                weather_current_class.setVisibility(visibility);
            if(wind_speed != -20000.0)
                weather_current_class.setWind_speed(wind_speed);
            if(wind_degrees != -20000)
                weather_current_class.setWind_degrees(wind_degrees);
            if(wind_gust != -20000.0)
                weather_current_class.setWind_gust(wind_gust);
            if(clouds_percentage != -20000)
                weather_current_class.setClouds(clouds_percentage);
            if(rain_volume != -20000.0)
                weather_current_class.setRain_volume_1h(rain_volume);
            weather_current_class.setSunrise(sunrise);
            weather_current_class.setSunset(sunset);

            weather_current_service.createOrUpdate(weather_current_class);
        }catch (IOException e){
            System.out.println("Weather Current can not be downloaded.");
        }
    }

    private static void downloadHourlyWeather(ApplicationContext ctx, String town, String country){
        TownService town_service = ctx.getBean(TownService.class);
        Weather_HourlyService weather_hourly_service = ctx.getBean(Weather_HourlyService.class);
        Town town_class;

        try{
            List<Weather_Hourly > weather_hourly_list = weather_hourly_service.getWeathersHourly();
            for(Weather_Hourly weather : weather_hourly_list) {
                if(weather.getTown().getName().equals(town))
                    weather_hourly_service.delete(weather);
            }
            if(town_service.exists(town))
                town_class = town_service.getTownById(town);
            else
                town_class = downloadTownAndCountry(ctx, town, country);

            double lon = town_class.getLon();
            double lat = town_class.getLat();

            JSONArray json_array_hourly = data_loader.load_hourly_weather(lat, lon);

            for (Object hour_weather : json_array_hourly){
                JSONObject json_object_hourly = (JSONObject) hour_weather;

                Date datetime = data_loader.get_datetime(json_object_hourly, "dt");

                //town

                JSONArray weather_hourly_json_array = json_object_hourly.getJSONArray("weather");
                JSONObject weather_hourly_json = weather_hourly_json_array.getJSONObject(0);
                String main_description = data_loader.get_string_part(weather_hourly_json, "main");
                String alongside_description = data_loader.get_string_part(weather_hourly_json, "description");
                String icon = data_loader.get_string_part(weather_hourly_json, "icon");

                JSONObject main_hourly = json_object_hourly.getJSONObject("main");
                double temperature = data_loader.get_double_part(main_hourly, "temp");
                double feel_like_temperature = data_loader.get_double_part(main_hourly, "feels_like");
                double min_temperature = data_loader.get_double_part(main_hourly, "temp_min");
                double max_temperature = data_loader.get_double_part(main_hourly, "temp_max");
                int pressure = data_loader.get_int_part(main_hourly, "pressure");
                int humidity = data_loader.get_int_part(main_hourly, "humidity");
                int sea_level = data_loader.get_int_part(main_hourly, "sea_level");
                int ground_level = data_loader.get_int_part(main_hourly, "grnd_level");

                int visibility = data_loader.get_int_part(json_object_hourly, "visibility");

                JSONObject wind_json_hourly = json_object_hourly.getJSONObject("wind");
                double wind_speed = data_loader.get_double_part(wind_json_hourly, "speed");
                int wind_degrees = data_loader.get_int_part(wind_json_hourly, "deg");
                double wind_gust = data_loader.get_double_part(wind_json_hourly, "gust");

                int clouds_percentage = data_loader.get_clouds_percentage(json_object_hourly);
                double precipitation_of_rain = data_loader.get_double_part(json_object_hourly, "pop");
                double rain_volume = data_loader.get_rain_volume(json_object_hourly);

                Weather_Hourly weather_hourly_class = ctx.getBean(Weather_Hourly.class);

                weather_hourly_class.setAllNotNull(datetime, town_class, main_description, alongside_description,
                        icon, temperature, feel_like_temperature, min_temperature, max_temperature);

                if(pressure != -20000)
                    weather_hourly_class.setPressure(pressure);
                if(humidity != -20000)
                    weather_hourly_class.setHumidity(humidity);
                if(sea_level != -20000)
                    weather_hourly_class.setSea_level(sea_level);
                if(ground_level != -20000)
                    weather_hourly_class.setGround_level(ground_level);
                if(visibility != -20000)
                    weather_hourly_class.setVisibility(visibility);
                if(wind_speed != -20000.0)
                    weather_hourly_class.setWind_speed(wind_speed);
                if(wind_degrees != -20000)
                    weather_hourly_class.setWind_degrees(wind_degrees);
                if(wind_gust != -20000.0)
                    weather_hourly_class.setWind_gust(wind_gust);
                if(clouds_percentage != -20000)
                    weather_hourly_class.setClouds(clouds_percentage);
                if(precipitation_of_rain != -20000.0)
                    weather_hourly_class.setPrecipitation_of_rain(precipitation_of_rain);
                if(rain_volume != -20000.0)
                    weather_hourly_class.setRain_volume_1h(rain_volume);
                weather_hourly_service.createOrUpdate(weather_hourly_class);
            }
        }
        catch (IOException e){
            System.out.println("Weather Current can not be downloaded.");
        }
    }
}