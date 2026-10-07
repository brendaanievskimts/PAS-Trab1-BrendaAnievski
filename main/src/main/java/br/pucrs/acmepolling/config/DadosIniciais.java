package br.pucrs.acmepolling.config;

import br.pucrs.acmepolling.domain.Candidato;
import br.pucrs.acmepolling.domain.Localidade;
import br.pucrs.acmepolling.domain.Partido;
import br.pucrs.acmepolling.domain.Situacao;
import br.pucrs.acmepolling.repository.CandidatoRepository;
import br.pucrs.acmepolling.repository.LocalidadeRepository;
import br.pucrs.acmepolling.repository.PartidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciais implements CommandLineRunner {
    private final PartidoRepository partidos;
    private final LocalidadeRepository localidades;
    private final CandidatoRepository candidatos;

    public DadosIniciais(PartidoRepository partidos, LocalidadeRepository localidades, CandidatoRepository candidatos) {
        this.partidos = partidos;
        this.localidades = localidades;
        this.candidatos = candidatos;
    }

    @Override
    public void run(String... args) {
        Partido azul = new Partido(1, "Partido Azul");
        Partido verde = new Partido(2, "Partido Verde");
        Partido laranja = new Partido(3, "Partido Laranja");
        partidos.salvar(azul);
        partidos.salvar(verde);
        partidos.salvar(laranja);

        Localidade centro = new Localidade("90010-000", "Porto Alegre Centro", 1000);
        Localidade cachoeirinha = new Localidade("94900-000", "Cachoeirinha", 800);
        Localidade canoas = new Localidade("92000-000", "Canoas", 3);
        localidades.salvar(centro);
        localidades.salvar(cachoeirinha);
        localidades.salvar(canoas);

        salvarElegivel(new Candidato(1010, "Ana Souza", azul, centro));
        salvarElegivel(new Candidato(2020, "Bruno Lima", verde, centro));
        salvarElegivel(new Candidato(3030, "Carla Dias", laranja, cachoeirinha));
    }

    private void salvarElegivel(Candidato candidato) {
        candidato.alterarSituacao(Situacao.ELEGIVEL);
        candidatos.salvar(candidato);
    }
}
