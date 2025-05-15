package com.angel.server_spring_boot.service.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.http.ResponseEntity;
import com.angel.server_spring_boot.config.DollarApi;

import java.util.Map;

@Service
public class CurrencyService {

    private final DollarApi dollarApi;
    private final RestTemplate restTemplate;
    // private static final String API_URL = "https://v6.exchangerate-api.com/v6/YOUR-KEY/latest/USD"; // Cambia YOUR_API_KEY por tu clave real

    @Autowired
    public CurrencyService(DollarApi dollarApi, RestTemplate restTemplate) {
        this.dollarApi = dollarApi;
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getDollarPrice() {
        String url = dollarApi.getDollarUrl();
        String key = dollarApi.getDollarKey();
        String API_URL = url + "/v6/" + key + "/latest/USD";
        System.out.println("Url: "+API_URL);
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(API_URL, Map.class);
            return response.getBody();
        } catch (HttpClientErrorException e) { // Error regresado desde la API 
            throw new RuntimeException("Error al obtener el precio del dólar: " + e.getMessage());
        } catch (Exception e) { // Error regresaso en caso de que la API no responsa
            throw new RuntimeException("Ocurrió un error inesperado al llamar a la API externa.");
        }
    }
}
