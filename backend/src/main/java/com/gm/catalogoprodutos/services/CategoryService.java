package com.gm.catalogoprodutos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gm.catalogoprodutos.Repositories.CategoryRepository;
import com.gm.catalogoprodutos.dto.CategoryDTO;
import com.gm.catalogoprodutos.entities.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {

		List<Category> categorias = repository.findAll();

		return categorias.stream().map(item -> new CategoryDTO(item)).collect(Collectors.toList());
	}
}
