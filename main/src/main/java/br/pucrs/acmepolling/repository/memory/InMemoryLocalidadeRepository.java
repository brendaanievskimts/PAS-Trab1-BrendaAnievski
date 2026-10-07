package br.pucrs.acmepolling.repository.memory;

import br.pucrs.acmepolling.domain.Localidade;
import br.pucrs.acmepolling.repository.LocalidadeRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryLocalidadeRepository implements LocalidadeRepository {
    private final Map<String, Localidade> dados = new ConcurrentHashMap<>();

    @Override public void salvar(Localidade localidade) { dados.put(localidade.getCep(), localidade); }
    @Override public Optional<Localidade> buscarPorCep(String cep) { return Optional.ofNullable(dados.get(cep)); }
}
