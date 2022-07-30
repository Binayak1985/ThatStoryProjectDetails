package com.startup.thatstory.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.startup.thatstory.entity.Project;
import com.startup.thatstory.entity.Sequence;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String>{

	Project findByTitle(String title); 
	
	@Query("{'createdby':?0}")
	List<Project> findProjectsByCreatedby(String createdby);
}
