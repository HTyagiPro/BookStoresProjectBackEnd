package com.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookStoreProject.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // Define custom queries or methods if needed
	@Query(nativeQuery = true,value = "select * from users where username=:username")
	Users getUserByUserName(@Param("username")String username);
}
