package br.pucrs.acmepolling.repository.memory;

import br.pucrs.acmepolling.domain.Candidato;
import br.pucrs.acmepolling.repository.CandidatoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCandidatoRepository implements CandidatoRepository {
    private final Map<Integer, Candidato> dados = new ConcurrentSkipListMap<>();

    @Override public void salvar(Candidato candidato) { dados.put(candidato.getNumero(), candidato); }
    @Override public Optional<Candidato> buscarPorNumero(int numero) { return Optional.ofNullable(dados.get(numero)); }
    @Override public List<Candidato> listar() { return new ArrayList<>(dados.values()); }

    @Override
    public List<Candidato> listarPorLocalidade(String cep) {
        return dados.values().stream()
                .filter(c -> c.getLocalidade().getCep().equals(cep))
                .toList();
    }
}
