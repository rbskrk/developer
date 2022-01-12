package com.rbs.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.rbs.os.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "O campo NOME é obrigatório!")
	private String nome;

	@CPF
	@NotEmpty(message = "O campo CPF é obrigatório!")
	private String cpf;

	@NotEmpty(message = "O campo TELEFONE é obrigatório!")
	private String telefone;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente objCliente) {
		super();
		this.id = objCliente.getId();
		this.nome = objCliente.getNome();
		this.cpf = objCliente.getCpf();
		this.telefone = objCliente.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
