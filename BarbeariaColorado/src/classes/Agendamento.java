package classes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {

    private Cliente cliente;
    private Funcionario funcionario;
    private Servico servico;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean confirmado;

    public Agendamento(Cliente cliente, Funcionario funcionario, Servico servico, LocalDate data, LocalTime horaInicio) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = calcularHoraFim(horaInicio, servico); // Considera a duração do serviço para calcular o horário final
        this.confirmado = false; // O agendamento não está confirmado inicialmente
    }

    // Método para calcular a hora de término do serviço com base na duração
    private LocalTime calcularHoraFim(LocalTime horaInicio, Servico servico) {
        Duration duracaoServico = servico.getDuracao();  // Duração do serviço
        return horaInicio.plus(duracaoServico); // Adiciona a duração ao horário de início
    }

// Método para confirmar o agendamento, garantindo que a confirmação e disponibilidade funcionem corretamente
    public void confirmarAgendamento() {
        if (!confirmado) {
            // Verifica se o funcionário está disponível e confirma o agendamento
            if (funcionario.verificarDisponibilidade(data, horaInicio, horaFim)) {
                confirmado = true;
                funcionario.adicionarAgendamento(this);  // Adiciona à agenda do funcionário
                System.out.println("Agendamento confirmado para o cliente " + cliente.getNome() + " no dia " + data + " às " + horaInicio);
            } else {
                System.out.println("O funcionário não está disponível neste horário.");
            }
        } else {
            System.out.println("Este agendamento já está confirmado.");
        }
    }

// Método para cancelar o agendamento
    public void cancelarAgendamento() {
        if (confirmado) {
            confirmado = false;
            funcionario.removerAgendamento(this); // Remove o agendamento da agenda do funcionário
            System.out.println("Agendamento cancelado para o cliente " + cliente.getNome() + " no dia " + data + " às " + horaInicio);
        } else {
            System.out.println("Este agendamento não está confirmado e não pode ser cancelado.");
        }
    }

    // Getters e Setters
    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }
}
