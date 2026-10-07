package br.pucrs.acmepolling.domain;

public class Localidade {
    private final String cep;
    private final String nome;
    private final int qtdEleitores;

    public Localidade(String cep, String nome, int qtdEleitores) {
        this.cep = cep;
        this.nome = nome;
        this.qtdEleitores = qtdEleitores;
    }

    public String getCep() { return cep; }
    public String getNome() { return nome; }
    public int getQtdEleitores() { return qtdEleitores; }
}
