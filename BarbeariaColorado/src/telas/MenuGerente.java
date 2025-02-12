/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

/**
 *
 * @author ARTHUR HENRIQUE
 */
public class MenuGerente extends javax.swing.JFrame {

    /**
     * Creates new form MenuGerente
     */
    public MenuGerente() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        botaoCadastrarProduto = new javax.swing.JButton();
        botaoCadastrarFuncionario = new javax.swing.JButton();
        botaoCadastrarCliente = new javax.swing.JButton();
        botaoDesconectar = new javax.swing.JButton();
        menuGerente = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botaoCadastrarProduto.setBorder(null);
        botaoCadastrarProduto.setContentAreaFilled(false);
        botaoCadastrarProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoCadastrarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(botaoCadastrarProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 290, 40));

        botaoCadastrarFuncionario.setBorder(null);
        botaoCadastrarFuncionario.setContentAreaFilled(false);
        botaoCadastrarFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoCadastrarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(botaoCadastrarFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 290, 40));

        botaoCadastrarCliente.setBorder(null);
        botaoCadastrarCliente.setContentAreaFilled(false);
        botaoCadastrarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(botaoCadastrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 290, 40));

        botaoDesconectar.setBorder(null);
        botaoDesconectar.setContentAreaFilled(false);
        botaoDesconectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDesconectarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoDesconectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 110, 30));

        menuGerente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/menu_gerente.png"))); // NOI18N
        menuGerente.setText("jLabel1");
        getContentPane().add(menuGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 689, 475));

        setSize(new java.awt.Dimension(693, 475));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDesconectarActionPerformed
        // TODO add your handling code here:
        new TelaLogin().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botaoDesconectarActionPerformed

    private void botaoCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarClienteActionPerformed
        // TODO add your handling code here:
        CadastroCliente cadastroCliente = new CadastroCliente(this);
        cadastroCliente.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botaoCadastrarClienteActionPerformed

    private void botaoCadastrarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarFuncionarioActionPerformed
        // TODO add your handling code here:
        new CadastroFuncionario().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botaoCadastrarFuncionarioActionPerformed

    private void botaoCadastrarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarProdutoActionPerformed
        // TODO add your handling code here:
        new CadastroProduto().setVisible(true);
    }//GEN-LAST:event_botaoCadastrarProdutoActionPerformed

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
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrarCliente;
    private javax.swing.JButton botaoCadastrarFuncionario;
    private javax.swing.JButton botaoCadastrarProduto;
    private javax.swing.JButton botaoDesconectar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel menuGerente;
    // End of variables declaration//GEN-END:variables
}
