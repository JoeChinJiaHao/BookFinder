package com.example.bookSearch.model;

import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class indivBookModel {
    private String picLink;
    private String title;
    private String excerpt="No excerpt found!";
    private String description="No description found!";


    public void setPicLink(String coverId){
        this.picLink=coverId;
    }
    public String getPicLink(){
        return this.picLink;
    }
    public void setTitle(String title){
        this.title=title;
    } 
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setExcerpt(String excerpt){
        this.excerpt=excerpt;
    }
    public String getExcerpt(){
        return this.excerpt;
    }
    public JsonObject toJson(){
        return Json.createObjectBuilder()
                    .add("title",this.title)
                    .add("excerpt",this.excerpt)
                    .add("description", this.description)
                    .build();
    }
    public static indivBookModel createUsingJsonObject(JsonObject O){
        indivBookModel ibook = new indivBookModel();
        ibook.setDescription(O.getString("description"));
        ibook.setExcerpt(O.getString("excerpt"));
        ibook.setTitle(O.getString("title"));
        return ibook;

    }

}
