package com.gm.catalogoprodutos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gm.catalogoprodutos.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	 
}
