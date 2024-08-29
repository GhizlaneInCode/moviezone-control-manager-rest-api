package com.giantlink.introduction.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.giantlink.introduction.entities.Categorie;
import com.giantlink.introduction.entities.Director;
import com.giantlink.introduction.models.requests.CategorieRequest;
import com.giantlink.introduction.models.requests.DirectorRequest;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	
	Categorie requestToCategorie(CategorieRequest categoryReq);

}
