package classes;

public class Servicos {
    public enum TipoServico {
        CORTE_SOCIAL("Corte Social", 50, "30 min"),
        CORTE_DEGRADE("Corte degradê", 40, "45 min"),
        BARBA("Barba", 30, "30 min"),
        SOBRANCELHA("Sobrancelha", 20, "10 min"),
        PINTAR_CABELO("Pintar cabelo", 100, "120 min"),
        HIDRATACAO("Hidratação", 40, "20 min");

        private String servicoPrestado;
        private int preco;
        private String duracao;

        TipoServico(String servicoPrestado, int preco, String duracao) {
            this.servicoPrestado = servicoPrestado;
            this.preco = preco;
            this.duracao = duracao;
        }

        public String getServicoPrestado() {
            return servicoPrestado;
        }

        public int getPreco() {
            return preco;
        }

        public String getDuracao() {
            return duracao;
        }
    }

    private TipoServico tipoServico;
    public void setServicoPrestado(int servicoEscolhido) {
        if (servicoEscolhido == 1) {
            tipoServico = TipoServico.CORTE_SOCIAL;
            System.out.println("Você deseja fazer um corte social!");
        } else if (servicoEscolhido == 2) {
            tipoServico = TipoServico.CORTE_DEGRADE;
            System.out.println("Você deseja fazer um corte degradê!");
        } else if (servicoEscolhido == 3) {
            tipoServico = TipoServico.BARBA;
            System.out.println("Você deseja fazer a barba!");
        } else if (servicoEscolhido == 4) {
            tipoServico = TipoServico.SOBRANCELHA;
            System.out.println("Você deseja fazer a sobrancelha!");
        } else if (servicoEscolhido == 5) {
            tipoServico = TipoServico.PINTAR_CABELO;
            System.out.println("Você deseja pintar o seu cabelo!");
        } else if (servicoEscolhido == 6) {
            tipoServico = TipoServico.HIDRATACAO;
            System.out.println("Você deseja hidratar o cabelo!");
        } else {
            System.out.println("Não temos esse serviço na barbearia.");
        }

        System.out.println("Você escolheu o serviço: " + tipoServico.getServicoPrestado());
        System.out.println("Preço: R$ " + tipoServico.getPreco());
        System.out.println("Duração: " + tipoServico.getDuracao());
    }


}

