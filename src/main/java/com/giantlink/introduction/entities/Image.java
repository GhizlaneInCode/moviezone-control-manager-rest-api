package com.giantlink.introduction.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Images")
public class Image {

	/*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	*/
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	
	private String link;
	private String type;
	
	private Boolean isCover;
	
	/*
	@ManyToOne
	@JsonManagedReference
	private Movie movie;
	*/
	
	
	@Lob
	private byte[] file;
	private String fileName;

	@ManyToOne()
	@JoinColumn(name = "movie_id", nullable = false)
	//@JsonIgnoreProperties
	@JsonBackReference
	private Movie movie;

	
	
}
