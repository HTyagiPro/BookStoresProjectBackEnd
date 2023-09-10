package com.example.bookStoreProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Book;



@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Define custom queries or methods if needed
	@Query(nativeQuery = true,value = "select * from books where title like %:title% ")
	List<Book> getBookByTitle(@Param("title")String title);
	
	
	@Query(nativeQuery = true,value = "select * from books where category like %:category%")
	List<Book> getBookByCategory(@Param("category")String category);
	
	@Query(nativeQuery = true,value = "select * from books inner join author on books.authorID = author.authorID where author_Name like %:author_Name%")
	List<Book> getBookByAuthor(@Param("author_Name")String author_Name);
	
	
	
}

