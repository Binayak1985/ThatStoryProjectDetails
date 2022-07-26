package com.startup.thatstory.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.startup.thatstory.entity.Sequence;

public interface SequenceRepository extends MongoRepository<Sequence, String> {

//	@Query("{ 'projectid' : ?0 }")
//	public List<Sequences> findAllByProjectId(String projectid);
	
//	@Query("{ 'projectid' : ?0 }")
//	public List<Sequences> findByProjectIdAndSequencenum(String projectid, Sequencenum);
}
