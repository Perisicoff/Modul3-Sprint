package FinalTest.zavrsniback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import FinalTest.zavrsniback.model.Stanje;
import FinalTest.zavrsniback.repository.StanjeRepository;
import FinalTest.zavrsniback.service.StanjeService;

@Service
public class JPAStanjeService implements StanjeService {

	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public List<Stanje> findAll() {
		return stanjeRepository.findAll();
	}

	@Override
	public Stanje findOne(Long id) {
		Optional<Stanje> stanje = stanjeRepository.findById(id);
		if (stanje.isPresent()) {
			return stanje.get();
		}
		return null;
	}

}
