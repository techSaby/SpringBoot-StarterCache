package com.techsaby.springbootcache.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techsaby.springbootcache.model.Books;

public interface BookRepository extends MongoRepository<Books, Long>{

}
