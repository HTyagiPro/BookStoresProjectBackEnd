package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Author;
import com.example.bookStoreProject.repository.AuthorRepository;
import com.example.bookStoreProject.services.AuthorService;

import java.util.List;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long authorId, Author author) {
        if (authorRepository.existsById(authorId)) {
            author.setAuthorID(authorId);
            return authorRepository.save(author);
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

	@Override
	public ResponseEntity<String> addAuthor(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			Author author = new Author();
			author.setAuthorName(map.get("authorName"));
			System.err.println(author);
			authorRepository.save(author);
			return new ResponseEntity<String>("Author Added Successfully!!!", HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

