package com.startup.thatstory.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.startup.thatstory.dto.ProjectResponseDTO;
import com.startup.thatstory.repository.ProjectRepository;

@Configuration
public class ThatStoryProjectDetailConfiguration {

	@Autowired
	ProjectRepository projectrepo;
	
	@Bean
	public ProjectResponseDTO getProjectResponseDTO()
	{
		return new ProjectResponseDTO();
	}
	
}