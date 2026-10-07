package br.pucrs.acmepolling.repository;

import br.pucrs.acmepolling.domain.Voto;
import java.util.List;

public interface VotoRepository {
    void salvar(Voto voto);
    boolean existePorId(int id);
    List<Voto> buscarPorCandidato(int numero);
    List<Voto> buscarPorLocalidade(String cep);
}
