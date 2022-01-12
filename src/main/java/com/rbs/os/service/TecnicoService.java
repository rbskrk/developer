package com.rbs.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbs.os.domain.Tecnico;
import com.rbs.os.dtos.TecnicoDTO;
import com.rbs.os.repository.TecnicoRepository;
import com.rbs.os.service.exception.DataIntegratyViolationException;
import com.rbs.os.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrando! Id: " + id + ", tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	/*
	 * Create Tecnico
	 */
	public Tecnico create(TecnicoDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados!");
		}

		Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	/*
	 * Update Tecnico
	 */
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {

		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados!");
		}

		oldObj.setCpf(objDTO.getCpf());
		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);
	}

	/*
	 * Delete Tecnico
	 */
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getList().size() > 0 ) {
			throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado!");
		}
		
		repository.deleteById(id);
	}

	public Tecnico findByCPF(TecnicoDTO objDTO) {
		Tecnico obj = repository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;

	}

}
