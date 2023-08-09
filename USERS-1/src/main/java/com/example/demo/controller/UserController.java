package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.demo.dto.UserDTO;

import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userSerice;
	

	
	@PostMapping("/saveusers")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		UserDTO saveUser = userSerice.saveUserDetails(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	

	
	@GetMapping("/allusers")
	public List<UserDTO> getAllUsers(){
		return userSerice.getAllUsers();
	}
	
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteExpense(@PathVariable String id) {
//        expenseService.deleteExpense(id);
//        return ResponseEntity.noContent().build();
//    }
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//		
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userSerice.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
//    @PutMapping("/{id}")
//    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable String id, @RequestBody ExpenseDTO updatedExpense) {
//        ExpenseDTO expense = expenseService.updateExpense(id, updatedExpense);
//        return ResponseEntity.ok(expense);
//    }
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		UserDTO user = userSerice.updateUser(id, userDTO);
		return ResponseEntity.ok(user);
	}
	
	

}
