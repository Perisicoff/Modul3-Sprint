package FinalTest.zavrsniback.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import FinalTest.zavrsniback.model.Stanje;
import FinalTest.zavrsniback.web.dto.StanjeDTO;

@Component
public class StanjeToStanjeDTO implements Converter<Stanje, StanjeDTO> {

	@Override
	public StanjeDTO convert(Stanje stanje) {
		StanjeDTO stanjeDTO = new StanjeDTO();

		stanjeDTO.setId(stanje.getId());
		stanjeDTO.setIme(stanje.getIme());

		return stanjeDTO;
	}

	public List<StanjeDTO> convert(List<Stanje> stanja) {
		List<StanjeDTO> stanjeDTOS = new ArrayList<>();

		for (Stanje stanje : stanja) {
			stanjeDTOS.add(convert(stanje));
		}

		return stanjeDTOS;
	}
}
