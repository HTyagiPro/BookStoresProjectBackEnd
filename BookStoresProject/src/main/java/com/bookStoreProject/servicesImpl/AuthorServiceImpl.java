package com.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookStoreProject.entity.Author;
import com.bookStoreProject.repository.AuthorRepository;
import com.bookStoreProject.services.AuthorService;

import java.util.List;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Get a list of all authors
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Get an author by their ID
    @Override
    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    // Create a new author
    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Update an existing author
    @Override
    public Author updateAuthor(Long authorId, Author author) {
        if (authorRepository.existsById(authorId)) {
            author.setAuthorID(authorId);
            return authorRepository.save(author);
        }
        return null;
    }

    // Delete an author by their ID
    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    // Add an author using a map of author details
    @Override
    public ResponseEntity<String> addAuthor(Map<String, String> map) {
        try {
            Author author = new Author();
            author.setAuthorName(map.get("authorName"));
            authorRepository.save(author);
            return new ResponseEntity<String>("Author Added Successfully!!!", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Get the most recently added author
    @Override
    public Author getAddedAuthor() {
        return authorRepository.getAddedAuthor();
    }
}















//package com.bookStoreProject.servicesImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Author;
//import com.bookStoreProject.repository.AuthorRepository;
//import com.bookStoreProject.services.AuthorService;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class AuthorServiceImpl implements AuthorService {
//    private final AuthorRepository authorRepository;
//
//    @Autowired
//    public AuthorServiceImpl(AuthorRepository authorRepository) {
//        this.authorRepository = authorRepository;
//    }
//
//    @Override
//    public List<Author> getAllAuthors() {
//        return authorRepository.findAll();
//    }
//
//    @Override
//    public Author getAuthorById(Long authorId) {
//        return authorRepository.findById(authorId).orElse(null);
//    }
//
//    @Override
//    public Author createAuthor(Author author) {
//        return authorRepository.save(author);
//    }
//
//    @Override
//    public Author updateAuthor(Long authorId, Author author) {
//        if (authorRepository.existsById(authorId)) {
//            author.setAuthorID(authorId);
//            return authorRepository.save(author);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteAuthor(Long authorId) {
//        authorRepository.deleteById(authorId);
//    }
//
//	@Override
//	public ResponseEntity<String> addAuthor(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			Author author = new Author();
//			author.setAuthorName(map.get("authorName"));
//			authorRepository.save(author);
//			return new ResponseEntity<String>("Author Added Successfully!!!", HttpStatus.OK);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public Author getAddedAuthor() {
//		// TODO Auto-generated method stub
//		return authorRepository.getAddedAuthor();
//	}
//}
//
