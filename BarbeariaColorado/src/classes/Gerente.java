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

}
