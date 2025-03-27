package com.github.eventure.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Requests {
    private static final Gson gson = new Gson();

    public static <T> T get(String address, Class<T> responseModel) {
        // Create the connection and request
        var httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofMinutes(1))
            .build();

        var httpRequest = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(address))
            .build();

        // Send the request
        try {
            HttpResponse<String> response = httpClient.send(
                httpRequest, HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) return gson.fromJson(response.body(), responseModel);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
