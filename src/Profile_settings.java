
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

public final class Profile_settings extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_profile_genaral_settings
     */
    
    public static String passedusername,userRole,first_name,last_name,Username,Email;

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Profile_settings() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        permissions();
        all_cansels();
        con = Database_connection_CLASS.connection();
        try
        {
            stmt = con.createStatement();

            String query = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                if(passedusername.equals(rs.getString(2)))
                {
                    this.first_name = rs.getString(3);
                    fname.setText(first_name);
                    
                    this.last_name = rs.getString(4);
                    lname.setText(rs.getString(4));
                    
                    this.Email = rs.getString(5);
                    email.setText(Email);
                    
                    this.Username = rs.getString(6);
                    username.setText(Username);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void permissions(){
    
        try{
            if(null == userRole){
  
            }
            else switch (userRole) {
                case "admin":
                    break;
                case "master":
                    lname6.setVisible(false);
                    account_type.setVisible(false);
                    break;
                default:
                    lname6.setVisible(false);
                    account_type.setVisible(false);
                    break;
            }
        }
        catch(Exception e){
            System.out.println("Permission check Faild");
        }
    }
    
    public void all_cansels(){
    
        error_msg.setVisible(false);
        username_wrong.setVisible(false);
        useername_right.setVisible(false);
        error_icon1.setVisible(false);
        error_icon2.setVisible(false);
    }
    
     public void usernameCheck(){
    
        try
        {
            stmt = con.createStatement();
            
            if( username.getText().equals("") )
            {
                
                username_wrong.setVisible(true);
                useername_right.setVisible(false);
                save.setEnabled(false);
            }
            else
            {
                boolean loopcheck = false;
                stmt = con.createStatement();
                String query = "SELECT * FROM users ";
                String check = username.getText();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    if (check.equals(rs.getString(6)))
                    {
                        loopcheck = true;
                        username_wrong.setVisible(true);
                        useername_right.setVisible(false);
                        save.setEnabled(true);
                    }
                }
                if(!loopcheck){
                    
                    useername_right.setVisible(true);
                    username_wrong.setVisible(false);
                    save.setEnabled(true);
                }   
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
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
        lname2 = new javax.swing.JLabel();
        lname1 = new javax.swing.JLabel();
        lname3 = new javax.swing.JLabel();
        lname4 = new javax.swing.JLabel();
        lname5 = new javax.swing.JLabel();
        account_type = new javax.swing.JComboBox<>();
        lname6 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        error_msg = new javax.swing.JLabel();
        username_wrong = new javax.swing.JLabel();
        useername_right = new javax.swing.JLabel();
        error_icon1 = new javax.swing.JLabel();
        error_icon2 = new javax.swing.JLabel();
        save = new accessories.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lname2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        lname2.setForeground(new java.awt.Color(102, 102, 102));
        lname2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname2.setText("Genaral Account settings");
        jPanel1.add(lname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 470, -1));

        lname1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lname1.setForeground(new java.awt.Color(51, 51, 51));
        lname1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname1.setText("Email");
        jPanel1.add(lname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 152, 31));

        lname3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lname3.setForeground(new java.awt.Color(51, 51, 51));
        lname3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname3.setText("First name");
        jPanel1.add(lname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 131, 152, -1));

        lname4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lname4.setForeground(new java.awt.Color(51, 51, 51));
        lname4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname4.setText("First name");
        jPanel1.add(lname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 131, 152, -1));

        lname5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lname5.setForeground(new java.awt.Color(51, 51, 51));
        lname5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname5.setText("username");
        jPanel1.add(lname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 152, -1));

        account_type.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        account_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "User" }));
        jPanel1.add(account_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 162, -1));

        lname6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lname6.setForeground(new java.awt.Color(51, 51, 51));
        lname6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname6.setText("Account Type :");
        jPanel1.add(lname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 152, 30));

        fname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        fname.setText("jTextField1");
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 162, 220, 40));

        lname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        lname.setText("jTextField1");
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 162, 220, 40));

        email.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        email.setText("jTextField1");
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 480, 40));

        username.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        username.setText("jTextField1");
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameKeyReleased(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 251, 480, 40));

        error_msg.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        error_msg.setText("jLabel1");
        jPanel1.add(error_msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 470, 32));

        username_wrong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N
        jPanel1.add(username_wrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 251, -1, 40));

        useername_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_ok_20px.png"))); // NOI18N
        jPanel1.add(useername_right, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, 40));

        error_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N
        jPanel1.add(error_icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, 40));

        error_icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_ok_20px.png"))); // NOI18N
        jPanel1.add(error_icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, 40));

        save.setBackground(new java.awt.Color(0, 102, 102));
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("Save changes");
        save.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 480, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
        // TODO add your handling code here:
        try{
            usernameCheck();
        }
        catch(Exception e){}
    }//GEN-LAST:event_usernameKeyReleased

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
        try{
            usernameCheck();
        }
        catch(Exception e){}
    }//GEN-LAST:event_usernameKeyPressed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
                try{
            
            String id = passedusername;
                    
            String check1  = fname.getText();
            String firstname  = first_name; 

            String check2 = lname.getText();
            String lastname = last_name;

            String check3 = username.getText();
            String USername = Username;
            
            String check4 = email.getText();
            String EMail = Email;     
            
            if((firstname == null ? check1 == null : firstname.equals(check1)) && (check2 == null ? lastname == null : check2.equals(lastname)) && 
                    (check3 == null ? USername == null : check3.equals(USername)) && (check4 == null ? EMail == null : check4.equals(EMail))){
                
                error_icon1.setVisible(true);
                error_msg.setVisible(true);
                error_msg.setText("Nothing Changed!");
            } 
            else {
                all_cansels();
                error_icon2.setVisible(true);
                error_msg.setVisible(true);
                error_msg.setText("Succesfully saved settings!");
                
                con = Database_connection_CLASS.connection();
                stmt = con.createStatement();
                String query ="UPDATE users SET  fname = ? , lname = ? , username = ? , email = ? WHERE user_id = ? ";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, check1);
                preparedStmt.setString(2, check2);
                preparedStmt.setString(3, check3);
                preparedStmt.setString(4, check4);
                preparedStmt.setString(5, id);
                preparedStmt.execute();
                
                
            }
        
        }
        catch(Exception e){
        }
    }//GEN-LAST:event_saveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> account_type;
    private javax.swing.JTextField email;
    private javax.swing.JLabel error_icon1;
    private javax.swing.JLabel error_icon2;
    private javax.swing.JLabel error_msg;
    private javax.swing.JTextField fname;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel lname1;
    private javax.swing.JLabel lname2;
    private javax.swing.JLabel lname3;
    private javax.swing.JLabel lname4;
    private javax.swing.JLabel lname5;
    private javax.swing.JLabel lname6;
    private accessories.Button save;
    private javax.swing.JLabel useername_right;
    private javax.swing.JTextField username;
    private javax.swing.JLabel username_wrong;
    // End of variables declaration//GEN-END:variables
}
