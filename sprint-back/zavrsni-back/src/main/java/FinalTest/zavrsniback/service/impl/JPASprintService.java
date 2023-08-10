package FinalTest.zavrsniback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FinalTest.zavrsniback.model.Sprint;
import FinalTest.zavrsniback.repository.SprintRepository;
import FinalTest.zavrsniback.service.SprintService;

@Service
public class JPASprintService implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public List<Sprint> findAll() {
		return sprintRepository.findAll();
	}

	@Override
	public Sprint findOne(Long id) {
		Optional<Sprint> sprint = sprintRepository.findById(id);
		if (sprint.isPresent()) {
			return sprint.get();
		}
		return null;
	}

	@Override
	public Sprint save(Sprint sprint) {
		return sprintRepository.save(sprint);
	}

}
