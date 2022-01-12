package com.rbs.os.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbs.os.domain.Cliente;
import com.rbs.os.domain.OS;
import com.rbs.os.domain.Tecnico;
import com.rbs.os.domain.enuns.Prioridade;
import com.rbs.os.domain.enuns.Status;
import com.rbs.os.repository.ClienteRepository;
import com.rbs.os.repository.OsRepository;
import com.rbs.os.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private OsRepository osRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	public void instanciaDb() {

		Tecnico t1 = new Tecnico(null, "Robert Batista da Silva", "010.174.970-87", "(34) 99777-2923");
		//Tecnico t2 = new Tecnico(null, "Carlos Ferreira", "010.174.970-87", "(34) 99777-2923");
		Cliente c1 = new Cliente(null, "Erika Santiago", "793.603.510-91", "(34) 99777-8977");
		OS os1 = new OS(null, Prioridade.ALTA, "Iniciando teste", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}
