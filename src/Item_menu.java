
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
public final class Item_menu extends javax.swing.JInternalFrame {

    /**
     * Creates new form MainDashboard
     */
    public static String USERNAME,userRole;
    
    public Item_menu() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        permissions();
        try
        {
 
            Item_view_list view = new Item_view_list ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

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
                    break;
                default:
                    removeProduct.setVisible(false);
                    break;
            }
        }
        catch(Exception e){
            System.out.println("Permission check Faild");
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
        WindowOpener = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        viewAll = new javax.swing.JButton();
        Additems = new javax.swing.JButton();
        removeProduct = new javax.swing.JButton();
        Dashboard13 = new javax.swing.JButton();
        Dashboard14 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WindowOpener.setPreferredSize(new java.awt.Dimension(1518, 745));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1610, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );

        WindowOpener.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout WindowOpenerLayout = new javax.swing.GroupLayout(WindowOpener);
        WindowOpener.setLayout(WindowOpenerLayout);
        WindowOpenerLayout.setHorizontalGroup(
            WindowOpenerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        WindowOpenerLayout.setVerticalGroup(
            WindowOpenerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(WindowOpener, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 1610, 745));

        viewAll.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        viewAll.setForeground(new java.awt.Color(51, 51, 51));
        viewAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons/icons8_view_32px_1.png"))); // NOI18N
        viewAll.setText("View All");
        viewAll.setContentAreaFilled(false);
        viewAll.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        viewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllActionPerformed(evt);
            }
        });
        jPanel1.add(viewAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, -1));

        Additems.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Additems.setForeground(new java.awt.Color(51, 51, 51));
        Additems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons/icons8_add_32px.png"))); // NOI18N
        Additems.setText("Add new product");
        Additems.setContentAreaFilled(false);
        Additems.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Additems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdditemsActionPerformed(evt);
            }
        });
        jPanel1.add(Additems, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, -1));

        removeProduct.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        removeProduct.setForeground(new java.awt.Color(51, 51, 51));
        removeProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons/icons8_delete_receipt_32px.png"))); // NOI18N
        removeProduct.setText("Remove product");
        removeProduct.setContentAreaFilled(false);
        removeProduct.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removeProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProductActionPerformed(evt);
            }
        });
        jPanel1.add(removeProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        Dashboard13.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Dashboard13.setForeground(new java.awt.Color(51, 51, 51));
        Dashboard13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons/icons8_update_32px.png"))); // NOI18N
        Dashboard13.setText("Update Product");
        Dashboard13.setContentAreaFilled(false);
        Dashboard13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Dashboard13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dashboard13ActionPerformed(evt);
            }
        });
        jPanel1.add(Dashboard13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, -1, -1));

        Dashboard14.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Dashboard14.setForeground(new java.awt.Color(51, 51, 51));
        Dashboard14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons/icons8_view_details_32px_1.png"))); // NOI18N
        Dashboard14.setText("View product details");
        Dashboard14.setContentAreaFilled(false);
        Dashboard14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Dashboard14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dashboard14ActionPerformed(evt);
            }
        });
        jPanel1.add(Dashboard14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllActionPerformed
        // TODO add your handling code here:
        try
        {

            Item_view_list view = new Item_view_list ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_viewAllActionPerformed

    private void AdditemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdditemsActionPerformed
        // TODO add your handling code here:
        try
        {

            Item_add_new view = new Item_add_new ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_AdditemsActionPerformed

    private void removeProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeProductActionPerformed
        // TODO add your handling code here:
        try
        {
            Item_remove_menu view = new Item_remove_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_removeProductActionPerformed

    private void Dashboard13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dashboard13ActionPerformed
        // TODO add your handling code here:
        try
        {
            Item_update_menu view = new Item_update_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_Dashboard13ActionPerformed

    private void Dashboard14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dashboard14ActionPerformed
        // TODO add your handling code here:
        
        try
        {

            Item_view_menu view = new Item_view_menu ();
            WindowOpener.removeAll();
            WindowOpener.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_Dashboard14ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Additems;
    private javax.swing.JButton Dashboard13;
    private javax.swing.JButton Dashboard14;
    private javax.swing.JDesktopPane WindowOpener;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton removeProduct;
    private javax.swing.JButton viewAll;
    // End of variables declaration//GEN-END:variables
}
