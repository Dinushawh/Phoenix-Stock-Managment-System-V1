import com.formdev.flatlaf.FlatIntelliJLaf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public final class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form AdminPanel
     */
    
    public static String USERNAME,USERROLE;

    static void Refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    
    public Dashboard() {
        initComponents();
        
        /* connect database */
        con = Database_connection_CLASS.connection();
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } 
        catch( UnsupportedLookAndFeelException ex ) {
            
            System.err.println( "Failed to initialize LaF" );
        }
        setValues();
        Dashboard_menu.USERNAME = USERNAME;
        permissions();
        sendRole();
        
        Dashboard_menu view = new Dashboard_menu ();
        WindowOpener.removeAll();
        WindowOpener.add(view).setVisible(true);

    }
    
     public void setValues(){
    
        try
        {
            stmt = con.createStatement();
            String query = "SELECT * FROM users";
            ResultSet rsd = stmt.executeQuery(query);
            
            while(rsd.next())
            {
                if(USERNAME.equals(rsd.getString(6)))
                {
                    String ID = (rsd.getString(2));
                    id.setText("Profile / "+rsd.getString(2)+" "+USERROLE);
                    Item_add_new.userid = ID;
                    Supplier_add_new.userid = ID;
                    Profile_menu.passedusername = ID;
                    Invoice_menu.USERNAME = ID;
                    Settings.USERNAME2 = ID;
                    Supplier_menu.USERNAME =ID;
                    Supplier_remove.userid = ID;
                    Item_remove.userid = ID;
                    Item_update.userid= ID;
                    Customer_add_new.userid= ID;
                    Customer_update.userid = ID;
                    Customer_remove.userid = ID;
                    User_manage_menu.userid = ID;
                    Reset_information.userID = ID;
                    Factory_reset.userID = ID;
                    Profile_delete.userid = ID;
                    
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
    }
    
    public void permissions(){
    
        try{
            if(null == USERROLE){
  
            }
            else switch (USERROLE) {
                case "admin":
                    break;
                case "master":
                    break;
                default:
                    reports.setVisible(false);
                    settingsPage.setVisible(false);
                    break;
            }
        }
        catch(Exception e){
            System.out.println("Permission check Faild");
        }
    }
    
    public void sendRole(){
    
        Item_menu.userRole = USERROLE;
        Item_update.userRole = USERROLE;
        Supplier_menu.userRole = USERROLE;
        Supplier_update.userRole = USERROLE;
        Customer_menu.userRole = USERROLE;
        Customer_update.userRole = USERROLE;
        Profile_menu.userRole = USERROLE;
        Profile_settings.userRole = USERROLE;
        User_add_new.userRole = USERROLE;
        User_manage.userRole = USERROLE;
        Settings.userRole = USERROLE;
        Reset_information.userRole = USERROLE;
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
        dashBoard = new javax.swing.JButton();
        viewAll = new javax.swing.JButton();
        viewAll2 = new javax.swing.JButton();
        viewAll4 = new javax.swing.JButton();
        customer = new javax.swing.JButton();
        reports = new javax.swing.JButton();
        WindowOpener = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        profile = new javax.swing.JButton();
        CloseBTN = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        Dashboard15 = new javax.swing.JButton();
        settingsPage = new javax.swing.JButton();
        minimize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        dashBoard.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        dashBoard.setForeground(new java.awt.Color(51, 51, 51));
        dashBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/AdminPanel/icons8_dashboard_32px_4.png"))); // NOI18N
        dashBoard.setText("Dashboard");
        dashBoard.setContentAreaFilled(false);
        dashBoard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dashBoard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashBoardActionPerformed(evt);
            }
        });

        viewAll.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        viewAll.setForeground(new java.awt.Color(51, 51, 51));
        viewAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_product_32px_1.png"))); // NOI18N
        viewAll.setText("Items");
        viewAll.setContentAreaFilled(false);
        viewAll.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        viewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllActionPerformed(evt);
            }
        });

        viewAll2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        viewAll2.setForeground(new java.awt.Color(51, 51, 51));
        viewAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_supplier_32px_1.png"))); // NOI18N
        viewAll2.setText("Suppliers");
        viewAll2.setContentAreaFilled(false);
        viewAll2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        viewAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAll2ActionPerformed(evt);
            }
        });

        viewAll4.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        viewAll4.setForeground(new java.awt.Color(51, 51, 51));
        viewAll4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_bill_32px.png"))); // NOI18N
        viewAll4.setText("Invoice");
        viewAll4.setContentAreaFilled(false);
        viewAll4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        viewAll4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAll4ActionPerformed(evt);
            }
        });

        customer.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        customer.setForeground(new java.awt.Color(51, 51, 51));
        customer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_Three_People_32px.png"))); // NOI18N
        customer.setText("Customers");
        customer.setContentAreaFilled(false);
        customer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerActionPerformed(evt);
            }
        });

        reports.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        reports.setForeground(new java.awt.Color(51, 51, 51));
        reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_slice_32px.png"))); // NOI18N
        reports.setText("Reports");
        reports.setContentAreaFilled(false);
        reports.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewAll4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewAll2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reports, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(dashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewAll4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewAll2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reports, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 190, 750));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        WindowOpener.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout WindowOpenerLayout = new javax.swing.GroupLayout(WindowOpener);
        WindowOpener.setLayout(WindowOpenerLayout);
        WindowOpenerLayout.setHorizontalGroup(
            WindowOpenerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        WindowOpenerLayout.setVerticalGroup(
            WindowOpenerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(WindowOpener, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 1610, 800));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Public/main2-01.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 330, 60));

        id.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        id.setForeground(new java.awt.Color(102, 102, 102));
        id.setText("jLabel26");
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 390, -1));

        profile.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        profile.setForeground(new java.awt.Color(51, 51, 51));
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/AdminPanel/icons8_contacts_32px.png"))); // NOI18N
        profile.setContentAreaFilled(false);
        profile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileActionPerformed(evt);
            }
        });
        jPanel1.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 20, -1, 49));

        CloseBTN.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        CloseBTN.setForeground(new java.awt.Color(51, 51, 51));
        CloseBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/AdminPanel/icons8_multiply_32px_1.png"))); // NOI18N
        CloseBTN.setContentAreaFilled(false);
        CloseBTN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CloseBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseBTNActionPerformed(evt);
            }
        });
        jPanel1.add(CloseBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1720, 20, -1, 49));

        Logout.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Logout.setForeground(new java.awt.Color(51, 51, 51));
        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/AdminPanel/icons8_shutdown_32px.png"))); // NOI18N
        Logout.setContentAreaFilled(false);
        Logout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        jPanel1.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 20, -1, 49));

        Dashboard15.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Dashboard15.setForeground(new java.awt.Color(51, 51, 51));
        Dashboard15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/AdminPanel/icons8_help_32px_1.png"))); // NOI18N
        Dashboard15.setContentAreaFilled(false);
        Dashboard15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Dashboard15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dashboard15ActionPerformed(evt);
            }
        });
        jPanel1.add(Dashboard15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 20, -1, 49));

        settingsPage.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        settingsPage.setForeground(new java.awt.Color(51, 51, 51));
        settingsPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/AdminPanel/icons8_settings_32px_3.png"))); // NOI18N
        settingsPage.setContentAreaFilled(false);
        settingsPage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        settingsPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsPageActionPerformed(evt);
            }
        });
        jPanel1.add(settingsPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, -1, 49));

        minimize.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        minimize.setForeground(new java.awt.Color(51, 51, 51));
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/AdminPanel/icons8_subtract_32px.png"))); // NOI18N
        minimize.setContentAreaFilled(false);
        minimize.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeActionPerformed(evt);
            }
        });
        jPanel1.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1660, 20, -1, 49));

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

    private void dashBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashBoardActionPerformed
        // TODO add your handling code here:
        try
        {

            Dashboard_menu view = new Dashboard_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_dashBoardActionPerformed

    private void profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileActionPerformed
        // TODO add your handling code here:
        try
        {

            Profile_menu view = new Profile_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_profileActionPerformed

    private void CloseBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseBTNActionPerformed
        // TODO add your handling code here:
         try
        {
            System.exit(0);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_CloseBTNActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        // TODO add your handling code here:
        try
        {
            this.setVisible(false);
            Login load = new Login();
            load.setVisible(true);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
         
    }//GEN-LAST:event_LogoutActionPerformed

    private void Dashboard15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dashboard15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dashboard15ActionPerformed

    private void settingsPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsPageActionPerformed
        // TODO add your handling code here:
        
        try{
            
            Settings view = new Settings ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);
            
        }
        catch(Exception e){
        }
    }//GEN-LAST:event_settingsPageActionPerformed

    private void viewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllActionPerformed
        // TODO add your handling code here:
        try
        {
            Item_menu view = new Item_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_viewAllActionPerformed

    private void viewAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAll2ActionPerformed
        // TODO add your handling code here:
        try
        {
            Supplier_menu view = new Supplier_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_viewAll2ActionPerformed

    private void viewAll4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAll4ActionPerformed
        // TODO add your handling code here:
        
        try
        {
            Invoice_menu view = new Invoice_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_viewAll4ActionPerformed

    private void customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerActionPerformed
        // TODO add your handling code here:
        try
        {
            Customer_menu view = new Customer_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_customerActionPerformed

    private void minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeActionPerformed
        // TODO add your handling code here:
        try{
        
            this.setExtendedState(Dashboard.ICONIFIED);
        }
        catch(Exception e){
        
           System.out.println("Error Minimixing");
        }
    }//GEN-LAST:event_minimizeActionPerformed

    private void reportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportsActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseBTN;
    private javax.swing.JButton Dashboard15;
    private javax.swing.JButton Logout;
    private javax.swing.JDesktopPane WindowOpener;
    private javax.swing.JButton customer;
    private javax.swing.JButton dashBoard;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton minimize;
    private javax.swing.JButton profile;
    private javax.swing.JButton reports;
    private javax.swing.JButton settingsPage;
    private javax.swing.JButton viewAll;
    private javax.swing.JButton viewAll2;
    private javax.swing.JButton viewAll4;
    // End of variables declaration//GEN-END:variables
}
