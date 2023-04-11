package com.gm.catalogoprodutos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gm.catalogoprodutos.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	 
}
