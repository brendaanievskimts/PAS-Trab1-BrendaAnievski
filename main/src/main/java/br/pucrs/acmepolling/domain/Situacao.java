package br.pucrs.acmepolling.domain;

import br.pucrs.acmepolling.exception.RegraDeNegocioException;
import java.util.EnumSet;
import java.util.Set;

/**
 * Padrão STATE (implementado como enum): cada estado conhece suas transições
 * permitidas e se pode receber votos. O Candidato apenas delega a decisão.
 */
public enum Situacao {
    PRECANDIDATO, ELEGIVEL, INELEGIVEL, ELEITO, NAOELEITO, REMOVIDO;

    public Set<Situacao> transicoesPermitidas() {
        return switch (this) {
            case PRECANDIDATO -> EnumSet.of(ELEGIVEL, INELEGIVEL, REMOVIDO);
            case INELEGIVEL -> EnumSet.of(ELEGIVEL, REMOVIDO);
            case ELEGIVEL -> EnumSet.of(INELEGIVEL, ELEITO, NAOELEITO);
            default -> EnumSet.noneOf(Situacao.class); // ELEITO, NAOELEITO, REMOVIDO são finais
        };
    }

    public boolean podeIrPara(Situacao destino) {
        return transicoesPermitidas().contains(destino);
    }

    public boolean podeReceberVotos() {
        return this == ELEGIVEL;
    }

    public static Situacao deTexto(String texto) {
        try {
            return valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new RegraDeNegocioException("Situação inválida: " + texto);
        }
    }
}
