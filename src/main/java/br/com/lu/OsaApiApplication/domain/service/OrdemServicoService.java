/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lu.OsaApiApplication.domain.service;

import br.com.lu.OsaApiApplication.StatusOrdemServico;
import br.com.lu.OsaApiApplication.domain.exception.DomainException;
import br.com.lu.OsaApiApplication.domain.model.OrdemServico;
import br.com.lu.OsaApiApplication.domain.repository.OrdemServicoRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author sesi3dia
 */

    @Service
public class OrdemServicoService {
   

    private final OrdemServicoRepository ordemServicoRepository;

    // Injeção via construtor (melhor prática)
    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    @Transactional
    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }
   
   public void excluir(Long ordemServicoID) {
        ordemServicoRepository.deleteById(ordemServicoID);
    }
   
    public OrdemServico atualizar (OrdemServico ordemServico) {
         return ordemServicoRepository.save(ordemServico);
    }
 
public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status) {

    Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);

    if (optOrdemServico.isPresent()) {

        OrdemServico ordemServico = optOrdemServico.get();

        // Verifica se ordem está ABERTA.
        if (ordemServico.getStatus() == StatusOrdemServico.ABERTA
                && status != StatusOrdemServico.ABERTA) {

            ordemServico.setStatus(status);
            ordemServico.setDataFinalizacao(LocalDateTime.now());
            ordemServicoRepository.save(ordemServico);
            return Optional.of(ordemServico);

        } else {

            // ops.. ordem FINALIZADA ou CANCELADA. Não alterar.
            return Optional.empty();
        }

    } else {

        // Lança exception se ID não encontrado.
        throw new DomainException("Não existe OS com o id " + ordemServicoID);
    }

}
}