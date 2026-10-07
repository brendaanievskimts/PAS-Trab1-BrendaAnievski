package br.pucrs.acmepolling.service.votacao;

import br.pucrs.acmepolling.domain.Voto;

public interface RegraValidacaoVoto {
    boolean invalida(Voto voto);
}
