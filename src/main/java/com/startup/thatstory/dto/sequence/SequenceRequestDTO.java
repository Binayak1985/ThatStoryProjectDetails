package com.startup.thatstory.dto.sequence;


import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class SequenceRequestDTO {

	private String _id;
	private String shortdescription; 
	private Integer sequencenum;
	//private Integer sequencenum; 
	//private String changeby;
	//private Date changedate;
	//private String createdby;
	//private Date createddate;
	private String instructions;
	
}
