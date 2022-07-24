package com.startup.thatstoryproject.controller;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import com.startup.thatstory.dto.ProjectRequestDTO;
//import com.startup.thatstory.entity.Project;
//import com.startup.thatstory.service.ProjectService;
//import com.startup.thatstory.service.SequenceService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ThatStoryControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	ProjectService projectsvc;
	
//	@MockBean
	//SequenceService sequencesvc;
	
	
	//ProjectRequestDTO projectrerequestdto = ProjectRequestDTO.builder().Id("1").build();
	
	
	@Test
	public void getProject_shouldReturnAllFieldsTest() throws Exception
	{	
		//Arrange
//		given(projectsvc.getProjectDetails()).willReturn(new Project().builder()
//																 .title("My First Project")
//				                                                 .build());
//	
		
		//Act
//		mockMvc.perform(MockMvcRequestBuilders.get("/project?projectId=1&versionId=1"))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.jsonPath("title").value("My First Project"));
	
		//Assert
	
	}
	
	@Test
	public void getSequence_shouldReturnAllFieldsTest() throws Exception
	{	
		//Arrange
	/*	given(sequencesvc.getAllSequence()).willReturn(SequenceResponseDTO.builder()
																			  .title("My First Project")
				                                                              .build());
	*/
		//Act
	//	mockMvc.perform(MockMvcRequestBuilders.get("/project?projectId=1&versionId=1"))
		//.andExpect(MockMvcResultMatchers.status().isOk())
		//.andExpect(MockMvcResultMatchers.jsonPath("title").value("My First Project"));
	
		//Assert
	
	}
}
