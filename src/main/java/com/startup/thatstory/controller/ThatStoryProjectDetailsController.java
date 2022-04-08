package com.startup.thatstory.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.startup.thatstory.dto.ProjectRequestDTO;
import com.startup.thatstory.dto.ProjectResponseDTO;
import com.startup.thatstory.entity.Project;
import com.startup.thatstory.repository.ProjectRepository;
import com.startup.thatstory.service.ProjectService;

@RestController
public class ThatStoryProjectDetailsController {
		    
    @Autowired
    ProjectRepository projectrepo;
	
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping("/project")
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> getProjectDetails(ProjectRequestDTO projectrequestdto) throws Exception
	{
		//ProjectResponseDTO projectresponsedto = new ProjectResponseDTO();
		//projectresponsedto.setProjectrequestdto(projectrequestdto);
		 	
    	logger.debug("_id is :: "+projectrequestdto.getId());
		ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
    	Optional<ProjectResponseDTO> projectresponsedtotemp = Optional.of(new ProjectResponseDTO(projsvc.getProjectDetails()));
		if(projectresponsedtotemp.isPresent())
		{
			return  ResponseEntity.ok(projectresponsedtotemp.get());	
		}
		else
		{
			return  ResponseEntity.notFound().build();
		}
		
		
	}
    
    @PostMapping("/project")
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> postProjectDetails(@RequestBody ProjectRequestDTO projectrequestdto) throws Exception
	{
		 	
    	logger.debug("projectid is :: "+projectrequestdto.getId());
		
    	ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
    	Project project = projsvc.addProjectDetails();
    	
    	if (project==null)
    	{
    		return ResponseEntity.ok().body(new ProjectResponseDTO(project));
    	}
    	else
    	{
    		return ResponseEntity.ok().body(new ProjectResponseDTO(project));	
    	}
		
	}

    
    @PutMapping("/project")
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> putProjectDetails(@RequestBody ProjectRequestDTO projectrequestdto) throws Exception
	{
		 	
    	logger.debug("projectid is :: "+projectrequestdto.getId());
		
    	ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
    	Project project = projsvc.updateAndSaveProjectDetails();
    			
    	
    	if (project==null)
    	{
    		return ResponseEntity.ok().body(new ProjectResponseDTO(project));
    	}
    	else
    	{
    		return ResponseEntity.ok().body(new ProjectResponseDTO(project));	
    	}
		
	}
    

    @DeleteMapping("/project")
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> deleteProject(@RequestBody ProjectRequestDTO projectrequestdto) throws Exception
	{
		 	
    	logger.debug("projectid is :: "+projectrequestdto.getId());
		
    	ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
    	Project project = projsvc.deleteAndSaveProject();
    			
    	
    	if (project==null)
    	{
    		return ResponseEntity.ok().body(new ProjectResponseDTO(project));
    	}
    	else
    	{
    		return ResponseEntity.ok().body(new ProjectResponseDTO(project));	
    	}
		
	}
    

    
    @ExceptionHandler(value = org.springframework.dao.DuplicateKeyException.class)
    public ResponseEntity<String> exception(org.springframework.dao.DuplicateKeyException ex)
    {
    	 return new ResponseEntity<>("ProjectId and versionId combination already exist", HttpStatus.NOT_ACCEPTABLE);	
    }
}
