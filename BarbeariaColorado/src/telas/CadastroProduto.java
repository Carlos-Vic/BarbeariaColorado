/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import classes.Produto;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class CadastroProduto extends javax.swing.JFrame {

    static ArrayList<Produto> Produtos;

    String botao;
    private int linhaSelecionada = -1; // Para armazenar a linha selecionada
    private boolean buscaDinamicaAtiva = false; // Controla se a busca dinâmica está ativa
    private final Font fontePadrao = new Font("JetBrains Mono", Font.PLAIN, 14); // variavel para setar fontes nos campos formatados
    private final Font fonteTabela = new Font("JetBrains Mono", Font.PLAIN, 12); // variavel para setar fontes nos campos formatados

    /**
     * Creates new form CadastroProduto
     */
    public CadastroProduto() {
        initComponents();

        Produtos = new ArrayList<>();

        // Setando a fonte para os campos e para a tabela
        campoCodigo.setFont(fontePadrao);
        campoNome.setFont(fontePadrao);
        campoPreco.setFont(fontePadrao);
        campoQuantidade.setFont(fontePadrao);
        tabelaProduto.setFont(fonteTabela);

        // Habilitar os botões
        configurarBotoes(true, true, false, true, false, false);

        // Desabilitando os campos
        configurarCampos(false, false, false, false);

        // Adicionar o DocumentListener ao campoCodigo para a pesquisa dinâmica
        campoCodigo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarProdutoDinamico();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarProdutoDinamico();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarProdutoDinamico();
            }
        });

        // Adicionar o DocumentListener ao campoNome para a pesquisa dinâmica
        campoNome.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarProdutoDinamico();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarProdutoDinamico();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarProdutoDinamico();
            }
        });

    }

    public void carregarTabelaProdutos() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Nome", "Preço", "Quantidade"}, 0);

        for (Produto produto : Produtos) {
            Object linha[] = new Object[]{
                produto.getCodigo(),
                produto.getNome(),
                produto.getPreco(), // Mantém o preço como Double
                produto.getQuantidade()
            };
            modelo.addRow(linha);
        }

        tabelaProduto.setModel(modelo);
    }

    private void buscarProdutoDinamico() {
        // Verifica se a busca dinâmica está ativa
        if (!buscaDinamicaAtiva) {
            return; // Sai do método se a busca dinâmica não estiver ativa
        }

        // Obtém os valores dos campos de pesquisa
        String pesquisaCodigo = campoCodigo.getText().trim(); // Código (int)
        String pesquisaNome = campoNome.getText().trim(); // Nome (String)

        // Se ambos os campos estiverem vazios, recarrega todos os produtos
        if (pesquisaCodigo.isEmpty() && pesquisaNome.isEmpty()) {
            carregarTabelaProdutos(); // Recarrega a tabela com todos os produtos
            return;
        }

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Nome", "Preço", "Quantidade"}, 0);

        for (Produto produto : Produtos) {
            // Converte o código do produto para String para facilitar a comparação
            String codigoProduto = String.valueOf(produto.getCodigo());

            boolean codigoCorresponde = pesquisaCodigo.isEmpty() || codigoProduto.startsWith(pesquisaCodigo);
            boolean nomeCorresponde = pesquisaNome.isEmpty() || produto.getNome().toLowerCase().contains(pesquisaNome.toLowerCase());

            // Verifica se o código ou o nome corresponde ao valor digitado
            if (codigoCorresponde && nomeCorresponde) {
                Object linha[] = new Object[]{
                    produto.getCodigo(),
                    produto.getNome(),
                    produto.getPreco(), // Mantém o preço como Double
                    produto.getQuantidade()
                };
                modelo.addRow(linha);
            }
        }

        tabelaProduto.setModel(modelo);

        // Tenta selecionar a primeira linha após a atualização
        if (modelo.getRowCount() > 0) {
            tabelaProduto.setRowSelectionInterval(0, 0); // Seleciona a primeira linha
        }
    }

    // Método para limpar os campos
    private void limparCampos() {
        campoCodigo.setText("");
        campoNome.setText("");
        campoPreco.setValue(null); // Limpa o campo de preço
        campoQuantidade.setText("");
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
    private void configurarCampos(boolean codigo, boolean nome, boolean preco, boolean quantidade) {
        campoCodigo.setEnabled(codigo);
        campoNome.setEnabled(nome);
        campoPreco.setEnabled(preco);
        campoQuantidade.setEnabled(quantidade);
    }

    // Método para configurar a interface para cadastrar o produto
    private void configurarModoNovo() {
        botao = "novo";
        limparCampos();
        configurarCampos(true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, true, false, true, false, true);
        buscaDinamicaAtiva = false; // Desativa a busca dinâmica
        campoCodigo.requestFocus();
    }

    // Método para configurar a interface para o modo de alteração
    private void configurarModoAlteracao() {
        botao = "alterar";
        configurarCampos(true, true, true, true); // Todos os campos habilitados
        configurarBotoes(true, false, false, false, false, true); // Apenas Salvar habilitado
        buscaDinamicaAtiva = false; // Desativa a busca dinâmica
        campoNome.requestFocus();
    }

    // Método para configurar a interface para o modo de pesquisa
    private void configurarModoPesquisa() {
        limparCampos();
        configurarCampos(true, true, false, false); // Nome e Codigo habilitado
        configurarBotoes(true, true, false, true, false, false); // Apenas Pesquisar habilitado
        buscaDinamicaAtiva = true; // Ativa a busca dinâmica
        campoCodigo.requestFocus();
    }

    // Método para configurar a interface para o modo de salvar
    private void configurarModoSalvar() {
        carregarTabelaProdutos();
        limparCampos();
        configurarCampos(false, false, false, false);
        configurarBotoes(true, true, false, true, false, false);
        buscaDinamicaAtiva = false;
        campoCodigo.requestFocus();
    }

    // Método para configurar a interface para o modo de excluir
    private void configurarModoExcluir() {
        carregarTabelaProdutos();
        limparCampos();
        configurarCampos(false, false, false, false);
        configurarBotoes(true, true, false, true, false, true);
        buscaDinamicaAtiva = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        campoCodigo = new javax.swing.JTextField();
        campoNome = new javax.swing.JTextField();
        campoPreco = new javax.swing.JFormattedTextField(NumberFormat.getNumberInstance());
        campoQuantidade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProduto = new javax.swing.JTable();
        botaoNovo = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        cadastroProdutos = new javax.swing.JLabel();

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

        campoCodigo.setBackground(new java.awt.Color(238, 238, 238));
        campoCodigo.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        campoCodigo.setForeground(new java.awt.Color(57, 62, 70));
        campoCodigo.setBorder(null);
        getContentPane().add(campoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 290, 30));

        campoNome.setBackground(new java.awt.Color(238, 238, 238));
        campoNome.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        campoNome.setForeground(new java.awt.Color(57, 62, 70));
        campoNome.setBorder(null);
        getContentPane().add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 290, 30));

        campoPreco.setBackground(new java.awt.Color(238, 238, 238));
        campoPreco.setBorder(null);
        campoPreco.setForeground(new java.awt.Color(57, 62, 70));
        campoPreco.setText("");
        campoPreco.setValue(0.0);
        getContentPane().add(campoPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 290, 30));

        campoQuantidade.setBackground(new java.awt.Color(238, 238, 238));
        campoQuantidade.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        campoQuantidade.setForeground(new java.awt.Color(57, 62, 70));
        campoQuantidade.setBorder(null);
        getContentPane().add(campoQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 290, 30));

        tabelaProduto.setBackground(new java.awt.Color(238, 238, 238));
        tabelaProduto.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tabelaProduto.setForeground(new java.awt.Color(57, 62, 70));
        tabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Preço", "Quantidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelaProduto.setSelectionForeground(new java.awt.Color(34, 40, 49));
        tabelaProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProduto);
        // Configura o renderer para a coluna de preço
        DefaultTableCellRenderer rendererMoeda = new DefaultTableCellRenderer() {
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(); // Formato de moeda com "R$"

            @Override
            public void setValue(Object value) {
                if (value instanceof Double) {
                    setText(formatoMoeda.format(value)); // Formata o valor como moeda
                } else {
                    setText(value == null ? "" : value.toString());
                }
            }
        };

        // Aplica o renderer à coluna de preço (índice 2)
        tabelaProduto.getColumnModel().getColumn(2).setCellRenderer(rendererMoeda);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 820, 540));

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

        cadastroProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tela_cadastro_produtos.png"))); // NOI18N
        getContentPane().add(cadastroProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 700));

        setSize(new java.awt.Dimension(1215, 699));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botaoVoltarActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        configurarModoNovo(); // chama o metodo de configuracao para cadastrar o produto
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        configurarModoAlteracao(); // chama o metodo de configuracao para alterar
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        if (Produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto está cadastrado!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
        } else {
            configurarModoPesquisa(); // chama o metodo de configuracao de pesquisa
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int index = tabelaProduto.getSelectedRow(); // Obtém a linha selecionada na tabela

        if (index >= 0 && index < tabelaProduto.getRowCount()) {  // Verifica se o índice é válido na tabela
            // Obtém o código do produto na linha selecionada
            int codigoSelecionado = (int) tabelaProduto.getValueAt(index, 0); // Código está na primeira coluna

            // Encontre o produto correspondente na lista 'Produtos' pelo código
            Produto produtoParaExcluir = null;
            for (Produto produto : Produtos) {
                if (produto.getCodigo() == codigoSelecionado) {
                    produtoParaExcluir = produto;
                    break;
                }
            }

            if (produtoParaExcluir != null) {
                int confirmacao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja excluir este produto?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Remove o produto da lista
                    Produtos.remove(produtoParaExcluir);
                    configurarModoExcluir(); // Chama o método de configuração de exclusão
                    JOptionPane.showMessageDialog(null, "Produto excluído", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum produto selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // Validação de campos vazios
        if (campoCodigo.getText().trim().isEmpty() || campoNome.getText().trim().isEmpty()
                || campoPreco.getText().trim().isEmpty() || campoQuantidade.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser inseridos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            campoCodigo.requestFocus();
            return;
        }

        // Capturar dados dos campos
        int codigo;
        double preco;
        int quantidade;
        String nome = campoNome.getText().trim();

        // Conversão de campo de código para inteiro
        try {
            codigo = Integer.parseInt(campoCodigo.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código inválido! Insira um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            campoCodigo.requestFocus();
            return;
        }

        try {
            preco = ((Number) campoPreco.getValue()).doubleValue();
            if (preco <= 0) {
                JOptionPane.showMessageDialog(null, "O preço deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                campoPreco.requestFocus();
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preço inválido! Insira um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            campoPreco.requestFocus();
            return;
        }

        // Conversão de campo de quantidade para inteiro
        try {
            quantidade = Integer.parseInt(campoQuantidade.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida! Insira um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            campoQuantidade.requestFocus();
            return;
        }

        int confirmacao;
        if (botao.equals("novo")) {
            confirmacao = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza de que deseja cadastrar este produto?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacao == JOptionPane.YES_OPTION) {
                Produto produto = new Produto(codigo, nome, preco, quantidade);
                Produtos.add(produto);
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            }

        } else if (botao.equals("alterar")) {
            if (linhaSelecionada >= 0) {
                confirmacao = JOptionPane.showConfirmDialog(
                        null, "Tem certeza de que deseja alterar os dados deste produto?",
                        "Confirmação", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    DefaultTableModel modelo = (DefaultTableModel) tabelaProduto.getModel();
                    int codigoSelecionado = (int) modelo.getValueAt(linhaSelecionada, 0); // Código na tabela

                    // Busca produto correto usando código
                    Produto produto = Produtos.stream()
                            .filter(p -> p.getCodigo() == codigoSelecionado)
                            .findFirst()
                            .orElse(null);

                    if (produto != null) {
                        produto.setNome(nome);
                        produto.setCodigo(codigo);
                        produto.setPreco(preco);
                        produto.setQuantidade(quantidade);

                        JOptionPane.showMessageDialog(null, "Dados do produto alterados com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao localizar o produto.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        configurarModoSalvar(); // Atualiza estado dos campos e botões após salvar
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void tabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoMouseClicked
        linhaSelecionada = tabelaProduto.getSelectedRow(); // Salva a linha selecionada

        if (linhaSelecionada >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) tabelaProduto.getModel();

            // Obtém os valores da linha selecionada na tabela (código, nome, preço, quantidade)
            int codigo = (int) modelo.getValueAt(linhaSelecionada, 0);
            String nome = (String) modelo.getValueAt(linhaSelecionada, 1);
            double preco = (double) modelo.getValueAt(linhaSelecionada, 2); // Preço como Double
            int quantidade = (int) modelo.getValueAt(linhaSelecionada, 3);

            // Preenche os campos com os dados da linha clicada
            campoCodigo.setText(String.valueOf(codigo));
            campoNome.setText(nome);
            campoPreco.setValue(preco); // Define o valor formatado para o campo de preço
            campoQuantidade.setText(String.valueOf(quantidade));
        }

        // Habilita/desabilita botões e campos conforme necessário
        configurarBotoes(true, true, true, true, true, false);
        configurarCampos(false, false, false, false);
    }//GEN-LAST:event_tabelaProdutoMouseClicked

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
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroProduto().setVisible(true);
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
    private javax.swing.JLabel cadastroProdutos;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoNome;
    private javax.swing.JFormattedTextField campoPreco;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProduto;
    // End of variables declaration//GEN-END:variables
}
