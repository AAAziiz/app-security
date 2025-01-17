package com.example.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	Admin findByEmail(String email);

	Admin findByEmailAndPassword(String email, String password);


}
