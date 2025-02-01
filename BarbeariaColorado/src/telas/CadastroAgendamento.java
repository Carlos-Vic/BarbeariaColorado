package telas;

import classes.Cliente;
import classes.Funcionario;
import classes.Servico;
import classes.Agendamento;
import static classes.Agendamento.verificarDisponibilidade;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import javax.swing.table.DefaultTableModel;

public class CadastroAgendamento extends javax.swing.JFrame {

    static ArrayList<Agendamento> Agendamentos = (ArrayList<Agendamento>) Agendamento.getAgendamentos();
    String botao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int linhaSelecionada = -1; // Para armazenar a linha selecionada
    private final Font fontePadrao = new Font("JetBrains Mono", Font.PLAIN, 14); // variavel para setar fontes nos campos formatados
    private final Font fonteTabela = new Font("JetBrains Mono", Font.PLAIN, 12); // variavel para setar fontes nos campos formatados

    public CadastroAgendamento() {
        initComponents();

        campoCliente.setFont(fontePadrao);
        campoFuncionario.setFont(fontePadrao);
        campoServico.setFont(fontePadrao);
        campoData.setFont(fontePadrao);
        campoHorario.setFont(fontePadrao);
        tabelaAgendamento.setFont(fonteTabela);

        // Habilitar os botões
        configurarBotoes(true, true, false, true, false, false);

        // Desabilitando os campos
        configurarCampos(false, false, false, false, false);

    }

    // Método para carregar a tabela na interface
    public void carregarTabelaAgendamentos() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Cliente", "Funcionario", "Serviço", "Data", "Horário"}, 0);

        for (Agendamento agendamento : Agendamentos) {
            Object linha[] = new Object[]{
                agendamento.getCliente(),
                agendamento.getFuncionario(),
                agendamento.getServico(),
                agendamento.getData().format(formatter),
                agendamento.getHoraInicio()};
            modelo.addRow(linha);
        }

        tabelaAgendamento.setModel(modelo);
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
    private void configurarBotoes(boolean voltar, boolean novo, boolean alterar, boolean pesquisar, boolean excluir, boolean salvar) {
        botaoVoltar.setEnabled(voltar);
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

    private Agendamento obterAgendamentoSelecionado() {
        // Obtém o índice da linha selecionada na tabela de agendamentos
        int index = tabelaAgendamento.getSelectedRow();

        // Verifica se o índice selecionado é válido (maior ou igual a 0 e menor que o número total de linhas)
        if (index >= 0 && index < tabelaAgendamento.getRowCount()) {
            // Aqui você busca o agendamento correspondente na lista de agendamentos
            Agendamento agendamentoSelecionado = Agendamentos.get(index);
            return agendamentoSelecionado;
        }
        return null; // Se não houver agendamento selecionado ou índice inválido
    }

    // Método para carregar os horários disponíveis na ComboBox (vai no actionPerformed do campoFuncionario)
    private void carregarHorariosDisponiveis(Funcionario funcionario, LocalDate data) {
        // Limpa os itens da ComboBox de horários
        campoHorario.removeAllItems();

        // Horário de trabalho do funcionário: das 8h às 18h
        LocalTime inicio = LocalTime.of(8, 0);
        LocalTime fim = LocalTime.of(18, 0);

        // Verifica o serviço selecionado e a sua duração
        Servico servicoSelecionado = (Servico) campoServico.getSelectedItem();
        if (servicoSelecionado == null) {
            return;
        }

        int duracaoServico = servicoSelecionado.getDuracao(); // Duração do serviço em minutos

        // Verifica se estamos alterando um agendamento existente
        Agendamento agendamentoSelecionado = obterAgendamentoSelecionado(); // Método que obtém o agendamento selecionado para alteração, caso exista
        LocalTime horarioOriginal = null;

        if (agendamentoSelecionado != null) {
            // Se estamos alterando um agendamento, mantemos o horário original de início
            horarioOriginal = agendamentoSelecionado.getHoraInicio();
        }

        // Verifica se a data é a data atual
        LocalTime agora = LocalTime.now();
        if (data.equals(LocalDate.now())) {
            // Se for a data atual, ajusta o início para o horário atual, mas não antes das 8h
            inicio = agora.isBefore(LocalTime.of(8, 0)) ? LocalTime.of(8, 0) : agora;
        }

        // Se estamos alterando um agendamento existente, começamos do horário original
        if (horarioOriginal != null) {
            inicio = horarioOriginal;
        }

        // Calcula os horários disponíveis com base na duração do serviço
        while (inicio.isBefore(fim)) {
            LocalTime horarioFimServico = inicio.plusMinutes(duracaoServico);

            // Verifica se o horário de término do serviço não ultrapassa o fim do expediente
            if (horarioFimServico.isAfter(fim)) {
                break;
            }

            // Verifica a disponibilidade do funcionário no horário e data especificados
            if (verificarDisponibilidade(funcionario, data, inicio, duracaoServico, agendamentoSelecionado, true)) {
                campoHorario.addItem(inicio); // Adiciona o horário como LocalTime
            } else if (horarioOriginal != null && inicio.equals(horarioOriginal)) {
                // Se o horário original não estiver disponível, mas for o horário do agendamento selecionado, adiciona de qualquer forma
                campoHorario.addItem(inicio);
            }

            // Incrementa o horário em intervalos de 30 minutos
            inicio = inicio.plusMinutes(30);
        }

        // Caso não haja horários disponíveis
        if (campoHorario.getItemCount() == 0) {
            campoHorario.addItem(null); // Adiciona 'null' para representar que não há horários disponíveis
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
        configurarBotoes(true, true, false, true, false, true);
        limparCampos();
        campoCliente.requestFocus();
    }

    // Método para configurar a interface para o modo de alteração
    private void configurarModoAlteracao() {
        botao = "alterar";
        configurarCampos(true, true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, true, false, true, false, true);
        campoCliente.requestFocus();
    }

    // Método para configurar a interface para o modo de pesquisa
    private void configurarModoPesquisa() {

    }

    // Método para configurar a interface para o modo de salvar
    private void configurarModoSalvar() {
        carregarTabelaAgendamentos();
        configurarCampos(false, false, false, false, false);
        configurarBotoes(true, true, false, true, false, false);
        limparCampos();
        campoCliente.requestFocus();
    }

    // Método para configurar a interface para o modo de excluir
    private void configurarModoExcluir() {
        carregarTabelaAgendamentos();
        configurarCampos(false, false, false, false, false);
        configurarBotoes(true, true, false, true, false, false);
        limparCampos();
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
        campoFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    // Obtém o funcionário selecionado
                    Funcionario funcionarioSelecionado = (Funcionario) campoFuncionario.getSelectedItem();
                    //System.out.println("Funcionário selecionado: " + funcionarioSelecionado);

                    // Obtém a data selecionada do JSpinner (que é um tipo Date)
                    Date dataSelecionada = (Date) campoData.getValue();
                    //System.out.println("Data selecionada: " + dataSelecionada);
                    LocalDate localDate = null;
                    if (dataSelecionada != null) {
                        localDate = dataSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        //System.out.println("Data convertida para LocalDate: " + localDate);
                    }

                    // Verifica o serviço selecionado
                    Servico servicoSelecionado = (Servico) campoServico.getSelectedItem();
                    //System.out.println("Serviço selecionado: " + servicoSelecionado);

                    // Verifica se todos os campos estão selecionados
                    if (funcionarioSelecionado != null && localDate != null && servicoSelecionado != null) {
                        //System.out.println("Todos os campos selecionados, habilitando horário...");

                        // Habilita a ComboBox de horários
                        campoHorario.setEnabled(true);

                        // Chama o método para carregar os horários, passando o funcionário e a data
                        carregarHorariosDisponiveis(funcionarioSelecionado, localDate);
                    } else {
                        //System.out.println("Algum campo não foi selecionado, desabilitando horário...");
                        campoHorario.setEnabled(false); // Desabilita a ComboBox de horários se algum item não for selecionado
                        campoHorario.removeAllItems(); // Limpa os itens da ComboBox de horários
                    }
                } catch (Exception e) {
                    //System.err.println("Erro ao processar a seleção do funcionário: " + e.getMessage());
                    e.printStackTrace();
                    campoHorario.setEnabled(false); // Desabilita a ComboBox de horários em caso de erro
                    campoHorario.removeAllItems(); // Limpa os itens da ComboBox de horários
                }
            }
        });
        getContentPane().add(campoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 290, 40));

        campoServico.setBackground(new java.awt.Color(238, 238, 238));
        campoServico.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        campoServico.setModel(new javax.swing.DefaultComboBoxModel<>());
        campoServico.setBorder(null);
        campoServico.addActionListener(new java.awt.event.ActionListener() {
            Agendamento agendamentoSelecionado = obterAgendamentoSelecionado();
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Obtém o funcionário selecionado
                Funcionario funcionarioSelecionado = (Funcionario) campoFuncionario.getSelectedItem();
                //System.out.println("Funcionário selecionado: " + funcionarioSelecionado);

                // Obtém a data selecionada do JSpinner
                Date dataSelecionada = (Date) campoData.getValue();
                //System.out.println("Data selecionada: " + dataSelecionada);
                LocalDate localDate = dataSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //System.out.println("Data convertida para LocalDate: " + localDate);

                // Verifica o serviço selecionado
                Servico servicoSelecionado = (Servico) campoServico.getSelectedItem();
                //System.out.println("Serviço selecionado: " + servicoSelecionado);

                // Verifica se todos os campos estão selecionados
                if (funcionarioSelecionado != null && localDate != null && servicoSelecionado != null) {
                    //System.out.println("Todos os campos selecionados, habilitando horário...");

                    // Habilita a ComboBox de horários
                    campoHorario.setEnabled(true);

                    // Chama o método para carregar os horários, passando o funcionário e a data
                    carregarHorariosDisponiveis(funcionarioSelecionado, localDate);
                } else {
                    //System.out.println("Algum campo não foi selecionado, desabilitando horário...");
                    campoHorario.setEnabled(false); // Desabilita a ComboBox de horários
                }
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
                // Obtém a data selecionada do JSpinner
                Date dataSelecionada = (Date) campoData.getValue();
                //System.out.println("Data selecionada: " + dataSelecionada);
                LocalDate localDate = dataSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //System.out.println("Data convertida para LocalDate: " + localDate);

                // Obtém o funcionário selecionado
                Funcionario funcionarioSelecionado = (Funcionario) campoFuncionario.getSelectedItem();
                //System.out.println("Funcionário selecionado: " + funcionarioSelecionado);

                // Verifica o serviço selecionado
                Servico servicoSelecionado = (Servico) campoServico.getSelectedItem();
                //System.out.println("Serviço selecionado: " + servicoSelecionado);

                // Verifica se todos os campos estão selecionados
                if (funcionarioSelecionado != null && localDate != null && servicoSelecionado != null) {
                    //System.out.println("Todos os campos selecionados, habilitando horário...");

                    // Habilita a ComboBox de horários
                    campoHorario.setEnabled(true);

                    // Chama o método para carregar os horários, passando o funcionário e a data
                    carregarHorariosDisponiveis(funcionarioSelecionado, localDate);
                } else {
                    //System.out.println("Algum campo não foi selecionado, desabilitando horário...");
                    campoHorario.setEnabled(false); // Desabilita a ComboBox de horários
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
        configurarModoNovo();
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // Verifica se os campos necessários estão preenchidos
        if (campoCliente.getSelectedItem() == null || campoFuncionario.getSelectedItem() == null
                || campoServico.getSelectedItem() == null || campoData.getValue() == null || campoHorario.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            campoCliente.requestFocus();
        } else {
            Cliente cliente = (Cliente) campoCliente.getSelectedItem();
            Funcionario funcionario = (Funcionario) campoFuncionario.getSelectedItem();
            Servico servico = (Servico) campoServico.getSelectedItem();

            // Converte o valor de campoData (java.util.Date) para LocalDate
            java.util.Date dataUtil = (java.util.Date) campoData.getValue(); // campoData pode retornar um java.util.Date
            LocalDate data = dataUtil.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate(); // Converte para LocalDate

            LocalTime horaInicio = (LocalTime) campoHorario.getSelectedItem();

            // Valida a data (não pode ser anterior à data atual)
            try {
                if (data.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("A data do agendamento não pode ser anterior à data atual!");
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                campoData.requestFocus();
                return;
            }

            int confirmacao;
            if (botao.equals("novo")) {
                confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja cadastrar este agendamento?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Criação do novo agendamento
                    Agendamento agendamento = new Agendamento(cliente, funcionario, servico, data, horaInicio);
                    Agendamentos.add(agendamento);
                    JOptionPane.showMessageDialog(null, "Agendamento cadastrado com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            } else if (botao.equals("alterar")) {
                if (linhaSelecionada >= 0) {
                    // Confirmação de alteração
                    confirmacao = JOptionPane.showConfirmDialog(
                            null, "Tem certeza de que deseja alterar este agendamento?",
                            "Confirmação", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        // Acessa o agendamento selecionado na lista
                        Agendamento agendamentoSelecionado = Agendamentos.get(linhaSelecionada);

                        // Atualiza os dados do agendamento com os valores dos campos
                        agendamentoSelecionado.setCliente(cliente);
                        agendamentoSelecionado.setFuncionario(funcionario);
                        agendamentoSelecionado.setServico(servico);
                        agendamentoSelecionado.setData(data);
                        agendamentoSelecionado.setHoraInicio(horaInicio);

                        // Mostra mensagem de sucesso
                        JOptionPane.showMessageDialog(null, "Agendamento alterado com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);

                        // Atualiza a tabela com os novos dados
                        carregarTabelaAgendamentos();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum agendamento selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

        }
        configurarModoSalvar();

    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void tabelaAgendamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAgendamentoMouseClicked
        linhaSelecionada = tabelaAgendamento.getSelectedRow();

        if (linhaSelecionada >= 0) {
            Agendamento agendamentoSelecionado = Agendamentos.get(linhaSelecionada);

            campoCliente.setSelectedItem(agendamentoSelecionado.getCliente());
            campoFuncionario.setSelectedItem(agendamentoSelecionado.getFuncionario());
            campoServico.setSelectedItem(agendamentoSelecionado.getServico());
            campoData.setValue(Date.from(agendamentoSelecionado.getData()
                    .atStartOfDay(ZoneId.systemDefault()).toInstant()));

            LocalTime horaSelecionada = agendamentoSelecionado.getHoraInicio();

            // Verifica se o horário já está no ComboBox e seleciona
            boolean horarioEncontrado = false;
            for (int i = 0; i < campoHorario.getItemCount(); i++) {
                if (campoHorario.getItemAt(i).equals(horaSelecionada)) {
                    campoHorario.setSelectedIndex(i);
                    horarioEncontrado = true;
                    break;
                }
            }

            // Adiciona dinamicamente caso não esteja presente
            if (!horarioEncontrado) {
                campoHorario.addItem(horaSelecionada);
                campoHorario.setSelectedItem(horaSelecionada);
            }
        }

        configurarBotoes(true, true, true, true, true, false);
        configurarCampos(false, false, false, false, false);
    }//GEN-LAST:event_tabelaAgendamentoMouseClicked

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        configurarModoAlteracao();
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int index = tabelaAgendamento.getSelectedRow();  // Obtém a linha selecionada na tabela de agendamentos

        if (index >= 0 && index < tabelaAgendamento.getRowCount()) {  // Verifica se o índice é válido
            Agendamento agendamentoSelecionado = Agendamentos.get(index);

            Cliente cliente = agendamentoSelecionado.getCliente();
            Funcionario funcionario = agendamentoSelecionado.getFuncionario();
            Servico servico = agendamentoSelecionado.getServico();
            LocalDate data = agendamentoSelecionado.getData();
            LocalTime horario = agendamentoSelecionado.getHoraFim();

            // Encontre o agendamento correspondente na lista 'Agendamentos' com base nos dados obtidos
            Agendamento agendamentoParaExcluir = null;
            for (Agendamento agendamento : Agendamentos) {
                if (agendamento.getCliente().getNome().equals(cliente)
                        && agendamento.getFuncionario().getNome().equals(funcionario)
                        && agendamento.getServico().getNome().equals(servico)
                        && agendamento.getData().equals(data)
                        && agendamento.getHoraInicio().equals(horario)) {

                    agendamentoParaExcluir = agendamento;
                    break;
                }
            }

            if (agendamentoParaExcluir != null) {
                // Caixa de diálogo para confirmação de exclusão
                int confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja excluir este agendamento?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Remove o agendamento da lista
                    Agendamentos.remove(agendamentoParaExcluir);
                    configurarModoExcluir();       // Configura a interface para o modo de exclusão

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Agendamento excluído", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                // Caso não encontre o agendamento correspondente
                JOptionPane.showMessageDialog(null, "Agendamento não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            // Caso não haja nenhum agendamento selecionado
            JOptionPane.showMessageDialog(null, "Nenhum agendamento selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

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
