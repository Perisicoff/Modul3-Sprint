package FinalTest.zavrsniback.service;

import java.util.List;

import org.springframework.data.domain.Page;

import FinalTest.zavrsniback.model.Zadatak;

public interface ZadatakService {

	Zadatak findOne(Long id);

	List<Zadatak> findAll();

	Page<Zadatak> findAll(int brojStranice);
	
	Page<Zadatak> search(String ime, Long sprintId, int brojStranice);

	Zadatak save(Zadatak zadatak);
	
	Zadatak update(Zadatak zadatak);
	
	Zadatak promenaStanja(Zadatak zadatak);

	Zadatak delete(Long id);
}
