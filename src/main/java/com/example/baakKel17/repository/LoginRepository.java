package com.example.baakKel17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.baakKel17.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
	User findByUsernameAndPassword(String username, String password);
}
