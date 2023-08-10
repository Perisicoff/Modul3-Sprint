package FinalTest.zavrsniback.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FinalTest.zavrsniback.model.Stanje;
import FinalTest.zavrsniback.service.StanjeService;
import FinalTest.zavrsniback.support.StanjeToStanjeDTO;
import FinalTest.zavrsniback.web.dto.StanjeDTO;

@RestController
@RequestMapping(value = "/api/stanja", produces = MediaType.APPLICATION_JSON_VALUE)
public class StanjeController {

	@Autowired
	private StanjeService stanjeService;

	@Autowired
	private StanjeToStanjeDTO toStanjeDTO;

	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<StanjeDTO>> get() {
		List<Stanje> sprintevi = stanjeService.findAll();
		return new ResponseEntity<>(toStanjeDTO.convert(sprintevi), HttpStatus.OK);
	}
}
