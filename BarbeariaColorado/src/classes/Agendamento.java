package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Agendamento {

    private Cliente cliente;
    private Funcionario funcionario;
    private Servico servico;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    // Lista estática para armazenar todos os agendamentos
    private static List<Agendamento> agendamentos = new ArrayList<>();

    // Construtor
    public Agendamento(Cliente cliente, Funcionario funcionario, Servico servico, LocalDate data, LocalTime horaInicio) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaInicio.plusMinutes(servico.getDuracao()); // Calcula a hora de fim

        // Verifica se o horário está disponível
        if (!verificarDisponibilidade()) {
            throw new IllegalArgumentException("Horário indisponível para agendamento.");
        }

        // Adiciona o agendamento à lista
        agendamentos.add(this);
    }

    // Getters
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

    // Método para verificar a disponibilidade do horário
    private boolean verificarDisponibilidade() {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getFuncionario().equals(this.funcionario) && agendamento.getData().equals(this.data)) {
                // Verifica se há sobreposição de horários
                if (this.horaInicio.isBefore(agendamento.getHoraFim()) && this.horaFim.isAfter(agendamento.getHoraInicio())) {
                    return false; // Há sobreposição de horários
                }
            }
        }
        return true; // Horário disponível
    }

    // Método estático para listar agendamentos de um funcionário em uma data
    public static List<Agendamento> listarAgendamentos(Funcionario funcionario, LocalDate data) {
        List<Agendamento> agendamentosDoDia = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getFuncionario().equals(funcionario) && agendamento.getData().equals(data)) {
                agendamentosDoDia.add(agendamento);
            }
        }
        return agendamentosDoDia;
    }

    // Método estático para verificar horários disponíveis de um funcionário em uma data
    public static List<LocalTime> verificarHorariosDisponiveis(Funcionario funcionario, LocalDate data) {
        List<LocalTime> horariosDisponiveis = new ArrayList<>();
        LocalTime horarioAtual = LocalTime.of(9, 0); // Horário de abertura da barbearia
        LocalTime horarioFechamento = LocalTime.of(18, 0); // Horário de fechamento da barbearia

        // Lista de agendamentos do funcionário na data especificada
        List<Agendamento> agendamentosDoDia = listarAgendamentos(funcionario, data);

        while (horarioAtual.isBefore(horarioFechamento)) {
            boolean horarioLivre = true;

            // Verifica se o horário atual está livre
            for (Agendamento agendamento : agendamentosDoDia) {
                if (horarioAtual.isBefore(agendamento.getHoraFim()) && horarioAtual.plusMinutes(30).isAfter(agendamento.getHoraInicio())) {
                    horarioLivre = false;
                    break;
                }
            }

            if (horarioLivre) {
                horariosDisponiveis.add(horarioAtual);
            }

            // Avança para o próximo horário (intervalo de 30 minutos)
            horarioAtual = horarioAtual.plusMinutes(30);
        }

        return horariosDisponiveis;
    }

    // Método estático para adicionar um agendamento
    public static void adicionarAgendamento(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    // Método estático para remover um agendamento
    public static void removerAgendamento(Agendamento agendamento) {
        agendamentos.remove(agendamento);
    }

    // Método estático para obter todos os agendamentos
    public static List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

}
