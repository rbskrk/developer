package com.rbs.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbs.os.domain.Cliente;
import com.rbs.os.domain.OS;
import com.rbs.os.domain.Tecnico;
import com.rbs.os.domain.enuns.Prioridade;
import com.rbs.os.domain.enuns.Status;
import com.rbs.os.dtos.OsDTO;
import com.rbs.os.repository.OsRepository;
import com.rbs.os.service.exception.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository repository;

	@Autowired
	private TecnicoService serviceTecnico;

	@Autowired
	private ClienteService serviceCliente;

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrando! Id: " + id + ", tipo: " + OS.class.getName()));
	}

	@Autowired
	public List<OS> findAll() {
		return repository.findAll();
	}

	public OS create(@Valid OsDTO osDto) {
		return fromDTO(osDto);
	}

	public OS update(@Valid OsDTO osDto) {
        findById(osDto.getId());
		return fromDTO(osDto);
	}

	private OS fromDTO(OsDTO obj) {
		OS newObj = new OS();

		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = serviceTecnico.findById(obj.getTecnico());
		Cliente cli = serviceCliente.findById(obj.getCliente());

		newObj.setCliente(cli);
		newObj.setTecnico(tec);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);

	}

}
