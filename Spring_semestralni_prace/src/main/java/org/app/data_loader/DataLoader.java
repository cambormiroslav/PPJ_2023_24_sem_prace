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

    public JSONObject getHistoricalData(double lat, double lon, int days) throws IOException {
        Date date = new Date();
        long date_timestamp_unix = date.getTime() / 1000;
        long one_day_unix = 86400;
        long start_timestamp_unix;
        if(days == 1) {
            start_timestamp_unix = date_timestamp_unix - one_day_unix;
        }else if (days == 7){
            start_timestamp_unix = date_timestamp_unix - (7 * one_day_unix);
        }else{
            start_timestamp_unix = date_timestamp_unix - (14 * one_day_unix);
        }
        String url = String.format("https://history.openweathermap.org/data/2.5/history/city?lat=%s&lon=%s&type=hour&start=%d&end=%d&units=metric&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon, start_timestamp_unix, date_timestamp_unix);
        return get_JSON(url);
    }


    public JSONObject load_town_and_country(String town, String country) throws IOException {
        String url_to_get_lat_and_lon = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s,%s&APPID=b90ae8e9a160e17dfd9a9115dac4cb80",town, country);
        JSONObject json_obj_lat_lon = get_JSON_without_out_array(url_to_get_lat_and_lon);

        return json_obj_lat_lon;
    }

    public JSONArray load_14days_weather(String town, double lat, double lon) throws IOException {
        String url_14days_weather = String.format("http://api.openweathermap.org/data/2.5/forecast/daily?lat=%f&lon=%f&units=metric&cnt=14&appid=b90ae8e9a160e17dfd9a9115dac4cb80", lat, lon);
        JSONObject json_14days_weather = get_JSON(url_14days_weather);

        return json_14days_weather.getJSONArray("list");
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
            return new Date(dt_hourly);
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
