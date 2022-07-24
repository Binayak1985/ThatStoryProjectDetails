package com.startup.thatstory.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MultimediaResponseDTO {

	@JsonProperty("bucketname")
	private String bucketname;
	@JsonProperty("filename")
	private String filename;
	@JsonProperty("timestamp")
	private Date timestamp;
}
