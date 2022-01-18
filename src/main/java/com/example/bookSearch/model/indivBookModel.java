package com.example.bookSearch.model;

import java.util.List;

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
   

}
