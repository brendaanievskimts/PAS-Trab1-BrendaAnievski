package br.pucrs.acmepolling.service.apuracao;

import java.util.Comparator;

public interface CriterioDeVitoria {
    Comparator<ResultadoCandidato> comparador();
}
