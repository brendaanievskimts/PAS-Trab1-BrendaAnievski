package br.pucrs.acmepolling.repository;

import br.pucrs.acmepolling.domain.Partido;
import java.util.List;
import java.util.Optional;

public interface PartidoRepository {
    void salvar(Partido partido);
    Optional<Partido> buscarPorCodigo(int codigo);
    List<Partido> listar();
}
