import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
public final class Invoice_reverse extends javax.swing.JInternalFrame {

    /**
     * Creates new form invoice_reverse
     */
    public static String invoice_number,

    /**
     * Creates new form invoice_reverse
     */
    userid,

    /**
     * Creates new form invoice_reverse
     */
    FNA;
    
    public String ReverseReference;
    
    public String DbInvoiceItemid;
    public int DbItemQuntity,DbproductQuntity;
    public double total;
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public Invoice_reverse() {
        initComponents();
        con = Database_connection_CLASS.connection();
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        invoiceNumber.setText(invoice_number);
        

        
        ViewAll();
        genarateReverseReference();
        getDetails();
        

    }
    
    public void activity(){
    
        try{
            
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT * FROM users");
            while(rsd.next()){
                if (userid.equals(rsd.getString(2)))
                {
                    this.FNA = rsd.getString(3)+" "+rsd.getString(4);
                }  
            }
            Date date2 = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  
            String strDate = dateFormat.format(date2);  
            
            String user = userid;
            String Activity = (FNA+" ("+userid+") reversed "+invoice_number);
            
            String Query = "INSERT INTO `activity`(`time`, `handled_user`, `activity`) VALUES (?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, strDate);
            preparedStmt.setString(2, userid);
            preparedStmt.setString(3, Activity);
            preparedStmt.execute();    

        }
        catch(SQLException e){
            
            
        }
    }
    
    public ArrayList<Invoice_item_CLASS> invoiceItem (String ValToSearch)
    {
        ArrayList<Invoice_item_CLASS> InvoiceItem = new ArrayList<Invoice_item_CLASS>();

        try{
            con = Database_connection_CLASS.connection();
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
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Error IN TAble");
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
       itemTable.setModel(model);
       
    }
    
    public void getDetails(){
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM invoice_details ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                if (invoice_number.equals(rs.getString(3)))
                {
                    invoiceNumber.setText(rs.getString(3));
                    clientid.setText(rs.getString(6));
                    fullname.setText(rs.getString(7));
                    address.setText(rs.getString(8));
                    phone.setText(rs.getString(9));
                    issue.setText(rs.getString(4));
                    due.setText(rs.getString(5));
                    paymethod.setText(rs.getString(11));
                    sub.setText(rs.getString(13));
                    discount.setText(rs.getString(14));
                    tax.setText(rs.getString(15));
                    tot.setText(rs.getString(10));
                    this.total = rs.getDouble(10);

                }
            }

        }
        catch(SQLException e){
        
            System.out.println("Reverse : Error getting data");
        }
   
    }
    
    public void genarateReverseReference(){
    
        try {
            con = Database_connection_CLASS.connection();
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(`reversed_reference`) FROM `invoice_reverse_info`");
            rsd.next();
            rsd.getString("MAX(`reversed_reference`)");
            if (rsd.getString("MAX(`reversed_reference`)") == null) {
                itemidset = "RREF00001";
                this.ReverseReference = itemidset;
                jLabel1.setText(ReverseReference);
            } 
            else {
                long id = Long.parseLong(rsd.getString("MAX(`reversed_reference`)").substring(4, rsd.getString("MAX(`reversed_reference`)").length()));
                id++;
                itemidset = ("RREF" + String.format("%05d", id));
                jLabel1.setText(ReverseReference);
                
                this.ReverseReference = itemidset;
                }
            } 
        catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        } 
    }
    
    public void quntityCorrection(){
    
        try{
            String INV = invoice_number;
            stmt = con.createStatement();
            DefaultTableModel dtm = (DefaultTableModel) itemTable.getModel();
            for(int i=0; i < dtm.getRowCount() ; i++){
                String rowID = String.valueOf(i);
//               get values from invoice item details 
               
                String query = "SELECT * FROM invoice_item ";
                ResultSet rsd = stmt.executeQuery(query);
                while(rsd.next())
                {
                    if (INV.equals(rsd.getString(2)) && rowID.equals(rsd.getString(14)))
                    {
                        this.DbInvoiceItemid = rsd.getString(3);
                        this.DbItemQuntity = rsd.getInt(6); 
                    }
                }
//                get quntity from product database
                String query2 = "SELECT * FROM products ";
                ResultSet rsd2 = stmt.executeQuery(query2);

                while(rsd2.next())
                {
                    if (DbInvoiceItemid.equals(rsd2.getString(4)))
                    {
                       this.DbproductQuntity = rsd2.getInt(8);
                    }
                }
                int DBQuntity = (DbItemQuntity + DbproductQuntity);
                
            
                System.out.println(DBQuntity);

                String Query3 = "UPDATE `products` SET `quantity` = ? WHERE `product_id` = ?; ";
                PreparedStatement preparedStmt2 = con.prepareStatement(Query3);
                preparedStmt2.setInt(1, DBQuntity);
                preparedStmt2.setString(2, DbInvoiceItemid);
                preparedStmt2.execute();  
            }
        }
        catch(SQLException e){
        
            System.out.println("Error correcting quntity od DB");
        }
        
    }
    
    public void moveInvoiceDetails(){
        
        try{
            
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String stat = "Reversed";
            String INV = invoice_number;
            String ref  = ReverseReference;
            String Query = "UPDATE `invoice_details` SET `invoice_status` = ?,`reference` = ? WHERE `invoice_number` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, stat);
            preparedStmt.setString(2, ref);
            preparedStmt.setString(3, INV);
            preparedStmt.execute();

            String Query2 ="INSERT INTO `invoice_details_history`(`handle_user`, `invoice_number`, `date`, `due_date`, `customer_id`, `fullname`, `address`, `phone_number`, `invoice_total`, `payment_method`, `invoiced_time`, `sub_total`, `total_discounted_ammount`, `total_taxed_ammount`, `invoice_status`, `outstand_balance`, `outstand_status`, `reference`) SELECT `handle_user`, `invoice_number`, `date`, `due_date`, `customer_id`, `fullname`, `address`, `phone_number`,`invoice_total`, `payment_method`, `invoiced_time`, `sub_total`, `total_discounted_ammount`, `total_taxed_ammount`,`invoice_status`, `outstand_balance`, `outstand_status`, `reference` FROM `invoice_details` WHERE `invoice_number` =?;" ;
            PreparedStatement preparedStmt2 = con.prepareStatement(Query2);
            preparedStmt2.setString(1, invoice_number);  
            preparedStmt2.execute();

        
        }
        catch(SQLException e){
        
            System.out.println("Invoice : Move Item Details Faild");
        }
    
    }
    
    public void moveInvoiceItems(){
        
        try{
            stmt = con.createStatement();
            String INV = invoice_number;
            String stat = "Reversed";
            String ref  = ReverseReference;
            String Query = "UPDATE `invoice_item` SET `status` = ?,`reference` = ? WHERE `invoice_no` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, stat);
            preparedStmt.setString(2, ref);
            preparedStmt.setString(3, INV);
            preparedStmt.execute();

            String Query1 = "INSERT INTO `invoice_item_history` (`invoice_no`, `item_id`, `item_name`, `unit_price`, `quantity`, `discount`, `tax`, `note`, `net_amount`, `disconted_ammount`, `taxed_ammount`, `finale_ammount`, `row_id`,`reference`)SELECT `invoice_no`, `item_id`, `item_name`, `unit_price`, `quantity`, `discount`, `tax`, `note`, `net_amount`, `disconted_ammount`, `taxed_ammount`, `finale_ammount`,`row_id`, `reference` FROM `invoice_item` WHERE `invoice_no` = ? ;";
            
            PreparedStatement preparedStmt2 = con.prepareStatement(Query1);
            preparedStmt2.setString(1, invoice_number);   
            preparedStmt2.execute();
 
        }
        catch(SQLException e){
        
            System.out.println("Error Moving database item details");
        }
    
        
    }
    
    public void invoiceDetailsUpdate(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String Query = "UPDATE `invoice_details` SET `invoice_total` = '0' , `sub_total` = '0' , `total_discounted_ammount` = '0' , `total_taxed_ammount` = '0'   WHERE `invoice_number` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, invoice_number);
             System.out.println("5");
            preparedStmt.execute();
        
        }
        catch(SQLException e){
        
            System.out.println("Reverse : Invoice Details update Faild");
        }
    }
    
    public void invoiceItemupdate(){
    
        try{
        
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String Query = "UPDATE `invoice_item` SET `quantity`='0',`discount`='0',`tax`='0',`net_amount`='0',`disconted_ammount`='0',`taxed_ammount`='0',`finale_ammount`='0' WHERE `invoice_no` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, invoice_number);
             System.out.println("5");
            preparedStmt.execute();
        }
        catch(SQLException e){
        
            System.out.println("Reverse : invoice item update faild");
        }
        
    }
    
    public void reverseInformation(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            
            Date date2 = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            String strDate = dateFormat.format(date2); 
            String dbreason = reason.getText();
            String cash_stat = balance_status.getSelectedItem().toString();
            String Query = "INSERT INTO `invoice_reverse_info`(`invoice_number`, `balance`, `balance_status`, `reversed_user`, `reversed_time`, `reason`, `reversed_reference`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt2 = con.prepareStatement(Query);
            preparedStmt2.setString(1, invoice_number);  
            preparedStmt2.setDouble(2, total);  
            preparedStmt2.setString(3, cash_stat);  
            preparedStmt2.setString(4, userid);  
            preparedStmt2.setString(5, strDate);  
            preparedStmt2.setString(6, dbreason);
            preparedStmt2.setString(7, ReverseReference);
            preparedStmt2.execute();
            
        }
        catch(SQLException e){
        
            System.out.println("Reverse : reverse information saving faild");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        tot = new javax.swing.JLabel();
        invoiceNumber = new javax.swing.JLabel();
        clientid = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        paymethod = new javax.swing.JLabel();
        due = new javax.swing.JLabel();
        issue = new javax.swing.JLabel();
        sub = new javax.swing.JLabel();
        discount = new javax.swing.JLabel();
        tax = new javax.swing.JLabel();
        reason = new javax.swing.JTextField();
        reverse = new accessories.Button();
        balance_status = new javax.swing.JComboBox<>();
        background = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        itemTable.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        itemTable.setModel(new javax.swing.table.DefaultTableModel(
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
        itemTable.setRowHeight(35);
        itemTable.setSelectionBackground(new java.awt.Color(0, 102, 102));
        jScrollPane2.setViewportView(itemTable);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1480, 310));

        tot.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        tot.setForeground(new java.awt.Color(0, 102, 102));
        tot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tot.setText("jLabel2");
        jPanel2.add(tot, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 570, 330, 80));

        invoiceNumber.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        invoiceNumber.setForeground(new java.awt.Color(0, 102, 102));
        invoiceNumber.setText("jLabel2");
        jPanel2.add(invoiceNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 290, 30));

        clientid.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        clientid.setForeground(new java.awt.Color(0, 102, 102));
        clientid.setText("jLabel2");
        jPanel2.add(clientid, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 290, 30));

        fullname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        fullname.setForeground(new java.awt.Color(0, 102, 102));
        fullname.setText("jLabel2");
        jPanel2.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 290, 30));

        address.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        address.setForeground(new java.awt.Color(0, 102, 102));
        address.setText("jLabel2");
        jPanel2.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 290, 30));

        phone.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        phone.setForeground(new java.awt.Color(0, 102, 102));
        phone.setText("jLabel2");
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 290, 30));

        paymethod.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        paymethod.setForeground(new java.awt.Color(0, 102, 102));
        paymethod.setText("jLabel2");
        jPanel2.add(paymethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 540, 290, 30));

        due.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        due.setForeground(new java.awt.Color(0, 102, 102));
        due.setText("jLabel2");
        jPanel2.add(due, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 490, 290, 30));

        issue.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        issue.setForeground(new java.awt.Color(0, 102, 102));
        issue.setText("jLabel2");
        jPanel2.add(issue, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 440, 290, 30));

        sub.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        sub.setForeground(new java.awt.Color(0, 102, 102));
        sub.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sub.setText("jLabel2");
        jPanel2.add(sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 440, 340, 40));

        discount.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        discount.setForeground(new java.awt.Color(0, 102, 102));
        discount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        discount.setText("jLabel2");
        jPanel2.add(discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 490, 330, 40));

        tax.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        tax.setForeground(new java.awt.Color(0, 102, 102));
        tax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tax.setText("jLabel2");
        jPanel2.add(tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 540, 330, 50));

        reason.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jPanel2.add(reason, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 710, 600, 40));

        reverse.setBackground(new java.awt.Color(255, 0, 0));
        reverse.setForeground(new java.awt.Color(255, 255, 255));
        reverse.setText("Reverse");
        reverse.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        reverse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reverseActionPerformed(evt);
            }
        });
        jPanel2.add(reverse, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 710, 230, 40));

        balance_status.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        balance_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Refunded", "Cheque Refunded", "Not Refundable", "Not Refunded", "Pending to Refund" }));
        jPanel2.add(balance_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 670, 290, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Forms/reverse-08.png"))); // NOI18N
        jPanel2.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -30, 1520, 880));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1585, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reverseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reverseActionPerformed
        // TODO add your handling code here:
        try{
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

                    String id = userid;
                    String passwd = new String(pass.getPassword());
                    stmt = con.createStatement();
                    String query = "SELECT * FROM users ";
                    ResultSet rsd = stmt.executeQuery(query);
                    while(rsd.next())
                    {
                        if (id.equals(rsd.getString(2)) && passwd.equals(rsd.getString(7))){

                            JOptionPane.showMessageDialog (null, "Invoice Reversed Successfully", "successful", JOptionPane.INFORMATION_MESSAGE); 
                            loopcheck = true;
                            
                            quntityCorrection();
                            reverseInformation();
                            moveInvoiceDetails();
                            moveInvoiceItems();
                            invoiceDetailsUpdate();
                            invoiceItemupdate();
                            activity();
//                            
                        }
                    }
                    if(!loopcheck){
                        JOptionPane.showMessageDialog (null, "Incorrect password Try Again!", "FAILD!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{

                     JOptionPane.showMessageDialog(null,"Canselled ");
                }
            }
        
        }
        catch(HeadlessException | SQLException e){
        
            System.out.println("Reverce : reverse action");
        }
    }//GEN-LAST:event_reverseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JLabel background;
    private javax.swing.JComboBox<String> balance_status;
    private javax.swing.JLabel clientid;
    private javax.swing.JLabel discount;
    private javax.swing.JLabel due;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel invoiceNumber;
    private javax.swing.JLabel issue;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel paymethod;
    private javax.swing.JLabel phone;
    private javax.swing.JTextField reason;
    private accessories.Button reverse;
    private javax.swing.JLabel sub;
    private javax.swing.JLabel tax;
    private javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables
}
