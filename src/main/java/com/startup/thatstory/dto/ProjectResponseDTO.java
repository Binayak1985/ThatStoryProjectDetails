package com.startup.thatstory.dto;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.startup.thatstory.entity.ActualMultimedia;
import com.startup.thatstory.entity.Project;
import com.startup.thatstory.entity.Sequences;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class ProjectResponseDTO {


	private String id;
	private String title;
	private String description;
	private Date plannedstartdate;
	private Date plannedenddate;
	private Date actualstartdate;
	private Date actualenddate;
	private String userid;
	private String changeby;
	private String changedate;
	private String createdby;
	private Date createddate;
	private List<Sequences> sequences;

	public ProjectResponseDTO(Project project) {

		this.id = project.get_Id();
		this.title = project.getTitle();
		this.description = project.getDescription();
		this.plannedenddate = project.getPlannedenddate();
		this.plannedstartdate = project.getPlannedstartdate();
		this.actualstartdate = project.getActualstartdate();
		this.actualenddate = project.getActualenddate();
		this.userid = project.getUserid();
		this.changeby = project.getChangeby();
		this.changedate = project.getChangedate();
		this.createdby = project.getCreatedby();
		this.createddate = project.getCreateddate();
		this.sequences = project.getSequences();

		
	}
	
	
}