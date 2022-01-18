package com.example.bookSearch.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.bookSearch.BookSearchApplication;
import com.example.bookSearch.Constants;
import com.example.bookSearch.Repositories.BookRepository;
import com.example.bookSearch.Services.BookService;
import com.example.bookSearch.model.indivBookModel;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    @Qualifier(Constants.SVC_Book)
    BookService bookSVC;

    @Autowired
    @Qualifier(Constants.Repo_redis)
    private BookRepository bookRepo;
    
    @GetMapping("/book/{works_id}")
    public String getBook(@PathVariable String works_id,Model model){
        model.addAttribute("test", works_id);
        //check if in cache
        Optional<List<indivBookModel>> opt = bookRepo.getbook(works_id); 

        if(opt.isPresent()){
            List<indivBookModel> indiv = opt.get();
            logger.log(Level.INFO, ">>>%s".formatted(indiv));
            model.addAttribute("book", indiv.get(0));
            model.addAttribute("isCache", true);
        }else{
            indivBookModel iBook= bookSVC.serachOneBook(works_id);
            model.addAttribute("book", iBook);
            model.addAttribute("isCache", false);
            List<indivBookModel> listOfBook = new ArrayList<indivBookModel>();
            listOfBook.add(iBook);
            bookRepo.save(works_id, listOfBook);
        }
        return "IndividualResult";
    }

    
}
