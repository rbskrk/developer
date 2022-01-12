package com.rbs.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbs.os.domain.Cliente;
import com.rbs.os.dtos.ClienteDTO;
import com.rbs.os.repository.ClienteRepository;
import com.rbs.os.service.exception.DataIntegratyViolationException;
import com.rbs.os.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrando! Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/*
	 * Create Cliente
	 */
	public Cliente create(ClienteDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados!");
		}

		Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	/*
	 * Update Cliente
	 */
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {

		Cliente oldObj = findById(id);

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
		Cliente obj = findById(id);
		
		if(obj.getList().size() > 0 ) {
			throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, não pode ser deletado!");
		}
		
		repository.deleteById(id);
	}

	public Cliente findByCPF(ClienteDTO objDTO) {
		Cliente obj = repository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;

	}

}
