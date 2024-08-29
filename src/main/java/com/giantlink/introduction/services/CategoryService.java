package com.giantlink.introduction.services;

import java.util.List;

import com.giantlink.introduction.entities.Categorie;
import com.giantlink.introduction.models.requests.CategorieRequest;

public interface CategoryService {

	Categorie add(CategorieRequest categorie);

	List<Categorie> getAll();

	Categorie get(Long id);

	void delete(Long id);
}
