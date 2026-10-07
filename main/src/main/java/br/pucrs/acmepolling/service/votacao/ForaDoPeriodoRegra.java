package br.pucrs.acmepolling.service.votacao;

import br.pucrs.acmepolling.domain.Voto;
import org.springframework.stereotype.Component;

@Component
public class ForaDoPeriodoRegra implements RegraValidacaoVoto {
    static final int HORA_INICIO = 8;
    static final int HORA_FIM = 17;

    @Override
    public boolean invalida(Voto voto) {
        return voto.getHora() < HORA_INICIO || voto.getHora() > HORA_FIM;
    }
}
