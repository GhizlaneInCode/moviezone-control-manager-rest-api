package com.giantlink.introduction.entities;


import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
@Builder
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	
	
	private Date releaseDate;
	private int duration;
	private String originalLanguage;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	//@JsonManagedReference
	private Director director;
	
	//fetch = FetchType.EAGER
	@OneToMany(mappedBy = "movie",cascade = CascadeType.REMOVE)
	//@JsonBackReference
	private List<Image> mvImages;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Categorie> mvCategories;
	
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Actor>  mvActors;
	
	
	@PrePersist
	private void onCreate() {
		this.releaseDate = new Date();
	}

	
}
