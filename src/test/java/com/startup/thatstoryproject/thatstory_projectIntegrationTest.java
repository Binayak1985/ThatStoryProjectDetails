package com.startup.thatstoryproject;

import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.startup.thatstory.configuration.ThatStoryProjectDetailConfiguration;
import com.startup.thatstory.dto.ProjectResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ThatStoryProjectDetailConfiguration.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
public class thatstory_projectIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	int port;
	
	@Test
	public void getProjectDetails_ShouldReturnProjectJSONTest() throws Exception
	{
		//Arrange
	
		HashMap<String, String> paramMap = new HashMap();
		paramMap.put("projectId", "1");
		paramMap.put("versionId", "1");
		//Act
		ResponseEntity<ProjectResponseDTO> responseEntity = testRestTemplate.getForEntity("http://localhost:"+port+"/project?projectId=1&versionId=1", ProjectResponseDTO.class);
	
	    //Assert
	    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    Assertions.assertEquals("My First Project", responseEntity.getBody().getTitle());
	}	

	
}
