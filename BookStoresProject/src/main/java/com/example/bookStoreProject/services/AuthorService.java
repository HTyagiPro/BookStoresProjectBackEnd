package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.Author;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long authorId);
    Author createAuthor(Author author);
    Author updateAuthor(Long authorId, Author author);
    void deleteAuthor(Long authorId);
}
