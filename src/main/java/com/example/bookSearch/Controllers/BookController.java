package com.example.bookSearch.Controllers;

import java.util.logging.Logger;

import com.example.bookSearch.BookSearchApplication;
import com.example.bookSearch.Constants;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Qualifier(Constants.Contr_Book)
@RequestMapping(produces=MediaType.TEXT_HTML_VALUE)
public class BookController {
    private static final Logger logger = Logger.getLogger(BookSearchApplication.class.getName());

    @GetMapping("/book/{works_id}")
    public String getBook(@PathVariable String works_id,Model model){
        model.addAttribute("test", works_id);
        return "IndividualResult";
    }

    
}
