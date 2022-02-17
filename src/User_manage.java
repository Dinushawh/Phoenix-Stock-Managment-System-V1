import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public final class User_manage extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_supplier_delete_step
     */
    
    public static String selectedUserid,userid,FNA,userRole;
    public int count;
    public double tot;
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public String sReference;
    public User_manage() {
        initComponents();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        UIManager.put( "TabbedPane.selectedBackground", Color.white );
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);

        con = Database_connection_CLASS.connection();
        
        setData();
        setinvoiceData ();
        permissions();
        
    }
    
    public void permissions(){
    
        try{
            if(null == userRole){
  
            }
            else switch (userRole) {
                case "admin":
//                    jTabbedPane1.remove(permission);
                    jLabel7.setVisible(false);
                    jComboBox1.setVisible(false);
                    button1.setVisible(false);
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

  
    public void setData(){
    
        try{
            
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM users ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                if (selectedUserid.equals(rs.getString(2)))
                {
                    fullname.setText(rs.getString(3)+ " "+rs.getString(4));
                    cr.setText(rs.getString(8) + " | "+rs.getString(5));
                }
            }

        }
        catch(SQLException e){
        
            System.out.println("Error Showing data");
        }
    }
    
    public void setinvoiceData (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM invoice_details ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                if (selectedUserid.equals(rs.getString(2)))
                {
                    count++;
                    double dbTOT = rs.getDouble(10);
                    tot = tot + dbTOT;
                }
                
            }
            noInvoice.setText(String.valueOf(count));
            invoiceTOT.setText(String.valueOf(tot));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    
//    public void genarateReference(){
//    
//        try {
//            con = database_connection.connection();
//            String itemidset = null;
//            stmt = con.createStatement();
//            ResultSet rsd = stmt.executeQuery("SELECT MAX(`reference`) FROM `supplier_update_info`");
//            rsd.next();
//            rsd.getString("MAX(`reference`)");
//            if (rsd.getString("MAX(`reference`)") == null) {
//                itemidset = "SREF00001";
//                
//                this.sReference = itemidset;
//                System.out.println(sReference);
//            } 
//            else {
//                long id = Long.parseLong(rsd.getString("MAX(`reference`)").substring(4, rsd.getString("MAX(`reference`)").length()));
//                id++;
//                itemidset = ("SREF" + String.format("%05d", id));
//                
//                this.sReference = itemidset;
//                System.out.println(sReference);
//                }
//            } 
//        catch (NumberFormatException | SQLException e) {
//            System.out.println(e);
//        } 
//    }
//    
//    public void refUpdateandMove (){
//    
//        try{
//            con = database_connection.connection();
//            stmt = con.createStatement();
//            String ref = sReference;
//            System.out.println(ref + "  "+ supplierID);
//            String Query = "UPDATE `suppliers` SET `reference` = ? WHERE `supplier_id` = ?;";
//            PreparedStatement preparedStmt = con.prepareStatement(Query);
//            preparedStmt.setString(1, ref);
//            preparedStmt.setString(2, supplierID);
//            preparedStmt.execute();
//            
//            String Query1 = "INSERT INTO `supplier_update_history`(`created_date`, `created_user`, `supplier_id`, `first_name`, `last_name`, `company_name`, `email`, `phone_number`, `website`, `address_line_1`, `address_line_2`, `city`, `zip_code`, `country`, `reference`) "
//                    + "SELECT `date_created`, `created_user`, `supplier_id`, `first_name`, `last_name`, `company_name`, `email`, `phone_number`, `website`, `address_line_1`, `address_line_2`, `city`, `zip_code`, `country`, `reference` FROM `suppliers` WHERE `supplier_id` = ? ;";
//            
//            PreparedStatement preparedStmt2 = con.prepareStatement(Query1);
//            preparedStmt2.setString(1, supplierID);   
//            preparedStmt2.execute();
//            
//            String stat = "Deleted";
//            String Query2 = "UPDATE `supplier_update_history` SET `status` = ? WHERE `supplier_id` = ? AND `reference` =? ;";
//            PreparedStatement preparedStmt3 = con.prepareStatement(Query2);
//            preparedStmt3.setString(1, stat);
//            preparedStmt3.setString(2, supplierID);
//            preparedStmt3.setString(3, sReference);
//            preparedStmt3.execute();
//        
//        }
//        catch(SQLException e){
//        
//            System.out.println("Error reference update and move");
//        }
//    }   
//    public void updateInformationUpdate(){
//    
//        try{
//            String sid = supplierID;
//            String uid = userid;
//            String ref = sReference;
//            con = database_connection.connection();
//            stmt = con.createStatement();
//            Date date2 = Calendar.getInstance().getTime();  
//            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  
//            String strDate = dateFormat.format(date2);  
//            
//            System.out.println(supplierID + userid + sReference +strDate );
//        
//            String Query ="INSERT INTO `supplier_update_info`(`supplier_id`, `updated_user`,`time`, `reference`) VALUES (?,?,?,?)";
//            PreparedStatement preparedStmt = con.prepareStatement(Query);
//            preparedStmt.setString(1, sid);
//            preparedStmt.setString(2, uid);
//            preparedStmt.setString(3, strDate);
//            preparedStmt.setString(4, ref);
//            preparedStmt.execute();
//        }
//        catch(SQLException e){
//    
//            System.out.println("Error updating update information");
//        
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cr = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        noInvoice = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        invoiceTOT = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        permission = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        button1 = new accessories.Button();
        jLabel9 = new javax.swing.JLabel();
        button2 = new accessories.Button();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        button3 = new accessories.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cr.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cr.setText("Sri lanka");
        jPanel1.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1140, 30));

        fullname.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        fullname.setForeground(new java.awt.Color(0, 102, 102));
        fullname.setText("Dinusha Weerakoon");
        jPanel1.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -10, 1090, 60));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Number of invoice issued");

        noInvoice.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        noInvoice.setForeground(new java.awt.Color(51, 51, 51));
        noInvoice.setText("200");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Transactions ");

        invoiceTOT.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        invoiceTOT.setForeground(new java.awt.Color(51, 51, 51));
        invoiceTOT.setText("RS.900000");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("The amount of work done on the software");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(invoiceTOT, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(392, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noInvoice)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invoiceTOT)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Activities   ", jPanel2);

        permission.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Permission of the user");

        button1.setBackground(new java.awt.Color(255, 0, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Save");
        button1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("in case of user forgot their password administration can reset it to defaults");

        button2.setBackground(new java.awt.Color(255, 0, 51));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Reset password to default");
        button2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        javax.swing.GroupLayout permissionLayout = new javax.swing.GroupLayout(permission);
        permission.setLayout(permissionLayout);
        permissionLayout.setHorizontalGroup(
            permissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permissionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(permissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(permissionLayout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(permissionLayout.createSequentialGroup()
                        .addGroup(permissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 392, Short.MAX_VALUE))))
        );
        permissionLayout.setVerticalGroup(
            permissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permissionLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(permissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(permissionLayout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Permissions and security  ", permission);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("in case of user forgot their password administration can reset it to defaults");

        button3.setBackground(new java.awt.Color(255, 0, 51));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Delete account");
        button3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(392, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Account delete   ", jPanel5);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 1260, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private accessories.Button button1;
    private accessories.Button button2;
    private accessories.Button button3;
    private javax.swing.JLabel cr;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel invoiceTOT;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel noInvoice;
    private javax.swing.JPanel permission;
    // End of variables declaration//GEN-END:variables
}
