package br.pucrs.acmepolling.controller;

import br.pucrs.acmepolling.dto.CadastroCandidatoRequest;
import br.pucrs.acmepolling.dto.CandidatoNumeroNomeDto;
import br.pucrs.acmepolling.dto.CandidatoResumoDto;
import br.pucrs.acmepolling.service.CandidatoService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acmepolling/cadastro")
public class CadastroController {
    private final CandidatoService service;

    public CadastroController(CandidatoService service) {
        this.service = service;
    }

    @GetMapping("/listacandidatos")
    public List<CandidatoResumoDto> listaCandidatos() {
        return service.listarTodos();
    }

    @GetMapping("/listacandidatoslocalidade/{cep}/situacao/{situacao}")
    public List<CandidatoNumeroNomeDto> listaPorLocalidadeESituacao(@PathVariable String cep,
                                                                     @PathVariable String situacao) {
        return service.listarPorLocalidadeESituacao(cep, situacao);
    }

    @PostMapping("/cadcandidato")
    public boolean cadastraCandidato(@RequestBody CadastroCandidatoRequest request) {
        return service.cadastrar(request);
    }

    @PutMapping("/atualizacandidato/{numero}/situacao/{status}")
    public CandidatoResumoDto atualizaSituacao(@PathVariable int numero, @PathVariable String status) {
        return service.atualizarSituacao(numero, status);
    }

    @DeleteMapping("/removecandidato")
    public boolean removeCandidato(@RequestBody Integer numero) {
        return service.remover(numero);
    }
}
