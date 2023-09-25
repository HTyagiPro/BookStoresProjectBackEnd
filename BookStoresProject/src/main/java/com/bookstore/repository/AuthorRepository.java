package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Define custom queries or methods if needed

    // Custom query to retrieve the most recently added author
    @Query(nativeQuery = true,
           value = "SELECT * FROM Author " +
                   "ORDER BY authorID DESC " +
                   "LIMIT 1")
    public Author getAddedAuthor();
}



















/*
package com.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookStoreProject.entity.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Define custom queries or methods if needed
	@Query(nativeQuery = true,
			value = "SELECT * FROM Author " +
            "ORDER BY authorID DESC " +
            "LIMIT 1")
	public Author getAddedAuthor();
}

*/