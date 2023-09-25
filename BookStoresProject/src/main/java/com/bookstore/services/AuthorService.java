package com.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.bookstore.entity.Author;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long authorId);
    Author createAuthor(Author author);
    Author updateAuthor(Long authorId, Author author);
    void deleteAuthor(Long authorId);
    public ResponseEntity<String> addAuthor(Map<String, String>map);
    public Author getAddedAuthor();
}
