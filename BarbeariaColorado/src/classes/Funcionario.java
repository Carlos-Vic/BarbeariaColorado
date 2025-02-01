package classes;

public class Funcionario extends Pessoa {

    private String cargo;
    private String senha;

    public Funcionario(String nome, String CPF, String email, String endereco, String celular, String cargo, String senha) {
        super(nome, CPF, email, endereco, celular);
        this.cargo = cargo;
        this.senha = senha;

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
    /*
    public String cadastrarCliente(ArrayList<Cliente> clientes, Cliente novoCliente) {
        if (novoCliente == null) {
            return "Cliente inválido.";
        }
        for (Cliente cliente : clientes) {
            if (cliente.equals(novoCliente)) {
                return "Cliente " + novoCliente.getNome() + " já está cadastrado!";
            }
        }
        clientes.add(novoCliente);
        return "Cliente " + novoCliente.getNome() + " cadastrado com sucesso!";
    }

    public String editarCliente(ArrayList<Cliente> clientes, String CPF) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getCPF().equals(CPF)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            return "Cliente não encontrado.";
        }

        while (true) {
            System.out.println("1. Nome: " + cliente.getNome());
            System.out.println("2. Email: " + cliente.getEmail());
            System.out.println("3. Endereço: " + cliente.getEndereco());
            System.out.println("4. Celular: " + cliente.getCelular());
            System.out.println("5. Data de Aniversário: " + cliente.getDataAniversario());
            System.out.println("6. Voltar");
            System.out.print("Escolha o atributo que deseja editar (1-6): ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o novo nome: ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isEmpty()) {
                        cliente.setNome(novoNome);
                        System.out.println("Nome atualizado com sucesso!");
                    }
                }
                case 2 -> {
                    System.out.print("Digite o novo email: ");
                    String novoEmail = scanner.nextLine();
                    if (!novoEmail.isEmpty()) {
                        cliente.setEmail(novoEmail);
                        System.out.println("Email atualizado com sucesso!");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o novo endereço: ");
                    String novoEndereco = scanner.nextLine();
                    if (!novoEndereco.isEmpty()) {
                        cliente.setEndereco(novoEndereco);
                        System.out.println("Endereço atualizado com sucesso!");
                    }
                }
                case 4 -> {
                    System.out.print("Digite o novo celular: ");
                    String novoCelular = scanner.nextLine();
                    if (!novoCelular.isEmpty()) {
                        cliente.setCelular(novoCelular);
                        System.out.println("Celular atualizado com sucesso!");
                    }
                }

                case 5 -> {
                    System.out.print("Digite a nova data de aniversário (yyyy-MM-dd): ");
                    String novaData = scanner.nextLine();
                    if (!novaData.isEmpty()) {
                        cliente.setDataAniversario(LocalDate.parse(novaData));
                        System.out.println("Data de aniversário atualizada com sucesso!");
                    }
                }
                case 6 -> {
                    System.out.println("Voltando ao menu principal...");
                    return "Edição concluída!";
                }
                default ->
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public String agendarAtendimento(ArrayList<Atendimento> atendimentos, Cliente cliente, Servico servico, LocalDate dataAtendimento) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getCliente().equals(cliente)
                    && atendimento.getServico().equals(servico)
                    && atendimento.getDataAtendimento().equals(dataAtendimento)) {
                return "Atendimento já cadastrado.";
            }
        }
        Atendimento novoAtendimento = new Atendimento(cliente, servico, dataAtendimento);
        atendimentos.add(novoAtendimento);
        return "Atendimento de " + cliente.getNome() + " agendado para o dia " + dataAtendimento + " no valor de R$: " + servico.getPreco();
    }

    public String cancelarAtendimento(ArrayList<Atendimento> atendimentos, Cliente cliente, Servico servico, LocalDate dataAtendimento) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getCliente().equals(cliente)
                    && atendimento.getServico().equals(servico)
                    && atendimento.getDataAtendimento().equals(dataAtendimento)) {
                atendimentos.remove(atendimento);
                return "Atendimento de " + cliente.getNome() + " no dia " + dataAtendimento + " foi cancelado com sucesso.";
            }
        }
        return "Atendimento não encontrado para cancelamento.";
    }
     */
}
