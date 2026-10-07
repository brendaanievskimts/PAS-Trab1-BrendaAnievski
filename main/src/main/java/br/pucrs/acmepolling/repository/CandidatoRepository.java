package br.pucrs.acmepolling.repository;

import br.pucrs.acmepolling.domain.Candidato;
import java.util.List;
import java.util.Optional;

public interface CandidatoRepository {
    void salvar(Candidato candidato);
    Optional<Candidato> buscarPorNumero(int numero);
    List<Candidato> listar();
    List<Candidato> listarPorLocalidade(String cep);
}
