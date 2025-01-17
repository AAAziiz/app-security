package com.example.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Admin;
import com.example.crud.repo.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository repo;
	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);
	}

}
