package com.example.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud.ResourceNotFoundException;
import com.example.crud.model.User;
import com.example.crud.repo.UserRepository;
import com.example.crud.service.EmailService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200") 

public class UserController {
	
	
	
	@Autowired
	private EmailService email;
	
	
	
	@Autowired
	private UserRepository repo;
	
	
	
	@PostMapping("/users")
	@CrossOrigin(origins = "http://localhost:4200") 
	public User addUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	
	@GetMapping("/usersget")
	@CrossOrigin(origins = "http://localhost:4200") 
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(repo.findAll());
	}
	
	 @GetMapping("/users/{id}")
	 @CrossOrigin(origins = "http://localhost:4200")
	 public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer userid)
	            throws ResourceNotFoundException {
    User user=repo.findById(userid)
    		.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userid));
    return ResponseEntity.ok().body(user);}
	 
	 
	 
	 @PutMapping("/users/{id}")
	    @CrossOrigin(origins = "http://localhost:4200")
	    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer userId,
	                                                   @RequestBody User userDetails) throws ResourceNotFoundException {
	        User user = repo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
	        user.setName(userDetails.getName());
	        user.setSurname(userDetails.getSurname());
	        user.setAge(userDetails.getAge());
	        user.setDate(userDetails.getDate());

	        final User updatedUser = repo.save(user);
	        return ResponseEntity.ok(updatedUser);
	    }

	    
	    
	    
	    @DeleteMapping("/users/{id}")
	    @CrossOrigin(origins = "http://localhost:4200")
	    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer userId)
	            throws ResourceNotFoundException {
	        User user = repo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
	        repo.delete(user);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	 
	 
	 
	 
	
	

	

	
	

}
