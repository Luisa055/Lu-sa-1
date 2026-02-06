/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lu.OsaApiApplication.api.controller;

import br.com.lu.OsaApiApplication.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
        
        
        @PersistenceContext
                private EntityManager manager;
       
        
    @GetMapping("/clientes")
    public List<Cliente> listas() {
       
        return manager.createQuery("from cliente", Cliente.class).getResultList();
       }
    }
    
