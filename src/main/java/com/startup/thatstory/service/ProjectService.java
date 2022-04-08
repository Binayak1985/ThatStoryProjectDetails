package com.startup.thatstory.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.startup.thatstory.customexception.CustomObjectNotFoundException;
import com.startup.thatstory.dto.ProjectRequestDTO;
import com.startup.thatstory.entity.Project;
import com.startup.thatstory.repository.ProjectRepository;

import lombok.Data;

@Data
@Service
public class ProjectService {

	ProjectRepository projectrepo;
	ProjectRequestDTO projectrequestdto;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public ProjectService(ProjectRequestDTO projectrequestdto, ProjectRepository projectrepo) {
		this.projectrepo = projectrepo;
		this.projectrequestdto = projectrequestdto;
	}

	public Project getProjectDetails() {
		logger.debug("inside getProjectDetails , _id is  :: " + projectrequestdto.getId());

		Project project = projectrepo.findById(projectrequestdto.getId()).get();
		if (project!=null) {
			return project;
		} else {
			throw new CustomObjectNotFoundException("Projectid Not found");
		}

	}

	public Project addProjectDetails()
	{
		
		Project project = new Project().builder()._Id(projectrequestdto.getId())
				                                 .title(projectrequestdto.getTitle())
				                                 .sequences(projectrequestdto.getSequences())
				                                 .build();
		
		
		//projectrequestdto.getSequences().stream().forEach(sequence-> logger.debug(sequence.getActualmultimedia().getUrl()));
		
		projectrepo.save(project);
		
		return project;
		
	}
	
	public Project updateAndSaveProjectDetails() throws Exception
	{
		
		
		
		Optional<Project> projecttemp = projectrepo.findById(projectrequestdto.getId());
		if(projecttemp.isPresent())
		{
		Project project = projecttemp.get();
		project.setActualenddate(projectrequestdto.getActualenddate());
		project.setActualstartdate(projectrequestdto.getActualstartdate());
		project.setChangeby(projectrequestdto.getChangeby());
		project.setChangedate(projectrequestdto.getChangedate());
		project.setCreatedby(projectrequestdto.getCreatedby());
		project.setCreateddate(projectrequestdto.getCreateddate());
		project.setDescription(projectrequestdto.getDescription());
		project.setPlannedenddate(projectrequestdto.getPlannedenddate());
		project.setPlannedstartdate(projectrequestdto.getPlannedstartdate());
		project.setSequences(projectrequestdto.getSequences());
		project.setTitle(projectrequestdto.getTitle());
		project.setUserid(projectrequestdto.getUserid());
		projectrepo.save(project);
		return project;
		}
		else
		{
			throw new CustomObjectNotFoundException("Projectid Not found");
		}
		
		
	}

	
	public Project deleteAndSaveProject() throws Exception
	{
		
		
		
		Optional<Project> projecttemp = projectrepo.findById(projectrequestdto.getId());
		if(projecttemp.isPresent())
		{
		Project project = projecttemp.get();
	    projectrepo.delete(project);
		return project;
		}
		else
		{
			throw new CustomObjectNotFoundException("Projectid Not found");
		}
		
		
	}

}
