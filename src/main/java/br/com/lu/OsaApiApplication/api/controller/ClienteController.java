/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lu.OsaApiApplication.api.controller;

import br.com.lu.OsaApiApplication.domain.model.Cliente;
import br.com.lu.OsaApiApplication.domain.repository.ClienteRepository;
import br.com.lu.OsaApiApplication.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })

    public List<Cliente> listar() {

        return clienteRepository.findAll();
    }

    @GetMapping("/clientes/{clienteID}")
    @Parameter(name = "id", description = "cliente id", example = "1")
    @Operation(summary = "Get a cliente by id", description = "Returns a cliente as per the id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The cliente was not found")
    })
    
    public ResponseEntity<Cliente> buscar(@PathVariable long clienteID) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping("/clientes")
    @Parameter(name = "cliente", description = "add cliente ")
    @Operation(summary = "Adicionar cliente", description = "Returns a cliente as per the id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @PutMapping("/clientes/{clienteID}")
    @Parameter(name = "client id", description = "atualizar cliente", example = "1")
    @Operation(summary = "Atualizar cliente", description = "Returns a cliente as per the id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The cliente was not found")
    })
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable long clienteID,
            @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteID);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("clientes/{clienteID}")
    @Parameter(name = " excluir id", description = "excluir cliente", example = "1")
    @Operation(summary = "Excluir cliente", description = "Returns a cliente as per the id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The cliente was not found")
    }) 
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }
}
