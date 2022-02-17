
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
public class Factory_reset extends javax.swing.JInternalFrame {

    /**
     * Creates new form Admin_data_reset
     */
    public static String userID,loopCheck;
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    
    public Factory_reset() {
        initComponents();
        
        con = Database_connection_CLASS.connection();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } 
        catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
    }
    public void getPasswordvalidation (){
        
        try{
            con = Database_connection_CLASS.connection();
            int res = JOptionPane.showOptionDialog(null, "This action cannot be undone. Are you sure you want to delete Invoice item history?", "WARNING!",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
            new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                con = Database_connection_CLASS.connection();
                boolean loopcheck = false;
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter a password : ");
                JPasswordField pass = new JPasswordField(10);
                panel.add(label);
                panel.add(pass);
                int action = JOptionPane.showConfirmDialog(null, panel,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    String id = userID;
                    String passwd = new String(pass.getPassword());
                    stmt = con.createStatement();
                    String query = "SELECT * FROM users ";
                    ResultSet rs = stmt.executeQuery(query);
                    while(rs.next())
                    {
                        if (id.equals(rs.getString(2)) && passwd.equals(rs.getString(7))){
                            loopcheck = true;
                            loopCheck = "1"; 
                        }
                    }
                    if(!loopcheck){
                        loopCheck ="2";
                        JOptionPane.showMessageDialog (null, "Incorrect Try Again!", "FAILD!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                     JOptionPane.showMessageDialog(null,"Canselled ");
                }
            }
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error Delete invoice details");
        }
    }
    
    public void deleteAllinvoiceInformation (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            System.out.println("2");
            String Query1 = "DELETE FROM `invoice_details`;";
            String Query2 = "DELETE FROM `invoice_details_history`;";
            String Query3 = "DELETE FROM `invoice_item`;";
            String Query4 = "DELETE FROM `invoice_item_history`;";
            String Query5 = "DELETE FROM `invoice_reverse_info`;";
            String Query6 = "DELETE FROM `invoice_update_info`;";
            stmt.addBatch(Query1);
            stmt.addBatch(Query2); 
            stmt.addBatch(Query3); 
            stmt.addBatch(Query4); 
            stmt.addBatch(Query5); 
            stmt.addBatch(Query6); 
            stmt.executeBatch();
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error deleting invoice data");
        }
    }
    public void deleteAllCustomerInformation (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            System.out.println("2");
            String Query1 = "DELETE FROM `customer`;";
            String Query2 = "DELETE FROM `customer_history`;";
            String Query3 = "DELETE FROM `customer_update_info`;";
            stmt.addBatch(Query1);
            stmt.addBatch(Query2); 
            stmt.addBatch(Query3); 
            stmt.executeBatch();
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error deleting invoice data");
        }
    }
    public void deleteAllSupplierInformation (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            System.out.println("2");
            String Query1 = "DELETE FROM `suppliers`;";
            String Query2 = "DELETE FROM `supplier_update_history`;";
            String Query3 = "DELETE FROM `supplier_update_info`;";
            stmt.addBatch(Query1);
            stmt.addBatch(Query2); 
            stmt.addBatch(Query3); 
            stmt.executeBatch();
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error deleting invoice data");
        }
    }
    public void deleteAllProductInformation (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            System.out.println("2");
            String Query1 = "DELETE FROM `products`;";
            String Query2 = "DELETE FROM `products_history`;";
            String Query3 = "DELETE FROM `products_update_info`;";
            stmt.addBatch(Query1);
            stmt.addBatch(Query2); 
            stmt.addBatch(Query3); 
            stmt.executeBatch();
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error deleting invoice data");
        }
    }
    
    public void deleteAllResentActivities (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            System.out.println("2");
            String Query1 = "DELETE FROM `activity`;";
            stmt.addBatch(Query1);
            stmt.executeBatch();
            
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error deleting invoice data");
        }
    }
    
     public void deleteAllSystemusers (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String Query1 = "DELETE FROM `users` WHERE  NOT `user_role` = ?;";
            PreparedStatement preparedStmt3 = con.prepareStatement(Query1);
            preparedStmt3.setString(1, "master");
            preparedStmt3.execute();
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error deleting invoice data");
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
        jLabel2 = new javax.swing.JLabel();
        button2 = new accessories.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel2.setText("Remove all the informations including");

        button2.setBackground(new java.awt.Color(255, 0, 51));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Reset");
        button2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Factory reset");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel3.setText("*System user informations");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel4.setText("*Customer informations");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel6.setText("*Supplier informations");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel7.setText("This action cannont be undone!");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("WARNING!");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel9.setText("*Product informations");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(511, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        try{
            getPasswordvalidation();
            if("1".equals(loopCheck)){
                System.out.println("OK");
                deleteAllSystemusers();
                deleteAllResentActivities();
                deleteAllProductInformation();
                deleteAllSupplierInformation();
                deleteAllCustomerInformation();
                deleteAllinvoiceInformation();
                JOptionPane.showMessageDialog (null, "System successfully rest", "successful", JOptionPane.INFORMATION_MESSAGE); 
            }
        }
        catch(HeadlessException e){
            System.out.println("error deleting invoice data :btn");
        }
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private accessories.Button button2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    
}
