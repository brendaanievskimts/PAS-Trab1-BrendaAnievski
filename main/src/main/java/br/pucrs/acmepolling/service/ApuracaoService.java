package br.pucrs.acmepolling.service;

import br.pucrs.acmepolling.domain.Candidato;
import br.pucrs.acmepolling.domain.Situacao;
import br.pucrs.acmepolling.domain.Voto;
import br.pucrs.acmepolling.dto.EleitoDto;
import br.pucrs.acmepolling.dto.MaisVotadoPartidoDto;
import br.pucrs.acmepolling.dto.VotacaoCandidatoDto;
import br.pucrs.acmepolling.dto.VotosCandidatoDto;
import br.pucrs.acmepolling.exception.RecursoNaoEncontradoException;
import br.pucrs.acmepolling.repository.CandidatoRepository;
import br.pucrs.acmepolling.repository.LocalidadeRepository;
import br.pucrs.acmepolling.repository.PartidoRepository;
import br.pucrs.acmepolling.repository.VotoRepository;
import br.pucrs.acmepolling.service.apuracao.CriterioDeVitoria;
import br.pucrs.acmepolling.service.apuracao.ResultadoCandidato;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ApuracaoService {
    private final CandidatoRepository candidatos;
    private final VotoRepository votos;
    private final LocalidadeRepository localidades;
    private final PartidoRepository partidos;
    private final CriterioDeVitoria criterio;

    public ApuracaoService(CandidatoRepository candidatos, VotoRepository votos, LocalidadeRepository localidades,
                           PartidoRepository partidos, CriterioDeVitoria criterio) {
        this.candidatos = candidatos;
        this.votos = votos;
        this.localidades = localidades;
        this.partidos = partidos;
        this.criterio = criterio;
    }

    public EleitoDto consultarEleito(String cep) {
        exigirLocalidade(cep);
        return candidatos.listarPorLocalidade(cep).stream()
                .filter(c -> c.getSituacao() == Situacao.ELEGIVEL || c.getSituacao() == Situacao.ELEITO)
                .map(this::resultadoDe)
                .filter(r -> r.votosValidos() > 0)
                .min(criterio.comparador())
                .map(r -> new EleitoDto(r.candidato().getNumero(), r.candidato().getNome(),
                        r.candidato().getPartido().getNome(), r.votosValidos()))
                .orElseThrow(() -> new RecursoNaoEncontradoException("Nenhum candidato eleito para o CEP " + cep));
    }

    public VotosCandidatoDto consultarCandidato(int numero) {
        Candidato candidato = candidatos.buscarPorNumero(numero)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Candidato não encontrado: " + numero));
        ResultadoCandidato r = resultadoDe(candidato);
        return new VotosCandidatoDto(r.votosValidos(), r.votosInvalidos());
    }

    public List<VotacaoCandidatoDto> consultarLocalidade(String cep) {
        exigirLocalidade(cep);
        return candidatos.listarPorLocalidade(cep).stream()
                .map(this::resultadoDe)
                .map(r -> new VotacaoCandidatoDto(r.candidato().getNumero(), r.candidato().getNome(),
                        r.votosValidos(), r.votosInvalidos()))
                .toList();
    }

    public List<MaisVotadoPartidoDto> listarMaisVotadosPorPartido() {
        return partidos.listar().stream()
                .flatMap(partido -> candidatos.listar().stream()
                        .filter(c -> c.getPartido().getCodigo() == partido.getCodigo())
                        .filter(c -> c.getSituacao() != Situacao.REMOVIDO)
                        .map(this::resultadoDe)
                        .min(criterio.comparador())
                        .stream())
                .map(r -> new MaisVotadoPartidoDto(r.candidato().getPartido().getNome(),
                        r.candidato().getNumero(), r.candidato().getNome(), r.votosValidos()))
                .toList();
    }

    private ResultadoCandidato resultadoDe(Candidato candidato) {
        List<Voto> doCandidato = votos.buscarPorCandidato(candidato.getNumero());
        long validos = doCandidato.stream().filter(Voto::isValido).count();
        long invalidos = doCandidato.size() - validos;
        int ultimoVotoValido = doCandidato.stream().filter(Voto::isValido)
                .mapToInt(Voto::getHora).max().orElse(Integer.MAX_VALUE);
        return new ResultadoCandidato(candidato, validos, invalidos, ultimoVotoValido);
    }

    private void exigirLocalidade(String cep) {
        if (localidades.buscarPorCep(cep).isEmpty()) {
            throw new RecursoNaoEncontradoException("Localidade não encontrada: " + cep);
        }
    }
}
