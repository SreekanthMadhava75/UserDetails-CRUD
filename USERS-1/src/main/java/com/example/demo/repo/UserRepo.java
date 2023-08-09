package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	//Optional<User> findById(Long id);
	
	

}
