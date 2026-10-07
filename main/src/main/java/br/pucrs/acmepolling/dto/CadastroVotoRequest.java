package br.pucrs.acmepolling.dto;

public record CadastroVotoRequest(int id, int hora, int numero, String cep) {
}
