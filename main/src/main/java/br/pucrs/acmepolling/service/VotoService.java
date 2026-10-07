package br.pucrs.acmepolling.service;

import br.pucrs.acmepolling.domain.Candidato;
import br.pucrs.acmepolling.domain.Localidade;
import br.pucrs.acmepolling.domain.Voto;
import br.pucrs.acmepolling.dto.CadastroVotoRequest;
import br.pucrs.acmepolling.repository.CandidatoRepository;
import br.pucrs.acmepolling.repository.LocalidadeRepository;
import br.pucrs.acmepolling.repository.VotoRepository;
import br.pucrs.acmepolling.service.votacao.RegraValidacaoVoto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class VotoService {
    private final VotoRepository votos;
    private final CandidatoRepository candidatos;
    private final LocalidadeRepository localidades;
    private final List<RegraValidacaoVoto> regras;

    public VotoService(VotoRepository votos, CandidatoRepository candidatos,
                       LocalidadeRepository localidades, List<RegraValidacaoVoto> regras) {
        this.votos = votos;
        this.candidatos = candidatos;
        this.localidades = localidades;
        this.regras = regras;
    }

    /** @return false se o id já existe ou a localidade não existe; true se o voto foi registrado. */
    public synchronized boolean cadastrar(CadastroVotoRequest req) {
        if (votos.existePorId(req.id())) {
            return false;
        }
        Optional<Localidade> localidade = localidades.buscarPorCep(req.cep());
        if (localidade.isEmpty()) {
            return false;
        }
        Candidato candidato = candidatos.buscarPorNumero(req.numero()).orElse(null);
        Voto voto = new Voto(req.id(), req.hora(), candidato, localidade.get());
        if (regras.stream().anyMatch(regra -> regra.invalida(voto))) {
            voto.invalidar();
        }
        votos.salvar(voto);
        return true;
    }
}
