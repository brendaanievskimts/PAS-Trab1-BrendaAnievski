package br.pucrs.acmepolling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EleitoDto(
        int numero,
        String nome,
        @JsonProperty("nome_partido") String nomePartido,
        @JsonProperty("quantidade_total_votos") long quantidadeTotalVotos) {
}
