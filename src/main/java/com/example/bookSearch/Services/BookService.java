package com.example.bookSearch.Services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.bookSearch.BookSearchApplication;
import com.example.bookSearch.Constants;
import com.example.bookSearch.model.bookModel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
@Service
@Qualifier(Constants.SVC_Book)
public class BookService {
    private static final Logger logger = Logger.getLogger(BookSearchApplication.class.getName());

    public List<bookModel> search(String searchTerm){
        final String url = UriComponentsBuilder
                .fromUriString(Constants.URL_Base)
                .queryParam("title", searchTerm.replace(" ", "+"))
                .queryParam("limit", "20")
                .toUriString(); 
        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);
        if (resp.getStatusCode() != HttpStatus.OK)
            throw new IllegalArgumentException(
                "Error: status code %s".formatted(resp.getStatusCode().toString())
            );
        final String body = resp.getBody();
        try (InputStream is = new ByteArrayInputStream(body.getBytes())){
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();
            final JsonArray readings = result.getJsonArray("docs");

            return readings.stream()
                    .map(v->(JsonObject)v)
                    .map(bookModel::createUsingJsonObject)
                    .collect(Collectors.toList());
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }



}
