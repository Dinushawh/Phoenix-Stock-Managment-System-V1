
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public final class Profile_delete extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_profile_delete
     */
    public static String passedusername,dpPass,dbAns,dbSc,userid;
    
    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Profile_delete() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        close();
        getDetails();
        canselMsg();
    }
    
    public void close(){
    
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        sc_list.setVisible(false);
        answer.setVisible(false);
        password.setVisible(false);
        confirm.setVisible(false);
        cansel.setVisible(false);
 
    }
    
    public void canselMsg(){
    
        error_msg.setVisible(false);
        pw_error_icon1.setVisible(false);
        pw_error_icon2.setVisible(false);
        
    }

    public void getDetails(){
        
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM users";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next())
                {
                    if(passedusername.equals(rsd.getString(2)))
                    {
                        this.dpPass = rsd.getString(7);
                        this.dbSc = rsd.getString(10);
                        this.dbAns = rsd.getString(11);
                    }
                }
        }
        catch(SQLException e){

        }
    }
    
    public void deleteRecord(){
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String Query1 = "DELETE FROM `users` WHERE `user_id` = ?;";
            PreparedStatement preparedStmt3 = con.prepareStatement(Query1);
            preparedStmt3.setString(1, userid);
            preparedStmt3.execute();
        }
        catch(SQLException e){
        }
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
        text3 = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        lname3 = new javax.swing.JLabel();
        sc_list = new javax.swing.JComboBox<>();
        text1 = new javax.swing.JLabel();
        answer = new javax.swing.JTextField();
        text2 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        error_msg = new javax.swing.JLabel();
        pw_error_icon1 = new javax.swing.JLabel();
        pw_error_icon2 = new javax.swing.JLabel();
        permently_delete = new accessories.Button();
        cansel = new accessories.Button();
        confirm = new accessories.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        text3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        text3.setForeground(new java.awt.Color(102, 102, 102));
        text3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        text3.setText("Password");
        jPanel1.add(text3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 480, -1));

        fname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        fname.setForeground(new java.awt.Color(102, 102, 102));
        fname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fname.setText("Are you sure? Your profile and related account information will be deleted from our server");
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        lname3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        lname3.setForeground(new java.awt.Color(102, 102, 102));
        lname3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname3.setText("Permanently delete account");
        jPanel1.add(lname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 470, -1));

        sc_list.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        sc_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?", "What high school did you attend?", "What is the name of your first school?", "What was the make of your first car?", "What was your favorite food as a child?", "Where did you meet your spouse?" }));
        jPanel1.add(sc_list, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 480, 40));

        text1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        text1.setForeground(new java.awt.Color(102, 102, 102));
        text1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        text1.setText("Select Security Question");
        jPanel1.add(text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 470, -1));

        answer.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        answer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                answerKeyPressed(evt);
            }
        });
        jPanel1.add(answer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 480, 40));

        text2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        text2.setForeground(new java.awt.Color(102, 102, 102));
        text2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        text2.setText("Answer");
        jPanel1.add(text2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 470, -1));

        password.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 480, 40));

        error_msg.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        error_msg.setText("jLabel4");
        jPanel1.add(error_msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 371, 70));

        pw_error_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N
        jPanel1.add(pw_error_icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 70));

        pw_error_icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_ok_20px.png"))); // NOI18N
        jPanel1.add(pw_error_icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 70));

        permently_delete.setBackground(new java.awt.Color(255, 0, 51));
        permently_delete.setForeground(new java.awt.Color(255, 255, 255));
        permently_delete.setText("Permanently delete");
        permently_delete.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        permently_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permently_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(permently_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 220, 40));

        cansel.setBackground(new java.awt.Color(0, 102, 102));
        cansel.setForeground(new java.awt.Color(255, 255, 255));
        cansel.setText("Cansel");
        cansel.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cansel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canselActionPerformed(evt);
            }
        });
        jPanel1.add(cansel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 230, 40));

        confirm.setBackground(new java.awt.Color(255, 0, 51));
        confirm.setForeground(new java.awt.Color(255, 255, 255));
        confirm.setText("Confirm");
        confirm.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });
        jPanel1.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 230, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1311, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void answerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_answerKeyPressed
        // TODO add your handling code here:
        try{
        
            error_msg.setVisible(false);
            pw_error_icon1.setVisible(false);
            pw_error_icon2.setVisible(false);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_answerKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        // TODO add your handling code here:
        try{
        
            error_msg.setVisible(false);
            pw_error_icon1.setVisible(false);
            pw_error_icon2.setVisible(false);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void permently_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permently_deleteActionPerformed
        // TODO add your handling code here:
        try{
        
            permently_delete.setVisible(false);
            text1.setVisible(true);
            text2.setVisible(true);
            text3.setVisible(true);
            sc_list.setVisible(true);
            answer.setVisible(true);
            password.setVisible(true);
            confirm.setVisible(true);
            cansel.setVisible(true);
            
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_permently_deleteActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // TODO add your handling code here:
        try{
            String id = passedusername;
            String pw = dpPass;
            String sc = dbSc;
            String ans = dbAns;

            if(answer.getText().equals("") || password.getText().equals("")){
            
                pw_error_icon1.setVisible(true);
                error_msg.setVisible(true);
                error_msg.setText("Feilds are empty");
            }
            else{

                if(dpPass == null ? password.getText() == null : dpPass.equals(password.getText()) ){

                    if(answer.getText() == null ? ans == null : answer.getText().equals(ans)){

                        if(sc == null ? sc_list.getSelectedItem().toString() == null : sc.equals(sc_list.getSelectedItem().toString())){
                            canselMsg();
                            deleteRecord();
                            int result = JOptionPane.showConfirmDialog(null, "Your account successfully deleted from the server ", "Secuess",
                                JOptionPane.OK_CANCEL_OPTION);
                            if (result == JOptionPane.OK_OPTION){
                                 System.exit(0);
                            }
                            else{
                                System.exit(0);
                            }
                            answer.setText("");
                            password.setText("");
                        }
                        else{
                            canselMsg();
                            pw_error_icon1.setVisible(true);
                            error_msg.setVisible(true);
                            error_msg.setText("Invalid Security Question");
                        }
                    }
                    else{
                        canselMsg();
                        pw_error_icon1.setVisible(true);
                        error_msg.setVisible(true);
                        error_msg.setText("Invalid Answer");
                    }
                }
                else{
                    canselMsg();
                    pw_error_icon1.setVisible(true);
                    error_msg.setVisible(true);
                    error_msg.setText("Invalid Password");
                }
            }   
        }
        catch(HeadlessException e){
        
        }
    }//GEN-LAST:event_confirmActionPerformed

    private void canselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canselActionPerformed
        // TODO add your handling code here:
        try{
        
            close();
            permently_delete.setVisible(true);
            
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_canselActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField answer;
    private accessories.Button cansel;
    private accessories.Button confirm;
    private javax.swing.JLabel error_msg;
    private javax.swing.JLabel fname;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lname3;
    private javax.swing.JPasswordField password;
    private accessories.Button permently_delete;
    private javax.swing.JLabel pw_error_icon1;
    private javax.swing.JLabel pw_error_icon2;
    private javax.swing.JComboBox<String> sc_list;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel text2;
    private javax.swing.JLabel text3;
    // End of variables declaration//GEN-END:variables
}