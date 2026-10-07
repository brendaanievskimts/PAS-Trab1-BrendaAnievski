package br.pucrs.acmepolling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VotacaoCandidatoDto(
        int numero,
        String nome,
        @JsonProperty("quantidade_votos_validos") long votosValidos,
        @JsonProperty("quantidade_votos_invalidos") long votosInvalidos) {
}
