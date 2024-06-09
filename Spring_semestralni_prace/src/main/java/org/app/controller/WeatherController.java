package org.app.controller;

import org.app.data.*;
import org.app.data_avg.DataAVGTransfer;
import org.app.data_loader.DataLoader;
import org.app.service.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private ApplicationContext ctx;

    private static DataLoader data_loader = new DataLoader();

    @GetMapping("/get_weather_current&town={town}")
    public List<String> getWeatherCurrentLast(@PathVariable String town){
        Weather_CurrentService weather_current_service = ctx.getBean(Weather_CurrentService.class);
        List<String> output = new ArrayList<>();
        List<Weather_Current> weather_current_list = weather_current_service.getWeatherCurrentForTown(town);
        for(Weather_Current weather : weather_current_list)
            output.add(weather.toString());
        return output;
    }

    @GetMapping("/get_weather_current_download&town={town}&country={country}")
    List<String> getWeatherCurrentNow(@PathVariable String town,@PathVariable String country){
        downloadCurrentWeather(town, country);
        return getWeatherCurrentLast(town);
    }

    @DeleteMapping("/delete_weather_current&town={town_name}")
    String deleteWeatherCurrent(@PathVariable String town_name){
        Weather_CurrentService weather_current_service = ctx.getBean(Weather_CurrentService.class);
        List<Weather_Current> weather_current_list = weather_current_service.getWeatherCurrentForTown(town_name);
        for(Weather_Current weather : weather_current_list)
            weather_current_service.delete(weather);
        return "Deleted.";
    }

    @GetMapping("/download_current_weather&town={town}&country={country}")
    String downloadCurrentWeather(@PathVariable String town, @PathVariable String country){
        Weather_CurrentService weather_current_service = ctx.getBean(Weather_CurrentService.class);
        TownService town_service = ctx.getBean(TownService.class);
        Town town_class;

        try{
            if(town_service.exists(town))
                town_class = town_service.getTownById(town);
            else
                town_class = downloadTownAndCountry(town, country);

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
            return "Weather Current downloaded.";
        }catch (IOException e){
            return getLogFile();
        }
    }

    @GetMapping("/get_weather_day&town={town}")
    List<String> getWeatherDayLastAVG(@PathVariable String town){
        Weather_DayService weather_day_service = ctx.getBean(Weather_DayService.class);
        List<String> output = new ArrayList<>();
        List<Weather_Day> weather_day_list = weather_day_service.getWeatherForTown(town);
        for(Weather_Day weather: weather_day_list)
            output.add(weather.toString());
        return output;
    }

    @GetMapping("/get_weather_day_download&town={town}&country={country}")
    List<String> getWeatherDayNowAVG(@PathVariable String town, @PathVariable String country){
        downloadDayWeather(town, country);
        return getWeatherDayLastAVG(town);
    }

    @DeleteMapping("/delete_weather_day&town={town_name}")
    String deleteWeatherDay(@PathVariable String town_name){
        Weather_DayService weather_day_service = ctx.getBean(Weather_DayService.class);
        List<Weather_Day> weather_day_list = weather_day_service.getWeatherForTown(town_name);
        for(Weather_Day weather : weather_day_list)
            weather_day_service.delete(weather);
        return "Deleted.";
    }

    @GetMapping("/download_day_weather&town={town}&country={country}")
    String downloadDayWeather(@PathVariable String town, @PathVariable String country){
        TownService town_service = ctx.getBean(TownService.class);
        Weather_DayService weather_day_service = ctx.getBean(Weather_DayService.class);
        Town town_class;
        try {
            if (town_service.exists(town))
                town_class = town_service.getTownById(town);
            else
                town_class = downloadTownAndCountry(town, country);

            double lon = town_class.getLon();
            double lat = town_class.getLat();

            List<Weather_Day> fourteen_days_weather_list = weather_day_service.getAllDayWeathers();
            for (Weather_Day weather : fourteen_days_weather_list) {
                if (weather.getTown().getName().equals(town))
                    weather_day_service.delete(weather);
            }

            DataAVGTransfer data_transfer = getAVGData(lat, lon, 1);
            Weather_Day weather_day_class = ctx.getBean(Weather_Day.class);
            weather_day_class.setAll(data_transfer.getDatetime(), town_class, data_transfer.getMin_temperature(),
                    data_transfer.getMax_temperature(), data_transfer.getTemperature(), data_transfer.getFeel_like_temperature(),
                    data_transfer.getPressure(), data_transfer.getHumidity(), data_transfer.getWind_speed(),
                    data_transfer.getWind_degrees(), data_transfer.getWind_gust(), data_transfer.getClouds_percentage());
            weather_day_service.createOrUpdate(weather_day_class);
            return "Weather Day downloaded.";
        }catch(IOException e){
            return getLogFile();
        }
    }

    @GetMapping("/get_weather_seven_days&town={town}")
    List<String> getSevenDaysWeatherLastAVG(@PathVariable String town){
        Seven_Days_WeatherService seven_days_weather_service = ctx.getBean(Seven_Days_WeatherService.class);
        List<String> output = new ArrayList<>();
        List<Seven_Days_Weather> seven_days_weather_list = seven_days_weather_service.getWeatherForTown(town);
        for(Seven_Days_Weather weather: seven_days_weather_list)
            output.add(weather.toString());
        return output;
    }

    @GetMapping("/get_weather_seven_days_download&town={town}&country={country}")
    List<String> getSevenDaysWeatherNowAVG(@PathVariable String town, @PathVariable String country){
        downloadSevenDaysWeather(town, country);
        return getSevenDaysWeatherLastAVG(town);
    }

    @DeleteMapping("/delete_weather_seven_days&town={town_name}")
    String deleteSevenDaysWeather(@PathVariable String town_name){
        Seven_Days_WeatherService seven_days_weather_service = ctx.getBean(Seven_Days_WeatherService.class);
        List<Seven_Days_Weather> seven_days_weather_list = seven_days_weather_service.getWeatherForTown(town_name);
        for(Seven_Days_Weather weather : seven_days_weather_list)
            seven_days_weather_service.delete(weather);
        return "Deleted.";
    }

    @GetMapping("/download_seven_days_weather&town={town}&country={country}")
    String downloadSevenDaysWeather(@PathVariable String town, @PathVariable String country){
        TownService town_service = ctx.getBean(TownService.class);
        Seven_Days_WeatherService seven_days_weather_service = ctx.getBean(Seven_Days_WeatherService.class);
        Town town_class;

        try {
            if (town_service.exists(town))
                town_class = town_service.getTownById(town);
            else
                town_class = downloadTownAndCountry(town, country);

            double lon = town_class.getLon();
            double lat = town_class.getLat();

            List<Seven_Days_Weather> seven_days_weather_list = seven_days_weather_service.getAllSevenDaysWeather();
            for (Seven_Days_Weather weather : seven_days_weather_list) {
                if (weather.getTown().getName().equals(town))
                    seven_days_weather_service.delete(weather);
            }

            DataAVGTransfer data_transfer = getAVGData(lat, lon, 7);
            Seven_Days_Weather seven_day_class = ctx.getBean(Seven_Days_Weather.class);
            seven_day_class.setAll(data_transfer.getDatetime(), town_class, data_transfer.getMin_temperature(),
                    data_transfer.getMax_temperature(), data_transfer.getTemperature(), data_transfer.getFeel_like_temperature(),
                    data_transfer.getPressure(), data_transfer.getHumidity(), data_transfer.getWind_speed(),
                    data_transfer.getWind_degrees(), data_transfer.getWind_gust(), data_transfer.getClouds_percentage());
            seven_days_weather_service.createOrUpdate(seven_day_class);
            return "Weather Seven Days downloaded.";
        }catch (IOException e){
            return getLogFile();
        }
    }

    @GetMapping("/get_weather_fourteen_days&town={town}")
    List<String> getFourteenDaysWeatherLastAVG(@PathVariable String town){
        Fourteen_Days_WeatherService fourteen_days_weather_service = ctx.getBean(Fourteen_Days_WeatherService.class);
        List<String> output = new ArrayList<>();
        List<Fourteen_Days_Weather> fourteen_days_weather_list = fourteen_days_weather_service.getWeatherForTown(town);
        for(Fourteen_Days_Weather weather : fourteen_days_weather_list)
            output.add(weather.toString());
        return output;
    }

    @GetMapping("/get_weather_fourteen_days_download&town={town}&country={country}")
    List<String> getFourteenDaysWeatherNowAVG(@PathVariable String town, @PathVariable String country){
        downloadFourteenDaysWeather(town, country);
        return getFourteenDaysWeatherLastAVG(town);
    }

    @DeleteMapping("/delete_weather_fourteen_days&town={town_name}")
    String deleteFourteenDaysWeather(@PathVariable String town_name){
        Fourteen_Days_WeatherService fourteen_days_weather_service = ctx.getBean(Fourteen_Days_WeatherService.class);
        List<Fourteen_Days_Weather> fourteen_days_weather_list = fourteen_days_weather_service.getWeatherForTown(town_name);
        for(Fourteen_Days_Weather weather : fourteen_days_weather_list)
            fourteen_days_weather_service.delete(weather);
        return "Deleted";
    }

    @GetMapping("/download_fourteen_days_weather&town={town}&country={country}")
    String downloadFourteenDaysWeather(@PathVariable String town, @PathVariable String country){
        TownService town_service = ctx.getBean(TownService.class);
        Fourteen_Days_WeatherService fourteen_days_weather_service = ctx.getBean(Fourteen_Days_WeatherService.class);
        Town town_class;

        try {
            if (town_service.exists(town))
                town_class = town_service.getTownById(town);
            else
                town_class = downloadTownAndCountry(town, country);

            double lon = town_class.getLon();
            double lat = town_class.getLat();

            List<Fourteen_Days_Weather> fourteen_days_weather_list = fourteen_days_weather_service.getAllFourteenDaysWeather();
            for (Fourteen_Days_Weather weather : fourteen_days_weather_list) {
                if (weather.getTown().getName().equals(town))
                    fourteen_days_weather_service.delete(weather);
            }

            DataAVGTransfer data_transfer = getAVGData(lat, lon, 14);
            Fourteen_Days_Weather fourteen_day_class = ctx.getBean(Fourteen_Days_Weather.class);
            fourteen_day_class.setAll(data_transfer.getDatetime(), town_class, data_transfer.getMin_temperature(),
                    data_transfer.getMax_temperature(), data_transfer.getTemperature(), data_transfer.getFeel_like_temperature(),
                    data_transfer.getPressure(), data_transfer.getHumidity(), data_transfer.getWind_speed(),
                    data_transfer.getWind_degrees(), data_transfer.getWind_gust(), data_transfer.getClouds_percentage());
            fourteen_days_weather_service.createOrUpdate(fourteen_day_class);
            return "Weather Fourteen Days downloaded.";
        }catch (IOException e){
            return getLogFile();
        }
    }
    @GetMapping("/get_town&town={town}")
    List<String> getTownsLast(@PathVariable String town){
        TownService town_service = ctx.getBean(TownService.class);
        List<String> output = new ArrayList<>();
        List<Town> town_list = town_service.findByTownName(town);
        for(Town town_class : town_list)
            output.add(town_class.toString());
        return output;
    }

    @GetMapping("/get_country&country={shortcut}")
    public List<String> getCountryLast(@PathVariable String shortcut){
        CountryService country_service = ctx.getBean(CountryService.class);
        List<String> output = new ArrayList<>();
        List<Country> country_list = country_service.getCountryByShortcut(shortcut);
        for(Country country_class : country_list)
            output.add(country_class.toString());
        return output;
    }

    @DeleteMapping("/delete_country&country={shortcut}")
    String deleteCountry(@PathVariable String shortcut){
        CountryService country_service = ctx.getBean(CountryService.class);
        List<Country> country_list = country_service.getCountryByShortcut(shortcut);
        for (Country country : country_list)
            country_service.delete(country);
        return "Deleted.";
    }

    @DeleteMapping("/delete_town&town={town_name}")
    String deleteTown(@PathVariable String town_name){
        TownService town_service = ctx.getBean(TownService.class);
        List<Town> town_list = town_service.findByTownName(town_name);
        for(Town town : town_list)
            town_service.delete(town);
        return "Deleted.";
    }

    Town downloadTownAndCountry(String town, String country){
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
            return null;
        }
    }

    DataAVGTransfer getAVGData(double lat, double lon, int days) throws IOException {
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
    }

    String getLogFile(){
        BufferedReader reader;
        StringBuilder string_builder = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader("log.out"));
            String line = reader.readLine();

            while (line != null) {
                line = reader.readLine();
                string_builder.append(line + "\n");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string_builder.toString();
    }
}
