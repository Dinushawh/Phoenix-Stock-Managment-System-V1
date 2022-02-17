
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public final class Customer_view extends javax.swing.JInternalFrame {

    /**
     * Creates new form customer_view
     */
    
    public static String customerid;
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public String invoiceNO;
    public double TOT;
    
    public Customer_view() {
        
        
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
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        
        jScrollPane2.getViewport().setBackground(Color.WHITE);
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        
       
        
        con = Database_connection_CLASS.connection();
        setDetails();
        transactionAmmount();
        checkAvailableInvoice();

    }
    
    public void checkAvailableInvoice(){
    
        try{
            boolean loopcheck = false;
            stmt = con.createStatement();

            String query = "SELECT * FROM invoice_details ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                if (customerid.equals(rs.getString(6)))
                {
                    loopcheck = true;
                    
                    ViewAll();

                    DefaultTableModel dtm = (DefaultTableModel) invoice.getModel();
                    String invoice_no = (dtm.getValueAt(1, 1).toString());
                    this.invoiceNO = invoice_no;
                    viewItems();

                }
            }
            if(!loopcheck){

                jLabel5.setVisible(false);
                jPanel4.setVisible(false);
                jLabel7.setText("No invoices yet");
                }
        
        }
        catch(SQLException e){
        
            
        }
        
    }
    
    public void setDetails(){
        
        try{
            
        con = Database_connection_CLASS.connection();
        stmt = con.createStatement();

        String query = "SELECT * FROM customer ";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
        {
            if (customerid.equals(rs.getString(2)))
            {
                fullname.setText(rs.getString(5)+ " "+rs.getString(6));
                cr.setText(rs.getString(12));
                email.setText(rs.getString(13));
                phone.setText(rs.getString(7));
                ad1.setText(rs.getString(8) + " "+rs.getString(9));
                ad4.setText(rs.getString(11));
                ad5.setText(rs.getString(10));
                ad6.setText(rs.getString(12));

            }
        }
        
        }
        catch(SQLException e){
        
            System.out.println("Error Showing data");
        }

    }
    public void transactionAmmount(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM invoice_details ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                if (customerid.equals(rs.getString(6)))
                {
                  double getDate =  rs.getDouble(10);
                  TOT = TOT + getDate;
                }
            }
            transaction.setText(Double.toString(TOT));

        }
        catch(SQLException e){
        
            System.out.println("Error calculation");
        }
    }
    
    public ArrayList<Customer_invoices_CLASS> VIEWINVOICE (String ValToSearch)
    {
        ArrayList<Customer_invoices_CLASS> viewinvoice = new ArrayList<Customer_invoices_CLASS>();
        con = Database_connection_CLASS.connection();
        try{
        
            
            stmt = con.createStatement();
            String searchQuery = "SELECT * FROM `invoice_details` WHERE CONCAT (`customer_id`,`invoice_number`, `date`, `invoice_total`) LIKE '%"+ValToSearch+"%'";

            rs = stmt.executeQuery(searchQuery);
            Customer_invoices_CLASS displayInvoice;

            while(rs.next())
            { 
                            
                displayInvoice = new Customer_invoices_CLASS(

                        rs.getString("invoice_number"),
                        rs.getString("date"),
                        rs.getString("invoice_total")

                                );
                viewinvoice.add(displayInvoice);

            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Error");
        }
        
        return viewinvoice;
        
    }
    
    public void ViewAll()
    {


        ArrayList<Customer_invoices_CLASS> UH = VIEWINVOICE (customerid);
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(new Object[]{"No","Invoice Number","Date","Total"});

        Object[] row = new Object[4];
        
        for(int i = 0; i < UH.size(); i++)
        {
            int b = i;b = b+1;
            row[0] = b;
            row[1] = UH.get(i).getInvoiceNo();
            row[2] = UH.get(i).getDate();
            row[3] = UH.get(i).getTotal();
            
            model.addRow(row);
        }
       invoice.setModel(model);
       
    }
    
    public void selceCell(){
    
         try{

            int SelectedRow = invoice.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel) invoice.getModel();
            
            String invoice_no = (dtm.getValueAt(SelectedRow, 1).toString());
            this.invoiceNO = invoice_no;
         }
         
         
         catch(Exception e){
          
            System.out.println("Error cell select");
             
         }
    }
    
    public ArrayList<Customer_purchesed_itms_CLASS> VIEWITMS (String ValToSearch)
    {
        ArrayList<Customer_purchesed_itms_CLASS> viewitms = new ArrayList<Customer_purchesed_itms_CLASS>();
        con = Database_connection_CLASS.connection();
        try{
        
            
            stmt = con.createStatement();
            String searchQuery = "SELECT * FROM `invoice_item` WHERE CONCAT (`invoice_no`,`item_name`,`quantity`) LIKE '%"+ValToSearch+"%'";

            rs = stmt.executeQuery(searchQuery);
            Customer_purchesed_itms_CLASS displayitems;

            while(rs.next())
            { 
                            
                displayitems = new Customer_purchesed_itms_CLASS(

                        rs.getString("item_name"),
                        rs.getString("quantity")
    
                                );
                viewitms.add(displayitems);

            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Error");
        }
        
        return viewitms;
        
    }
    
    public void viewItems()
    {


        ArrayList<Customer_purchesed_itms_CLASS> UH = VIEWITMS (invoiceNO);
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(new Object[]{"No","Product name","Quntity"});

        Object[] row = new Object[4];
        
        for(int i = 0; i < UH.size(); i++)
        {
            int b = i;b = b+1;
            row[0] = b;
            row[1] = UH.get(i).getItmName();
            row[2] = UH.get(i).getQuntity();

            model.addRow(row);
        }
       jTable1.setModel(model);
       
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
        cr = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ad1 = new javax.swing.JLabel();
        ad4 = new javax.swing.JLabel();
        ad5 = new javax.swing.JLabel();
        ad6 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        transaction = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        invoice = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cr.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cr.setText("Sri lanka");
        jPanel1.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 410, 30));

        fullname.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        fullname.setForeground(new java.awt.Color(0, 102, 102));
        fullname.setText("Dinusha Weerakoon");
        jPanel1.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 410, 40));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Email");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Phone number");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Address");

        ad1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        ad1.setText("Company");

        ad4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        ad4.setText("Company");

        ad5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        ad5.setText("Company");

        ad6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        ad6.setText("Company");

        email.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        email.setText("Company");

        phone.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        phone.setText("Company");

        transaction.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        transaction.setForeground(new java.awt.Color(0, 102, 102));
        transaction.setText("00.00");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Transactions");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ad6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ad5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ad4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ad1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ad1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ad4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ad5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ad6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Overview   ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(35);
        jTable1.setSelectionBackground(new java.awt.Color(0, 102, 102));
        jScrollPane1.setViewportView(jTable1);

        invoice.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        invoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        invoice.setRowHeight(35);
        invoice.setSelectionBackground(new java.awt.Color(0, 102, 102));
        invoice.getTableHeader().setReorderingAllowed(false);
        invoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoiceMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(invoice);
        if (invoice.getColumnModel().getColumnCount() > 0) {
            invoice.getColumnModel().getColumn(0).setResizable(false);
            invoice.getColumnModel().getColumn(1).setResizable(false);
            invoice.getColumnModel().getColumn(2).setResizable(false);
            invoice.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Invoice");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 451, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Purchesed Items");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 31, 933, -1));

        jTabbedPane1.addTab("   Purchase history   ", jPanel3);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1530, 460));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1630, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void invoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceMouseClicked
        // TODO add your handling code here:
        try{
        
            selceCell();
            viewItems();
            System.out.println(invoiceNO);
            
        }
        catch(Exception e){
        
            System.out.println("Error click");
        }
    }//GEN-LAST:event_invoiceMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ad1;
    private javax.swing.JLabel ad4;
    private javax.swing.JLabel ad5;
    private javax.swing.JLabel ad6;
    private javax.swing.JLabel cr;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fullname;
    private javax.swing.JTable invoice;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel transaction;
    // End of variables declaration//GEN-END:variables
}
