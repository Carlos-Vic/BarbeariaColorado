package telas;

import classes.Cliente;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class CadastroCliente extends javax.swing.JFrame {

    static ArrayList<Cliente> Clientes;

    String botao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private boolean buscaDinamicaAtiva = false; // Controla se a busca dinâmica está ativa
    private int linhaSelecionada = -1; // Para armazenar a linha selecionada
    private final Font fontePadrao = new Font("JetBrains Mono", Font.PLAIN, 14); // variavel para setar fontes nos campos formatados
    private final Font fonteTabela = new Font("JetBrains Mono", Font.PLAIN, 12); // variavel para setar fontes nos campos formatados

    /**
     * Creates new form CadastroCliente
     */
    public CadastroCliente() {
        initComponents();

        Clientes = new ArrayList<>();

        // Setando a fonte para os campos e para a tabela
        campoNome.setFont(fontePadrao);
        campoCpf.setFont(fontePadrao);
        campoEmail.setFont(fontePadrao);
        campoEndereco.setFont(fontePadrao);
        campoCelular.setFont(fontePadrao);
        campoDataNascimento.setFont(fontePadrao);
        tabelaClientes.setFont(fonteTabela);

        // Habilitar os botões
        configurarBotoes(true, true, false, true, false, false);

        // Desabilitando os campos
        configurarCampos(false, false, false, false, false, false);

        // Adicionar o DocumentListener ao campoCpf para a pesquisa dinâmica
        campoCpf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarClienteDinamico();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarClienteDinamico();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarClienteDinamico();
            }
        });

        // Adicionar o DocumentListener ao campoNome para a pesquisa dinâmica
        campoNome.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarClienteDinamico();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarClienteDinamico();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarClienteDinamico();
            }
        });

    }

    public void carregarTabelaClientes() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nome", "CPF", "Email", "Endereço", "Celular", "Data de Nascimento"}, 0);

        for (Cliente cliente : Clientes) {
            Object linha[] = new Object[]{
                cliente.getNome(),
                cliente.getCPF(),
                cliente.getEmail(),
                cliente.getEndereco(),
                cliente.getCelular(),
                cliente.getDataAniversario().format(formatter)};
            modelo.addRow(linha);
        }

        tabelaClientes.setModel(modelo);
    }

    private void buscarClienteDinamico() {
        // Verifica se a busca dinâmica está ativa
        if (!buscaDinamicaAtiva) {
            return; // Sai do método se a busca dinâmica não estiver ativa
        }

        // Obtém o valor do campoCpf (sem formatação)
        String pesquisaCpf = campoCpf.getText().trim(); // Remove caracteres não numéricos
        String pesquisaNome = campoNome.getText().trim();

        // Se ambos os campos estiverem vazios, recarrega todos os clientes
        if (pesquisaCpf.isEmpty() && pesquisaNome.isEmpty()) {
            carregarTabelaClientes(); // Recarrega a tabela com todos os clientes
            return;
        }

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nome", "CPF", "Email", "Endereço", "Celular", "Data de Nascimento"}, 0);

        for (Cliente cliente : Clientes) {
            // Remove a formatação do CPF do cliente para comparação
            String cpfCliente = cliente.getCPF();

            boolean cpfCorresponde = pesquisaCpf.isEmpty() || cpfCliente.startsWith(pesquisaCpf);
            boolean nomeCorresponde = pesquisaNome.isEmpty() || cliente.getNome().toLowerCase().contains(pesquisaNome.toLowerCase());

            // Verifica se o CPF ou o nome corresponde ao valor digitado
            if (cpfCorresponde && nomeCorresponde) {
                Object linha[] = new Object[]{
                    cliente.getNome(),
                    cliente.getCPF(),
                    cliente.getEmail(),
                    cliente.getEndereco(),
                    cliente.getCelular(),
                    cliente.getDataAniversario().format(formatter)};
                modelo.addRow(linha);
            }
        }

        tabelaClientes.setModel(modelo);

        // Tenta selecionar a primeira linha após a atualização
        if (modelo.getRowCount() > 0) {
            tabelaClientes.setRowSelectionInterval(0, 0); // Seleciona a primeira linha
        }

    }

    // Método para limpar os campos
    private void limparCampos() {
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoEndereco.setText("");

        // Para limpar os JFortmatterTextField corretamente
        campoCelular.setValue(null);
        campoCelular.setText("");
        campoDataNascimento.setValue(null);
        campoDataNascimento.setText("");
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

    // Método para habilitar/desabilitar campos de texto
    private void configurarCampos(boolean nome, boolean cpf, boolean email, boolean endereco, boolean celular, boolean dataNascimento) {
        campoNome.setEnabled(nome);
        campoCpf.setEnabled(cpf);
        campoEmail.setEnabled(email);
        campoEndereco.setEnabled(endereco);
        campoCelular.setEnabled(celular);
        campoDataNascimento.setEnabled(dataNascimento);
    }

    // Método para configurar a interface para o modo de novo cliente
    private void configurarModoNovo() {
        botao = "novo";
        limparCampos();
        configurarCampos(true, true, true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, true, false, true, false, true);
        buscaDinamicaAtiva = false; // Desativa a busca dinâmica
        campoNome.requestFocus();
    }

    // Método para configurar a interface para o modo de alteração
    private void configurarModoAlteracao() {
        botao = "alterar";
        configurarCampos(true, true, true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, false, false, false, false, true); // Apenas Salvar habilitado
        buscaDinamicaAtiva = false; // Desativa a busca dinâmica
        campoNome.requestFocus();
    }

    // Método para configurar a interface para o modo de pesquisa
    private void configurarModoPesquisa() {
        limparCampos();
        configurarCampos(true, true, false, false, false, false); // Nome e CPF habilitado
        configurarBotoes(true, true, false, true, false, false); // Apenas Pesquisar habilitado
        buscaDinamicaAtiva = true; // Ativa a busca dinâmica
        campoNome.requestFocus();
    }

    // Método para configurar a interface para o modo de salvar
    private void configurarModoSalvar() {
        carregarTabelaClientes();
        limparCampos();
        configurarCampos(false, false, false, false, false, false); // Apenas CPF habilitado
        configurarBotoes(true, true, false, true, false, false);
        buscaDinamicaAtiva = false;
        campoCpf.requestFocus();
    }

    // Método para configurar a interface para o modo de excluir
    private void configurarModoExcluir() {
        carregarTabelaClientes();
        limparCampos();
        configurarCampos(false, false, false, false, false, false);
        configurarBotoes(true, true, false, false, false, true);
        buscaDinamicaAtiva = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        campoCpf = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        campoEndereco = new javax.swing.JTextField();
        campoCelular = new javax.swing.JFormattedTextField();
        campoDataNascimento = new javax.swing.JFormattedTextField();
        botaoNovo = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        cadatroClientes = new javax.swing.JLabel();

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

        campoNome.setBackground(new java.awt.Color(238, 238, 238));
        campoNome.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        campoNome.setForeground(new java.awt.Color(57, 62, 70));
        campoNome.setBorder(null);
        getContentPane().add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 290, 30));

        campoCpf.setBackground(new java.awt.Color(238, 238, 238));
        campoCpf.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        campoCpf.setForeground(new java.awt.Color(57, 62, 70));
        campoCpf.setBorder(null);
        getContentPane().add(campoCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 290, 30));

        campoEmail.setBackground(new java.awt.Color(238, 238, 238));
        campoEmail.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoEmail.setForeground(new java.awt.Color(57, 62, 70));
        campoEmail.setBorder(null);
        getContentPane().add(campoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 290, 30));

        campoEndereco.setBackground(new java.awt.Color(238, 238, 238));
        campoEndereco.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoEndereco.setForeground(new java.awt.Color(57, 62, 70));
        campoEndereco.setBorder(null);
        getContentPane().add(campoEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 290, 30));

        campoCelular.setBackground(new java.awt.Color(238, 238, 238));
        campoCelular.setBorder(null);
        campoCelular.setForeground(new java.awt.Color(57, 62, 70));
        try {
            campoCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(campoCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 290, 30));

        campoDataNascimento.setBackground(new java.awt.Color(238, 238, 238));
        campoDataNascimento.setBorder(null);
        campoDataNascimento.setForeground(new java.awt.Color(57, 62, 70));
        try {
            campoDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(campoDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 290, 30));

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

        tabelaClientes.setBackground(new java.awt.Color(238, 238, 238));
        tabelaClientes.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tabelaClientes.setForeground(new java.awt.Color(57, 62, 70));
        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Email", "Endereço", "Celular", "Data de Nascimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelaClientes.setSelectionForeground(new java.awt.Color(34, 40, 49));
        tabelaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 770, 540));

        cadatroClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tela_cadastro_clientes.png"))); // NOI18N
        getContentPane().add(cadatroClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1220, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botaoVoltarActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        configurarModoNovo();
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void tabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClientesMouseClicked
        linhaSelecionada = tabelaClientes.getSelectedRow(); // Salva a linha selecionada

        if (linhaSelecionada >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();

            // Obtém os valores da linha selecionada na tabela (nome, cpf, etc.)
            String nome = (String) modelo.getValueAt(linhaSelecionada, 0);
            String cpf = (String) modelo.getValueAt(linhaSelecionada, 1); // CPF sem formatação
            String email = (String) modelo.getValueAt(linhaSelecionada, 2);
            String endereco = (String) modelo.getValueAt(linhaSelecionada, 3);
            String celular = (String) modelo.getValueAt(linhaSelecionada, 4);
            String dataNascimento = (String) modelo.getValueAt(linhaSelecionada, 5);

            // Preenche os campos com os dados da linha clicada
            campoNome.setText(nome);
            campoCpf.setText(cpf);
            campoEmail.setText(email);
            campoEndereco.setText(endereco);
            campoCelular.setValue(celular); // Usa setValue() para definir o valor do celular
            campoDataNascimento.setValue(dataNascimento); // Usa setValue() para definir o valor da data
        }

        // Habilita/desabilita botões e campos conforme necessário
        configurarBotoes(true, true, true, true, true, false);
        configurarCampos(false, false, false, false, false, false);

    }//GEN-LAST:event_tabelaClientesMouseClicked

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        configurarModoAlteracao();
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int index = tabelaClientes.getSelectedRow();

        if (index >= 0 && index < tabelaClientes.getRowCount()) {  // Verifica se o índice é válido na tabela
            // Obtém o CPF do cliente na linha selecionada
            String cpfSelecionado = (String) tabelaClientes.getValueAt(index, 1); // Supondo que o CPF está na segunda coluna

            // Encontre o cliente correspondente na lista 'Clientes' pelo CPF
            Cliente clienteParaExcluir = null;
            for (Cliente cliente : Clientes) {
                if (cliente.getCPF().equals(cpfSelecionado)) {
                    clienteParaExcluir = cliente;
                    break;
                }
            }

            if (clienteParaExcluir != null) {
                int confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja excluir este cliente?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Remove o cliente da lista
                    Clientes.remove(clienteParaExcluir);
                    configurarModoExcluir();
                    JOptionPane.showMessageDialog(null, "Cliente excluído", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        if (campoNome.getText().equals("") || campoCpf.getText().equals("") || campoEmail.getText().equals("")
                || campoEndereco.getText().equals("") || campoCelular.getText().equals("")
                || campoDataNascimento.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser inseridos!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            campoNome.requestFocus();
        } else {
            String nome = campoNome.getText(), cpf = campoCpf.getText(), email = campoEmail.getText(),
                    endereco = campoEndereco.getText(), celular = campoCelular.getText();

            LocalDate dataNascimento;
            try {
                dataNascimento = LocalDate.parse(campoDataNascimento.getText(), formatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Data inválida! Insira no formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                campoDataNascimento.requestFocus();
                return;
            }

            int confirmacao;
            if (botao.equals("novo")) {
                confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja cadastrar este cliente?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    Cliente cliente = new Cliente(nome, cpf, email, endereco, celular, dataNascimento);
                    Clientes.add(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            } else if (botao.equals("alterar")) {
                if (linhaSelecionada >= 0) {
                    confirmacao = JOptionPane.showConfirmDialog(
                            null, "Tem certeza de que deseja alterar os dados deste cliente?",
                            "Confirmação", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
                        String cpfSelecionado = (String) modelo.getValueAt(linhaSelecionada, 1); // CPF na tabela

                        // Busca cliente correto usando CPF
                        Cliente cliente = Clientes.stream()
                                .filter(c -> c.getCPF().equals(cpfSelecionado))
                                .findFirst()
                                .orElse(null);

                        if (cliente != null) {
                            cliente.setNome(nome);
                            cliente.setCPF(cpf);
                            cliente.setEmail(email);
                            cliente.setEndereco(endereco);
                            cliente.setCelular(celular);
                            cliente.setDataAniversario(dataNascimento);

                            JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao localizar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            configurarModoSalvar();
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        if (Clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente está cadastrado!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
        } else {
            configurarModoPesquisa();
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCliente().setVisible(true);
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
    private javax.swing.JLabel cadatroClientes;
    private javax.swing.JFormattedTextField campoCelular;
    private javax.swing.JTextField campoCpf;
    private javax.swing.JFormattedTextField campoDataNascimento;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoNome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaClientes;
    // End of variables declaration//GEN-END:variables
}
