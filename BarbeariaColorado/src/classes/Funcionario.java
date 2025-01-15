package classes;

public class Funcionario extends Pessoa {

    private String cargo;
    private String senha;

    public Funcionario(String nome, String CPF, String email, String endereco, String celular, String cargo, String senha) {
        super(nome, CPF, email, endereco, celular);
        this.cargo = cargo;
        this.senha = senha;

    }

    /*
    public void cadastrarServico;
    public void editarServico;
    public void excluirServico;*/
}
