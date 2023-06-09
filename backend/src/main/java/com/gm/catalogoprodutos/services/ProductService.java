package com.gm.catalogoprodutos.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gm.catalogoprodutos.dto.CategoryDTO;
import com.gm.catalogoprodutos.dto.ProductDTO;
import com.gm.catalogoprodutos.entities.Category;
import com.gm.catalogoprodutos.entities.Product;
import com.gm.catalogoprodutos.repositories.CategoryRepository;
import com.gm.catalogoprodutos.repositories.ProductRepository;
import com.gm.catalogoprodutos.services.exceptions.DatabaseException;
import com.gm.catalogoprodutos.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {

		Page<Product> categorias = repository.findAll(pageRequest);
		return categorias.map(item -> new ProductDTO(item));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {

		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}

	}


	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear();
		for(CategoryDTO categoriaDTO : dto.getCategories()) {
			Category category = categoryRepository.getOne(categoriaDTO.getId()); 
			entity.getCategories().add(category);
		}
		
	}
}
