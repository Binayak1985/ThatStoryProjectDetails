package com.startup.thatstory.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.startup.thatstory.dto.MultimediaResponseDTO;
import com.startup.thatstory.restclient.MultimediaRestClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MultimediaService {

	@Autowired
	MultimediaRestClient multimediarestclient;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@CircuitBreaker(name="uploadMultimedia", fallbackMethod="uploadMultimediaFallback")
	public MultimediaResponseDTO uploadMultimedia(String bucketname, String filename, MultipartFile file)
	{
		logger.debug(" uploaded filename is :: "+file.getOriginalFilename());
		return  multimediarestclient.uploadPic(bucketname, filename, file);
		
//		return  multimediarestclient.uploadPic(file);
		
	}
	
	
	public MultimediaResponseDTO uploadMultimediaFallback(Exception e)
	{
		
		return MultimediaResponseDTO.builder().bucketname("Not Available").filename("Not Available").timestamp(new Date()).build();
	}
	
	
	
	
//	public MultimediaResponseDTO deleteMultimedia(String bucketname, String filename, MultipartFile file)
//	{
//		return  multimediarestclient.uploadPic(bucketname, filename, file);
//		
//		
//	}
	
//	public MultimediaResponseDTO updateMultimedia(String bucketname, String filename, MultipartFile file)
//	{
//		return  multimediarestclient.uploadPic(bucketname, filename, file);
//		
//	}
//	
	
}
