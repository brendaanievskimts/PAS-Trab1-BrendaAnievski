package br.pucrs.acmepolling.dto;

import br.pucrs.acmepolling.domain.Candidato;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CandidatoResumoDto(
        int numero,
        String nome,
        String situacao,
        @JsonProperty("nome_partido") String nomePartido,
        @JsonProperty("nome_localidade") String nomeLocalidade) {

    public static CandidatoResumoDto de(Candidato c) {
        return new CandidatoResumoDto(c.getNumero(), c.getNome(), c.getSituacao().name(),
                c.getPartido().getNome(), c.getLocalidade().getNome());
    }
}
