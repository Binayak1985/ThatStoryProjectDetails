package com.startup.thatstory.entity;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Project {
	@Id
	private String _Id;
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

}
