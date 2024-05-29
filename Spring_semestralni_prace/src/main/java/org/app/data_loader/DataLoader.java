package org.app.data_loader;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataLoader {
    private static SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void load_town_and_country(String town, String country) throws IOException {
        String url_to_get_lat_and_lon = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s,%s&APPID=b90ae8e9a160e17dfd9a9115dac4cb80","Bilina","CZ");
        JSONObject json_obj_lat_lon = get_JSON_without_out_array(url_to_get_lat_and_lon);

        String country_get = json_obj_lat_lon.get("country").toString();
        String town_get = json_obj_lat_lon.get("name").toString();
        String location = json_obj_lat_lon.get("state").toString();
        double lon = json_obj_lat_lon.getDouble("lon");
        double lat = json_obj_lat_lon.getDouble("lat");
    }

    public void load_14days_weather(String town, double lat, double lon) throws IOException {
        String url_14days_weather = String.format("http://api.openweathermap.org/data/2.5/forecast/daily?lat=%f&lon=%f&units=metric&cnt=14&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon);
        JSONObject json_14days_weather = get_JSON(url_14days_weather);

        JSONArray json_array_14days = json_14days_weather.getJSONArray("list");

        for (Object weather_14days : json_array_14days){
            JSONObject json_object_14days = (JSONObject) weather_14days;

            String fourteen_days_datetime = get_datetime(json_object_14days, "dt");

            //town

            JSONArray weather_14days_json_array = json_object_14days.getJSONArray("weather");
            JSONObject weather_14days_json = weather_14days_json_array.getJSONObject(0);
            String main_description = get_string_part(weather_14days_json, "main");
            String alongside_description = get_string_part(weather_14days_json, "description");
            String icon = get_string_part(weather_14days_json, "icon");

            JSONObject temperature_json = json_object_14days.getJSONObject("temp");
            double temperature_min = get_double_part(temperature_json, "min");
            double temperature_max = get_double_part(temperature_json, "max");
            double temperature_day = get_double_part(temperature_json, "day");
            double temperature_night = get_double_part(temperature_json, "night");
            double temperature_eve = get_double_part(temperature_json, "eve");
            double temperature_morning = get_double_part(temperature_json, "morn");

            double feel_like_temperature_day = get_double_part(temperature_json, "day");
            double feel_like_temperature_night = get_double_part(temperature_json, "night");
            double feel_like_temperature_eve = get_double_part(temperature_json, "eve");
            double feel_like_temperature_morning = get_double_part(temperature_json, "morn");

            int pressure = get_int_part(json_object_14days, "pressure");
            int humidity = get_int_part(json_object_14days, "humidity");
            double wind_speed = get_double_part(json_object_14days, "speed");
            int wind_degrees = get_int_part(json_object_14days, "deg");
            double wind_gust = get_double_part(json_object_14days, "gust");
            int clouds_percentage = get_int_part(json_object_14days, "clouds");
            double precipitation_of_rain = get_double_part(json_object_14days, "pop");
            double rain_volume_1h = get_double_part(json_object_14days, "rain");
            String sunrise = get_datetime(json_object_14days, "sunrise");
            String sunset = get_datetime(json_object_14days, "sunset");
        }
    }

    public void load_hourly_weather(String town, double lat, double lon) throws IOException {
        String url_hourly_weather = String.format("https://pro.openweathermap.org/data/2.5/forecast/hourly?lat=%f&lon=%f&units=metric&cnt=24&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon);
        JSONObject json_hourly_weather = get_JSON(url_hourly_weather);

        JSONArray json_array_hourly = json_hourly_weather.getJSONArray("list");

        for (Object hour_weather : json_array_hourly){
            JSONObject json_object_hourly = (JSONObject) hour_weather;

            String hourly_date = get_datetime(json_object_hourly, "dt");

            //town

            JSONArray weather_hourly_json_array = json_object_hourly.getJSONArray("weather");
            JSONObject weather_hourly_json = weather_hourly_json_array.getJSONObject(0);
            String main_description = get_string_part(weather_hourly_json, "main");
            String alongside_description = get_string_part(weather_hourly_json, "description");
            String icon = get_string_part(weather_hourly_json, "icon");

            JSONObject main_hourly = json_object_hourly.getJSONObject("main");
            double temperature = get_double_part(main_hourly, "temp");
            double feel_like_temperature = get_double_part(main_hourly, "feels_like");
            double min_temperature = get_double_part(main_hourly, "temp_min");
            double max_temperature = get_double_part(main_hourly, "temp_max");
            int pressure = get_int_part(main_hourly, "pressure");
            int humidity = get_int_part(main_hourly, "humidity");
            int sea_level = get_int_part(main_hourly, "sea_level");
            int ground_level = get_int_part(main_hourly, "grnd_level");

            int visibility = get_int_part(json_object_hourly, "visibility");

            JSONObject wind_json_hourly = json_object_hourly.getJSONObject("wind");
            double wind_speed = get_double_part(wind_json_hourly, "speed");
            int wind_degrees = get_int_part(wind_json_hourly, "deg");
            double wind_gust = get_double_part(wind_json_hourly, "gust");

            int clouds_percentage = get_clouds_percentage(json_object_hourly);
            double precipitation_of_rain = get_double_part(json_object_hourly, "pop");
            double rain_volume = get_rain_volume(json_object_hourly);
        }
    }
    public void load_current_weather(String town, double lat, double lon) throws IOException {
        String url_current_wheather = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=metric&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon);
        JSONObject json_current_wheather = get_JSON(url_current_wheather);

        String datetime = get_datetime(json_current_wheather, "dt");

        //town

        JSONArray weather_json_array = json_current_wheather.getJSONArray("weather");
        JSONObject weather_json = weather_json_array.getJSONObject(0);
        String main_description = get_string_part(weather_json, "main");
        String alongside_description = get_string_part(weather_json, "description");
        String icon = get_string_part(weather_json, "icon");

        String base = get_string_part(json_current_wheather, "base");

        JSONObject main = json_current_wheather.getJSONObject("main");
        double temperature = get_double_part(main, "temp");
        double feel_like_temperature = get_double_part(main, "feels_like");
        double min_temperature = get_double_part(main, "temp_min");
        double max_temperature = get_double_part(main, "temp_max");
        int pressure = get_int_part(main, "pressure");
        int humidity = get_int_part(main, "humidity");
        int sea_level = get_int_part(main, "sea_level");
        int ground_level = get_int_part(main, "grnd_level");

        int visibility = get_int_part(json_current_wheather, "visibility");

        JSONObject wind_json = json_current_wheather.getJSONObject("wind");
        double wind_speed = get_double_part(wind_json, "speed");
        int wind_degrees = get_int_part(wind_json, "deg");
        double wind_gust = get_double_part(wind_json, "gust");

        int clouds_percentage = get_clouds_percentage(json_current_wheather);
        double rain_volume = get_rain_volume(json_current_wheather);

        JSONObject sys_json = json_current_wheather.getJSONObject("sys");
        String sunrise = get_datetime(sys_json, "sunrise");
        String sunset = get_datetime(sys_json, "sunset");
    }

    private static JSONObject get_JSON_without_out_array(String url) throws IOException {
        URL url_lat_lon = new URL(url);
        String json = IOUtils.toString(url_lat_lon, Charset.forName("UTF-8"));
        json = json.substring(1, json.length() - 1);
        return new JSONObject(json);
    }

    private static JSONObject get_JSON(String url) throws IOException {
        URL url_lat_lon = new URL(url);
        String json = IOUtils.toString(url_lat_lon, Charset.forName("UTF-8"));
        return new JSONObject(json);
    }

    private static String get_datetime(JSONObject json_object, String key){
        try{
            long dt_hourly = json_object.getLong(key) * 1000;
            Date date_hourly_weather = new Date(dt_hourly);
            return DateFor.format(date_hourly_weather);
        }catch(Exception e){
            return "null";
        }
    }

    private static String get_string_part(JSONObject json_object, String key){
        try{
            return json_object.getString(key);
        }catch(Exception e){
            return "null";
        }
    }

    private static double get_double_part(JSONObject json_object, String key){
        try{
            return json_object.getDouble(key);
        }catch(Exception e){
            return -20000.0;
        }
    }

    private static int get_int_part(JSONObject json_object, String key){
        try{
            return json_object.getInt(key);
        }catch(Exception e){
            return -20000;
        }
    }

    private static int get_clouds_percentage(JSONObject json_object){
        try{
            return json_object.getJSONObject("clouds").getInt("all");
        }catch(Exception e){
            return -20000;
        }
    }

    private static double get_rain_volume(JSONObject json_object){
        try{
            return json_object.getJSONObject("rain").getDouble("1h");
        }catch(Exception e){
            return -20000.0;
        }
    }
}
