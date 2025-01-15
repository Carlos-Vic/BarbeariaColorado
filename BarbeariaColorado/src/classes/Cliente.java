package classes;

import java.time.LocalDate;

public class Cliente extends Pessoa{

    private LocalDate dataAniversario;

    public Cliente(String nome, String cpf, String endereco, String email, String celular, LocalDate dataAniversario) {
        super(nome, cpf, endereco, email, celular);
        this.dataAniversario = dataAniversario;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }
}

