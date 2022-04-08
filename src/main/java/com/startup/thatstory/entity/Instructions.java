package com.startup.thatstory.entity;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Instructions {

	@Id
	private String id;
	private Integer sequencenum;
    private String type;//whether text or audio
    private String multimediaurl;//store url for audio

}
