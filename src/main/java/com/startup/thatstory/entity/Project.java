package com.startup.thatstory.entity;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
	private Date changedate;
	private String createdby;
	private Date createddate;
	@Value("DRAFT")
	private String status;
	private List<Sequence> sequences;

	public void setCreatedby(String email)
	{
		this.createdby = email;
	}
	
	public void setChangedby(String email)
	{
		this.changeby = email;
	}
	
	public void setChangedate()
	{
		this.changedate = new java.util.Date();
	}
	
	
	public void setCreatedateby(Date email)
	{
		this.createddate = new java.util.Date();
	}
}
