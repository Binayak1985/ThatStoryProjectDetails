package com.startup.thatstory.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.startup.thatstory.dto.MultimediaResponseDTO;
import com.startup.thatstory.dto.ProjectRequestDTO;
import com.startup.thatstory.dto.ProjectResponseDTO;
import com.startup.thatstory.entity.Project;
import com.startup.thatstory.repository.ProjectRepository;
import com.startup.thatstory.service.MultimediaService;
import com.startup.thatstory.service.ProjectService;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@RestController
@EnableFeignClients
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class ThatStoryProjectDetailsController {

	@Autowired
	MultimediaService multimediasvc;

	@Autowired
	ProjectRepository projectrepo;

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	
	@GetMapping("/profile")
	public Info getProfile(OAuth2AuthenticationToken authenticationtoken, HttpServletRequest request, HttpServletResponse response) {
				
		return new Info()
                .setApplication("tutorial-social-logins")
                .setPrincipal(authenticationtoken.getPrincipal().getAttributes());

	}

	@GetMapping("/project")
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> getProjectDetails(ProjectRequestDTO projectrequestdto) throws Exception {
		// ProjectResponseDTO projectresponsedto = new ProjectResponseDTO();
		// projectresponsedto.setProjectrequestdto(projectrequestdto);

		log.debug("_id is :: " + projectrequestdto.getId());
		ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
		Optional<ProjectResponseDTO> projectresponsedtotemp = Optional
				.of(new ProjectResponseDTO(projsvc.getProjectDetails()));
		if (projectresponsedtotemp.isPresent()) {
			return ResponseEntity.ok(projectresponsedtotemp.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping(value = "/project", consumes = { MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> postProjectDetails(@RequestPart("bucketname") String bucketname,
			@RequestPart("filename") String filename,
			@RequestPart("projectrequestdto") ProjectRequestDTO projectrequestdto,
			@RequestPart("file") MultipartFile file) throws Exception {

		log.debug("projectid is :: " + projectrequestdto.getId());

		ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
		Project project = projsvc.addProjectDetails();

		MultimediaResponseDTO multimediaresponse = multimediasvc.uploadMultimedia(bucketname, filename, file);
		// logger.debug("filename is :: "+file.getOriginalFilename());

		log.debug(multimediaresponse.toString());
		if (project == null) {
			return ResponseEntity.ok().body(new ProjectResponseDTO(project));
		} else {
			return ResponseEntity.ok().body(new ProjectResponseDTO(project));
		}

	}

	@PutMapping("/project")
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> putProjectDetails(@RequestBody ProjectRequestDTO projectrequestdto)
			throws Exception {

		log.debug("projectid is :: " + projectrequestdto.getId());

		ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
		Project project = projsvc.updateAndSaveProjectDetails();

		if (project == null) {
			return ResponseEntity.ok().body(new ProjectResponseDTO(project));
		} else {
			return ResponseEntity.ok().body(new ProjectResponseDTO(project));
		}

	}

	@DeleteMapping("/project")
	@ResponseBody
	public ResponseEntity<ProjectResponseDTO> deleteProject(@RequestBody ProjectRequestDTO projectrequestdto)
			throws Exception {

		log.debug("projectid is :: " + projectrequestdto.getId());

		ProjectService projsvc = new ProjectService(projectrequestdto, projectrepo);
		Project project = projsvc.deleteAndSaveProject();

		if (project == null) {
			return ResponseEntity.ok().body(new ProjectResponseDTO(project));
		} else {
			return ResponseEntity.ok().body(new ProjectResponseDTO(project));
		}

	}

	@PostMapping(value = "/multimedia", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public MultimediaResponseDTO postmultimedia(@RequestPart("bucketname") String bucketname,
			@RequestPart("filename") String filename, @RequestPart("file") MultipartFile file) {
		multimediasvc.uploadMultimedia(bucketname, filename, file);
		// logger.debug("filename is "+file.getOriginalFilename());
		return MultimediaResponseDTO.builder().bucketname("test").filename(file.getOriginalFilename()).build();
	}

	@SneakyThrows
	@GetMapping("")
	public void loginRedirect(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient, OAuth2AuthenticationToken token, HttpServletRequest request, HttpServletResponse response) {
			
		  
		
		  response.sendRedirect("http://localhost:3000/projectlist");
	}

	@ExceptionHandler(value = org.springframework.dao.DuplicateKeyException.class)
	public ResponseEntity<String> exception(org.springframework.dao.DuplicateKeyException ex) {
		return new ResponseEntity<>("ProjectId and versionId combination already exist", HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request)
	{
		logger.debug("/logout cicked");
		request.getSession().invalidate();
		return ResponseEntity.ok().body("loggedout");
	}
	
	@Data
    @Accessors(chain = true)
    private static class Info {
        private String application;
        private Map<String, Object> principal;
    }
	

}
