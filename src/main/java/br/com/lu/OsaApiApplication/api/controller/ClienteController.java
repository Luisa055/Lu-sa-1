/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lu.OsaApiApplication.api.controller;

import br.com.lu.OsaApiApplication.domain.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */

    @RestController
public class ClienteController {
        List<Cliente> listaClientes;
    @GetMapping("/clientes")
    public List<Cliente> listas() {
        listaClientes = new ArrayList<Cliente>();
        listaClientes.add(new Cliente(1,"KGe","KGe@teste.com","11-99999-99999"));
        listaClientes.add(new Cliente(1,"Maria","Maria@teste.com","11-88888-88888"));
        listaClientes.add(new Cliente(1,"Joao","Joao@teste.com","11-77777-77777"));
        return listaClientes;
       }
    }
    
