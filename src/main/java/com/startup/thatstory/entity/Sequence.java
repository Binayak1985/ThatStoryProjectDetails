package com.startup.thatstory.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Sequence {
 
	@NotEmpty(message="sequence description is required")
	private String shortdescription; 
	@NotEmpty(message="multimedia file isn't specified")
	private String multimediaurl; 
	@NotBlank(message="sequencenum is required")
	@UniqueElements(message="sequencenum should be unique in a project")
	private Integer sequencenum; 
	private String changeby;
	private Date changedate;
	private String createdby;
	private Date createddate;
	private String instructions;
	//private List<ActualMultimedia> actualmultimedia;
   
    
}
