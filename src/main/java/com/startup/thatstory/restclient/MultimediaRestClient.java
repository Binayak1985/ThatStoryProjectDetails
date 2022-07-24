package com.startup.thatstory.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.startup.thatstory.configuration.FeignClientEncoderConfiguration;
import com.startup.thatstory.dto.MultimediaResponseDTO;

import feign.Headers;

//since this project is eurakaclient enabled, fire EUREKA discovery with service name to get the url based on the name of service
@FeignClient(value="THATSTORY-MULTIMEDIA", configuration=FeignClientEncoderConfiguration.class)

public interface MultimediaRestClient  {

	@PostMapping(value = "multimedia", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	//public MultimediaResponseDTO uploadPic(@RequestPart("file") MultipartFile file);
	
	public MultimediaResponseDTO uploadPic(@RequestPart("bucketname") String bucketname, @RequestPart("filename") String filename, @RequestPart("file") MultipartFile file);
}
