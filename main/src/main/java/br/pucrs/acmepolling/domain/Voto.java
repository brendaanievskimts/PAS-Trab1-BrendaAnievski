package br.pucrs.acmepolling.domain;

public class Voto {
    private final int id;
    private final int hora;
    private final Candidato candidato;
    private final Localidade localidade;
    private boolean valido = true;

    public Voto(int id, int hora, Candidato candidato, Localidade localidade) {
        this.id = id;
        this.hora = hora;
        this.candidato = candidato;
        this.localidade = localidade;
    }

    public void invalidar() { this.valido = false; }

    public int getId() { return id; }
    public int getHora() { return hora; }
    public Candidato getCandidato() { return candidato; }
    public Localidade getLocalidade() { return localidade; }
    public boolean isValido() { return valido; }
}
