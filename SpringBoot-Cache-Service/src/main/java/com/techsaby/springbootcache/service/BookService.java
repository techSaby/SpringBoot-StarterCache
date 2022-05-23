package com.techsaby.springbootcache.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.techsaby.springbootcache.Repository.BookRepository;
import com.techsaby.springbootcache.model.Books;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	

	@CacheEvict(cacheNames="books" , allEntries = true)
	public Books addBook(Books book) {
		log.info("Adding book in DB with id: "+ book.getId());
		return bookRepository.save(book);
	}

	@CachePut(cacheNames="books", key="#book.id")
	public Books updateBook(Books book) throws Exception {
		Optional<Books> bookfromDB = bookRepository.findById(book.getId());
		Books updatedBook = new Books();
		if (bookfromDB.isPresent()) {
			bookfromDB.get().setId(book.getId());
			bookfromDB.get().setName(book.getName());
			
			updatedBook = bookRepository.save(bookfromDB.get());
			log.info("Book updated in DB with new name: "+book.getName() + " for Book ID: "+book.getId());
		} else {
			throw new Exception("No Book Found in DB with the ID "+book.getId());
		}
		return updatedBook;
	}

	@Cacheable(cacheNames="books", key="#id")
	public Books getBook(long id) {
		log.info("Fetching book from db for Book ID: "+id);
		Optional<Books> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		} else {
			return new Books();
		}
	}

	
	@CacheEvict(cacheNames="books", key="#id")
	public String deleteBook(long id) {
		log.info("Fetching book from db for Book ID: "+id);
		bookRepository.deleteById(id);
		log.info("Deleted book from db for Book ID: "+id);
		return "Book deleted";
	}

	@Cacheable(cacheNames="books")
	public List<Books> getAllBooks() {
		log.info("Fetching All records from DB ");
		return bookRepository.findAll();
	}
}
