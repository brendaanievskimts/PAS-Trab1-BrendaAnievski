package br.pucrs.acmepolling.service.votacao;

import br.pucrs.acmepolling.domain.Voto;
import br.pucrs.acmepolling.domain.Localidade;
import br.pucrs.acmepolling.repository.VotoRepository;
import org.springframework.stereotype.Component;

@Component
public class LimiteEleitoresRegra implements RegraValidacaoVoto {
    private final VotoRepository votos;

    public LimiteEleitoresRegra(VotoRepository votos) {
        this.votos = votos;
    }

    @Override
    public boolean invalida(Voto voto) {
        Localidade localidade = voto.getLocalidade();
        long validosNaLocalidade = votos.buscarPorLocalidade(localidade.getCep()).stream()
                .filter(Voto::isValido)
                .count();
        return validosNaLocalidade >= localidade.getQtdEleitores();
    }
}
