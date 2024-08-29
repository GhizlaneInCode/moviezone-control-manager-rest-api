package com.giantlink.introduction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giantlink.introduction.entities.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

	Optional<Director> findByName(String name); //select where name =?
}
