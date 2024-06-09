package org.app;

import org.app.controller.WeatherController;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.desktop.SystemEventListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
@EnableJpaRepositories("org.app.repositories")
@PropertySource("classpath:application-${spring.profiles.active}.properties")
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
    }
}