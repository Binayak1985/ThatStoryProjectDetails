package com.startup.thatstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.startup.thatstory.dto.sequence.SequenceResponseDTO;
import com.startup.thatstory.entity.Sequence;
import com.startup.thatstory.repository.SequenceRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class SequenceService {
	
	
	SequenceRepository sequencerepo;
	/*
	public SequenceResponseDTO getAllSequence(String projectid) {


		List<Sequences> sequenceoptional = sequencerepo.findAllByProjectId(projectid);
		if(!sequenceoptional.isEmpty())
		{
		
			//sequenceoptional.stream().forEach(action);
			SequenceResponseDTO sequenceresponsedto = new SequenceResponseDTO(sequenceoptional.get());
			
		}
		else
		{
			
		}
		return null;

	}
		*/
}
