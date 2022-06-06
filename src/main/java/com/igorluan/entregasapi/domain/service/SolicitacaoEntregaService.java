package com.igorluan.entregasapi.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.igorluan.entregasapi.api.model.EntregaModel;
import com.igorluan.entregasapi.domain.exception.NegocioException;
import com.igorluan.entregasapi.domain.model.Cliente;
import com.igorluan.entregasapi.domain.model.Entrega;
import com.igorluan.entregasapi.domain.model.StatusEntrega;
import com.igorluan.entregasapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service

public class SolicitacaoEntregaService {
	
	private EntregaRepository entregaRepository;
private CadastroClienteService cadastroClienteService;


    public Entrega EntregaModel (Long entregaId) {
	return entregaRepository.findById(entregaId)
			.orElseThrow(()-> new NegocioException("Cliente nao encontrado."));
}


	@Transactional
	public Entrega solicitar(Entrega entrega) {		
		Cliente cliente = cadastroClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
				
		return entregaRepository.save(entrega);
	}
}
