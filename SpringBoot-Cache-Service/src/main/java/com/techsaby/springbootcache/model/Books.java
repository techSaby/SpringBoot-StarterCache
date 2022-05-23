package com.techsaby.springbootcache.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Books_Cache_Service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

	@Id
	private long id;
    private String name;
    private String category;
    private String author;
    private String publisher;
    private String edition;
}
