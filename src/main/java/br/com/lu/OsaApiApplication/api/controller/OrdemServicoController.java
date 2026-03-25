/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lu.OsaApiApplication.api.controller;

import br.com.lu.OsaApiApplication.domain.dto.AtualizaStatusDTO;
import br.com.lu.OsaApiApplication.domain.model.OrdemServico;
import br.com.lu.OsaApiApplication.domain.repository.OrdemServicoRepository;
import br.com.lu.OsaApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesi3dia
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
   
    @Autowired
    private final OrdemServicoRepository ordemServicoRepository = null;
   
    @Autowired
    private final OrdemServicoService ordemServicoService;

    // Injeção via construtor: mais seguro e fácil de testar
    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }
   
   
    @GetMapping
    public List<OrdemServico> listar() {

        return ordemServicoRepository.findAll();
    }
   
   
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
       
    }
   
   
     @GetMapping("/{ordem_servicoID}")

    public ResponseEntity<OrdemServico> buscar(@PathVariable long ordem_servicoID) {

        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordem_servicoID);

        if (ordemServico.isPresent()) {

            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
   
   
    @DeleteMapping("/{ordem_servicoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long ordem_servicoID) {

        if (!ordemServicoRepository.existsById(ordem_servicoID)) {
            return ResponseEntity.notFound().build();
        }
        ordemServicoService.excluir(ordem_servicoID);
        return ResponseEntity.noContent().build();
       
    }
   
   
    @PutMapping("/{ordem_servicoID}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordem_servicoID, @RequestBody OrdemServico ordemServico) {
         if (!ordemServicoRepository.existsById(ordem_servicoID)) {
            return ResponseEntity.notFound().build();
        }

        ordemServico.setId(ordem_servicoID);
        ordemServico = ordemServicoService.atualizar(ordemServico);

        return ResponseEntity.ok(ordemServico);
    }
   
   
   
    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
        @PathVariable Long ordemServicoID,
        @Valid @RequestBody AtualizaStatusDTO statusDTO) {

    Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
            ordemServicoID,
            statusDTO.status());

    if (optOS.isPresent()) {
        return ResponseEntity.ok(optOS.get());

    } else {
        return ResponseEntity.notFound().build();

    }

  }

}