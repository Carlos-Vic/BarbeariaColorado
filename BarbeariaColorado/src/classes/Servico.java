package classes;

public enum Servico {
    CORTE_SOCIAL("Corte Social", 40, 30),
    CORTE_DEGRADE("Corte Degradê", 45, 30),
    BARBA("Barba", 25, 30),
    BARBA_CORTESOCIAL("Barba + Corte Social", 65, 60),
    BARBA_CORTEDEGRADE("Barba + Corte Degradê", 70, 60),
    PINTAR_CABELO("Pintar Cabelo", 100, 30),
    HIDRATACAO("Hidratação", 40, 30);

    private final String nome; // Nome do serviço (para exibição no combobox)
    private final double preco;   // Preço do serviço
    private final int duracao; // Duração do serviço em minutos

    // Construtor
    Servico(String nome, int preco, int duracao) {
        this.nome = nome;
        this.preco = preco;
        this.duracao = duracao;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getDuracao() {
        return duracao;
    }

    // Sobrescreve o método toString() para exibir o nome do serviço no combobox da tela agendamento
    @Override
    public String toString() {
        return nome;
    }
}
