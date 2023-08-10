package FinalTest.zavrsniback.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import FinalTest.zavrsniback.model.Zadatak;
import FinalTest.zavrsniback.service.SprintService;
import FinalTest.zavrsniback.service.StanjeService;
import FinalTest.zavrsniback.service.ZadatakService;
import FinalTest.zavrsniback.web.dto.ZadatakDTO;

@Component
public class ZadatakDTOToZadatak implements Converter<ZadatakDTO, Zadatak> {

	@Autowired
	private ZadatakService zadatakService;

	@Autowired
	private StanjeService stanjeService;

	@Autowired
	private SprintService sprintService;

	@Override
	public Zadatak convert(ZadatakDTO zadatakDTO) {
		Zadatak zadatak;

		if (zadatakDTO.getId() == null) {
			zadatak = new Zadatak();
		} else {
			zadatak = zadatakService.findOne(zadatakDTO.getId());
		}

		if (zadatak != null) {
			zadatak.setBodovi(zadatakDTO.getBodovi());
			zadatak.setIme(zadatakDTO.getIme());
			zadatak.setZaduzeni(zadatakDTO.getZaduzeni());
			zadatak.setSprint(sprintService.findOne(zadatakDTO.getSprintId()));
			if (zadatakDTO.getId() != null) {
				zadatak.setStanje(stanjeService.findOne(zadatakDTO.getStanjeId()));		
			}
		}
		return zadatak;
	}

}
