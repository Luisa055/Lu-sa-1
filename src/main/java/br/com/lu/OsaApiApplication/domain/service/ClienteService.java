/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lu.OsaApiApplication.domain.service;

import br.com.lu.OsaApiApplication.domain.exception.DomainException;
import br.com.lu.OsaApiApplication.domain.model.Cliente;
import br.com.lu.OsaApiApplication.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author sesi3dia
 */
    @Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente) {

        Cliente clienteExistente = 
                clienteRepository.findByEmail(cliente.getEmail());

        // Se existir outro cliente com o mesmo e-mail, lança exceção
        if (clienteExistente != null && !clienteExistente.equals(cliente.getId())) {

            throw new DomainException("Já existe um cliente cadastrado com esse e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}

