package com.giantlink.introduction.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantlink.introduction.entities.Categorie;
import com.giantlink.introduction.models.mapper.CategoryMapper;
import com.giantlink.introduction.models.requests.CategorieRequest;
import com.giantlink.introduction.repositories.CategoryRepository;
import com.giantlink.introduction.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Categorie add(CategorieRequest categorie) {
		
		Categorie newCategory = categoryMapper.requestToCategorie(categorie);
		categoryRepository.save(newCategory);
		return newCategory;
	}
	
	@Override
	public List<Categorie> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Categorie get(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		 categoryRepository.deleteById(id);
		
	}

	

}
