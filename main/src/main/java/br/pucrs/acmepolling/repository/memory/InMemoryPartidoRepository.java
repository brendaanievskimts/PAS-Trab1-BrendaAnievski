package br.pucrs.acmepolling.repository.memory;

import br.pucrs.acmepolling.domain.Partido;
import br.pucrs.acmepolling.repository.PartidoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPartidoRepository implements PartidoRepository {
    private final Map<Integer, Partido> dados = new ConcurrentSkipListMap<>();

    @Override public void salvar(Partido partido) { dados.put(partido.getCodigo(), partido); }
    @Override public Optional<Partido> buscarPorCodigo(int codigo) { return Optional.ofNullable(dados.get(codigo)); }
    @Override public List<Partido> listar() { return new ArrayList<>(dados.values()); }
}
