/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import DataBase.ConectoBD;
import Menu.MenuUI;
import Login.Recu.Recu_Pas1;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 *
 * @author Desarrollo
 */
public class LoginUi extends javax.swing.JFrame {

    /**
     * Creates new form LoginUi
     */
    public static String usuario_se = "";
    public static String id_usuario_se = "";
    int contador = 0;

    public LoginUi() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jtUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtPass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtUsuario.setBorder(null);
        jtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(jtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 114, 180, 30));

        jButton1.setText("Ingresar");
        jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 190, 60));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Olvide mi contraseña");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 190, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/descarga (1).jpeg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 270, 180));

        jtPass.setBorder(null);
        jtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtPassKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtPassKeyTyped(evt);
            }
        });
        getContentPane().add(jtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 180, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/fondoLogin2.jpeg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ConectoBD conectar = new ConectoBD();
        conectar.Conectado();
        try {
            if (!jtUsuario.getText().equals("") && !jtPass.getText().equals("")) {
                if (control_acceso_pass() == true) {
                    if (control_acceso_usu() == true) {
                        ResultSet rs_usu = conectar.listar("username, pass, idusuario", "usuario",
                                "where username = '" + jtUsuario.getText() + "'");
                        if (rs_usu.next() == true) {
                            id_usuario_se = rs_usu.getString(3);
                            usuario_se = jtUsuario.getText();
                            MenuUI men = new MenuUI();
                            men.setVisible(true);
                            jtUsuario.setText("");
                            jtPass.setText("");
                            this.setVisible(false);

                        } else {
                            JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectas!!!", "Error", JOptionPane.ERROR_MESSAGE);
                            usuario_se = "";
                            id_usuario_se = "";
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Caracteres no permitido en el nombre de usuario!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Caracteres no permitido en la contrasña!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos!!!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
        conectar.Desconectado();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPassKeyReleased
        ConectoBD conectar = new ConectoBD();
        conectar.Conectado();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if (!jtUsuario.getText().equals("") && !jtPass.getText().equals("")) {
                    if (control_acceso_pass() == true) {
                        if (control_acceso_usu() == true) {
                            ResultSet rs_usu = conectar.listar("username, pass, idusuario", "usuario",
                                    "where username = '" + jtUsuario.getText() + "'");
                            if (rs_usu.next() == true) {
                                id_usuario_se = rs_usu.getString(3);
                                usuario_se = jtUsuario.getText();
                                MenuUI men = new MenuUI();
                                men.setVisible(true);
                                jtUsuario.setText("");
                                jtPass.setText("");
                                this.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectas!!!", "Error", JOptionPane.ERROR_MESSAGE);
                                usuario_se = "";
                                id_usuario_se = "";
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Caracteres no permitido en el nombre de usuario!!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Caracteres no permitido en la contrasña!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
            }
        }
        conectar.Desconectado();
    }//GEN-LAST:event_jtPassKeyReleased

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        Recu_Pas1 contrasena = new Recu_Pas1();
        contrasena.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtUsuarioKeyTyped
        char c4 = evt.getKeyChar();
        if (jtUsuario.getText().length() >= 45) {
            evt.consume();
            getToolkit().beep();
        } else {
            boolean b = true;
            String letra = Character.toString(c4);
            String lineIwant = letra;
            String caracteres_permitidos = "[.',\"\"/*-+@!&^%$()=|;:]";
            b = lineIwant.matches(caracteres_permitidos);
            if (b == true) {
                evt.consume();
                getToolkit().beep();
            }
        }
    }//GEN-LAST:event_jtUsuarioKeyTyped

    private void jtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPassKeyTyped
        char c4 = evt.getKeyChar();
        if (jtPass.getText().length() >= 45) {
            evt.consume();
            getToolkit().beep();
        } else {
            boolean b = true;
            String letra = Character.toString(c4);
            String lineIwant = letra;
            String caracteres_permitidos = "[.',\"\"/*-+!&^%$()=|;:]";
            b = lineIwant.matches(caracteres_permitidos);
            if (b == true) {
                evt.consume();
                getToolkit().beep();
            } else {

            }
        }
    }//GEN-LAST:event_jtPassKeyTyped

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
            java.util.logging.Logger.getLogger(LoginUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUi().setVisible(true);
            }
        });
    }

    public boolean control_acceso_usu() {

        try {
            String pass = jtUsuario.getText();
            for (int i = 0; i < pass.length(); i++) {
                String valor = "" + pass.charAt(i);
                if (valor.equals(";")
                        || valor.equals("'")
                        || valor.equals("(")
                        || valor.equals(")")
                        || valor.equals("=")
                        || valor.equals("\\")) {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    public boolean control_acceso_pass() {

        try {
            String pass = jtPass.getText();
            for (int i = 0; i < pass.length(); i++) {
                String valor = "" + pass.charAt(i);
                if (valor.equals(";")
                        || valor.equals("'")
                        || valor.equals("(")
                        || valor.equals(")")
                        || valor.equals("=")
                        || valor.equals("\\")) {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    public static String decrypt(String cadena) {
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
        s.setPassword("uniquekey");
        String devuelve = "";
        try {
            devuelve = s.decrypt(cadena);
        } catch (Exception e) {
        }
        return devuelve;
    }

    public void Limpiar() {
        //jtUsuario.setText("");
        jtPass.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jtPass;
    private javax.swing.JTextField jtUsuario;
    // End of variables declaration//GEN-END:variables

}
