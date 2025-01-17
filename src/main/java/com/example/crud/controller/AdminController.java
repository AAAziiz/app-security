package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Admin;
import com.example.crud.repo.AdminRepository;
import com.example.crud.service.AdminService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")

public class AdminController {

	@Autowired
	private AdminRepository repo;
	
	@Autowired
	private AdminService service;
	
	@PostMapping("/registeradmin")
	@CrossOrigin(origins="http://localhost:4200")
	public Admin RegisterAdmin(@RequestBody Admin admin) throws Exception {
		String tempEmailId=admin.getEmail();
		if(tempEmailId!=null&&!"".equals(tempEmailId)) {	
			Admin userobj=repo.findByEmail(tempEmailId);
			if(userobj!=null) {
				throw new Exception("Admin with "+tempEmailId+"is already exists");
			}
		}
		Admin userObj=null;
		userObj=service.saveAdmin(admin);
		return userObj;
	}
	@PostMapping("/loginadmin")
	@CrossOrigin(origins="http://localhost:4200")
	public Admin loginAdmin(@RequestBody Admin admin) throws Exception {
		
		String tempEmailId =admin.getEmail();
		String tempassword=admin.getPassword();
		Admin adminObj=null;
		if(tempEmailId != null && tempassword !=null) {
			adminObj=repo.findByEmailAndPassword(tempEmailId, tempassword);
		}
		if(adminObj==null) {
			throw new Exception("User not found");
		}
		return adminObj;
	}

	
}
