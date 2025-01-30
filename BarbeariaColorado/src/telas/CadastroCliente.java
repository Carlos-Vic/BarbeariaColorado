package telas;

import classes.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastroCliente extends javax.swing.JFrame {
    
    static ArrayList<Cliente> Clientes;
    
    String botao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Creates new form CadastroCliente
     */
    public CadastroCliente() {
        initComponents();
        
        Clientes = new ArrayList();

        //Habilitar os botões
        botaoVoltar.setEnabled(true);
        botaoBuscar.setEnabled(false);
        botaoNovo.setEnabled(true);
        botaoAlterar.setEnabled(false);
        botaoExcluir.setEnabled(false);
        botaoSalvar.setEnabled(false);

        //Desabilitando os campos
        campoNome.setEnabled(false);
        campoCpf.setEnabled(false);
        campoEmail.setEnabled(false);
        campoEndereco.setEnabled(false);
        campoCelular.setEnabled(false);
        campoDataNascimento.setEnabled(false);
        
    }

    // Carregar a tabela com clientes da lista
    public void carregarTabelaClientes() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nome",
            "CPF", "Email", "Endereço", "Celular", "Data de Nascimento"}, 0);
        
        for (int i = 0; i < Clientes.size(); i++) {
            Object linha[] = new Object[]{
                Clientes.get(i).getNome(),
                Clientes.get(i).getCPF(),
                Clientes.get(i).getEmail(),
                Clientes.get(i).getEndereco(),
                Clientes.get(i).getCelular(),
                Clientes.get(i).getDataAniversario().format(formatter)};
            
            modelo.addRow(linha);
        }
        
        tabelaClientes.setModel(modelo);
        
        tabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabelaClientes.getColumnModel().getColumn(4).setPreferredWidth(20);
        tabelaClientes.getColumnModel().getColumn(5).setPreferredWidth(20);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        campoCpf = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        campoEmail = new javax.swing.JTextField();
        campoEndereco = new javax.swing.JTextField();
        campoCelular = new javax.swing.JTextField();
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
        campoNome.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoNome.setForeground(new java.awt.Color(57, 62, 70));
        campoNome.setBorder(null);
        getContentPane().add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 290, 30));

        campoCpf.setBackground(new java.awt.Color(238, 238, 238));
        campoCpf.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoCpf.setForeground(new java.awt.Color(57, 62, 70));
        campoCpf.setBorder(null);
        getContentPane().add(campoCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 290, 30));

        botaoBuscar.setBorder(null);
        botaoBuscar.setBorderPainted(false);
        botaoBuscar.setContentAreaFilled(false);
        botaoBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 80, 30));

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
        campoCelular.setFont(new java.awt.Font("JetBrains Mono", 0, 16)); // NOI18N
        campoCelular.setForeground(new java.awt.Color(57, 62, 70));
        campoCelular.setBorder(null);
        getContentPane().add(campoCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 290, 30));

        campoDataNascimento.setBackground(new java.awt.Color(238, 238, 238));
        campoDataNascimento.setBorder(null);
        campoDataNascimento.setForeground(new java.awt.Color(57, 62, 70));
        try {
            campoDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataNascimento.setText("  /  /    ");
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

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        if (campoCpf.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O CPF deve ser informado!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
        } else {
            Cliente cl;         
            String nome = "", cpf = "", email = "", endereco = "", celular = "", dataNascimento = "";
            
            for (int i = 0; i < Clientes.size(); i++) {
                cl = Clientes.get(i);
                
                if (campoCpf.getText().equals(cl.getCPF())) {
                    nome = cl.getNome();
                    cpf = cl.getCPF();
                    email = cl.getEmail();
                    endereco = cl.getEndereco();
                    celular = cl.getCelular();
                    dataNascimento = String.valueOf(cl.getDataAniversario().format(formatter));
                    
                }
            }
            
            if (cpf.equals("")) {
                JOptionPane.showMessageDialog(null, "Este cliente não existe!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                campoNome.setText("");
                campoCpf.setText("");
                campoEmail.setText("");
                campoEndereco.setText("");
                campoCelular.setText("");
                campoDataNascimento.setText("");
            } else {
                campoNome.setText(nome);
                campoCpf.setText(cpf);
                campoEmail.setText(email);
                campoEndereco.setText(endereco);
                campoCelular.setText(celular);
                campoDataNascimento.setText(dataNascimento);
            }
            
            campoNome.selectAll();
            campoNome.requestFocus();
        }
        

    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        botao = "novo";

        //Limpar os campos
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoEndereco.setText("");
        campoCelular.setText("");
        campoDataNascimento.setText("");

        //Habilitar ou desabilitar os botoes
        botaoVoltar.setEnabled(true);
        botaoBuscar.setEnabled(false);
        botaoNovo.setEnabled(false);
        botaoAlterar.setEnabled(false);
        botaoExcluir.setEnabled(false);
        botaoSalvar.setEnabled(true);

        // Habilitar os campos de texto
        campoNome.setEnabled(true);
        campoCpf.setEnabled(true);
        campoEmail.setEnabled(true);
        campoEndereco.setEnabled(true);
        campoCelular.setEnabled(true);
        campoDataNascimento.setEnabled(true);
        
        campoNome.requestFocus();
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void tabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClientesMouseClicked
        int i = tabelaClientes.getSelectedRow();
        
        if (i >= 0 && i < Clientes.size()) {
            Cliente cl = Clientes.get(i);
            campoNome.setText(cl.getNome());
            campoCpf.setText(cl.getCPF());
            campoEmail.setText(cl.getEmail());
            campoEndereco.setText(cl.getEndereco());
            campoCelular.setText(cl.getCelular());
            campoDataNascimento.setText(String.valueOf(cl.getDataAniversario().format(formatter)));
        }

        // Habilitar ou desabilitar os botoes
        botaoVoltar.setEnabled(true);
        botaoBuscar.setEnabled(true);
        botaoNovo.setEnabled(true);
        botaoAlterar.setEnabled(true);
        botaoExcluir.setEnabled(true);
        botaoSalvar.setEnabled(false);

        // Desabilitar os campos de texto
        campoNome.setEnabled(false);
        campoCpf.setEnabled(false);
        campoEmail.setEnabled(false);
        campoEndereco.setEnabled(false);
        campoCelular.setEnabled(false);
        campoDataNascimento.setEnabled(false);

    }//GEN-LAST:event_tabelaClientesMouseClicked

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        botao = "alterar";

        // Habilitar ou desabilitar os botoes
        botaoVoltar.setEnabled(true);
        botaoBuscar.setEnabled(false);
        botaoNovo.setEnabled(false);
        botaoAlterar.setEnabled(false);
        botaoExcluir.setEnabled(false);
        botaoSalvar.setEnabled(true);

        // Habilitar os campos de texto
        campoNome.setEnabled(true);
        campoCpf.setEnabled(true);
        campoEmail.setEnabled(true);
        campoEndereco.setEnabled(true);
        campoCelular.setEnabled(true);
        campoDataNascimento.setEnabled(true);
        
        campoNome.requestFocus();
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int index = tabelaClientes.getSelectedRow();
        
        if (index >= 0 && index < Clientes.size()) {
            
            Clientes.remove(index);
        }
        
        carregarTabelaClientes();

        //Limpar os campos
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoEndereco.setText("");
        campoCelular.setText("");
        campoDataNascimento.setText("");

        //Habilitar ou desabilitar os botoes
        botaoVoltar.setEnabled(true);
        botaoBuscar.setEnabled(true);
        botaoNovo.setEnabled(true);
        botaoAlterar.setEnabled(false);
        botaoExcluir.setEnabled(false);
        botaoSalvar.setEnabled(true);

        // Habilitar os campos de texto
        campoNome.setEnabled(false);
        campoCpf.setEnabled(false);
        campoEmail.setEnabled(false);
        campoEndereco.setEnabled(false);
        campoCelular.setEnabled(false);
        campoDataNascimento.setEnabled(false);
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
            
            LocalDate dataNascimento = LocalDate.parse(campoDataNascimento.getText(), formatter);
            
            if (botao.equals("novo")) {
                Cliente cliente = new Cliente(nome, cpf, email, endereco, celular, dataNascimento);
                
                Clientes.add(cliente);
                
            } else if (botao.equals("alterar")) {
                int index = tabelaClientes.getSelectedRow();
                
                Clientes.get(index).setNome(nome);
                Clientes.get(index).setCPF(cpf);
                Clientes.get(index).setEmail(email);
                Clientes.get(index).setEndereco(endereco);
                Clientes.get(index).setCelular(celular);
                Clientes.get(index).setDataAniversario(dataNascimento);
            }
            
            carregarTabelaClientes();

            //Limpar os campos
            campoNome.setText("");
            campoCpf.setText("");
            campoEmail.setText("");
            campoEndereco.setText("");
            campoCelular.setText("");
            campoDataNascimento.setText("");

            //Habilitar ou desabilitar os botoes
            botaoVoltar.setEnabled(true);
            botaoBuscar.setEnabled(false);
            botaoNovo.setEnabled(true);
            botaoAlterar.setEnabled(false);
            botaoExcluir.setEnabled(false);
            botaoSalvar.setEnabled(false);

            // Habilitar os campos de texto
            campoNome.setEnabled(false);
            campoCpf.setEnabled(false);
            campoEmail.setEnabled(false);
            campoEndereco.setEnabled(false);
            campoCelular.setEnabled(false);
            campoDataNascimento.setEnabled(false);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        if (Clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente está cadastrado!", "Menssagem", JOptionPane.PLAIN_MESSAGE);
        } else {
            //Limpar os campos
            campoNome.setText("");
            campoCpf.setText("");
            campoEmail.setText("");
            campoEndereco.setText("");
            campoCelular.setText("");
            campoDataNascimento.setText("");

            //Habilitar ou desabilitar os botoes
            botaoVoltar.setEnabled(true);
            botaoBuscar.setEnabled(true);
            botaoNovo.setEnabled(false);
            botaoAlterar.setEnabled(false);
            botaoExcluir.setEnabled(false);
            botaoSalvar.setEnabled(false);

            // Habilitar os campos de texto
            campoNome.setEnabled(false);
            campoCpf.setEnabled(true);
            campoEmail.setEnabled(false);
            campoEndereco.setEnabled(false);
            campoCelular.setEnabled(false);
            campoDataNascimento.setEnabled(false);
            
            campoCpf.requestFocus();
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
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel cadatroClientes;
    private javax.swing.JTextField campoCelular;
    private javax.swing.JTextField campoCpf;
    private javax.swing.JFormattedTextField campoDataNascimento;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoNome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaClientes;
    // End of variables declaration//GEN-END:variables
}
