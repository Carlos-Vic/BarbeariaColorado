package classes;

import java.time.Duration;

public class Servico {

    private String tipo;
    private String descricao;
    private double preco;
    private Duration duracao;

    public Servico(String tipo, String descricao, double preco, Duration duracao) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
        this.duracao = duracao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

}
