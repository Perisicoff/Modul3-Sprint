package FinalTest.zavrsniback.service;

import java.util.List;

import FinalTest.zavrsniback.model.Sprint;

public interface SprintService {

	Sprint findOne(Long id);

	List<Sprint> findAll();

	Sprint save(Sprint sprint);
}
