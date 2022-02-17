
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Master_login extends javax.swing.JFrame {

    /**
     * Creates new form MasterLogin
     */
    
    Connection con = null;
    Statement stmt = null;
    
    public Master_login() {
        initComponents();
        
        errorIcon.setVisible(false);
        errormassage.setVisible(false);
        
        /* connect database */
        con = Database_connection_CLASS.connection();
 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        container = new javax.swing.JLabel();
        errorIcon = new javax.swing.JLabel();
        errormassage = new javax.swing.JLabel();
        username = new accessories.TextField();
        password = new accessories.PasswordField();
        button1 = new accessories.Button();
        jLabel1 = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        container.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/MasterLogin/master-01.png"))); // NOI18N
        jPanel2.add(container, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 400, 90));

        errorIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_28px.png"))); // NOI18N
        jPanel2.add(errorIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 40, 80));

        errormassage.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        errormassage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        errormassage.setText("jLabel4");
        jPanel2.add(errormassage, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 330, 70));

        username.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        username.setLabelText("Username");
        username.setLineColor(new java.awt.Color(255, 51, 51));
        jPanel2.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 390, 88));

        password.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        password.setLabelText("Password");
        password.setLineColor(new java.awt.Color(255, 51, 51));
        jPanel2.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 390, 84));

        button1.setBackground(new java.awt.Color(255, 51, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Access Now");
        button1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 390, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/MasterLogin/MasterIntro-01.png"))); // NOI18N

        close.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Public/icons8_delete_32px_1.png"))); // NOI18N
        close.setContentAreaFilled(false);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Public/icons8_left_arrow_40px.png"))); // NOI18N
        back.setContentAreaFilled(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        try
        {
            System.exit(0);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_closeActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        try
        {
            this.setVisible(false);
            Login load = new Login ();
            load.setVisible(true);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_backActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
                try
        {
            if( username.getText().equals("")||password.getText().equals("")  )
            {
                errorIcon.setVisible(true);
                errormassage.setVisible(true);
                Color orange = new Color(255,102,0);
                errormassage.setForeground(orange);
                errormassage.setText("Username and password feil empty");
            }
            else
            {
                boolean loopcheck = false;

                stmt = con.createStatement();
                String query = "SELECT * FROM users ";

                String Username = username.getText();
                String Password = password.getText();
                

                ResultSet rs = stmt.executeQuery(query);

                while(rs.next())
                {
                    if (Username.equals(rs.getString(6)) && Password.equals(rs.getString(7)))
                    {
                        String ROLE;
                        ROLE = (rs.getString(8));
                        
                        if("master".equals(ROLE))
                        {
//                            AdminPanel.USERNAME = Username;
                            loopcheck = true;
                            setVisible(false);
                            Username_check view2 = new Username_check();
                            view2.setVisible(true);
                        }
                       
                        else
                        {
//                            Home.passedusername = Username;
                            loopcheck = true;
                            errorIcon.setVisible(true);
                            errormassage.setVisible(true);
                            Color orange = new Color(255,102,0);
                            errormassage.setForeground(orange);
                            errormassage.setText("Permission denied !");
                            
                        }
                    }
                }
                if(!loopcheck){
                    
                    errorIcon.setVisible(true);
                    errormassage.setVisible(true);
                    Color red = new Color(255,0,0);
                    errormassage.setForeground(red);
                    errormassage.setText("Username and password not match");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
//            DatabaseError er = new DatabaseError ();
//            er.setVisible(true);
        }
    }//GEN-LAST:event_button1ActionPerformed

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
            java.util.logging.Logger.getLogger(Master_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Master_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Master_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Master_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Master_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private accessories.Button button1;
    private javax.swing.JButton close;
    private javax.swing.JLabel container;
    private javax.swing.JLabel errorIcon;
    private javax.swing.JLabel errormassage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private accessories.PasswordField password;
    private accessories.TextField username;
    // End of variables declaration//GEN-END:variables
}
