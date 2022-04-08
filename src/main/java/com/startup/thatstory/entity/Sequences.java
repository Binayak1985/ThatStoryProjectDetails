package com.startup.thatstory.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Sequences {

	private Integer sequencenum; 
	private String shortdescription; 
	private String multimediaurl; 
	private Integer sequenceno; 
	private String changeby;
	private Date changedate;
	private String createdby;
	private Date createddate;
	private String instructions;
	private List<ActualMultimedia> actualmultimedia;
    
    
}
