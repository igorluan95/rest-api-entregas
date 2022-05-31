package com.igorluan.entregasapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.igorluan.entregasapi.domain.exception.NegocioException;
import com.igorluan.entregasapi.domain.model.Cliente;
import com.igorluan.entregasapi.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service

public class CadastroClienteService {
	
	
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar (Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteJaCadastrado -> !clienteJaCadastrado.equals(cliente));

		if (emailEmUso) {
			throw new NegocioException("Esse e-mail já está cadastrado para um cliente.");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir (Long clienteId) {
		clienteRepository.deleteById(clienteId);;
	}

}
