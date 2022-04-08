package com.startup.thatstory.dto;

import java.util.Date;
import com.startup.thatstory.entity.Sequences;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SequenceResponseDTO {
 
	
	 
	Integer sequencenum; 
	String shortdescription; 
	String multimediaurl; 
	String changeby;
	Date changedate;
    String createdby;
    Date createddate;

    public SequenceResponseDTO(Sequences sequence)
    {
    	
    	this.sequencenum = sequence.getSequencenum();
    	this.shortdescription = sequence.getShortdescription();
    	this.multimediaurl = sequence.getMultimediaurl();
    	this.changeby = sequence.getChangeby();
    	this.changedate = sequence.getChangedate();
    	this.createdby = sequence.getCreatedby();
    	this.createddate = sequence.getCreateddate();
    }
}
