package org.app;

import org.app.data.*;
import org.app.data.Weather_Current;
import org.app.data_avg.DataAVGTransfer;
import org.app.data_loader.DataLoader;
import org.app.service.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
@EnableJpaRepositories("org.app.repositories")
public class Main {
    private static DataLoader data_loader = new DataLoader();

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
    Weather_Day weather_hourly(){
        return new Weather_Day();
    }

    @Bean
    Fourteen_Days_Weather fourteen_days_weather(){
        return new Fourteen_Days_Weather();
    }

    @Bean
    Seven_Days_Weather seven_days_weather(){
        return new Seven_Days_Weather();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        downloadCurrentWeather(ctx, "Bílina", "CZ");
        //downloadDayWeather(ctx, "Bílina", "CZ");
        //downloadFourteenDaysWeather(ctx, "Bílina", "CZ");
        //downloadSevenDaysWeather(ctx, "Bílina", "CZ");

        //getWeatherCurrentLast(ctx, "Liberec");
        //getWeatherCurrentNow(ctx, "Bílina","CZ");
        //getWeatherDayLastAVG(ctx, "Liberec");
        //getWeatherDayNowAVG(ctx, "Liberec", "CZ");
        //getSevenDaysWeatherLastAVG(ctx, "Liberec");
        //getSevenDaysWeatherNowAVG(ctx, "Liberec", "CZ");
        //getFourteenDaysWeatherLastAVG(ctx, "Liberec");
        //getFourteenDaysWeatherNowAVG(ctx, "Liberec", "CZ");

        //deleteWeatherCurrent(ctx, "Liberec");
        //deleteWeatherDay(ctx, "Liberec");
        //deleteSevenDaysWeather(ctx, "Liberec");
        //deleteFourteenDaysWeather(ctx, "Liberec");
        //deleteTown(ctx, "Bílina");
        //deleteCountry(ctx, "CZ");
    }

    private static void getWeatherCurrentLast(ApplicationContext ctx, String town){
        Weather_CurrentService weather_current_service = ctx.getBean(Weather_CurrentService.class);
        List<Weather_Current> weather_current_list = weather_current_service.getWeatherCurrentForTown(town);
        for(Weather_Current weather : weather_current_list)
            System.out.println(weather);
    }

    private static void getWeatherCurrentNow(ApplicationContext ctx, String town, String country){
        downloadCurrentWeather(ctx, town, country);
        getWeatherCurrentLast(ctx, town);
    }

    private static void getWeatherDayLastAVG(ApplicationContext ctx, String town){
        Weather_DayService weather_day_service = ctx.getBean(Weather_DayService.class);
        List<Weather_Day> weather_day_list = weather_day_service.getWeatherForTown(town);
        for(Weather_Day weather: weather_day_list)
            System.out.println(weather);
    }

    private static void getWeatherDayNowAVG(ApplicationContext ctx, String town, String country){
        downloadDayWeather(ctx, town, country);
        getWeatherDayLastAVG(ctx, town);
    }

    private static void getSevenDaysWeatherLastAVG(ApplicationContext ctx, String town){
        Seven_Days_WeatherService seven_days_weather_service = ctx.getBean(Seven_Days_WeatherService.class);
        List<Seven_Days_Weather> seven_days_weather_list = seven_days_weather_service.getWeatherForTown(town);
        for(Seven_Days_Weather weather: seven_days_weather_list)
            System.out.println(weather);
    }

    private static void getSevenDaysWeatherNowAVG(ApplicationContext ctx, String town, String country){
        downloadSevenDaysWeather(ctx, town, country);
        getSevenDaysWeatherLastAVG(ctx, town);
    }

    private static void getFourteenDaysWeatherLastAVG(ApplicationContext ctx, String town){
        Fourteen_Days_WeatherService fourteen_days_weather_service = ctx.getBean(Fourteen_Days_WeatherService.class);
        List<Fourteen_Days_Weather> fourteen_days_weather_list = fourteen_days_weather_service.getWeatherForTown(town);
        for(Fourteen_Days_Weather weather : fourteen_days_weather_list)
            System.out.println(weather);
    }

    private static void getFourteenDaysWeatherNowAVG(ApplicationContext ctx, String town, String country){
        downloadFourteenDaysWeather(ctx, town, country);
        getFourteenDaysWeatherLastAVG(ctx, town);
    }

    private static void deleteCountry(ApplicationContext ctx, String shortcut){
        CountryService country_service = ctx.getBean(CountryService.class);
        List<Country> country_list = country_service.getCountryByShortcut(shortcut);
        for (Country country : country_list)
            country_service.delete(country);
    }

    private static void deleteTown(ApplicationContext ctx, String town_name){
        TownService town_service = ctx.getBean(TownService.class);
        List<Town> town_list = town_service.findByTownName(town_name);
        for(Town town : town_list)
            town_service.delete(town);
    }

    private static void deleteWeatherCurrent(ApplicationContext ctx, String town_name){
        Weather_CurrentService weather_current_service = ctx.getBean(Weather_CurrentService.class);
        List<Weather_Current> weather_current_list = weather_current_service.getWeatherCurrentForTown(town_name);
        for(Weather_Current weather : weather_current_list)
            weather_current_service.delete(weather);
    }

    private static void deleteWeatherDay(ApplicationContext ctx, String town_name){
        Weather_DayService weather_day_service = ctx.getBean(Weather_DayService.class);
        List<Weather_Day> weather_day_list = weather_day_service.getWeatherForTown(town_name);
        for(Weather_Day weather : weather_day_list)
            weather_day_service.delete(weather);
    }

    private static void deleteSevenDaysWeather(ApplicationContext ctx, String town_name){
        Seven_Days_WeatherService seven_days_weather_service = ctx.getBean(Seven_Days_WeatherService.class);
        List<Seven_Days_Weather> seven_days_weather_list = seven_days_weather_service.getWeatherForTown(town_name);
        for(Seven_Days_Weather weather : seven_days_weather_list)
            seven_days_weather_service.delete(weather);
    }

    private static void deleteFourteenDaysWeather(ApplicationContext ctx, String town){
        Fourteen_Days_WeatherService fourteen_days_weather_service = ctx.getBean(Fourteen_Days_WeatherService.class);
        List<Fourteen_Days_Weather> fourteen_days_weather_list = fourteen_days_weather_service.getWeatherForTown(town);
        for(Fourteen_Days_Weather weather : fourteen_days_weather_list)
            fourteen_days_weather_service.delete(weather);
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

    private static void downloadDayWeather(ApplicationContext ctx, String town, String country){
        TownService town_service = ctx.getBean(TownService.class);
        Weather_DayService weather_day_service = ctx.getBean(Weather_DayService.class);
        Town town_class;

        if(town_service.exists(town))
            town_class = town_service.getTownById(town);
        else
            town_class = downloadTownAndCountry(ctx, town, country);

        double lon = town_class.getLon();
        double lat = town_class.getLat();

        List<Weather_Day> fourteen_days_weather_list = weather_day_service.getAllDayWeathers();
        for(Weather_Day weather : fourteen_days_weather_list){
            if(weather.getTown().getName().equals(town))
                weather_day_service.delete(weather);
        }

        DataAVGTransfer data_transfer = getAVGData(lat, lon, 1);
        Weather_Day weather_day_class = ctx.getBean(Weather_Day.class);
        weather_day_class.setAll(data_transfer.getDatetime(), town_class, data_transfer.getMin_temperature(),
                data_transfer.getMax_temperature(), data_transfer.getTemperature(), data_transfer.getFeel_like_temperature(),
                data_transfer.getPressure(), data_transfer.getHumidity(), data_transfer.getWind_speed(),
                data_transfer.getWind_degrees(), data_transfer.getWind_gust(), data_transfer.getClouds_percentage());
        weather_day_service.createOrUpdate(weather_day_class);

    }

    private static void downloadFourteenDaysWeather(ApplicationContext ctx, String town, String country){
        TownService town_service = ctx.getBean(TownService.class);
        Fourteen_Days_WeatherService fourteen_days_weather_service = ctx.getBean(Fourteen_Days_WeatherService.class);
        Town town_class;

        if(town_service.exists(town))
            town_class = town_service.getTownById(town);
        else
            town_class = downloadTownAndCountry(ctx, town, country);

        double lon = town_class.getLon();
        double lat = town_class.getLat();

        List<Fourteen_Days_Weather> fourteen_days_weather_list = fourteen_days_weather_service.getAllFourteenDaysWeather();
        for(Fourteen_Days_Weather weather : fourteen_days_weather_list){
            if(weather.getTown().getName().equals(town))
                fourteen_days_weather_service.delete(weather);
        }

        DataAVGTransfer data_transfer = getAVGData(lat, lon, 14);
        Fourteen_Days_Weather fourteen_day_class = ctx.getBean(Fourteen_Days_Weather.class);
        fourteen_day_class.setAll(data_transfer.getDatetime(), town_class, data_transfer.getMin_temperature(),
                data_transfer.getMax_temperature(), data_transfer.getTemperature(), data_transfer.getFeel_like_temperature(),
                data_transfer.getPressure(), data_transfer.getHumidity(), data_transfer.getWind_speed(),
                data_transfer.getWind_degrees(), data_transfer.getWind_gust(), data_transfer.getClouds_percentage());
        fourteen_days_weather_service.createOrUpdate(fourteen_day_class);
    }

    private static void downloadSevenDaysWeather(ApplicationContext ctx, String town, String country){
        TownService town_service = ctx.getBean(TownService.class);
        Seven_Days_WeatherService seven_days_weather_service = ctx.getBean(Seven_Days_WeatherService.class);
        Town town_class;

        if(town_service.exists(town))
            town_class = town_service.getTownById(town);
        else
            town_class = downloadTownAndCountry(ctx, town, country);

        double lon = town_class.getLon();
        double lat = town_class.getLat();

        List<Seven_Days_Weather> seven_days_weather_list = seven_days_weather_service.getAllSevenDaysWeather();
        for(Seven_Days_Weather weather : seven_days_weather_list){
            if(weather.getTown().getName().equals(town))
                seven_days_weather_service.delete(weather);
        }

        DataAVGTransfer data_transfer = getAVGData(lat, lon, 7);
        Seven_Days_Weather seven_day_class = ctx.getBean(Seven_Days_Weather.class);
        seven_day_class.setAll(data_transfer.getDatetime(), town_class, data_transfer.getMin_temperature(),
                data_transfer.getMax_temperature(), data_transfer.getTemperature(), data_transfer.getFeel_like_temperature(),
                data_transfer.getPressure(), data_transfer.getHumidity(), data_transfer.getWind_speed(),
                data_transfer.getWind_degrees(), data_transfer.getWind_gust(), data_transfer.getClouds_percentage());
        seven_days_weather_service.createOrUpdate(seven_day_class);
    }

    public static DataAVGTransfer getAVGData(double lat, double lon, int days){
        try {
            JSONObject json_data = data_loader.getHistoricalData(lat, lon, days);
            System.out.println(json_data);
            JSONArray json_array_hourly = json_data.getJSONArray("list");

            int json_array_length = json_array_hourly.length();
            JSONObject obj_last = (JSONObject) json_array_hourly.get(json_array_length - 1);
            Date datetime = data_loader.get_datetime(obj_last, "dt");

            double temperature_tmp = 0.0, feel_like_temperature_tmp = 0.0, min_temperature_tmp = 0.0,
                    max_temperature_tmp = 0.0, wind_speed_tmp = 0.0, wind_gust_tmp = 0.0;
            int pressure_tmp = 0, humidity_tmp = 0, clouds_percentage_tmp = 0, wind_degrees_tmp = 0;

            for (Object data : json_array_hourly){
                JSONObject data_json = (JSONObject) data;

                JSONObject main_hourly = data_json.getJSONObject("main");
                temperature_tmp += data_loader.get_double_part(main_hourly, "temp");
                feel_like_temperature_tmp += data_loader.get_double_part(main_hourly, "feels_like");
                min_temperature_tmp += data_loader.get_double_part(main_hourly, "temp_min");
                max_temperature_tmp += data_loader.get_double_part(main_hourly, "temp_max");
                pressure_tmp += data_loader.get_int_part(main_hourly, "pressure");
                humidity_tmp += data_loader.get_int_part(main_hourly, "humidity");

                clouds_percentage_tmp += data_loader.get_clouds_percentage(data_json);

                JSONObject wind_json_hourly = data_json.getJSONObject("wind");
                wind_speed_tmp += data_loader.get_double_part(wind_json_hourly, "speed");
                wind_degrees_tmp += data_loader.get_int_part(wind_json_hourly, "deg");
                wind_gust_tmp += data_loader.get_double_part(wind_json_hourly, "gust");
            }

            double temperature = temperature_tmp / json_array_length;
            double feel_like_temperature = feel_like_temperature_tmp / json_array_length;
            double min_temperature = min_temperature_tmp / json_array_length;
            double max_temperature = max_temperature_tmp / json_array_length;
            double wind_speed = wind_speed_tmp / json_array_length;
            double wind_gust = wind_gust_tmp / json_array_length;
            int pressure = pressure_tmp / json_array_length;
            int humidity = humidity_tmp / json_array_length;
            int clouds_percentage = clouds_percentage_tmp / json_array_length;
            int wind_degrees = wind_degrees_tmp / json_array_length;

            DataAVGTransfer data_transfer = new DataAVGTransfer();
            data_transfer.setAll(datetime, temperature, feel_like_temperature, min_temperature, max_temperature,
            wind_speed, wind_gust, pressure, humidity, clouds_percentage, wind_degrees);

            return data_transfer;
        }catch(IOException e){
            System.out.println("Historická data nemohla být načtena.");
        }
        return null;
    }
}