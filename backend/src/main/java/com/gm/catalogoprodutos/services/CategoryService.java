package com.gm.catalogoprodutos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.catalogoprodutos.Repositories.CategoryRepository;
import com.gm.catalogoprodutos.entities.Category;

@Service 
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
}
