package com.example.demo.service;



import java.text.ParseException;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepo userRepo;
	private final ModelMapper modalMapper;
	
	
	private User mapToEntity(UserDTO userDTO) {
		User us = modalMapper.map(userDTO, User.class);
		if (us.getId() == null) {
			us.setUserId(UUID.randomUUID().toString());
		}
		return us;
		
	}
	
	
	public UserDTO saveUserDetails(UserDTO userDTO) {
		User us = mapToEntity(userDTO);
		us = userRepo.save(us);
		return mapToDTO(us);
		
		
	}
	
	private UserDTO mapToDTO(User user) {
		UserDTO userDT = modalMapper.map(user, UserDTO.class);
		return userDT;
	}
	
	
	public List<UserDTO> getAllUsers(){
		List<User> user = userRepo.findAll();
		List<UserDTO> userList = user.stream().map(this :: mapToDTO).collect(Collectors.toList());
		return userList;
	}
	
	
	public void deleteUser(Long id) {
		User user  = userRepo.findById(id).orElseThrow(() ->  new RuntimeException("not found id" + id));
		userRepo.delete(user);
	}
	
	
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		User existingUser  = userRepo.findById(id).orElseThrow(() ->  new RuntimeException("not found id" + id));
		
		existingUser.setName(userDTO.getName());
		existingUser.setId(userDTO.getId());
		existingUser.setEmail(userDTO.getEmail());
		existingUser.setUserId(userDTO.getUserId());
		
		// save the updated expense
		User saveUser = userRepo.save(existingUser);
		
		UserDTO user = mapToDTO(saveUser);
		
		return user;
		
		
	}
	
	
	
	
	
}
