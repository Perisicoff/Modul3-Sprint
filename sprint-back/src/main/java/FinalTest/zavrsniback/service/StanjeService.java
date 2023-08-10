package FinalTest.zavrsniback.service;

import java.util.List;

import FinalTest.zavrsniback.model.Stanje;

public interface StanjeService {

	Stanje findOne(Long id);

	List<Stanje> findAll();
}
