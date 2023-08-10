package FinalTest.zavrsniback.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FinalTest.zavrsniback.model.Sprint;
import FinalTest.zavrsniback.model.Zadatak;
import FinalTest.zavrsniback.service.SprintService;
import FinalTest.zavrsniback.service.ZadatakService;
import FinalTest.zavrsniback.support.ZadatakDTOToZadatak;
import FinalTest.zavrsniback.support.ZadatakToZadatakDTO;
import FinalTest.zavrsniback.web.dto.ZadatakDTO;

@RestController
@RequestMapping(value = "/api/zadaci", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZadatakController {

	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private SprintService sprintService;

	@Autowired
	private ZadatakToZadatakDTO toZadatakDTO;

	@Autowired
	private ZadatakDTOToZadatak toZadatak;

	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<ZadatakDTO>> get(@RequestParam(required = false, defaultValue = "") String ime,
												@RequestParam(required = false, defaultValue = "") Long sprintId,
												@RequestParam(value = "pageNo", defaultValue = "0") int page) {
		Page<Zadatak> zadaci = zadatakService.search(ime, sprintId, page);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(zadaci.getTotalPages()));

		return new ResponseEntity<>(toZadatakDTO.convert(zadaci.getContent()), headers, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ZadatakDTO> get(@PathVariable Long id) {
		Zadatak zadatak = zadatakService.findOne(id);

		if (zadatak != null) {
			return new ResponseEntity<>(toZadatakDTO.convert(zadatak), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Zadatak obrisaniZadatak = zadatakService.delete(id);

		if (obrisaniZadatak != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZadatakDTO> create(@Valid @RequestBody ZadatakDTO zadatakDTO) {
		Zadatak zadatak = toZadatak.convert(zadatakDTO);

		if (zadatak.getSprint() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Zadatak sacuvaniZadatak = zadatakService.save(zadatak);

		return new ResponseEntity<>(toZadatakDTO.convert(sacuvaniZadatak), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZadatakDTO> update(@PathVariable Long id, @Valid @RequestBody ZadatakDTO zadatakDTO) {

		if (!id.equals(zadatakDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Zadatak stariZadatak = zadatakService.findOne(id);
		Sprint stariSprint = stariZadatak.getSprint();
		stariSprint.obrisiZadatak(stariZadatak);
		sprintService.save(stariSprint);

		Zadatak zadatak = toZadatak.convert(zadatakDTO);
		
		Sprint noviSprint = zadatak.getSprint();
		noviSprint.dodajZadatak(zadatak);
		sprintService.save(noviSprint);
		
		if (zadatak.getSprint() == null || zadatak.getStanje() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Zadatak sacuvaniZadatak = zadatakService.update(zadatak);

		return new ResponseEntity<>(toZadatakDTO.convert(sacuvaniZadatak), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PutMapping(value = "/{id}/novoStanje")
	public ResponseEntity<Void> promenaStanja(@PathVariable Long id) {

		Zadatak zadatak = zadatakService.findOne(id);

		if (zadatak.getSprint() == null || zadatak.getStanje() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		zadatakService.promenaStanja(zadatak);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
