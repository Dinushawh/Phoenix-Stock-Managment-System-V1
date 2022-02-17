
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
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
public class Invoice_details extends javax.swing.JInternalFrame {

    /**
     * Creates new form Admin_invoiceDetails
     */
    
    public static String invoice_number;
    public static String id;
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public Invoice_details() {
        initComponents();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
            UIManager.put( "TabbedPane.selectedBackground", Color.white );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        UIManager.put( "TabbedPane.selectedBackground", Color.white );
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        

        
        jScrollPane2.getViewport().setBackground(Color.WHITE);
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        

        
        String InvNo = invoice_number;
        this.id = InvNo;
        inv_no.setText(invoice_number);
        con = Database_connection_CLASS.connection();
        try{
            boolean loopcheck = false;
            stmt = con.createStatement();
            String query = "SELECT * FROM invoice_details ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                if (InvNo.equals(rs.getString(3)))
                {
                   inv_no.setText(rs.getString(3));
                   client_id.setText(rs.getString(6));
                   client_name.setText(rs.getString(7));
                   address.setText(rs.getString(8));
                   contact.setText(rs.getString(9));
                   issed_date.setText(rs.getString(4));
                   issued_time.setText(rs.getString(12));
                   due_date.setText(rs.getString(5));
                   payment.setText(rs.getString(11));
                   issued_user.setText(rs.getString(2));
                   subTot.setText(rs.getString(13));
                   subDiscount.setText(rs.getString(14));
                   TotTax.setText(rs.getString(15));
                   finaleAmmount.setText(rs.getString(10));
                   status.setText(rs.getString(16));

                }
            }

        }
        catch(Exception e){
        
        }
        ViewAll();

    }
    
    public ArrayList<Invoice_item_CLASS> invoiceItem (String ValToSearch)
    {
        ArrayList<Invoice_item_CLASS> InvoiceItem = new ArrayList<Invoice_item_CLASS>();

        try{
        
            stmt = con.createStatement();
            String searchQuery = "SELECT * FROM `invoice_item` WHERE CONCAT(`row_id`,`invoice_no`,`item_id`, `item_name`, `unit_price`, `quantity`, "
                    + "`discount`, `tax`, `note`, `net_amount`, `disconted_ammount`, `taxed_ammount`, "
                    + "`finale_ammount`) LIKE '%"+ValToSearch+"%'";
            
            
            rs = stmt.executeQuery(searchQuery);
            
            Invoice_item_CLASS DisplayIntemList;
            
            while(rs.next())
            {
                            
                DisplayIntemList = new Invoice_item_CLASS(

                        rs.getString("row_id"),
                        rs.getString("invoice_no"),
                        rs.getString("item_id"),
                        rs.getString("item_name"),
                        rs.getDouble("unit_price"),
                        rs.getInt("quantity"),
                        rs.getDouble("discount"),
                        rs.getDouble("tax"),
                        rs.getString("note"),
                        rs.getDouble("net_amount"),
                        rs.getDouble("disconted_ammount"),
                        rs.getDouble("taxed_ammount"),
                        rs.getDouble("finale_ammount")

                                );
                InvoiceItem.add(DisplayIntemList);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return InvoiceItem;
    }
    
    public void ViewAll()
    {

        ArrayList<Invoice_item_CLASS> InvoiceItem = invoiceItem(invoice_number);
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(new Object[]{"No","Item Id","Item name","Unit price","Quntity","Discount","Tax","Note",
            "Net Ammount","Discounted Ammount","Taxed Ammount","Finale Ammount"});

        Object[] row = new Object[12];
        
        for(int i = 0; i < InvoiceItem.size(); i++)
        {
            row[0] = InvoiceItem.get(i).getRowNo();
            row[1] = InvoiceItem.get(i).getItemID();
            row[2] = InvoiceItem.get(i).getItemName();
            row[3] = InvoiceItem.get(i).getUniprice();
            row[4] = InvoiceItem.get(i).getQuntity();
            row[5] = InvoiceItem.get(i).getDiscount();
            row[6] = InvoiceItem.get(i).getTax();
            row[7] = InvoiceItem.get(i).getNote();
            row[8] = InvoiceItem.get(i).getNetAmmount();
            row[9] = InvoiceItem.get(i).getDiscountAmmount();
            row[10] = InvoiceItem.get(i).getTaxAmmount();
            row[11] = InvoiceItem.get(i).getFinaleAmmount();
            model.addRow(row);
        }
       itemList.setModel(model);
       
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
        jPanel2 = new javax.swing.JPanel();
        inv_no = new javax.swing.JLabel();
        client_id = new javax.swing.JLabel();
        client_name = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        issued_user = new javax.swing.JLabel();
        finaleAmmount = new javax.swing.JLabel();
        TotTax = new javax.swing.JLabel();
        payment = new javax.swing.JLabel();
        subDiscount = new javax.swing.JLabel();
        due_date = new javax.swing.JLabel();
        subTot = new javax.swing.JLabel();
        issued_time = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        issed_date = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemList = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inv_no.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        inv_no.setForeground(new java.awt.Color(0, 102, 102));
        inv_no.setText("jLabel3");
        jPanel2.add(inv_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 240, 40));

        client_id.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        client_id.setForeground(new java.awt.Color(0, 102, 102));
        client_id.setText("jLabel3");
        jPanel2.add(client_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 190, 40));

        client_name.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        client_name.setForeground(new java.awt.Color(0, 102, 102));
        client_name.setText("jLabel3");
        jPanel2.add(client_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 230, 40));

        address.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        address.setForeground(new java.awt.Color(0, 102, 102));
        address.setText("jLabel3");
        jPanel2.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 190, 40));

        contact.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        contact.setForeground(new java.awt.Color(0, 102, 102));
        contact.setText("jLabel3");
        jPanel2.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 90, 190, 40));

        issued_user.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        issued_user.setForeground(new java.awt.Color(0, 102, 102));
        issued_user.setText("jLabel3");
        jPanel2.add(issued_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 170, 190, 40));

        finaleAmmount.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        finaleAmmount.setForeground(new java.awt.Color(0, 102, 102));
        finaleAmmount.setText("jLabel3");
        jPanel2.add(finaleAmmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 250, 190, 40));

        TotTax.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        TotTax.setForeground(new java.awt.Color(0, 102, 102));
        TotTax.setText("jLabel3");
        jPanel2.add(TotTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 190, 40));

        payment.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        payment.setForeground(new java.awt.Color(0, 102, 102));
        payment.setText("jLabel3");
        jPanel2.add(payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 190, 40));

        subDiscount.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        subDiscount.setForeground(new java.awt.Color(0, 102, 102));
        subDiscount.setText("jLabel3");
        jPanel2.add(subDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 190, 40));

        due_date.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        due_date.setForeground(new java.awt.Color(0, 102, 102));
        due_date.setText("jLabel3");
        jPanel2.add(due_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 190, 40));

        subTot.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        subTot.setForeground(new java.awt.Color(0, 102, 102));
        subTot.setText("jLabel3");
        jPanel2.add(subTot, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 190, 40));

        issued_time.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        issued_time.setForeground(new java.awt.Color(0, 102, 102));
        issued_time.setText("jLabel3");
        jPanel2.add(issued_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 190, 40));

        status.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        status.setForeground(new java.awt.Color(0, 102, 102));
        status.setText("jLabel3");
        jPanel2.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 190, 40));

        issed_date.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        issed_date.setForeground(new java.awt.Color(0, 102, 102));
        issed_date.setText("jLabel3");
        jPanel2.add(issed_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 190, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Forms/IncoiceView-04.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 30, 1500, 370));

        jTabbedPane1.addTab("   Genaral Informations   ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        itemList.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        itemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        itemList.setRowHeight(35);
        itemList.setSelectionBackground(new java.awt.Color(0, 102, 102));
        jScrollPane2.setViewportView(itemList);

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel2.setText("Purchased items");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("   Item list   ", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotTax;
    private javax.swing.JLabel address;
    private javax.swing.JLabel client_id;
    private javax.swing.JLabel client_name;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel due_date;
    private javax.swing.JLabel finaleAmmount;
    private javax.swing.JLabel inv_no;
    private javax.swing.JLabel issed_date;
    private javax.swing.JLabel issued_time;
    private javax.swing.JLabel issued_user;
    private javax.swing.JTable itemList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel payment;
    private javax.swing.JLabel status;
    private javax.swing.JLabel subDiscount;
    private javax.swing.JLabel subTot;
    // End of variables declaration//GEN-END:variables
}
