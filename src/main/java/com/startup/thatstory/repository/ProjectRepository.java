package com.startup.thatstory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.startup.thatstory.entity.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String>{

	Project findByTitle(String title); 
}
