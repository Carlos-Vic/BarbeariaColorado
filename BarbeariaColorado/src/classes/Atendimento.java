package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Atendimento {

    private Cliente cliente;
    private Servico servico;
    private LocalDate dataAtendimento;
    private List<Produto> produtosComprados; // Lista de produtos comprados
    private double totalPagamento;

    public Atendimento(Cliente cliente, Servico servico, LocalDate dataAtendimento) {
        this.cliente = cliente;
        this.servico = servico;
        this.dataAtendimento = dataAtendimento;
    }

    
    public Atendimento(Cliente cliente, Servico servico, LocalDate dataAtendimento, List<Produto> produtosComprados) {
        this.cliente = cliente;
        this.servico = servico;
        this.dataAtendimento = dataAtendimento;
        this.produtosComprados = produtosComprados;
    }
    
    public Atendimento(Cliente cliente, Servico servico, LocalDate dataAtendimento, double totalPagamento) {
        this.cliente = cliente;
        this.servico = servico;
        this.dataAtendimento = dataAtendimento;
        this.produtosComprados = new ArrayList<>(); // Inicializa a lista
        this.totalPagamento = servico.getPreco(); // Inicializa com o preço do serviço 
    }

    // Adiciona um produto ao atendimento
    public void adicionarProduto(Produto produto) {
        produtosComprados.add(produto);
    }

    // Remove um produto do atendimento
    public void removerProduto(Produto produto) {
        produtosComprados.remove(produto);
    }

    // Calcula o total do pagamento
    public double calcularTotalPagamento() {
        for (Produto produto : produtosComprados) {
            totalPagamento += produto.getPreco(); // Soma o preço de cada produto
        }
        return totalPagamento;
    }

    // Getters
    public Cliente getCliente() {
        return cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public List<Produto> getProdutosComprados() {
        return produtosComprados;
    }
}
