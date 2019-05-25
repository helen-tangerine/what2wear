package com.example.closet.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is responsible for retrieving weather information.
 */
@Service
public class WeatherService {

    @Value("${api.key}")
    private String apiKey;

    public String retrieveCurrentWeather(final String location) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) (new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey)).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            StringBuffer buffer = new StringBuffer();
            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            inputStream.close();
            urlConnection.disconnect();
            return buffer.toString();
        } catch(Throwable t) {
            t.printStackTrace();
        } finally {
            try { inputStream.close(); } catch(Throwable t) {}
            try { urlConnection.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }

}
