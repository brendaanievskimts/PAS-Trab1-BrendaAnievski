package br.pucrs.acmepolling.repository.memory;

import br.pucrs.acmepolling.domain.Voto;
import br.pucrs.acmepolling.repository.VotoRepository;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryVotoRepository implements VotoRepository {
    private final List<Voto> dados = new CopyOnWriteArrayList<>();

    @Override public void salvar(Voto voto) { dados.add(voto); }
    @Override public boolean existePorId(int id) { return dados.stream().anyMatch(v -> v.getId() == id); }

    @Override
    public List<Voto> buscarPorCandidato(int numero) {
        return dados.stream()
                .filter(v -> v.getCandidato() != null && v.getCandidato().getNumero() == numero)
                .toList();
    }

    @Override
    public List<Voto> buscarPorLocalidade(String cep) {
        return dados.stream().filter(v -> v.getLocalidade().getCep().equals(cep)).toList();
    }
}
