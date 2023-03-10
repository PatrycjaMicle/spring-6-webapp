package guru.springframework.spring6webapp.controller;


import guru.springframework.spring6webapp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookService bookservice;

    public BookController(BookService bookservice) {
        this.bookservice = bookservice;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){

        model.addAttribute("books",bookservice.findAll());

        return "books";
    }



}
