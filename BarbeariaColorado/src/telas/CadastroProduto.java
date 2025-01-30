package telas;

import classes.Funcionario;
import classes.Produto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CadastroProduto extends javax.swing.JFrame {

    ArrayList<Produto> Produtos = new ArrayList<>();
    private javax.swing.table.DefaultTableModel modeloTabela;

    public CadastroProduto() {
        initComponents();
        modeloTabela = new DefaultTableModel(
                new Object[]{"Código", "Nome", "Preço", "Quantidade"},
                0 // Indica que a tabela começa com 0 linhas
        );
        tabelaProduto.setModel(modeloTabela);
        tabelaProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        campoCodigo = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        campoPreco = new javax.swing.JTextField();
        campoQuantidade = new javax.swing.JTextField();
        botaoNovo = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProduto = new javax.swing.JTable();
        cadastroProduto = new javax.swing.JLabel();

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
        campoCodigo.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoCodigo.setForeground(new java.awt.Color(57, 62, 70));
        campoCodigo.setBorder(null);
        getContentPane().add(campoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 290, 30));

        botaoBuscar.setBorder(null);
        botaoBuscar.setBorderPainted(false);
        botaoBuscar.setContentAreaFilled(false);
        botaoBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 70, 30));

        campoNome.setBackground(new java.awt.Color(238, 238, 238));
        campoNome.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoNome.setForeground(new java.awt.Color(57, 62, 70));
        campoNome.setBorder(null);
        getContentPane().add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 280, 30));

        campoPreco.setBackground(new java.awt.Color(238, 238, 238));
        campoPreco.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoPreco.setForeground(new java.awt.Color(57, 62, 70));
        campoPreco.setBorder(null);
        getContentPane().add(campoPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 290, 20));

        campoQuantidade.setBackground(new java.awt.Color(238, 238, 238));
        campoQuantidade.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoQuantidade.setForeground(new java.awt.Color(57, 62, 70));
        campoQuantidade.setBorder(null);
        getContentPane().add(campoQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 290, 30));

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
        getContentPane().add(botaoSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 650, 140, 30));

        tabelaProduto.setBackground(new java.awt.Color(238, 238, 238));
        tabelaProduto.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Código", "Nome", "Preço", "Quantidade"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaProduto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 750, 560));

        cadastroProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tela_cadastro_produtos.png"))); // NOI18N
        getContentPane().add(cadastroProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1220, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        MenuGerente menuGerente = new MenuGerente();
        menuGerente.setVisible(true);
        this.dispose();
    }

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int codigo = Integer.parseInt(campoCodigo.getText());
        int quantidade = Integer.parseInt(campoQuantidade.getText());
        String nome = campoNome.getText();
        double preco = Double.parseDouble(campoPreco.getText());
        Produto produto = new Produto(codigo, quantidade, nome, preco);
        System.out.println("cadastro de " + produto.getNome());
        modeloTabela.addRow(new Object[]{
            produto.getCodigo(),
            produto.getNome(),
            produto.getPreco(),
            produto.getQuantidade(),});
        Produtos.add(produto);
        limparCampos();

    }

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            int produtoBuscado = Integer.parseInt(campoCodigo.getText());
            boolean produtoEncontrado = false;

            for (Produto produto : Produtos) {
                if (produto.getCodigo() == produtoBuscado) {
                    System.out.println(produto);
                    limparCampos();
                    produtoEncontrado = true;
                    break;
                }
            }

            if (!produtoEncontrado) {
                System.out.println("Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Digite um código de produto válido.");
        }

    }

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int selectedRow = tabelaProduto.getSelectedRow();
        System.out.println("Linha selecionada: " + selectedRow);
        if (selectedRow >= 0 && selectedRow < Produtos.size()) {
            // Remover a linha da tabela primeiro
            modeloTabela.removeRow(selectedRow);
            Produtos.remove(selectedRow);
            System.out.println("Produto excluido com sucesso.");
        } else {
            System.out.println("Selecione um produto valido para excluir.");
        }
    }

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int selectedRow = tabelaProduto.getSelectedRow();

        if (selectedRow >= 0 && selectedRow < Produtos.size()) {
            Produto produto = Produtos.get(selectedRow);

            // Verifica o campo a ser alterado e muda
            if (!campoCodigo.getText().isEmpty() && !campoCodigo.getText().equals(produto.getCodigo())) {
                produto.setCodigo(Integer.parseInt(campoCodigo.getText()));
                modeloTabela.setValueAt(produto.getCodigo(), selectedRow, 0);  // Atualiza o código na tabela
                System.out.println("Código alterado com sucesso");
                limparCampos();
            }
            if (!campoNome.getText().isEmpty() && !campoNome.getText().equals(produto.getNome())) {
                produto.setNome(campoNome.getText());
                modeloTabela.setValueAt(produto.getNome(), selectedRow, 1);  // Atualiza o nome na tabela
                System.out.println("Nome alterado com sucesso");
                limparCampos();
            }
            if (!campoPreco.getText().isEmpty() && !campoPreco.getText().equals(produto.getPreco())) {
                produto.setPreco(Double.parseDouble(campoPreco.getText()));
                modeloTabela.setValueAt(produto.getPreco(), selectedRow, 2);  // Atualiza o preço na tabela
                System.out.println("Preço alterado com sucesso");
                limparCampos();
            }
            if (!campoQuantidade.getText().isEmpty() && !campoQuantidade.getText().equals(produto.getQuantidade())) {
                produto.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                modeloTabela.setValueAt(produto.getQuantidade(), selectedRow, 3);  // Atualiza a quantidade na tabela
                System.out.println("Quantidade alterada com sucesso");
                limparCampos();
            }

            // Atualiza o objeto na lista
            Produtos.set(selectedRow, produto);

            // Atualiza a tabela
            modeloTabela.fireTableDataChanged();

            System.out.println("Todos os dados do produto foram atualizados com sucesso!");

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

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel cadastroProduto;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPreco;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProduto;

    // End of variables declaration                   
    private void limparCampos() {
        campoCodigo.setText("");
        campoQuantidade.setText("");
        campoNome.setText("");
        campoPreco.setText("");
    }
}
