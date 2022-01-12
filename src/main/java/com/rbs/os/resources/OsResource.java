package com.rbs.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rbs.os.dtos.OsDTO;
import com.rbs.os.service.OsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value ="/os")
public class OsResource {
	
	@Autowired
	private OsService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
		OsDTO osDto = new OsDTO(service.findById(id));
		
		return ResponseEntity.ok().body(osDto);

	}
	
	@GetMapping
	public ResponseEntity<List<OsDTO>> findAll(){
		
		// Convert OS List to DTO List
		List<OsDTO> listDto = service.findAll().stream()
				.map(obj -> new OsDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);

	}
	
	@PostMapping
	public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO osDto){
		osDto = new OsDTO(service.create(osDto));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(osDto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping
	public ResponseEntity<OsDTO> updateOS(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(service.update(obj));
		
		return ResponseEntity.ok().body(obj);
	}
	
	
}
