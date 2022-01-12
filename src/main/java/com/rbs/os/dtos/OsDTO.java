package com.rbs.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rbs.os.domain.OS;

public class OsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é obrigatório!")
	private String observacoes;
	private Integer status;
	private Integer tecnico;
	private Integer cliente;
	
	public OsDTO() {
		super();
	}

	public OsDTO(OS objOS) {
		super();
		this.id = objOS.getId();
		this.dataAbertura = objOS.getDataAbertura();
		this.dataFechamento = objOS.getDataFechamento();
		this.prioridade = objOS.getPrioridade().getCod();
		this.observacoes = objOS.getObservacoes();
		this.status = objOS.getStatus().getCod();
		this.tecnico = objOS.getTecnico().getId();
		this.cliente = objOS.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

}
