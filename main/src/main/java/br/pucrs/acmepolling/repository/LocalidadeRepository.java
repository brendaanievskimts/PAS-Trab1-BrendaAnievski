package br.pucrs.acmepolling.repository;

import br.pucrs.acmepolling.domain.Localidade;
import java.util.Optional;

public interface LocalidadeRepository {
    void salvar(Localidade localidade);
    Optional<Localidade> buscarPorCep(String cep);
}
