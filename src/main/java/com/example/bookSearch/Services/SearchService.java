package com.example.bookSearch.Services;

import java.util.Arrays;
import java.util.List;

import com.example.bookSearch.Constants;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchService {
    public static void main(String[] args) {
       
        final String url = UriComponentsBuilder
                .fromUriString(Constants.URL_Find_One_Book_Base.formatted("ABCD"))
                .toUriString(); 
                System.out.println(url);
    }
}
