package com.example.bookSearch.Repositories;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.bookSearch.BookSearchApplication;
import com.example.bookSearch.Constants;
import com.example.bookSearch.model.indivBookModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Repository
@Qualifier(Constants.Repo_redis)
public class BookRepository {
    private static final Logger logger = Logger.getLogger(BookSearchApplication.class.getName());

    @Autowired
    @Qualifier(Constants.Bean_Book_Cache)
    private RedisTemplate<String,String> template;
    
    public void save(String works_id, String value) {
        template.opsForValue().set(works_id, value, 10L, TimeUnit.MINUTES);
    }

    public Optional<String> get(String works_id) {
        String value = template.opsForValue().get(works_id);
        return Optional.ofNullable(value);
    }

    public void save(String works_id,List<indivBookModel> booklist){
        JsonArrayBuilder arrbuilder = Json.createArrayBuilder();
        booklist.stream()       
                    .forEach(v->arrbuilder.add(v.toJson()));
        template.opsForValue().set(works_id, arrbuilder.build().toString(),10L,TimeUnit.MINUTES);
    }
    public Optional<List<indivBookModel>> getbook(String works_id){
        Optional<String> opt = Optional.ofNullable(template.opsForValue().get(works_id));
        if(opt.isEmpty())
            return Optional.empty();
        
        JsonArray jsonArray = parseJsonArray(opt.get());
        List<indivBookModel> books = jsonArray.stream()
                                        .map(v->(JsonObject)v)
                                        .map(indivBookModel::createUsingJsonObject)
                                        .collect(Collectors.toList());
        return Optional.of(books);
    }
    private JsonArray parseJsonArray(String s) {
        try (InputStream is = new ByteArrayInputStream(s.getBytes())) {
            JsonReader reader = Json.createReader(is);
            return reader.readArray();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Parsing", ex);
        }
        return Json.createArrayBuilder().build();
    }

}
