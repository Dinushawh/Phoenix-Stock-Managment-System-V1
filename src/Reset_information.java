
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
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
import javax.swing.UnsupportedLookAndFeelException;
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
public final class Reset_information extends javax.swing.JInternalFrame {

    /**
     * Creates new form Admin_data_reset
     */
    public static String userID,userRole,loopCheck ;
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    
    public Reset_information() {
        initComponents();
        
        con = Database_connection_CLASS.connection();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
            UIManager.put( "jTabbedPane1.selectedBackground", Color.white );
        } 
        catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        UIManager.put( "TabbedPane.selectedBackground", Color.white );
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        permissions();
    }
    
    public void permissions(){
    
        try{
            if(null == userRole){
  
            }
            else switch (userRole) {
                case "admin":
                    jTabbedPane1.remove(jPanel2);
                    break;
                case "master":
                    break;
                default:

                    break;
            }
        }
        catch(Exception e){
            System.out.println("Permission check Faild");
        }
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
            JOptionPane.showMessageDialog (null, "Invoice details are successfully deleted", "successful", JOptionPane.INFORMATION_MESSAGE); 
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
            JOptionPane.showMessageDialog (null, "customer details are successfully deleted", "successful", JOptionPane.INFORMATION_MESSAGE); 
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
            JOptionPane.showMessageDialog (null, "Supplier details are successfully deleted", "successful", JOptionPane.INFORMATION_MESSAGE); 
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
            JOptionPane.showMessageDialog (null, "Product details are successfully deleted", "successful", JOptionPane.INFORMATION_MESSAGE); 
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
            JOptionPane.showMessageDialog (null, "Resent activities are successfully deleted", "successful", JOptionPane.INFORMATION_MESSAGE); 
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
            JOptionPane.showMessageDialog (null, "System user details are successfully deleted without system ower", "successful", JOptionPane.INFORMATION_MESSAGE); 
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        removeInvoice = new accessories.Button();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        deleteCustomer = new accessories.Button();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        removeSuppliers = new accessories.Button();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        removeProducts = new accessories.Button();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        removeResent = new accessories.Button();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        button2 = new accessories.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        removeInvoice.setBackground(new java.awt.Color(255, 0, 51));
        removeInvoice.setForeground(new java.awt.Color(255, 255, 255));
        removeInvoice.setText("Delete");
        removeInvoice.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        removeInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeInvoiceActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel3.setText("Remove all invoice information including currnt invoices, reversed invoice informations, updated and deleted invoice informations");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Remove invoice informations");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(609, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Remove invoice informations  ", jPanel7);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        deleteCustomer.setBackground(new java.awt.Color(255, 0, 51));
        deleteCustomer.setForeground(new java.awt.Color(255, 255, 255));
        deleteCustomer.setText("Delete");
        deleteCustomer.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        deleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel4.setText("Remove all customer information including customer details ");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Remove customer informations");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(609, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove customer informations", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        removeSuppliers.setBackground(new java.awt.Color(255, 0, 51));
        removeSuppliers.setForeground(new java.awt.Color(255, 255, 255));
        removeSuppliers.setText("Delete");
        removeSuppliers.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        removeSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSuppliersActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel8.setText("Remove all customer information including customer details ");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Remove supplier informations");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(609, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove supplier informations", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        removeProducts.setBackground(new java.awt.Color(255, 0, 51));
        removeProducts.setForeground(new java.awt.Color(255, 255, 255));
        removeProducts.setText("Delete");
        removeProducts.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        removeProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProductsActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel10.setText("Remove all product information ");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Remove product informations");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(609, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove product informations", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        removeResent.setBackground(new java.awt.Color(255, 0, 51));
        removeResent.setForeground(new java.awt.Color(255, 255, 255));
        removeResent.setText("Delete");
        removeResent.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        removeResent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeResentActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel12.setText("Remove all resent activity ion the system");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Remove resent activities");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeResent, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeResent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(609, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove resent activities  ", jPanel6);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        button2.setBackground(new java.awt.Color(255, 0, 51));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Delete");
        button2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel2.setText("Remove all customer information including customer details ");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Remove system user informations");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(609, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove system user informations", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
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
            }
        }
        catch(HeadlessException e){
            System.out.println("error deleting invoice data :btn");
        }
       
    }//GEN-LAST:event_button2ActionPerformed

    private void removeInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeInvoiceActionPerformed
        // TODO add your handling code here:
        try{
            getPasswordvalidation();
            if("1".equals(loopCheck)){
                System.out.println("OK");
                deleteAllinvoiceInformation ();
            }
        }
        catch(HeadlessException e){
            System.out.println("error deleting invoice data :btn");
        }
    }//GEN-LAST:event_removeInvoiceActionPerformed

    private void deleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerActionPerformed
        // TODO add your handling code here:
        try{
            getPasswordvalidation();
            if("1".equals(loopCheck)){
                System.out.println("OK");
                deleteAllCustomerInformation ();
            }
        }
        catch(HeadlessException e){
            System.out.println("error deleting invoice data :btn");
        }
    }//GEN-LAST:event_deleteCustomerActionPerformed

    private void removeSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSuppliersActionPerformed
        // TODO add your handling code here:
        try{
            getPasswordvalidation();
            if("1".equals(loopCheck)){
                System.out.println("OK");
                deleteAllSupplierInformation();
            }
        }
        catch(HeadlessException e){
            System.out.println("error deleting invoice data :btn");
        }
    }//GEN-LAST:event_removeSuppliersActionPerformed

    private void removeProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeProductsActionPerformed
        // TODO add your handling code here:
        try{
            getPasswordvalidation();
            if("1".equals(loopCheck)){
                System.out.println("OK");
                deleteAllProductInformation();
            }
        }
        catch(HeadlessException e){
            System.out.println("error deleting invoice data :btn");
        }
    }//GEN-LAST:event_removeProductsActionPerformed

    private void removeResentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeResentActionPerformed
        // TODO add your handling code here:
        try{
            getPasswordvalidation();
            if("1".equals(loopCheck)){
                System.out.println("OK");
                deleteAllResentActivities();
            }
        }
        catch(HeadlessException e){
            System.out.println("error deleting invoice data :btn");
        }
    }//GEN-LAST:event_removeResentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private accessories.Button button2;
    private accessories.Button deleteCustomer;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private accessories.Button removeInvoice;
    private accessories.Button removeProducts;
    private accessories.Button removeResent;
    private accessories.Button removeSuppliers;
    // End of variables declaration//GEN-END:variables

    
}
