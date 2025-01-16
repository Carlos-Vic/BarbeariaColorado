package classes;

import java.time.LocalDate;
import java.util.*;

public class Gerente extends Pessoa {
    private String senha;

    public Gerente(String nome, String CPF, String email, String endereco, String celular, String senha) {
        super(nome, CPF, email, endereco, celular);
        this.senha = senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String cadastrarFuncionario(ArrayList<Funcionario> funcionarios, Funcionario novoFuncionario) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.equals(novoFuncionario)) {
                return "Funcionário(a) já está cadastrado.";
            }
        }
        funcionarios.add(novoFuncionario);
        return "Funcionário(a) " + novoFuncionario.getNome() + " cadastrado com sucesso!";
    }

    public Object buscarFuncionario(ArrayList<Funcionario> funcionarios, String CPF) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCPF().equals(CPF)) {
                return funcionario;
            }
        }
        System.out.println("Funcionário(a) não encontrado.");
        return " ";
    }
    public String excluirFuncionario(ArrayList<Funcionario> funcionarios, String CPF) {
        Funcionario funcionario = (Funcionario) buscarFuncionario(funcionarios, CPF);
        if (funcionario != null) {
            funcionarios.remove(funcionario);
            return "Funcionário(a) " + funcionario.getNome() + " demitido.";
        } else {
            return "Funcionário(a) não cadastrado.";
        }

    }

    public String cadastrarServico(ArrayList<Servico> servicos, Servico novoServico) {
        for (Servico servico : servicos) {
            if (servico.getTipo().equals(novoServico.getTipo())) {
                return "Serviço já existente.";
            }
        }
        servicos.add(novoServico);
        return "Serviço " + novoServico.getTipo() + " cadastrado com sucesso!";


    }

    public String editarServico(ArrayList<Servico> servicos, String tipo, String novaDescricao, Double novoPreco) {
        for (Servico servico : servicos) {
            if (servico.getTipo().equals(tipo)) {
                if (novaDescricao != null && !novaDescricao.isEmpty()) {
                    servico.setDescricao(novaDescricao);
                }
                if (novoPreco != null && novoPreco > 0) {
                    servico.setPreco(novoPreco);
                }
                return "Serviço " + servico.getTipo() + " atualizado com sucesso!";
            }
        }
        return "Serviço não encontrado.";
    }

    public String excluirServico(ArrayList<Servico> servicos, Servico servico) {
        if (servico == null) {
            return "Serviço inválido.";
        }
        for (Servico servicoExistente : servicos) {
            if (servicoExistente.equals(servico)) {
                servicos.remove(servicoExistente);
                return "Serviço " + servicoExistente.getTipo() + " excluído.";
            }
        }
        return "Serviço não presente na lista da barbearia.";
    }

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
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /*
    public void editarCliente {};
    public void editarServico;
 */


}

