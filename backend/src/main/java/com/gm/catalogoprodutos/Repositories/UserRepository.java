package com.gm.catalogoprodutos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gm.catalogoprodutos.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	 User findByEmail(String email);
}
