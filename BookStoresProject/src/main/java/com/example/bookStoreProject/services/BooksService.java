package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.Book;

public interface BooksService {
    List<Book> getAllBooks();
    Book getBookById(Long bookId);
    Book createBook(Book book);
    Book updateBook(Long bookId, Book book);
    void deleteBook(Long bookId);
}

