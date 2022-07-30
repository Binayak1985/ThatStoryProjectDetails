package com.startup.thatstory.dto.sequence;


import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class SequenceRequestDTO {

	private String _id;
	@NotBlank(message="description cant be blank")
	private String shortdescription; 
	private Integer sequencenum;
	private String multimediaurl; 
	//private Integer sequencenum; 
	//private String changeby;
	//private Date changedate;
	//private String createdby;
	//private Date createddate;
	private String instructions;
	
}
