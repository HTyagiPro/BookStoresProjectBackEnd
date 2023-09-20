package com.bookStoreProject.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.bookStoreProject.entity.Book;

public interface BooksService {
    List<Book> getAllBooks();
    Book getBookById(Long bookId);
    Book createBook(Book book);
    Book updateBook(Long bookId, Book book);
    void deleteBook(Long bookId);
    public ResponseEntity<String> addBooks(Map<String, String>map);
    public ResponseEntity<Book> searchBook(Map<String, String>map);
    public ResponseEntity<List<Book>> searchBooks(Map<String, String>map);
}

