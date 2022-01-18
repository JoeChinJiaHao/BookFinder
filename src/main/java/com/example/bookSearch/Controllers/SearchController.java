package com.example.bookSearch.Controllers;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.bookSearch.BookSearchApplication;
import com.example.bookSearch.Constants;
import com.example.bookSearch.Services.BookService;
import com.example.bookSearch.model.bookModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Qualifier(Constants.Contr_Search)
@RequestMapping(produces=MediaType.TEXT_HTML_VALUE)
public class SearchController {
    private static final Logger logger = Logger.getLogger(BookSearchApplication.class.getName());

    @Autowired
    @Qualifier(Constants.SVC_Book)
    public BookService bookSVC;

    @PostMapping("/result")
    public String getResults(@RequestBody MultiValueMap<String,String> form,Model model){
        String query= form.getFirst("searchTerm").trim();
        model.addAttribute("QueryName", "\'"+query+"\'");
        List<bookModel> listOfBooks = Collections.emptyList();
        boolean isEmpty = true;

        //get from web
        listOfBooks=bookSVC.search(query);
        if(listOfBooks.isEmpty()){
            isEmpty=true;
        }else{
            isEmpty=false;
        }
        //logger.log(Level.INFO, ">>>>%s".formatted(listOfBooks));
        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("results", listOfBooks);
        return "Results";
    }
}
