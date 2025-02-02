package telas;

import classes.Cliente;
import classes.Funcionario;
import classes.Servico;
import classes.Agendamento;
import static classes.Agendamento.verificarDisponibilidade;
import static classes.Agendamento.verificarDisponibilidadeCliente;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import javax.swing.table.DefaultTableModel;

public class CadastroAgendamento extends javax.swing.JFrame {

    static ArrayList<Agendamento> Agendamentos = (ArrayList<Agendamento>) Agendamento.getAgendamentos();
    String botao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private boolean criandoNovoAgendamento = false; //  flag para saber se está criando um novo agendamento
    private final Font fontePadrao = new Font("JetBrains Mono", Font.PLAIN, 14); // variavel para setar fontes nos campos formatados
    private final Font fonteTabela = new Font("JetBrains Mono", Font.PLAIN, 12); // variavel para setar fontes da tabela
    private List<Agendamento> agendamentosFiltrados = new ArrayList<>(); // lista para armazenar os agendamentos filtrados para pesquisa

    public CadastroAgendamento() {
        initComponents();

        // setando as fontes para os campos e para a tabela
        campoCliente.setFont(fontePadrao);
        campoFuncionario.setFont(fontePadrao);
        campoServico.setFont(fontePadrao);
        campoData.setFont(fontePadrao);
        campoHorario.setFont(fontePadrao);
        tabelaAgendamento.setFont(fonteTabela);

        // Habilitar os botões
        configurarBotoes(true, false, true, false, true, false, false);

        // Desabilitando os campos
        configurarCampos(false, false, false, false, false);

    }

    // Método para habilitar o campoHorario somente quando selecionar os campos de funcionario, data, serviço
    private void atualizarHorariosDisponiveis() {
        try {
            // Obtém os valores dos campos
            Funcionario funcionarioSelecionado = (Funcionario) campoFuncionario.getSelectedItem();
            Servico servicoSelecionado = (Servico) campoServico.getSelectedItem();
            Date dataSelecionada = (Date) campoData.getValue();

            // Verifica se todos os campos necessários estão preenchidos
            if (funcionarioSelecionado != null && servicoSelecionado != null && dataSelecionada != null) {
                // Converte a data para LocalDate
                LocalDate localDate = dataSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Verifica se a data selecionada é válida (não deve ser anterior ao dia atual)
                if (localDate.isBefore(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "A data não pode ser anterior à atual.", "Erro", JOptionPane.ERROR_MESSAGE);
                    campoData.requestFocus();
                    return;
                }

                // Habilita o campo de horários e carrega os horários disponíveis
                campoHorario.setEnabled(true);
                carregarHorariosDisponiveis(funcionarioSelecionado, localDate);
            } else {
                // Desabilita o campo de horários se algum campo não estiver preenchido
                campoHorario.setEnabled(false);
                campoHorario.removeAllItems();
            }
        } catch (Exception e) {
            e.printStackTrace();
            campoHorario.setEnabled(false);
            campoHorario.removeAllItems();
        }
    }

    // Método para carregar a tabela na interface
    public void carregarTabelaAgendamentos() {
        // Ordena a lista de agendamentos por data e depois por horário de início
        List<Agendamento> agendamentosOrdenados = new ArrayList<>(Agendamentos);
        agendamentosOrdenados.sort(
                Comparator.comparing(Agendamento::getData)
                        .thenComparing(Agendamento::getHoraInicio)
        );

        // Cria o modelo da tabela
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Cliente", "Funcionário", "Serviço", "Data", "Horário"}, 0);

        // Preenche a tabela com os agendamentos ordenados
        for (Agendamento agendamento : agendamentosOrdenados) {
            Object[] linha = new Object[]{
                agendamento.getCliente().getNome(),
                agendamento.getFuncionario().getNome(),
                agendamento.getServico().getNome(),
                agendamento.getData().format(formatter),
                agendamento.getHoraInicio()
            };
            modelo.addRow(linha);
        }

        tabelaAgendamento.setModel(modelo);
    }

    private void carregarTabelaModoPesquisa(List<Agendamento> agendamentos) {
        // Ordena a lista de agendamentos filtrados por data e depois por horário
        agendamentos.sort(
                Comparator.comparing(Agendamento::getData)
                        .thenComparing(Agendamento::getHoraInicio)
        );

        DefaultTableModel modelo = (DefaultTableModel) tabelaAgendamento.getModel();
        modelo.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados

        // Preenche a tabela com os agendamentos ordenados
        for (Agendamento agendamento : agendamentos) {
            modelo.addRow(new Object[]{
                agendamento.getCliente().getNome(),
                agendamento.getFuncionario().getNome(),
                agendamento.getServico().getNome(),
                agendamento.getData().format(formatter),
                agendamento.getHoraInicio()
            });
        }
    }

    // Método para filtrar os agendamentos por Clientes para a pesquisa
    private List<Agendamento> filtrarAgendamentosPorCliente(Cliente cliente) {
        List<Agendamento> agendamentosCliente = new ArrayList<>();

        // Aqui você faz a filtragem dos agendamentos com base no cliente
        for (Agendamento agendamento : Agendamentos) {
            if (agendamento.getCliente().equals(cliente)) {
                agendamentosCliente.add(agendamento);
            }
        }

        return agendamentosCliente;
    }

    // Método para limpar os campos
    private void limparCampos() {
        campoCliente.setSelectedItem(null);
        campoFuncionario.setSelectedItem(null);
        campoServico.setSelectedItem(null);
        campoData.setValue(new java.util.Date());  // Define a data atual como padrão
        campoHorario.setSelectedItem(null);
    }

    // Método para habilitar/desabilitar campos e botões
    private void configurarBotoes(boolean voltar, boolean buscar, boolean novo, boolean alterar, boolean pesquisar, boolean excluir, boolean salvar) {
        botaoVoltar.setEnabled(voltar);
        botaoBuscar.setEnabled(buscar);
        botaoNovo.setEnabled(novo);
        botaoAlterar.setEnabled(alterar);
        botaoPesquisar.setEnabled(pesquisar);
        botaoExcluir.setEnabled(excluir);
        botaoSalvar.setEnabled(salvar);
    }

    // Método para habilitar/desabilitar campos
    private void configurarCampos(boolean cliente, boolean funcionario, boolean servico, boolean data, boolean horario) {
        campoCliente.setEnabled(cliente);
        campoFuncionario.setEnabled(funcionario);
        campoServico.setEnabled(servico);
        campoData.setEnabled(data);
        campoHorario.setEnabled(horario);
    }

    // Método para carregar os clientes na ComboBox
    private void carregarClientes() {
        //Remover os itens da ComboBox
        campoCliente.removeAllItems();

        for (Cliente cliente : CadastroCliente.Clientes) {
            campoCliente.addItem(cliente);
        }
    }

    // Método para carregar os funcionarios na ComboBox
    private void carregarFuncionarios() {
        //Remover os itens da ComboBox
        campoFuncionario.removeAllItems();

        for (Funcionario funcionario : CadastroFuncionario.Funcionarios) {
            campoFuncionario.addItem(funcionario);
        }
    }

    // Método para carregar os servicos na ComboBox
    private void carregarServicos() {
        //Remover os itens da ComboBox
        campoServico.removeAllItems();

        for (Servico servico : Servico.values()) {
            campoServico.addItem(servico);
        }
    }

    // Método que obtem o agendamento selecionado 
    private Agendamento obterAgendamentoSelecionado() {
        int index = tabelaAgendamento.getSelectedRow();

        // Verifica se há uma linha válida selecionada
        if (index >= 0) {
            String nomeCliente = (String) tabelaAgendamento.getValueAt(index, 0);
            String nomeFuncionario = (String) tabelaAgendamento.getValueAt(index, 1);
            String nomeServico = (String) tabelaAgendamento.getValueAt(index, 2);
            String dataAgendamentoStr = (String) tabelaAgendamento.getValueAt(index, 3);
            LocalDate dataAgendamento = LocalDate.parse(dataAgendamentoStr, formatter);
            LocalTime horaAgendamento = (LocalTime) tabelaAgendamento.getValueAt(index, 4);

            // Procura o agendamento correspondente na lista
            List<Agendamento> listaDeAgendamentos = agendamentosFiltrados.isEmpty() ? Agendamentos : agendamentosFiltrados;

            for (Agendamento agendamento : listaDeAgendamentos) {
                if (agendamento.getCliente().getNome().equals(nomeCliente)
                        && agendamento.getFuncionario().getNome().equals(nomeFuncionario)
                        && agendamento.getServico().getNome().equals(nomeServico)
                        && agendamento.getData().equals(dataAgendamento)
                        && agendamento.getHoraInicio().equals(horaAgendamento)) {

                    return agendamento;
                }
            }
        }
        return null; // Caso não encontre ou índice inválido
    }

    // Método que obtém os agendamentos para um funcionário específico em uma data determinada
    private List<Agendamento> obterAgendamentosParaFuncionario(Funcionario funcionario, LocalDate data) {
        // Filtra a lista de agendamentos para encontrar aqueles que correspondem ao funcionário e a data informados
        return Agendamento.getAgendamentos().stream()
                .filter(a -> a.getFuncionario().equals(funcionario) && a.getData().equals(data))
                .collect(Collectors.toList());
    }

    // Método que obtém os horários disponíveis para agendamento considerando conflitos com a agenda
    private List<LocalTime> obterHorariosDisponiveis(Funcionario funcionario, LocalDate data, int duracaoServico) {
        List<LocalTime> horariosDisponiveis = new ArrayList<>();
        LocalTime inicioExpediente = LocalTime.of(8, 0);  // Início do expediente (08:00)
        LocalTime fimExpediente = LocalTime.of(18, 0);    // Fim do expediente (18:00)

        // Filtra os agendamentos do funcionário para o dia específico
        List<Agendamento> agendamentosDoDia = obterAgendamentosParaFuncionario(funcionario, data);

        LocalTime horarioAtual = inicioExpediente;

        while (horarioAtual.plusMinutes(duracaoServico).isBefore(fimExpediente)
                || horarioAtual.plusMinutes(duracaoServico).equals(fimExpediente)) {

            boolean horarioConflitante = false;

            // Verifica se algum agendamento para o funcionário específico conflita com esse horário
            for (Agendamento agendamento : agendamentosDoDia) {
                LocalTime inicioAgendamento = agendamento.getHoraInicio();
                LocalTime fimAgendamento = inicioAgendamento.plusMinutes(agendamento.getServico().getDuracao());

                // Verifica se há conflito (ocupação por outro agendamento)
                if ((horarioAtual.isBefore(fimAgendamento) && horarioAtual.plusMinutes(duracaoServico).isAfter(inicioAgendamento))) {
                    horarioConflitante = true;
                    break;
                }
            }

            // Se não houver conflito, o horário está disponível
            if (!horarioConflitante) {
                horariosDisponiveis.add(horarioAtual);
            }

            // Incrementa para o próximo horário disponível (múltiplos de 30 minutos)
            horarioAtual = horarioAtual.plusMinutes(30);
        }

        return horariosDisponiveis;
    }

    // Método para carregar os horários disponíveis na ComboBox
    private void carregarHorariosDisponiveis(Funcionario funcionario, LocalDate data) {
        // Limpa os itens da ComboBox de horários
        campoHorario.removeAllItems();

        // Verifica o serviço selecionado e a sua duração
        Servico servicoSelecionado = (Servico) campoServico.getSelectedItem();
        if (servicoSelecionado == null || funcionario == null || data == null) {
            return;  // Se o serviço, o funcionário ou a data não estiverem definidos, retorna sem carregar os horários
        }

        int duracaoServico = servicoSelecionado.getDuracao();

        // Obtém agendamento selecionado (para alteração)
        Agendamento agendamentoSelecionado = obterAgendamentoSelecionado();
        LocalTime horarioOriginal = (agendamentoSelecionado != null) ? agendamentoSelecionado.getHoraInicio() : null;

        // Obtém os horários disponíveis para o funcionário e a data selecionada
        List<LocalTime> horariosDisponiveis = obterHorariosDisponiveis(funcionario, data, duracaoServico);

        // Se o horário original for válido para o novo serviço, adicione-o à lista de horários
        if (horarioOriginal != null) {
            // Verifica se o horário original ainda está disponível para o novo serviço
            boolean horarioDisponivelParaAlteracao = verificarDisponibilidade(
                    funcionario, data, horarioOriginal, duracaoServico, agendamentoSelecionado, true);

            // Se o horário original for válido (não conflitante), adiciona-o na lista
            if (horarioDisponivelParaAlteracao) {
                campoHorario.addItem(horarioOriginal);
            }
        }

        // Carrega os horários na ComboBox
        for (LocalTime horario : horariosDisponiveis) {
            // Adiciona os outros horários disponíveis, excluindo o horário original, se já foi adicionado
            if (horarioOriginal == null || !horario.equals(horarioOriginal)) {
                campoHorario.addItem(horario);
            }
        }

        // Caso não haja horários, adicione a opção nula
        if (campoHorario.getItemCount() == 0) {
            campoHorario.addItem(null);
        }
    }

    // Método para configurar a interface para cadastrar novo agendamento
    private void configurarModoNovo() {
        botao = "novo";

        // Verifica se as listas estão inicializadas (não nulas) e se estão vazias
        if (CadastroCliente.Clientes == null || CadastroCliente.Clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há clientes cadastrados. Cadastre um cliente primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return; // Interrompe o fluxo caso não haja clientes
        }

        if (CadastroFuncionario.Funcionarios == null || CadastroFuncionario.Funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há funcionários cadastrados. Cadastre um funcionário primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return; // Interrompe o fluxo caso não haja funcionários
        }

        // Caso existam clientes e funcionários, prossegue com a configuração do modo novo
        carregarClientes();
        carregarFuncionarios();
        carregarServicos();
        configurarCampos(true, true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, false, true, false, true, false, true);
        limparCampos();
        campoCliente.requestFocus();
    }

    // Método para configurar a interface para o modo de salvar
    private void configurarModoSalvar() {
        carregarTabelaAgendamentos();
        configurarCampos(false, false, false, false, false);
        configurarBotoes(true, false, true, false, true, false, false);
        limparCampos();
        campoCliente.requestFocus();
    }

    // Método para configurar a interface para o modo de excluir
    private void configurarModoExcluir() {
        carregarTabelaAgendamentos();
        configurarCampos(false, false, false, false, false);
        configurarBotoes(true, false, true, false, true, false, false);
        limparCampos();
    }

    // Método para configurar a interface para o modo de pesquisa
    private void configurarModoPesquisa() {
        configurarCampos(true, false, false, false, false);
        configurarBotoes(true, true, false, false, false, false, false);
        limparCampos();
    }

    // Método para configurar a interface para o modo de alteraçao
    private void configurarModoAlteracao() {
        criandoNovoAgendamento = false;
        botao = "alterar"; // Define o estado do botão como "alterar"
        configurarCampos(false, true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, false, true, false, true, false, true);
        campoCliente.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        campoCliente = new javax.swing.JComboBox<>();
        campoFuncionario = new javax.swing.JComboBox<>();
        campoServico = new javax.swing.JComboBox<>();
        campoData = new javax.swing.JSpinner();
        campoHorario = new javax.swing.JComboBox<>();
        botaoNovo = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAgendamento = new javax.swing.JTable();
        cadastroAgendamento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botaoVoltar.setBackground(new java.awt.Color(57, 62, 70));
        botaoVoltar.setBorder(null);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 40));

        campoCliente.setBackground(new java.awt.Color(238, 238, 238));
        campoCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        campoCliente.setModel(new javax.swing.DefaultComboBoxModel<>());
        campoCliente.setBorder(null);
        getContentPane().add(campoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 290, 40));

        campoFuncionario.setBackground(new java.awt.Color(238, 238, 238));
        campoFuncionario.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        campoFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>());
        campoFuncionario.setBorder(null);
        campoFuncionario.addActionListener(e -> {
            // Só habilita o campoHorario se a busca não estiver ativa
            if (criandoNovoAgendamento) {
                atualizarHorariosDisponiveis();  // Habilita campoHorario
            }
        });
        getContentPane().add(campoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 290, 40));

        campoServico.setBackground(new java.awt.Color(238, 238, 238));
        campoServico.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        campoServico.setModel(new javax.swing.DefaultComboBoxModel<>());
        campoServico.setBorder(null);
        campoServico.addActionListener(e -> {
            // Só habilita o campoHorario se a busca não estiver ativa
            if (criandoNovoAgendamento) {
                atualizarHorariosDisponiveis();  // Habilita campoHorario
            }
        });
        getContentPane().add(campoServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 290, 40));

        campoData.setModel(new javax.swing.SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(campoData, "dd/MM/yyyy");
        campoData.setEditor(editor);

        campoData.setBorder(null);
        campoData.setBackground(new java.awt.Color(238, 238, 238));

        campoData.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                // Só atualiza os horários se a busca não estiver ativa
                if (criandoNovoAgendamento) {
                    atualizarHorariosDisponiveis();  // Habilita campoHorario
                }
            }
        });
        getContentPane().add(campoData, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 290, 40));

        campoHorario.setBackground(new java.awt.Color(238, 238, 238));
        campoHorario.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        campoHorario.setModel(new javax.swing.DefaultComboBoxModel<>());
        campoHorario.setBorder(null);
        getContentPane().add(campoHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 290, 40));

        botaoNovo.setBorder(null);
        botaoNovo.setBorderPainted(false);
        botaoNovo.setContentAreaFilled(false);
        botaoNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });
        getContentPane().add(botaoNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 650, 140, 30));

        botaoAlterar.setBorder(null);
        botaoAlterar.setBorderPainted(false);
        botaoAlterar.setContentAreaFilled(false);
        botaoAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 650, 140, 30));

        botaoPesquisar.setBorder(null);
        botaoPesquisar.setBorderPainted(false);
        botaoPesquisar.setContentAreaFilled(false);
        botaoPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 650, 150, 30));

        botaoExcluir.setBorder(null);
        botaoExcluir.setBorderPainted(false);
        botaoExcluir.setContentAreaFilled(false);
        botaoExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(botaoExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 650, 150, 30));

        botaoSalvar.setBorder(null);
        botaoSalvar.setBorderPainted(false);
        botaoSalvar.setContentAreaFilled(false);
        botaoSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 650, 140, 30));

        botaoBuscar.setBorder(null);
        botaoBuscar.setBorderPainted(false);
        botaoBuscar.setContentAreaFilled(false);
        botaoBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 80, 40));

        tabelaAgendamento.setBackground(new java.awt.Color(238, 238, 238));
        tabelaAgendamento.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tabelaAgendamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Funcionário", "Serviço", "Data", "Horário"
            }
        ));
        tabelaAgendamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAgendamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaAgendamento);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 750, 560));

        cadastroAgendamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tela_agendamento.png"))); // NOI18N
        getContentPane().add(cadastroAgendamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1220, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoVoltarActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        criandoNovoAgendamento = true;
        configurarModoNovo();
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        try {
            // Verifica se todos os campos necessários estão preenchidos
            if (campoCliente.getSelectedItem() == null || campoFuncionario.getSelectedItem() == null
                    || campoServico.getSelectedItem() == null || campoData.getValue() == null
                    || campoHorario.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                campoCliente.requestFocus();
                return;
            }

            // Obtém os valores dos campos
            Cliente cliente = (Cliente) campoCliente.getSelectedItem();
            Funcionario funcionario = (Funcionario) campoFuncionario.getSelectedItem();
            Servico servico = (Servico) campoServico.getSelectedItem();

            java.util.Date dataUtil = (java.util.Date) campoData.getValue();
            LocalDate data = dataUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime horaInicio = (LocalTime) campoHorario.getSelectedItem();

            // Verifica a data do agendamento
            if (data.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, "A data do agendamento não pode ser anterior à data atual!", "Erro", JOptionPane.ERROR_MESSAGE);
                campoData.requestFocus();
                return;
            }

            // Determina se é uma alteração
            Agendamento agendamentoSelecionado = obterAgendamentoSelecionado();
            boolean ehAlteracao = botao.equals("alterar");

            // Verifica se o cliente já tem agendamento nesse horário, ignorando o atual em caso de alteração
            if (!verificarDisponibilidadeCliente(cliente, data, horaInicio, agendamentoSelecionado)) {
                JOptionPane.showMessageDialog(null, "O cliente " + cliente.getNome() + " já tem um agendamento neste horário.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica conflitos de horário do funcionário
            if (!verificarDisponibilidade(funcionario, data, horaInicio, servico.getDuracao(), agendamentoSelecionado, ehAlteracao)) {
                JOptionPane.showMessageDialog(null, "Conflito de horário! O funcionário já possui um agendamento neste horário.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirmação do agendamento
            int confirmacao = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza de que deseja agendar esse horário?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacao == JOptionPane.YES_OPTION) {
                if (botao.equals("novo")) {
                    // Cria um novo agendamento
                    Agendamento novoAgendamento = new Agendamento(cliente, funcionario, servico, data, horaInicio);
                    Agendamento.getAgendamentos().add(novoAgendamento);
                    JOptionPane.showMessageDialog(null, "Horário marcado com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                } else if (ehAlteracao) {
                    // Atualiza os dados do agendamento
                    agendamentoSelecionado.setCliente(cliente);
                    agendamentoSelecionado.setFuncionario(funcionario);
                    agendamentoSelecionado.setServico(servico);
                    agendamentoSelecionado.setData(data);
                    agendamentoSelecionado.setHoraInicio(horaInicio);

                    JOptionPane.showMessageDialog(null, "Horário alterado com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            }

            // Atualiza a tabela com os novos dados
            carregarTabelaAgendamentos();
            limparCampos();
            configurarModoSalvar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o agendamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void tabelaAgendamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAgendamentoMouseClicked
        // Obtém a linha selecionada na tabela
        int linhaSelecionada = tabelaAgendamento.getSelectedRow();

        // Verifica se a linha selecionada é válida
        if (linhaSelecionada >= 0) {
            // Usamos a lista filtrada de agendamentos, caso a busca tenha sido realizada
            List<Agendamento> listaAgendamentos = agendamentosFiltrados.isEmpty() ? Agendamentos : agendamentosFiltrados;

            // Verifica se o índice é válido para a lista escolhida
            if (linhaSelecionada < listaAgendamentos.size()) {
                // Obtém o agendamento correspondente da lista
                Agendamento agendamentoSelecionado = listaAgendamentos.get(linhaSelecionada);

                // Preenche os campos com os dados do agendamento selecionado
                campoCliente.setSelectedItem(agendamentoSelecionado.getCliente());
                campoFuncionario.setSelectedItem(agendamentoSelecionado.getFuncionario());
                campoServico.setSelectedItem(agendamentoSelecionado.getServico());
                campoData.setValue(java.sql.Date.valueOf(agendamentoSelecionado.getData()));

                // Obtém horários disponíveis para o funcionário e o dia selecionados
                List<LocalTime> horariosDisponiveis = obterHorariosDisponiveis(
                        agendamentoSelecionado.getFuncionario(),
                        agendamentoSelecionado.getData(),
                        agendamentoSelecionado.getServico().getDuracao()
                );

                // Inclui o horário original do agendamento no início da lista de horários
                if (!horariosDisponiveis.contains(agendamentoSelecionado.getHoraInicio())) {
                    horariosDisponiveis.add(0, agendamentoSelecionado.getHoraInicio());
                }

                // Ordena os horários para manter a lista consistente
                horariosDisponiveis.sort(Comparator.naturalOrder());

                // Atualiza o campo de horário com os horários disponíveis
                campoHorario.setModel(new DefaultComboBoxModel<>(horariosDisponiveis.toArray(LocalTime[]::new)));
                campoHorario.setSelectedItem(agendamentoSelecionado.getHoraInicio());
            }

        }

        configurarBotoes(true, false, true, true, true, true, false);
        configurarCampos(false, false, false, false, false);
    }//GEN-LAST:event_tabelaAgendamentoMouseClicked

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        configurarModoAlteracao();

        // Obtém o agendamento selecionado através do método auxiliar
        Agendamento agendamentoSelecionado = obterAgendamentoSelecionado();

        if (agendamentoSelecionado != null) {
            // Preenche os campos com os dados do agendamento selecionado
            campoCliente.setSelectedItem(agendamentoSelecionado.getCliente());
            campoFuncionario.setSelectedItem(agendamentoSelecionado.getFuncionario());
            campoServico.setSelectedItem(agendamentoSelecionado.getServico());
            campoData.setValue(java.sql.Date.valueOf(agendamentoSelecionado.getData()));

            // Obtém horários disponíveis para o funcionário e o dia selecionados
            List<LocalTime> horariosDisponiveis = obterHorariosDisponiveis(
                    agendamentoSelecionado.getFuncionario(),
                    agendamentoSelecionado.getData(),
                    agendamentoSelecionado.getServico().getDuracao()
            );

            // Inclui o horário original do agendamento no início da lista de horários
            if (!horariosDisponiveis.contains(agendamentoSelecionado.getHoraInicio())) {
                horariosDisponiveis.add(0, agendamentoSelecionado.getHoraInicio());
            }

            // Ordena os horários para manter a lista consistente
            horariosDisponiveis.sort(Comparator.naturalOrder());

            // Atualiza o campo de horário com os horários disponíveis
            campoHorario.setModel(new DefaultComboBoxModel<>(horariosDisponiveis.toArray(LocalTime[]::new)));
            campoHorario.setSelectedItem(agendamentoSelecionado.getHoraInicio());
        } else {
            // Caso nenhum agendamento tenha sido selecionado
            JOptionPane.showMessageDialog(null, "Nenhum agendamento selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        // Obtém o agendamento selecionado
        Agendamento agendamentoSelecionado = obterAgendamentoSelecionado();

        if (agendamentoSelecionado != null) {
            // Confirmação da exclusão
            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir o agendamento selecionado?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                // Remove o agendamento da lista geral
                Agendamento.getAgendamentos().remove(agendamentoSelecionado);

                // Verifica se a lista filtrada está em uso e remove da mesma
                if (!agendamentosFiltrados.isEmpty()) {
                    agendamentosFiltrados.remove(agendamentoSelecionado);
                }

                // Exibe a mensagem de sucesso
                JOptionPane.showMessageDialog(this,
                        "Agendamento excluído com sucesso!");

                // Recarrega a tabela com os agendamentos atualizados
                carregarTabelaAgendamentos();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Por favor, selecione um agendamento para excluir.");
        }

        configurarModoExcluir();

    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        configurarModoPesquisa();

    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        // Desabilita o comboBox e o botão "Buscar" após a pesquisa
        campoCliente.setEnabled(false);
        configurarBotoes(true, false, true, false, true, false, false);

        // Obtém o cliente selecionado no comboBox
        Cliente clienteSelecionado = (Cliente) campoCliente.getSelectedItem();

        if (clienteSelecionado != null) {
            // Filtra os agendamentos desse cliente
            List<Agendamento> agendamentosCliente = filtrarAgendamentosPorCliente(clienteSelecionado);

            // Atualiza a tabela com os agendamentos filtrados
            carregarTabelaModoPesquisa(agendamentosCliente);

            // Armazena a lista filtrada para referência futura
            agendamentosFiltrados = agendamentosCliente;
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente para buscar!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroAgendamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel cadastroAgendamento;
    private javax.swing.JComboBox<Cliente> campoCliente;
    private javax.swing.JSpinner campoData;
    private javax.swing.JComboBox<Funcionario> campoFuncionario;
    private javax.swing.JComboBox<LocalTime> campoHorario;
    private javax.swing.JComboBox<Servico> campoServico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaAgendamento;
    // End of variables declaration//GEN-END:variables
}
