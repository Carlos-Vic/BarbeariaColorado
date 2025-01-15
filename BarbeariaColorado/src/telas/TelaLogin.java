package telas;

public class TelaLogin extends javax.swing.JFrame {


    public TelaLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoCpf = new javax.swing.JTextField();
        campoSenha = new javax.swing.JPasswordField();
        botaoEntrar = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();
        telaLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        campoCpf.setBackground(new java.awt.Color(0, 173, 181));
        campoCpf.setForeground(new java.awt.Color(255, 255, 255));
        campoCpf.setBorder(null);
        getContentPane().add(campoCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 148, 280, 30));

        campoSenha.setBackground(new java.awt.Color(0, 173, 181));
        campoSenha.setForeground(new java.awt.Color(255, 255, 255));
        campoSenha.setBorder(null);
        getContentPane().add(campoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 280, 30));

        botaoEntrar.setBackground(new java.awt.Color(57, 62, 70));
        botaoEntrar.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        botaoEntrar.setForeground(new java.awt.Color(238, 238, 238));
        botaoEntrar.setBorder(null);
        botaoEntrar.setBorderPainted(false);
        botaoEntrar.setContentAreaFilled(false);
        botaoEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoEntrar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        getContentPane().add(botaoEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 323, 270, 30));

        botaoSair.setBackground(new java.awt.Color(57, 62, 70));
        botaoSair.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        botaoSair.setForeground(new java.awt.Color(238, 238, 238));
        botaoSair.setBorder(null);
        botaoSair.setContentAreaFilled(false);
        botaoSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        getContentPane().add(botaoSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 392, 80, 30));

        telaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tela_login.png"))); // NOI18N
        getContentPane().add(telaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(701, 501));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
        dispose();
    }//GEN-LAST:event_botaoSairActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEntrar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JTextField campoCpf;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel telaLogin;
    // End of variables declaration//GEN-END:variables
}
