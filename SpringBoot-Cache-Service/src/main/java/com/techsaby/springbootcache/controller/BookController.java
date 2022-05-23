package com.techsaby.springbootcache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techsaby.springbootcache.model.Books;
import com.techsaby.springbootcache.service.BookService;

@RestController
public class BookController {
	
	@Autowired
    private BookService bookService;

    @PostMapping("/add-book")
    public Books addBook(@RequestBody Books book){
        return bookService.addBook(book);
    }
    
    @GetMapping("/get-all")
    public List<Books> getAll(){
    	return bookService.getAllBooks();
    }

    @PutMapping("/update-book")
    public Books updateBook(@RequestBody Books book) throws Exception {
        return bookService.updateBook(book);
    }

    @GetMapping("/get-book/{id}")
    public Books getBook(@PathVariable long id){
        return bookService.getBook(id);
    }
    @DeleteMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable long id){
        return bookService.deleteBook(id);
    }

}
