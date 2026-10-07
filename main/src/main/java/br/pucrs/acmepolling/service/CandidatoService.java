package br.pucrs.acmepolling.service;

import br.pucrs.acmepolling.domain.Candidato;
import br.pucrs.acmepolling.domain.Localidade;
import br.pucrs.acmepolling.domain.Partido;
import br.pucrs.acmepolling.domain.Situacao;
import br.pucrs.acmepolling.dto.CadastroCandidatoRequest;
import br.pucrs.acmepolling.dto.CandidatoNumeroNomeDto;
import br.pucrs.acmepolling.dto.CandidatoResumoDto;
import br.pucrs.acmepolling.exception.RecursoNaoEncontradoException;
import br.pucrs.acmepolling.repository.CandidatoRepository;
import br.pucrs.acmepolling.repository.LocalidadeRepository;
import br.pucrs.acmepolling.repository.PartidoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
    private final CandidatoRepository candidatos;
    private final PartidoRepository partidos;
    private final LocalidadeRepository localidades;

    public CandidatoService(CandidatoRepository candidatos, PartidoRepository partidos, LocalidadeRepository localidades) {
        this.candidatos = candidatos;
        this.partidos = partidos;
        this.localidades = localidades;
    }

    public List<CandidatoResumoDto> listarTodos() {
        return candidatos.listar().stream().map(CandidatoResumoDto::de).toList();
    }

    public List<CandidatoNumeroNomeDto> listarPorLocalidadeESituacao(String cep, String situacaoTexto) {
        Situacao situacao = Situacao.deTexto(situacaoTexto);
        return candidatos.listarPorLocalidade(cep).stream()
                .filter(c -> c.getSituacao() == situacao)
                .map(c -> new CandidatoNumeroNomeDto(c.getNumero(), c.getNome()))
                .toList();
    }

    public synchronized boolean cadastrar(CadastroCandidatoRequest req) {
        if (req.numero() <= 0 || req.nome() == null || req.nome().isBlank() || req.cep() == null) {
            return false;
        }
        if (candidatos.buscarPorNumero(req.numero()).isPresent()) {
            return false;
        }
        Optional<Partido> partido = partidos.buscarPorCodigo(req.codigo());
        Optional<Localidade> localidade = localidades.buscarPorCep(req.cep());
        if (partido.isEmpty() || localidade.isEmpty()) {
            return false;
        }
        candidatos.salvar(new Candidato(req.numero(), req.nome(), partido.get(), localidade.get()));
        return true;
    }

    public CandidatoResumoDto atualizarSituacao(int numero, String status) {
        Candidato candidato = candidatos.buscarPorNumero(numero)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Candidato não encontrado: " + numero));
        candidato.alterarSituacao(Situacao.deTexto(status));
        return CandidatoResumoDto.de(candidato);
    }

    public synchronized boolean remover(int numero) {
        Optional<Candidato> candidato = candidatos.buscarPorNumero(numero);
        if (candidato.isEmpty() || !candidato.get().getSituacao().podeIrPara(Situacao.REMOVIDO)) {
            return false;
        }
        candidato.get().alterarSituacao(Situacao.REMOVIDO);
        return true;
    }
}
