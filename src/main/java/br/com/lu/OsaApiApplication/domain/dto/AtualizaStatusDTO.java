/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.lu.OsaApiApplication.domain.dto;

import br.com.lu.OsaApiApplication.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author sesi3dia
 */
public record AtualizaStatusDTO(
        @NotNull (message = "Status é obrigatório")
        StatusOrdemServico status

        ){}
