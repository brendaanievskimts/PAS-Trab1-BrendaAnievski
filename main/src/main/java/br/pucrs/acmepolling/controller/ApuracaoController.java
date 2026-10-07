package br.pucrs.acmepolling.controller;

import br.pucrs.acmepolling.dto.EleitoDto;
import br.pucrs.acmepolling.dto.MaisVotadoPartidoDto;
import br.pucrs.acmepolling.dto.VotacaoCandidatoDto;
import br.pucrs.acmepolling.dto.VotosCandidatoDto;
import br.pucrs.acmepolling.service.ApuracaoService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acmepolling/apuracao")
public class ApuracaoController {
    private final ApuracaoService service;

    public ApuracaoController(ApuracaoService service) {
        this.service = service;
    }

    @GetMapping("/consultaeleito")
    public EleitoDto consultaEleito(@RequestParam String cep) {
        return service.consultarEleito(cep);
    }

    @GetMapping("/consultacandidato")
    public VotosCandidatoDto consultaCandidato(@RequestParam int numero) {
        return service.consultarCandidato(numero);
    }

    @GetMapping("/consultalocalidade")
    public List<VotacaoCandidatoDto> consultaLocalidade(@RequestParam String cep) {
        return service.consultarLocalidade(cep);
    }

    @GetMapping("/listamaisvotadospartido")
    public List<MaisVotadoPartidoDto> listaMaisVotadosPartido() {
        return service.listarMaisVotadosPorPartido();
    }
}
