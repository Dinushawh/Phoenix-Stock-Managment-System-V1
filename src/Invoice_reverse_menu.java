
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class Invoice_reverse_menu extends javax.swing.JInternalFrame {

    /**
     * Creates new form invoice_reverse_menu
     */
    public static String userid;
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public Invoice_reverse_menu() {
        initComponents();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        con = Database_connection_CLASS.connection();
        errors();
        
    }
    public void errors(){
    
        error_icon.setVisible(false);
        error_icon2.setVisible(false);
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
        search = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        error_icon2 = new javax.swing.JLabel();
        error_icon = new javax.swing.JLabel();
        update = new accessories.Button();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        search.setForeground(new java.awt.Color(0, 102, 102));
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 390, 40));

        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1600, 530));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1600, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1600, 520));

        error_icon2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        error_icon2.setText("jLabel2");
        jPanel1.add(error_icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 540, 50));

        error_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N
        jPanel1.add(error_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 50));

        update.setBackground(new java.awt.Color(255, 0, 0));
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Reverse");
        update.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 140, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/History/reverseInvoice-06.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 450, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1614, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        // TODO add your handling code here:
        try{
            errors();
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_searchKeyPressed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        try{
            errors();
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_searchKeyReleased

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        // TODO add your handling code here:
        try{
            errors();
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_searchKeyTyped

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        try{
            boolean loopcheck = false;
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String invNo = search.getText();
           
            String query = "SELECT * FROM invoice_details ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                if (invNo.equals(rs.getString(3)))
                {
                    String stat = rs.getString(16);
                    if(!"Reversed".equals(stat)){
                        errors();
                        Invoice_reverse.invoice_number = invNo;
                        Invoice_reverse.userid = userid;
                        loopcheck = true;
                        Invoice_reverse view = new Invoice_reverse ();
                        jDesktopPane1.removeAll();
                        jDesktopPane1.add(view).setVisible(true);
                    }
                    else{
                        loopcheck = true;
                        error_icon.setVisible(true);
                        error_icon2.setVisible(true);
                        error_icon2.setText("Invoice Already Reversed!");
                        Empty view = new Empty ();
                        jDesktopPane1.removeAll();
                        jDesktopPane1.add(view).setVisible(true);  
                    }

                }
                
            if(!loopcheck){
                error_icon.setVisible(true);
                error_icon2.setVisible(true);
                error_icon2.setText("No invoices found in system please check invoice number again");
                Empty view = new Empty ();
                jDesktopPane1.removeAll();
                jDesktopPane1.add(view).setVisible(true);
                
                }  
            }
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_updateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel error_icon;
    private javax.swing.JLabel error_icon2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField search;
    private accessories.Button update;
    // End of variables declaration//GEN-END:variables
}
