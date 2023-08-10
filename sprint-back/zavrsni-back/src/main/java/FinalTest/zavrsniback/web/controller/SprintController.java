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

import FinalTest.zavrsniback.model.Sprint;
import FinalTest.zavrsniback.service.SprintService;
import FinalTest.zavrsniback.support.SprintToSprintDTO;
import FinalTest.zavrsniback.web.dto.SprintDTO;

@RestController
@RequestMapping(value = "/api/sprintevi", produces = MediaType.APPLICATION_JSON_VALUE)
public class SprintController {

	@Autowired
	private SprintService sprintService;

	@Autowired
	private SprintToSprintDTO toSprintDTO;

	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<SprintDTO>> get() {
		List<Sprint> sprintevi = sprintService.findAll();
		return new ResponseEntity<>(toSprintDTO.convert(sprintevi), HttpStatus.OK);
	}

}
