import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
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
public final class User_add_new extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewallItems
     */
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    public static String userRole;
    public String newUserID,loop,loop2;

    
    public User_add_new() {
            initComponents();

            
            this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
            UIE.setNorthPane(null);
        
            con = Database_connection_CLASS.connection();
            generateUserID ();
            errorVisibility ();
            permissions ();
    }
    
    public void permissions (){
    
        try{
            if(null == userRole){

            }
            else switch (userRole) {
                case "admin":
                    JComboBox test = new JComboBox();
                    userType.addItem("admin");
                    userType.addItem("user");
                    break;
                case "master":
                    userType.addItem("master");
                    userType.addItem("admin");
                    userType.addItem("user");
                    break;
                default:
                    break;
}
        
        }
        catch(Exception e){
        
        }
    }
    
    
    public void errorVisibility (){
    
        try{
            wrong.setVisible(false);
            right.setVisible(false);
        }
        catch(Exception e){
            System.out.println("Error with error icons");
        }
    }
    
    public void clearAll(){
    
        try{    
            username.setText("");
            fname.setText("");
            lname.setText("");
            email.setText("");
        }
        catch(Exception e){
            System.out.println("error claer all feild");
        
        }
    }
    
    public void generateUserID (){
    
        try{
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(user_id) FROM users");
            rsd.next();
            rsd.getString("MAX(user_id)");
            if (rsd.getString("MAX(user_id)") == null) {
                itemidset = "ID00000001";
                userID.setText("Customer id : "+itemidset);
                this.newUserID = itemidset;
            } 
            else {
                long id = Long.parseLong(rsd.getString("MAX(user_id)").substring(2, rsd.getString("MAX(user_id)").length()));
                id++;
                itemidset = ("ID" + String.format("%08d", id));
                userID.setText("Customer id : "+itemidset);  
                this.newUserID = itemidset;
            }
        }
        catch(NumberFormatException | SQLException e){
            System.out.println("Error Genarate user id");
        }
    }
    
    public void usernameCheck(){
    
        try{
            stmt = con.createStatement();
            String userid = username.getText();

            if( username.getText().equals("") )
            {
                errorVisibility ();
                wrong.setVisible(true);
            }
            else
            {
                boolean loopcheck = false;
                stmt = con.createStatement();
                String query = "SELECT * FROM users ";
                String Username = username.getText();
                ResultSet rsd = stmt.executeQuery(query);
                while(rsd.next())
                {
                    if (Username.equals(rsd.getString(6)))
                    {
                        errorVisibility ();
                        loopcheck = true;
                        wrong.setVisible(true);
                    }
                }
                if(!loopcheck){
                    errorVisibility ();
                    right.setVisible(true);
                    this.loop = "1";
                }
                }
        
        }
        catch(SQLException e){
            System.out.println("Error checking username");
        }
    }
    
    public void checkEmpty(){
    
        try{
            if("".equals(username.getText()) || "".equals(fname.getText()) || "".equals(lname.getText()) || "".equals(email.getText())){
            
                JOptionPane.showMessageDialog (null, "Please fill out the fields!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
            }
            else{
            
                this.loop2 = "1";
            }
        }
        catch(HeadlessException e){
            System.out.println("Error checking empyt");
        }
    }
    
    public void saveData(){
    
        try{
            
            stmt = con.createStatement();
            String query2 = "INSERT INTO users (user_id, fname, lname, username, email, password, login_time,user_role) VALUES (?,?,?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query2);
            preparedStmt.setString(1, newUserID);
            preparedStmt.setString(2, fname.getText());
            preparedStmt.setString(3, lname.getText());
            preparedStmt.setString(4, username.getText());
            preparedStmt.setString(5, email.getText());
            preparedStmt.setString(6, username.getText());
            preparedStmt.setString(7, "2");
            preparedStmt.setString(8, userType.getSelectedItem().toString());
            preparedStmt.execute();
            
            JOptionPane.showMessageDialog (null, "Account has been created successfully", "success!", JOptionPane.INFORMATION_MESSAGE);
            UIManager UI=new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
            
            
        
        }
        catch(SQLException e){
            System.out.println("Error saving data");   
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
        jPanel2 = new javax.swing.JPanel();
        userID = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        fname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        button1 = new accessories.Button();
        wrong = new javax.swing.JLabel();
        right = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        userType = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userID.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        userID.setText("userid");
        jPanel2.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 290, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/user/newUser-06.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -40, 670, 180));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel2.setText("The new user's password is set to his username by default, Be sure to change his password and security question when logging in for the first time");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel4.setText("Username");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel5.setText("First name");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel6.setText("Last name");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel7.setText("User type");

        lname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        username.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameKeyTyped(evt);
            }
        });

        fname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        email.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        button1.setBackground(new java.awt.Color(255, 0, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Add now");
        button1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        wrong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N

        right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_ok_20px.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel8.setText("Email");

        userType.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(userType, javax.swing.GroupLayout.Alignment.LEADING, 0, 300, Short.MAX_VALUE)
                                .addComponent(email, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wrong)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(right)))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
        try{
        
            usernameCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_usernameKeyPressed

    private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
        // TODO add your handling code here:
        try{
        
            usernameCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_usernameKeyReleased

    private void usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyTyped
        // TODO add your handling code here:
        try{
        
            usernameCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_usernameKeyTyped

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        try{
        
            checkEmpty();
            if("1".equals(loop) && "1".equals(loop2)){
            
                saveData();
                clearAll();
                errorVisibility ();
                generateUserID ();
                
            }
        }
        catch(Exception e){
        
        }        
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private accessories.Button button1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel right;
    private javax.swing.JLabel userID;
    private javax.swing.JComboBox<String> userType;
    private javax.swing.JTextField username;
    private javax.swing.JLabel wrong;
    // End of variables declaration//GEN-END:variables
}
