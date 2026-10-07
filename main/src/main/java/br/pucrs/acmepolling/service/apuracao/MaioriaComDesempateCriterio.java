package br.pucrs.acmepolling.service.apuracao;

import java.util.Comparator;
import org.springframework.stereotype.Component;

@Component
public class MaioriaComDesempateCriterio implements CriterioDeVitoria {
    @Override
    public Comparator<ResultadoCandidato> comparador() {
        return Comparator.<ResultadoCandidato>comparingLong(r -> r.votosValidos()).reversed()
                .thenComparingInt(r -> r.horaUltimoVotoValido())
                .thenComparingInt(r -> r.candidato().getNumero());
    }
}