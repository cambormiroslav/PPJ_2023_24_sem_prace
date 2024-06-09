package org.app;

import org.app.Main;
import org.app.controller.WeatherController;
import org.app.data.Country;
import org.app.data.Weather_Current;
import org.app.data.Weather_Day;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Paths.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class ControllerTest {
    /*@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;*/

    @Autowired ApplicationContext ctx;

    Pattern pattern_weather_current = Pattern.compile("Weather Current:  \\{date : [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+\\.[0-9]+, town name : [a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, main description : [a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, alongside description : [a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ ]+, icon : [0-9a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, base : [a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, temperature : [0-9,]+, feel like temperature : [0-9,]+, temperature min : [0-9,]+, temperature max : [0-9,]+, pressure : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, humidity : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, sea level : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, ground level : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, visibility : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind speed : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind degrees : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind gust : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, clouds percentage : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, rain volume : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, sunrise : [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+\\.[0-9]+, sunset : [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+\\.[0-9]+\\}", Pattern.CASE_INSENSITIVE);
    Pattern pattern_weather_day = Pattern.compile("Weather Day AVG: \\{date : [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+\\.[0-9]+, town name : [a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, temperature : [0-9,]+, feel like temperature : [0-9,]+, temperature min : [0-9,]+, temperature max : [0-9,]+, pressure : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, humidity : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind speed : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind degrees : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind gust : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, clouds percentage : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+\\}", Pattern.CASE_INSENSITIVE);
    Pattern pattern_seven_and_fourteen_days_weather = Pattern.compile("[a-zA-Z]+ Days Weather AVG: \\{date : [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+\\.[0-9]+, town name : [a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, temperature : [0-9,]+, feel like temperature : [0-9,]+, temperature min : [0-9,]+, temperature max : [0-9,]+, pressure : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, humidity : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind speed : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind degrees : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, wind gust : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, clouds percentage : [0-9a-zA-Z,ěĚšŠčČřŘžŽýÝáÁíÍéÉ]+\\}", Pattern.CASE_INSENSITIVE);
    Pattern pattern_country = Pattern.compile("Country: \\{shortcut : [a-zA-Z]+\\}", Pattern.CASE_INSENSITIVE);
    Pattern pattern_town = Pattern.compile("Town: \\{town name : [0-9a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, location : [0-9a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉ]+, lat : [0-9,]+, lon : [0-9,]+\\}", Pattern.CASE_INSENSITIVE);


    @Test
    public void assertGetCountry(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_country&country=CZ", String.class);

        Matcher matcher = pattern_country.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertGetTown(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_town&town=Bílina", String.class);

        Matcher matcher = pattern_town.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertDownloadWeatherCurrent(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/download_current_weather&town=Bílina&country=CZ", String.class);

        assertTrue(response.equals("Weather Current downloaded."));
    }

    @Test
    public void assertGetWeatherCurrent(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_current&town=Bílina", String.class);

        Matcher matcher = pattern_weather_current.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertGetAndDownloadWeatherCurrent(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_current_download&town=Bílina&country=CZ", String.class);

        Matcher matcher = pattern_weather_current.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertDownloadWeatherDay(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/download_day_weather&town=Bílina&country=CZ", String.class);

        assertTrue(response.equals("Weather Day downloaded."));
    }

    @Test
    public void assertGetWeatherDay(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_day&town=Bílina", String.class);

        Matcher matcher = pattern_weather_day.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertGetAndDownloadWeatherDay(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_day_download&town=Bílina&country=CZ", String.class);

        Matcher matcher = pattern_weather_day.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertDownloadWeatherSevenDays(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/download_seven_days_weather&town=Bílina&country=CZ", String.class);

        assertTrue(response.equals("Weather Seven Days downloaded."));
    }

    @Test
    public void assertGetWeatherSevenDays(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_seven_days&town=Bílina", String.class);

        Matcher matcher = pattern_seven_and_fourteen_days_weather.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertGetAndDownloadWeatherSevenDays(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_seven_days_download&town=Bílina&country=CZ", String.class);

        Matcher matcher = pattern_seven_and_fourteen_days_weather.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertDownloadWeatherFourteenDays(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/download_fourteen_days_weather&town=Bílina&country=CZ", String.class);

        assertTrue(response.equals("Weather Fourteen Days downloaded."));
    }

    @Test
    public void assertGetWeatherFourteenDays(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_fourteen_days&town=Bílina", String.class);

        Matcher matcher = pattern_seven_and_fourteen_days_weather.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }

    @Test
    public void assertGetAndDownloadWeatherFourteenDays(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/get_weather_seven_days_download&town=Bílina&country=CZ", String.class);

        Matcher matcher = pattern_seven_and_fourteen_days_weather.matcher(response.substring(1, response.length() - 1));
        boolean matchFound = matcher.find();
        assertTrue(matchFound);
    }
}
