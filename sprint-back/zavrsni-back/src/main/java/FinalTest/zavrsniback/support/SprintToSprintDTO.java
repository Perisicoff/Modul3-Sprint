package FinalTest.zavrsniback.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import FinalTest.zavrsniback.model.Sprint;
import FinalTest.zavrsniback.web.dto.SprintDTO;

@Component
public class SprintToSprintDTO implements Converter<Sprint, SprintDTO> {

	@Override
	public SprintDTO convert(Sprint sprint) {
		SprintDTO sprintDTO = new SprintDTO();

		sprintDTO.setId(sprint.getId());
		sprintDTO.setIme(sprint.getIme());
		sprintDTO.setUkupnoBodova(sprint.getUkupnoBodova());

		return sprintDTO;
	}

	public List<SprintDTO> convert(List<Sprint> sprintovi) {
		List<SprintDTO> sprintDTOS = new ArrayList<>();

		for (Sprint sprint : sprintovi) {
			sprintDTOS.add(convert(sprint));
		}

		return sprintDTOS;
	}

}
