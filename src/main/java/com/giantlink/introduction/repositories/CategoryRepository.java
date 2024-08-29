package com.giantlink.introduction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giantlink.introduction.entities.Categorie;

public interface CategoryRepository extends JpaRepository<Categorie, Long> {
	
	Optional<Categorie> findByName(String name);

}
