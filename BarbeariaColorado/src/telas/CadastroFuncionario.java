package telas;

import classes.Funcionario;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class CadastroFuncionario extends javax.swing.JFrame {
    static ArrayList<Funcionario> Funcionarios;
    String botao;
    private boolean buscaDinamicaAtiva = false;
    private int linhaSelecionada = -1;
    private final Font fontePadrao = new Font("JetBrains Mono", Font.PLAIN, 14); // variavel para setar fontes nos campos formatados
    private final Font fonteTabela = new Font("JetBrains Mono", Font.PLAIN, 12); // variavel para setar fontes nos campos formatados

    public CadastroFuncionario() {
        initComponents();
        Funcionarios = new ArrayList<>();
        
        // Setando a fonte para os campos e para a tabela
        campoNome.setFont(fontePadrao);
        campoCpf.setFont(fontePadrao);
        campoEmail.setFont(fontePadrao);
        campoEndereco.setFont(fontePadrao);
        campoCelular.setFont(fontePadrao);
        campoCargo.setFont(fontePadrao);
        campoSenha.setFont(fontePadrao);
        tabelaFuncionarios.setFont(fonteTabela);
        
        // Habilitar os botões
        configurarBotoes(true, true, false, true, false, false);
        // Desabilitando os campos
        configurarCampos(false, false, false, false, false, false,false);
        
        campoCpf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarFuncionarioDinamico();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarFuncionarioDinamico();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarFuncionarioDinamico();
            }
    
        });
        
        campoNome.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarFuncionarioDinamico();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarFuncionarioDinamico();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarFuncionarioDinamico();
            }
    
        });
                        
    }
    
    public void carregarTabelaFuncionarios() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nome", "CPF", "Email", "Endereço", "Celular", "Cargo", "Senha"}, 0);
        
        for (Funcionario funcionario : Funcionarios) {
            Object linha[] = new Object[]{
                funcionario.getNome(),
                funcionario.getCPF(),
                funcionario.getEmail(),
                funcionario.getEndereco(),
                funcionario.getCelular(),
                funcionario.getCargo(),
                funcionario.getSenha()};
            modelo.addRow(linha);
        }
        
        tabelaFuncionarios.setModel(modelo);
    }
    
    private void buscarFuncionarioDinamico() {
        // Verifica se a busca dinâmica está ativa
        if (!buscaDinamicaAtiva) {
            return;            
        }

        // Obtém o valor do campoCpf (sem formatação)
        String pesquisaCpf = campoCpf.getText().trim(); // Remove caracteres não numéricos
        String pesquisaNome = campoNome.getText().trim();
        
        // Se ambos os campos estiverem vazios, recarrega todos os clientes
        if (pesquisaCpf.isEmpty() && pesquisaNome.isEmpty()) {
            carregarTabelaFuncionarios(); // Recarrega a tabela com todos os clientes
            return;
        }
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nome", "CPF", "Email", "Endereço", "Celular", "Cargo", "Senha"}, 0);
        
        for (Funcionario funcionario : Funcionarios) {
            // Remove a formatação do CPF do cliente para comparação
            String cpfFuncionario = funcionario.getCPF();
            
            boolean cpfCorresponde = pesquisaCpf.isEmpty() || cpfFuncionario.startsWith(pesquisaCpf);
            boolean nomeCorresponde = pesquisaNome.isEmpty() || funcionario.getNome().toLowerCase().contains(pesquisaNome.toLowerCase());
            
            // Verifica se o CPF ou o nome corresponde ao valor digitado
            if (cpfCorresponde && nomeCorresponde) {
                Object linha[] = new Object[]{
                    funcionario.getNome(),
                    funcionario.getCPF(),
                    funcionario.getEmail(),
                    funcionario.getEndereco(),
                    funcionario.getCelular(),
                    funcionario.getCargo(),
                    funcionario.getSenha()};
                modelo.addRow(linha);
            }
        }
        
        tabelaFuncionarios.setModel(modelo);
        // Tenta selecionar a primeira linha após a atualização
        if (modelo.getRowCount() > 0) {
            tabelaFuncionarios.setRowSelectionInterval(0, 0); // Seleciona a primeira linha
        }
        
    }
    
    // Método para limpar os campos
    private void limparCampos() {
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoEndereco.setText("");
        campoCelular.setText("");
        campoCargo.setText("");
        campoSenha.setText("");
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
    private void configurarCampos(boolean nome, boolean cpf, boolean email, boolean endereco, boolean celular, boolean cargo, boolean senha) {
        campoNome.setEnabled(nome);
        campoCpf.setEnabled(cpf);
        campoEmail.setEnabled(email);
        campoEndereco.setEnabled(endereco);
        campoCelular.setEnabled(celular);
        campoCargo.setEnabled(cargo);
        campoSenha.setEnabled(senha);
    }
    
    // Método para configurar a interface para o cadastra o cliente
    private void configurarModoNovo() {
        botao = "novo";
        limparCampos();
        configurarCampos(true, true, true, true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, true, false, true, false, true);
        buscaDinamicaAtiva = false; // Desativa a busca dinâmica
        campoNome.requestFocus();
    }
    
    // Método para configurar a interface para o modo de alteração
    private void configurarModoAlteracao() {
        botao = "alterar";
        configurarCampos(true, true, true, true, true, true,true); // Todos os campos habilitados
        configurarBotoes(true, false, false, false, false, true); // Apenas Salvar habilitado
        buscaDinamicaAtiva = false; // Desativa a busca dinâmica
        campoNome.requestFocus();
    }
    
    // Método para configurar a interface para o modo de pesquisa
    private void configurarModoPesquisa() {
        limparCampos();
        configurarCampos(true, true, false, false, false, false,false); // Nome e CPF habilitado
        configurarBotoes(true, true, false, true, false, false); // Apenas Pesquisar habilitado
        buscaDinamicaAtiva = true; // Ativa a busca dinâmica
        campoNome.requestFocus();
    }
    
    // Método para configurar a interface para o modo de salvar
    private void configurarModoSalvar() {
        carregarTabelaFuncionarios();
        limparCampos();
        configurarCampos(false, false, false, false, false, false,false); // Apenas CPF habilitado
        configurarBotoes(true, true, false, true, false, false);
        buscaDinamicaAtiva = false;
        campoCpf.requestFocus();
    }
    
    // Método para configurar a interface para o modo de excluir
    private void configurarModoExcluir() {
        carregarTabelaFuncionarios();
        limparCampos();
        configurarCampos(false, false, false, false, false, false,false);
        configurarBotoes(true, true, false, false, false, true);
        buscaDinamicaAtiva = false;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        campoCpf = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        campoEmail = new javax.swing.JTextField();
        campoEndereco = new javax.swing.JTextField();
        campoCelular = new javax.swing.JTextField();
        campoCargo = new javax.swing.JTextField();
        campoSenha = new javax.swing.JTextField();
        botaoNovo = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        cadastroFuncionario = new javax.swing.JLabel();

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
        campoNome.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoNome.setForeground(new java.awt.Color(57, 62, 70));
        campoNome.setBorder(null);
        getContentPane().add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 290, 30));

        campoCpf.setBackground(new java.awt.Color(238, 238, 238));
        campoCpf.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoCpf.setForeground(new java.awt.Color(57, 62, 70));
        campoCpf.setBorder(null);
        campoCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCpfActionPerformed(evt);
            }
        });
        getContentPane().add(campoCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 290, 30));

        botaoBuscar.setBorder(null);
        botaoBuscar.setBorderPainted(false);
        botaoBuscar.setContentAreaFilled(false);
        botaoBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 70, 30));

        campoEmail.setBackground(new java.awt.Color(238, 238, 238));
        campoEmail.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoEmail.setForeground(new java.awt.Color(57, 62, 70));
        campoEmail.setBorder(null);
        getContentPane().add(campoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 290, 30));

        campoEndereco.setBackground(new java.awt.Color(238, 238, 238));
        campoEndereco.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoEndereco.setForeground(new java.awt.Color(57, 62, 70));
        campoEndereco.setBorder(null);
        getContentPane().add(campoEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 290, 30));

        campoCelular.setBackground(new java.awt.Color(238, 238, 238));
        campoCelular.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoCelular.setForeground(new java.awt.Color(57, 62, 70));
        campoCelular.setBorder(null);
        getContentPane().add(campoCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 290, 30));

        campoCargo.setBackground(new java.awt.Color(238, 238, 238));
        campoCargo.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoCargo.setForeground(new java.awt.Color(57, 62, 70));
        campoCargo.setBorder(null);
        campoCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCargoActionPerformed(evt);
            }
        });
        getContentPane().add(campoCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 290, 30));

        campoSenha.setBackground(new java.awt.Color(238, 238, 238));
        campoSenha.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoSenha.setForeground(new java.awt.Color(57, 62, 70));
        campoSenha.setBorder(null);
        getContentPane().add(campoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 290, 30));

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

        tabelaFuncionarios.setBackground(new java.awt.Color(238, 238, 238));
        tabelaFuncionarios.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Email", "Endereço", "Celular", "Cargo", "Senha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tabelaFuncionarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 750, 560));

        cadastroFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tela_cadastro_funcionarios.png"))); // NOI18N
        getContentPane().add(cadastroFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1220, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>                        
    
    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.setVisible(false);
    }                                           

    private void campoCpfActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void campoCargoActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {                                          
        configurarModoNovo();                       
    }                                         

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                            
         if (campoNome.getText().equals("") || campoCpf.getText().equals("") || campoEmail.getText().equals("")
                || campoEndereco.getText().equals("") || campoCelular.getText().equals("")
                || campoCargo.getText().equals("") || campoSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser inseridos!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            campoNome.requestFocus();
        } 

            int confirmacao;
            if (botao.equals("novo")) {
                confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja cadastrar este funcionário?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    Funcionario funcionario = new Funcionario(nome, cpf, email, endereco, celular, cargo, senha);
                    Funcionarios.add(funcionario);
                    JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            } else if (botao.equals("alterar")) {
                if (linhaSelecionada >= 0) {
                    confirmacao = JOptionPane.showConfirmDialog(
                            null, "Tem certeza de que deseja alterar os dados deste funcionário?",
                            "Confirmação", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionarios.getModel();
                        String cpfSelecionado = (String) modelo.getValueAt(linhaSelecionada, 1); // CPF na tabela

                        // Busca funcionario correto usando CPF
                        Funcionario funcionario = Funcionarios.stream()
                                .filter(c -> c.getCPF().equals(cpfSelecionado))
                                .findFirst()
                                .orElse(null);

                        if (funcionario != null) {
                            funcionario.setNome(nome);
                            funcionario.setCPF(cpf);
                            funcionario.setEmail(email);
                            funcionario.setEndereco(endereco);
                            funcionario.setCelular(celular);
                            funcionario.setCargo(cargo);
                            funcionario.setSenha(senha);

                            JOptionPane.showMessageDialog(null, "Dados do funcionário alterados com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao localizar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            configurarModoSalvar(); // chama o metodo de configuracao para salvar
        }
                                               
     

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                             
         int index = tabelaFuncionarios.getSelectedRow();

        if (index >= 0 && index < tabelaFuncionarios.getRowCount()) {  // Verifica se o índice é válido na tabela
            // Obtém o CPF do funcionário na linha selecionada
            String cpfSelecionado = (String) tabelaFuncionarios.getValueAt(index, 1);

            // Encontre o funcionario correspondente na lista 'Funcionários' pelo CPF
            Funcionario funcionarioParaExcluir = null;
            for (Funcionario funcionario : Funcionarios) {
                if (funcionario.getCPF().equals(cpfSelecionado)) {
                    funcionarioParaExcluir = funcionario;
                    break;
                }
            }
            
            if (funcionarioParaExcluir != null) {
                int confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja excluir este cliente?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Remove o funcionário da lista
                    Funcionarios.remove(funcionarioParaExcluir);
                    configurarModoExcluir(); // chama o metodo de configuraçao de excluir
                    JOptionPane.showMessageDialog(null, "Funcionário excluído", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }                                           

                                                
    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        configurarModoAlteracao();
    }                                            

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        String cpfBuscado = campoCpf.getText();
        if(cpfBuscado.isEmpty()) {
            System.out.println("Digite um CPF");
        }
        for (Funcionario funcionario : Funcionarios) {
            if(funcionario.getCPF().equals(cpfBuscado)){
                System.out.println(funcionario);
                limparCampos();
                break;
            } else {
                System.out.println("Não existe funcionário com o CPF: " + cpfBuscado);
                limparCampos();
            }           
        }                
    }                                           

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
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel cadastroFuncionario;
    private javax.swing.JTextField campoCargo;
    private javax.swing.JTextField campoCelular;
    private javax.swing.JTextField campoCpf;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoSenha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaFuncionarios;
    // End of variables declaration                   

    private void limparCampos() {
    campoNome.setText("");
    campoCpf.setText("");
    campoEmail.setText("");
    campoEndereco.setText("");
    campoCelular.setText("");
    campoCargo.setText("");
    campoSenha.setText("");
    }
}    
    

