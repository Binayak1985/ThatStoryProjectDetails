package com.startup.thatstory.dto;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.startup.thatstory.entity.Sequence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class ProjectRequestDTO {

	private String Id;
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
	//private List<Sequence> sequences;

	
}