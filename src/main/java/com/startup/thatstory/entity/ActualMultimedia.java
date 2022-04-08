package com.startup.thatstory.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ActualMultimedia {

	private String filename;
	private String url;
	private Date createdate;
	private String createdby;
}
