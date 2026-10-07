package br.pucrs.acmepolling.domain;

import br.pucrs.acmepolling.exception.RegraDeNegocioException;

public class Candidato {
    private final int numero;
    private final String nome;
    private final Partido partido;
    private final Localidade localidade;
    private Situacao situacao = Situacao.PRECANDIDATO;

    public Candidato(int numero, String nome, Partido partido, Localidade localidade) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.localidade = localidade;
    }

    public void alterarSituacao(Situacao nova) {
        if (!situacao.podeIrPara(nova)) {
            throw new RegraDeNegocioException("Transição inválida: " + situacao + " -> " + nova);
        }
        this.situacao = nova;
    }

    public boolean podeReceberVotos() { return situacao.podeReceberVotos(); }

    public int getNumero() { return numero; }
    public String getNome() { return nome; }
    public Partido getPartido() { return partido; }
    public Localidade getLocalidade() { return localidade; }
    public Situacao getSituacao() { return situacao; }
}
