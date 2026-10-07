package br.pucrs.acmepolling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MaisVotadoPartidoDto(
        @JsonProperty("nome_partido") String nomePartido,
        int numero,
        String nome,
        @JsonProperty("quantidade_votos_validos") long votosValidos) {
}
