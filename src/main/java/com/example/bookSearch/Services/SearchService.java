package com.example.bookSearch.Services;

import java.util.Arrays;
import java.util.List;

public class SearchService {
    public static void main(String[] args) {
        String test = "/works/abcd";
        List<String> ttt = Arrays.asList(test.split("/"));
        System.out.println(ttt.get(ttt.size()-1));
    }
}
