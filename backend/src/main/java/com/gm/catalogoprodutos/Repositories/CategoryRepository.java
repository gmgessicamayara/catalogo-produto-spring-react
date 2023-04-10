package com.gm.catalogoprodutos.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gm.catalogoprodutos.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	 
}
