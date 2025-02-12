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

}
