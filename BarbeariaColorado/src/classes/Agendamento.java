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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public static void setAgendamentos(List<Agendamento> agendamentos) {
        Agendamento.agendamentos = agendamentos;
    }

    // Método para adicionar um agendamento à lista
    public static void adicionarAgendamento(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public static boolean verificarDisponibilidade(Funcionario funcionario, LocalDate data, LocalTime horaInicio, int duracao, Agendamento agendamentoIgnorado, boolean ehAlteracao) {
        LocalTime horaFim = horaInicio.plusMinutes(duracao); // Calcular o horário de fim do novo agendamento

        // Verificar todos os agendamentos para o mesmo funcionário na mesma data
        for (Agendamento agendamento : agendamentos) {
            // Ignora o agendamento especificado (por exemplo, o agendamento que está sendo alterado)
            if (agendamentoIgnorado != null && agendamento.equals(agendamentoIgnorado)) {
                continue;
            }

            if (agendamento.getFuncionario().equals(funcionario) && agendamento.getData().equals(data)) {
                LocalTime agendamentoInicio = agendamento.getHoraInicio();
                LocalTime agendamentoFim = agendamento.getHoraFim(); // Hora de fim do agendamento existente

                // Verificar se os horários se sobrepõem
                if (horaInicio.isBefore(agendamentoFim) && horaFim.isAfter(agendamentoInicio)) {
                    // Se for uma alteração, permite que o horário original seja mantido
                    if (ehAlteracao && horaInicio.equals(agendamentoIgnorado.getHoraInicio())) {
                        continue; // Ignora o conflito para o horário original durante a alteração
                    }
                    return false; // Conflito de horário
                }
            }
        }
        return true; // Horário disponível
    }

    // Método para obter todos os agendamentos de um funcionário em uma data específica
    public static List<Agendamento> getAgendamentosPorFuncionarioEData(Funcionario funcionario, LocalDate data) {
        List<Agendamento> agendamentosDoDia = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getFuncionario().equals(funcionario) && agendamento.getData().equals(data)) {
                agendamentosDoDia.add(agendamento);
            }
        }
        return agendamentosDoDia;
    }

    public static List<Agendamento> getAgendamentos() {
        return agendamentos;
    }
}
