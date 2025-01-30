package classes;

public abstract class Pessoa {

    private String nome;
    private String CPF;
    private String email;
    private String endereco;
    private String celular;

    public Pessoa(String nome, String CPF, String email, String endereco, String celular) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
        this.endereco = endereco;
        this.celular = celular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

  
}
