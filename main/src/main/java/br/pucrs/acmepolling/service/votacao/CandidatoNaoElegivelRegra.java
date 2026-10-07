package br.pucrs.acmepolling.service.votacao;

import br.pucrs.acmepolling.domain.Voto;
import org.springframework.stereotype.Component;

@Component
public class CandidatoNaoElegivelRegra implements RegraValidacaoVoto {
    @Override
    public boolean invalida(Voto voto) {
        return voto.getCandidato() != null && !voto.getCandidato().podeReceberVotos();
    }
}
