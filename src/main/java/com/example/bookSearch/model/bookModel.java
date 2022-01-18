package com.example.bookSearch.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import jakarta.json.JsonObject;

public class bookModel {
    private String title;
    private String worksId;

    public void setWorksId(String worksId){
        List<String> temp = Arrays.asList(worksId.split("/"));
        this.worksId=(temp.get(temp.size()-1));
    }
    public String getWorksId(){
        return this.worksId;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return this.title;
    }

    public static bookModel createUsingJsonObject(JsonObject O){
        bookModel b = new bookModel();
        b.setTitle(O.getString("title"));
        b.setWorksId(O.getString("key"));
        return b;
    }
}
