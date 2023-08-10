package FinalTest.zavrsniback.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import FinalTest.zavrsniback.model.Zadatak;
import FinalTest.zavrsniback.web.dto.ZadatakDTO;

@Component
public class ZadatakToZadatakDTO implements Converter<Zadatak, ZadatakDTO> {

	@Override
	public ZadatakDTO convert(Zadatak zadatak) {
		ZadatakDTO zadatakDTO = new ZadatakDTO();

		zadatakDTO.setId(zadatak.getId());
		zadatakDTO.setIme(zadatak.getIme());
		zadatakDTO.setBodovi(zadatak.getBodovi());
		zadatakDTO.setZaduzeni(zadatak.getZaduzeni());

		zadatakDTO.setSprintId(zadatak.getSprint().getId());
		zadatakDTO.setNazivSprinta(zadatak.getSprint().getIme());

		zadatakDTO.setStanjeId(zadatak.getStanje().getId());
		zadatakDTO.setNazivStanje(zadatak.getStanje().getIme());

		return zadatakDTO;
	}

	public List<ZadatakDTO> convert(List<Zadatak> zadaci) {
		List<ZadatakDTO> zadatakDTOS = new ArrayList<>();

		for (Zadatak zadatak : zadaci) {
			zadatakDTOS.add(convert(zadatak));
		}

		return zadatakDTOS;
	}
}
