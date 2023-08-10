package FinalTest.zavrsniback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import FinalTest.zavrsniback.model.Sprint;
import FinalTest.zavrsniback.model.Stanje;
import FinalTest.zavrsniback.model.Zadatak;
import FinalTest.zavrsniback.repository.SprintRepository;
import FinalTest.zavrsniback.repository.StanjeRepository;
import FinalTest.zavrsniback.repository.ZadatakRepository;
import FinalTest.zavrsniback.service.ZadatakService;

@Service
public class JPAZadatakService implements ZadatakService {

	@Autowired
	private ZadatakRepository zadatakRepository;
	
	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public Zadatak findOne(Long id) {
		Optional<Zadatak> zadatak = zadatakRepository.findById(id);
		if (zadatak.isPresent()) {
			return zadatak.get();
		}
		return null;
	}

	@Override
	public List<Zadatak> findAll() {
		return zadatakRepository.findAll();
	}

	@Override
	public Page<Zadatak> findAll(int brojStranice) {
		return zadatakRepository.findAll(PageRequest.of(brojStranice, 3));
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		if (zadatak.getId() == null) {
			Optional<Stanje> stanje = stanjeRepository.findById(1L);
			if (stanje.isPresent()) {
				zadatak.setStanje(stanje.get());
			}
			Sprint sprint = zadatak.getSprint();
			int noviBodovi = Integer.parseInt(sprint.getUkupnoBodova()) + zadatak.getBodovi();
			sprint.setUkupnoBodova(String.valueOf(noviBodovi));
			sprintRepository.save(sprint);
			return zadatakRepository.save(zadatak);
		}
		return null;
	}
	
	@Override
	public Zadatak update(Zadatak zadatak) {
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak delete(Long id) {
		Zadatak zadatak = findOne(id);
		if (zadatak != null) {
			Sprint sprint = zadatak.getSprint();
			int noviBodovi = Integer.parseInt(sprint.getUkupnoBodova()) - zadatak.getBodovi();
			sprint.setUkupnoBodova(String.valueOf(noviBodovi));		
			sprintRepository.save(sprint);
			zadatakRepository.deleteById(id);
			return zadatak;
		}
		return null;
	}

	@Override
	public Zadatak promenaStanja(Zadatak zadatak) {
		if (zadatak.getId() != null) {
			Stanje stanje = zadatak.getStanje();
			Long staroStanjeId = stanje.getId();
			if (staroStanjeId == 1L) {
				Stanje novoStanje = stanjeRepository.getOne(2L);
				zadatak.setStanje(novoStanje);
			}
			if (staroStanjeId == 2L) {
				Stanje novoStanje = stanjeRepository.getOne(3L);
				zadatak.setStanje(novoStanje);
			}
			zadatakRepository.save(zadatak);
		}
		return null;
	}

	@Override
	public Page<Zadatak> search(String ime, Long sprintId, int brojStranice) {
		return zadatakRepository.search(ime, sprintId, PageRequest.of(brojStranice, 2));
	}


}
