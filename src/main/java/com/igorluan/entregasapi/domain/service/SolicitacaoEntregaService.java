package com.igorluan.entregasapi.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.igorluan.entregasapi.domain.exception.NegocioException;
import com.igorluan.entregasapi.domain.model.Cliente;
import com.igorluan.entregasapi.domain.model.Entrega;
import com.igorluan.entregasapi.domain.model.StatusEntrega;
import com.igorluan.entregasapi.domain.repository.ClienteRepository;
import com.igorluan.entregasapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service

public class SolicitacaoEntregaService {
	
	private EntregaRepository entregaRepository;
private CadastroClienteService cadastroClienteService;	
	@Transactional
	public Entrega solicitar(Entrega entrega) {		
		Cliente cliente = cadastroClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
				
		return entregaRepository.save(entrega);
	}
}
