package br.pucrs.acmepolling.controller;

import br.pucrs.acmepolling.dto.CadastroVotoRequest;
import br.pucrs.acmepolling.service.VotoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acmepolling/votacao")
public class VotacaoController {
    private final VotoService service;

    public VotacaoController(VotoService service) {
        this.service = service;
    }

    @PostMapping("/cadvoto")
    public boolean cadastraVoto(@RequestBody CadastroVotoRequest request) {
        return service.cadastrar(request);
    }
}
