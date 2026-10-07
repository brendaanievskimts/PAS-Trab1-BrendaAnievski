package br.pucrs.acmepolling.domain;

public class Partido {
    private final int codigo;
    private final String nome;

    public Partido(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
}
