package com.startup.thatstory.service;

import java.util.ArrayList;
import java.util.Comparator;
//import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.startup.thatstory.customexception.CustomObjectNotFoundException;
import com.startup.thatstory.dto.ProjectRequestDTO;
import com.startup.thatstory.dto.sequence.SequenceRequestDTO;
import com.startup.thatstory.entity.Project;
import com.startup.thatstory.entity.Sequence;
import com.startup.thatstory.repository.ProjectRepository;

import lombok.Data;

@Data
// @Service
public class ProjectService {
	
	ProjectRepository projectrepo;
	ProjectRequestDTO projectrequestdto;
	OAuth2AuthenticationToken authenticationToken;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public ProjectService(ProjectRequestDTO projectrequestdto, ProjectRepository projectrepo, OAuth2AuthenticationToken authenticationToken) {
		this.projectrepo = projectrepo;
		this.projectrequestdto = projectrequestdto;
		this.authenticationToken = authenticationToken;
	}

	public ProjectService(ProjectRepository projectrepo, OAuth2AuthenticationToken authenticationToken) {
		this.projectrepo = projectrepo;
		this.authenticationToken = authenticationToken;
	}

	public List<Project> getProjectbyCreatedBy(String createdby) {

		logger.debug("createdby is "+createdby);
		List<Project> project = projectrepo.findProjectsByCreatedby(createdby);
		return project;
	

	}

	public List<Project> getProjectDetails(String _id) {
		logger.debug("inside getProjectDetails , _id is  :: " + _id);

		Project project = projectrepo.findById(_id).get();
		if (project != null) {
			List<Project> projectlist = new ArrayList<Project>();
			projectlist.add(project);
			{
				return projectlist;
			}
		} else {
			throw new CustomObjectNotFoundException("Projectid Not found");
		}

	}

	public List<Project> addProjectDetails() {

		List<Project> projectlist = new ArrayList<Project>();
		
		logger.debug(" email :: "+authenticationToken.getPrincipal().getAttributes());
		logger.debug(" project description is "+projectrequestdto.getDescription());
		logger.debug(" project title is "+projectrequestdto.getTitle());
		Project project = Project.builder().title(projectrequestdto.getTitle()).description(projectrequestdto.getDescription()).createdby(authenticationToken.getPrincipal().getAttribute("email")).changeby(authenticationToken.getPrincipal().getAttribute("email")).build();
		
//		Project project = Project.builder().title(projectrequestdto.getTitle()).description(projectrequestdto.getDescription()).createdby("binayakchakraborty1@gmail.com").changeby("binayakchakraborty1@gmail.com").build();
		
//		Date currentdate = new java.util.Date();
//		project.setCreateddate(currentdate);
//		project.setCreateddate(currentdate);

		// projectrequestdto.getSequences().stream().forEach(sequence->
		// logger.debug(sequence.getActualmultimedia().getUrl()));
		
		projectlist.add(projectrepo.save(project));
        return projectlist;

	}

	public List<Project> updateAndSaveProjectDetails() throws Exception {

		List<Project> projectlist = new ArrayList<Project>();
		Optional<Project> projecttemp = projectrepo.findById(projectrequestdto.getId());
		if (projecttemp.isPresent()) {
			Project project = projecttemp.get();
			project.setActualenddate(projectrequestdto.getActualenddate());
			project.setActualstartdate(projectrequestdto.getActualstartdate());
			project.setChangeby("binayakchakraborty1@gmail.com");
			project.setChangedate(new java.util.Date());
			project.setDescription(projectrequestdto.getDescription());
			project.setPlannedenddate(projectrequestdto.getPlannedenddate());
			project.setPlannedstartdate(projectrequestdto.getPlannedstartdate());
//			project.setSequences(projectrequestdto.getSequences());
			project.setTitle(projectrequestdto.getTitle());
			project.setUserid(projectrequestdto.getUserid());
			projectlist.add(projectrepo.save(project));
			return projectlist;
		} else {
			throw new CustomObjectNotFoundException("Projectid Not found");
		}

	}

	public List<Project> deleteAndSaveProject(String _id) throws Exception {

		List<Project> projectlist = new ArrayList<Project>();

		Optional<Project> projecttemp = projectrepo.findById(_id);
		if (projecttemp.isPresent()) {
			Project project = projecttemp.get();
			projectrepo.delete(project);
			projectlist.add(project);
			return projectlist;
		} else {
			throw new CustomObjectNotFoundException("Projectid Not found");
		}

	}

	public Sequence getSequenceDetails(String projectId, Integer sequencenum) throws Exception {

		Optional<Project> projecttemp = projectrepo.findById(projectId);
		if (projecttemp.isPresent()) {
			Project project = projecttemp.get();
			List<Sequence> sequencelist = project.getSequences();
			Optional<Sequence> sequenceedetail = sequencelist.stream()
					.filter(sequence -> sequence.getSequencenum() == sequencenum).findFirst();
			if (sequenceedetail.isPresent()) {
				return sequenceedetail.get();
			}

		}

		throw new CustomObjectNotFoundException("Projectid Not found");
	}

	public Sequence addSequenceDetails(SequenceRequestDTO sequencereqdto) throws Exception {

		Optional<Project> projecttemp = projectrepo.findById(sequencereqdto.get_id());
		if (projecttemp.isPresent()) {
			Project project = projecttemp.get();
			List<Sequence> sequencelist = project.getSequences();
			Optional<Sequence> sequenceedetail = sequencelist.stream()
					.max(Comparator.comparingInt(Sequence::getSequencenum));
			if (sequenceedetail.isPresent()) {
				Sequence sequence = sequenceedetail.get();
				int maxseq = sequence.getSequencenum();
				Sequence newseq = new Sequence();
				newseq.setShortdescription(sequencereqdto.getShortdescription());
				newseq.setInstructions(sequencereqdto.getInstructions());
				newseq.setSequencenum(maxseq+1);
				newseq.setMultimediaurl("test url");
				sequencelist.add(newseq);
				project.setSequences(sequencelist);
				projectrepo.save(project);
				return newseq;
			}

		}

		throw new CustomObjectNotFoundException("Projectid Not found");
	}

	public Sequence updateSequenceDetails(SequenceRequestDTO sequencereqdto) throws Exception {

		Optional<Project> projecttemp = projectrepo.findById(sequencereqdto.get_id());
//		final Sequence newseq;
		if (projecttemp.isPresent()) {
			Project project = projecttemp.get();
			List<Sequence> sequencelist = project.getSequences();
			sequencelist.stream().forEach((seq) -> {
				if (seq.getSequencenum() == sequencereqdto.getSequencenum()) {
					seq.setShortdescription(sequencereqdto.getShortdescription());
					seq.setInstructions(sequencereqdto.getInstructions());
					seq.setMultimediaurl("dummy url");
}
			});
			project.setSequences(sequencelist);
			projectrepo.save(project);
			return getSequenceDetails(sequencereqdto.get_id(),sequencereqdto.getSequencenum());
		}

		throw new CustomObjectNotFoundException("Sequence Not found");
	}
	
	public List<Project> deleteSequenceDetails(String _id , Integer sequencenum) throws Exception {

		logger.debug("projectid deleteing:: "+_id);
		Optional<Project> projecttemp = projectrepo.findById(_id);
//		final Sequence newseq;
		if (projecttemp.isPresent()) {
			Project project = projecttemp.get();
			List<Sequence> sequencelist = project.getSequences();
			List<Sequence> newsequencelist= sequencelist.stream().filter(seq -> seq.getSequencenum()!=sequencenum).collect(Collectors.toList());
			project.setSequences(newsequencelist);
			projectrepo.save(project);
			return getProjectDetails(_id);
		}

		throw new CustomObjectNotFoundException("Project Not found");
	}

}