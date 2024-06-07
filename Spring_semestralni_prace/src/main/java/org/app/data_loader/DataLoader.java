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

    public JSONObject load_town_and_country(String town, String country) throws IOException {
        String url_to_get_lat_and_lon = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s,%s&APPID=b90ae8e9a160e17dfd9a9115dac4cb80",town, country);
        JSONObject json_obj_lat_lon = get_JSON_without_out_array(url_to_get_lat_and_lon);

        return json_obj_lat_lon;
    }

    public void load_14days_weather(String town, double lat, double lon) throws IOException {
        String url_14days_weather = String.format("http://api.openweathermap.org/data/2.5/forecast/daily?lat=%f&lon=%f&units=metric&cnt=14&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon);
        JSONObject json_14days_weather = get_JSON(url_14days_weather);

        JSONArray json_array_14days = json_14days_weather.getJSONArray("list");

        for (Object weather_14days : json_array_14days){
            JSONObject json_object_14days = (JSONObject) weather_14days;

            Date fourteen_days_datetime = get_datetime(json_object_14days, "dt");

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
            Date sunrise = get_datetime(json_object_14days, "sunrise");
            Date sunset = get_datetime(json_object_14days, "sunset");
        }
    }

    public JSONArray load_hourly_weather(double lat, double lon) throws IOException {
        String url_hourly_weather = String.format("https://pro.openweathermap.org/data/2.5/forecast/hourly?lat=%f&lon=%f&units=metric&cnt=24&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon);
        JSONObject json_hourly_weather = get_JSON(url_hourly_weather);

        JSONArray json_array_hourly = json_hourly_weather.getJSONArray("list");
        return json_array_hourly;
    }
    public JSONObject load_current_weather(String town, double lat, double lon) throws IOException {
        String url_current_wheather = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=metric&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon);
        JSONObject json_current_wheather = get_JSON(url_current_wheather);

        return json_current_wheather;
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

    public Date get_datetime(JSONObject json_object, String key){
        try{
            long dt_hourly = json_object.getLong(key) * 1000;
            Date date_hourly_weather = new Date(dt_hourly);
            return date_hourly_weather;
        }catch(Exception e){
            return null;
        }
    }

    public String get_string_part(JSONObject json_object, String key){
        try{
            return json_object.getString(key);
        }catch(Exception e){
            return "null";
        }
    }

    public double get_double_part(JSONObject json_object, String key){
        try{
            return json_object.getDouble(key);
        }catch(Exception e){
            return -20000.0;
        }
    }

    public int get_int_part(JSONObject json_object, String key){
        try{
            return json_object.getInt(key);
        }catch(Exception e){
            return -20000;
        }
    }

    public int get_clouds_percentage(JSONObject json_object){
        try{
            return json_object.getJSONObject("clouds").getInt("all");
        }catch(Exception e){
            return -20000;
        }
    }

    public double get_rain_volume(JSONObject json_object){
        try{
            return json_object.getJSONObject("rain").getDouble("1h");
        }catch(Exception e){
            return -20000.0;
        }
    }
}
