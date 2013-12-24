package com.roosearch.android.provider.rest;

import com.roosearch.android.domain.Customer;
import com.roosearch.android.domain.Survey;
import com.roosearch.android.domain.dto.SurveyComplete;
import com.roosearch.android.provider.DataProvider;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestDataProvider implements DataProvider {
    public Customer getCustomerDetails(int customerId) {

        try {
            final String url = "http://roosearchdev.jameselsey.cloudbees.net/api/customer/{query}";

            HttpHeaders requestHeaders = new HttpHeaders();

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            // Perform the HTTP GET request
            ResponseEntity<Customer> response = restTemplate.exchange(url, HttpMethod.GET,
                    new HttpEntity<Object>(requestHeaders), Customer.class, customerId);

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Oops, got an error retrieving from server.. + e");
        }
         return null;
    }

    public Survey getSurvey(Integer surveyId) {
        try {
            final String url = "http://roosearchdev.jameselsey.cloudbees.net/api/survey/{query}";

            HttpHeaders requestHeaders = new HttpHeaders();

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            // Perform the HTTP GET request
            ResponseEntity<Survey> response = restTemplate.exchange(url, HttpMethod.GET,
                    new HttpEntity<Object>(requestHeaders), Survey.class, surveyId);

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Oops, got an error retrieving from server.. + e");
        }
        return null;
    }

    //todo: look at handling the 204 a bit more gracefully.
    public void postResults(Survey survey) {
        SurveyComplete dto = SurveyComplete.from(survey);

        // post it!
        String response = null;
        try {
            final String url = "http://roosearchdev.jameselsey.cloudbees.net/api/survey";

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(new MediaType("application","json"));

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            restTemplate.postForObject(url, dto, String.class);


        } catch (Exception e) {
            System.out.println("Oops, got an error retrieving from server.. + e");
        }
    }
}
