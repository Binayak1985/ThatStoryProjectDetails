package com.startup.thatstory.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Sequences {

	@Id
//	@GeneratedValue 
	private UUID sequenceid; 
	private String shortdescription; 
	private String multimediaurl; 
	private Integer sequencenum; 
	private String changeby;
	private Date changedate;
	private String createdby;
	private Date createddate;
	private String instructions;
	private List<ActualMultimedia> actualmultimedia;
    
    
}
