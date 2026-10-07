package br.pucrs.acmepolling.service.apuracao;

import br.pucrs.acmepolling.domain.Candidato;

public record ResultadoCandidato(Candidato candidato, long votosValidos, long votosInvalidos, int horaUltimoVotoValido) {
}
