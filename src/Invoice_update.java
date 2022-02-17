
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
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
public final class Invoice_update extends javax.swing.JInternalFrame {

    /**
     * Creates new form invoice_update
     */
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
  
//    Interconection Data
    public static String invoice_number,userid;
    
//    Reciving public Table Data
    public String tableItemID,tabledbRowNO;
    public int TableQuntity;
    public double TableDicount,TableTax;
    
//    Saving Dta
    public double DBUnitPrice;
    public String DBName;

//    Extracted Data
    public int ExQun;
    public double ExUnitPrice;
    
    public boolean exitOnCallTrigger;
    public String DeleteCall;
    
//    DBItemDetails
    public double ExNetAmmount,ExDiscountedAmmount,ExTaxedAmmount,ExFinalAmmount;
    
//    cal
    double finaleDiscount,finaleNetAmmount,finalTax,finalTotal,outStand = 0;
    
    //    dbnewVal
    public double dbDiscount,dbNetAmmount,dbTax,dbTotal,dboutStand;
    public String DBCustomerID ;
    
//    generate update reference number
    public String gReference;
    
//    public basic details
    public String PUBcientName,PUBaddress,PUBcontact,PUBisseeddate,PUBduedate,PUBclientid,FNA;
    
//    public empty check vals
    public int checkQuntity;
    public double checkDiscount,checkTax;
    public String checkNote;
    public boolean cell,basicInf ;
    
//    public quntity correction
    public int correctQuntityt,CRdbQun;
    public String correctItemID;
    
    public double tott;
    
    
    
    
    public Invoice_update() throws ParseException {
        initComponents();
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
        
        jScrollPane2.getViewport().setBackground(Color.WHITE);
        
        
        con = Database_connection_CLASS.connection();
           
        
        ViewAll();
        setValues ();
        genarateReference();
         

        status.setVisible(false);
        
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
            String Activity = (FNA+" ("+userid+") updated "+invoice_number);
            
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
       itemList.setModel(model);
       
    }

    public void setValues () throws ParseException {
    
        String InvNo = invoice_number;
        con = Database_connection_CLASS.connection();
        try{
            
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT customer_id FROM customer");
            while(rs.next()){
                String Value = rs.getString("customer_id");
                customer.addItem(Value);
            }
            boolean loopcheck = false;
            stmt = con.createStatement();
            String query = "SELECT * FROM invoice_details ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next())
            {
                if (InvNo.equals(rsd.getString(3)))
                {
                    
                    Inv_No.setText(rsd.getString(3));
                    this.PUBisseeddate = rsd.getString(4);issued_date.setText(PUBisseeddate);
                    this.PUBduedate =  rsd.getString(5);
                    this.PUBclientid = rsd.getString(6);
                    this.PUBcientName = rsd.getString(7);client_name.setText(PUBcientName);
                    this.PUBaddress = rsd.getString(8); address.setText(PUBaddress);
                    this.PUBcontact = rsd.getString(9); contact_number.setText(PUBcontact);
                    payment_method.setText(rsd.getString(11));
                    sub_total.setText(rsd.getString(13));
                    discount.setText(rsd.getString(14));
                    total_tax.setText(rsd.getString(15));
                    this.tott = rsd.getFloat(10);
                    total.setText(rsd.getString(10));
                    this.outStand = Double.parseDouble(rsd.getString(10));
                    customer.setSelectedItem(PUBclientid);
                    
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(PUBduedate);
                    dueDate.setDate(date);
                }
            }
//            
        }
        catch(NumberFormatException | SQLException e){ 
        }
    }
    
    public void cal(){
        
        double CalNetAmmount = TableQuntity * ExUnitPrice;
        double CalDiscount = CalNetAmmount * (TableDicount/100);
        double CalTax = CalNetAmmount * (TableTax/100);
        double FinaleAmmount = CalNetAmmount -(CalDiscount+CalTax);
        
        int SelectedRow = itemList.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
        dtm.setValueAt(String.valueOf(CalNetAmmount),SelectedRow,8);
        dtm.setValueAt(String.valueOf(CalDiscount),SelectedRow,9);
        dtm.setValueAt(String.valueOf(CalTax),SelectedRow,10);
        dtm.setValueAt(String.valueOf(FinaleAmmount),SelectedRow,11);
        
        
        
        DefaultTableModel dtm2 = (DefaultTableModel) itemList.getModel();
            for(int i=0; i < dtm2.getRowCount() ; i++){
                
                String netamount = itemList.getValueAt(i, 8).toString();
                String discountammount = itemList.getValueAt(i, 9).toString();
                String taxedammount = itemList.getValueAt(i, 10).toString();
                String finaleammount = itemList.getValueAt(i, 11).toString();
                
                this.finaleNetAmmount = finaleNetAmmount + Double.parseDouble(netamount);
                this.finaleDiscount =  finaleDiscount + Double.parseDouble(discountammount) ;
                this.finalTax = finalTax + Double.parseDouble(taxedammount);
                this.finalTotal = finalTotal + Double.parseDouble(finaleammount);
            }

            new_subTotal.setText(Double.toString(finaleNetAmmount));this.dbNetAmmount = finaleNetAmmount; this.finaleNetAmmount = 0;
            
            newDiscountedAmmount.setText(Double.toString(finaleDiscount));this.dbDiscount = finaleDiscount;this.finaleDiscount = 0;
            
            newTaxedAmmount.setText(Double.toString(finalTax));this.dbTax = finalTax;this.finalTax = 0;
            
            NewTotal.setText(Double.toString(finalTotal));this.dbTotal = finalTotal;
            
            double outstand = outStand - finalTotal;this.dboutStand = outstand;
            
            if (outstand < 0) {
                outstandbalance.setForeground(Color.RED);
                outstandbalance.setText("To Get Paid : "+Double.toString(outstand));
            } else {
               outstandbalance.setForeground(Color.GREEN);
               outstandbalance.setText("To Refund : "+Double.toString(outstand));
            }
            this.finalTotal = 0;

    }
    public void DbValues(){
    
        try{
    
        con = Database_connection_CLASS.connection();
        stmt = con.createStatement();
        String query2 = "SELECT * FROM products ";
        ResultSet rsd = stmt.executeQuery(query2);

        while(rsd.next())
        {
            
            if (tableItemID.equals(rsd.getString(4)))
            {
               this.ExQun = rsd.getInt(8);
               this.ExUnitPrice = (rsd.getDouble(11));
            }
        }
        }
        catch(SQLException e){

        }
    }
    
    public void DBItemDetails(){
    
        try{
    
        con = Database_connection_CLASS.connection();
        stmt = con.createStatement();
        String query2 = "SELECT * FROM invoice_item ";
        ResultSet rsd = stmt.executeQuery(query2);

        while(rsd.next())
        {
            
            if (tableItemID.equals(rsd.getString(3)) && tabledbRowNO.equals(rsd.getString(14)) && invoice_number.equals(rsd.getString(2)))
            {
               this.ExNetAmmount = rsd.getInt(10);
               this.ExDiscountedAmmount = (rsd.getDouble(11));
               this.ExTaxedAmmount = (rsd.getDouble(12));
               this.ExFinalAmmount = (rsd.getDouble(13));

            }
        }
        }
        catch(SQLException e){

        }
    }
//    

    public void SavingOldInvoiceItemDetails(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
                
            DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
            for(int i=0; i < dtm.getRowCount() ; i++){
                
                String SOITDInvoiceNumber = invoice_number;
                String SOITDid = itemList.getValueAt(i, 1).toString();
                
                String query2 = "SELECT * FROM products ";
                ResultSet rsd = stmt.executeQuery(query2);
                while(rsd.next())
                {
                    if (SOITDid.equals(rsd.getString(4)))
                    {
                         this.DBUnitPrice = (rsd.getDouble(11));
                         this.DBName = (rsd.getString(6));
                    }
                }
                String SOITDName = DBName;
                double unitprice = DBUnitPrice;
                String SOITDUnitPrice = Double.toString(unitprice);
                String SOITDQuntity = itemList.getValueAt(i, 4).toString();
                String SOITDDiscount = itemList.getValueAt(i, 5).toString();
                String SOITDTax = itemList.getValueAt(i, 6).toString();
                String SOITDNote = itemList.getValueAt(i, 7).toString();
                String SOITDNetAmmount = itemList.getValueAt(i, 8).toString();
                String SOITDDiscountAmmount = itemList.getValueAt(i, 9).toString();
                String SOITDTaxedAmmount = itemList.getValueAt(i, 10).toString();
                String SOITDFinaleAmmount = itemList.getValueAt(i, 11).toString();
                String SOITDRowNo = String.valueOf(i);
                String SOITDStatus = "Updated";
                
                
                
                String Query2 = "INSERT INTO `invoice_item_updated_history`(`invoice_no`, `item_id`, `item_name`, "
                        + "`unit_price`, `quantity`, `discount`, `tax`, `note`, `net_amount`, `disconted_ammount`, "
                        + "`taxed_ammount`, `finale_ammount`, `row_id`, `status`) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement preparedStmt2 = con.prepareStatement(Query2);
                preparedStmt2.setString(1, SOITDInvoiceNumber);
                preparedStmt2.setString(2, SOITDid);
                preparedStmt2.setString(3, SOITDName);
                preparedStmt2.setString(4, SOITDUnitPrice);
                preparedStmt2.setString(5, SOITDQuntity);
                preparedStmt2.setString(6, SOITDDiscount);
                preparedStmt2.setString(7, SOITDTax);
                preparedStmt2.setString(8, SOITDNote);
                preparedStmt2.setString(9, SOITDNetAmmount);
                preparedStmt2.setString(10, SOITDDiscountAmmount);
                preparedStmt2.setString(11, SOITDTaxedAmmount);
                preparedStmt2.setString(12, SOITDFinaleAmmount);
                preparedStmt2.setString(13, SOITDRowNo);
                preparedStmt2.setString(14, SOITDStatus);
                preparedStmt2.execute();

            }  

        }
        catch(SQLException e){
        
            
        }   
    }
    
    public void DeleteTemporaryFiles(){
        try{
            DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
            for(int i=0; i < dtm.getRowCount() ; i++){
                
                con = Database_connection_CLASS.connection();
                stmt = con.createStatement();
                String Query3 = "DELETE FROM `invoice_item_updated_history` where invoice_no = ?";
                PreparedStatement preparedStmt2 = con.prepareStatement(Query3);
                preparedStmt2.setString(1, invoice_number);
                preparedStmt2.execute();
            }
        
        }
        catch(SQLException e){
        
            
        }

    }
    public void moveinvoiceDetails(){
        
        try{
            
            con = Database_connection_CLASS.connection();
 
            stmt = con.createStatement();
            String INV = invoice_number;
            String ref  = gReference;
            String Query = "UPDATE `invoice_details` SET `reference` = ? WHERE `invoice_number` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, ref);
            preparedStmt.setString(2, INV);
            preparedStmt.execute();

            String Query2 ="INSERT INTO `invoice_details_history`(`handle_user`, `invoice_number`, `date`, `due_date`, `customer_id`, `fullname`, `address`, `phone_number`, `invoice_total`, `payment_method`, `invoiced_time`, `sub_total`, `total_discounted_ammount`, `total_taxed_ammount`, `invoice_status`, `outstand_balance`, `outstand_status`, `reference`) SELECT `handle_user`, `invoice_number`, `date`, `due_date`, `customer_id`, `fullname`, `address`, `phone_number`,`invoice_total`, `payment_method`, `invoiced_time`, `sub_total`, `total_discounted_ammount`, `total_taxed_ammount`,`invoice_status`, `outstand_balance`, `outstand_status`, `reference` FROM `invoice_details` WHERE `invoice_number` =?;" ;
            PreparedStatement preparedStmt2 = con.prepareStatement(Query2);
            preparedStmt2.setString(1, invoice_number);  
            preparedStmt2.execute();
            
           
            
            
        }
        catch(SQLException e){
        
            System.out.println("Error Moving database invoice details");
        }

    }
    
    public void moveitemDetails(){
    
        try{
            stmt = con.createStatement();
            String INV = invoice_number;
            String ref  = gReference;
            String Query = "UPDATE `invoice_item` SET `reference` = ? WHERE `invoice_no` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, ref);
            preparedStmt.setString(2, INV);
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
    
    public void genarateReference(){
    
        try {
            con = Database_connection_CLASS.connection();
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(`update_reference`) FROM `invoice_update_info`");
            rsd.next();
            rsd.getString("MAX(`update_reference`)");
            if (rsd.getString("MAX(`update_reference`)") == null) {
                itemidset = "REF00001";
                
                this.gReference = itemidset;
            } 
            else {
                long id = Long.parseLong(rsd.getString("MAX(`update_reference`)").substring(3, rsd.getString("MAX(`update_reference`)").length()));
                id++;
                itemidset = ("REF" + String.format("%05d", id));
                
                this.gReference = itemidset;
                }
            } 
        catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        } 
    }
    
    public void invoiceupdateInformations(){
        
        try{
        
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            
            Date date2 = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  
            String strDate = dateFormat.format(date2);  
            String invoiceNumber = invoice_number;
            String uid = userid;
            String ref = gReference;
            String paymentStatement = paymentStat.getSelectedItem().toString();
            String Query = "INSERT INTO `invoice_update_info`(`invoice_no`, `updated_user`,  `updated_time`, `update_reference`, `total`, `outstand_balance`, `outstand_status`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, invoiceNumber);
            preparedStmt.setString(2, uid);
            preparedStmt.setString(3, strDate);
            preparedStmt.setString(4, ref);
            preparedStmt.setDouble(5, tott);
            preparedStmt.setDouble(6, dboutStand);
            preparedStmt.setString(7, paymentStatement);
            preparedStmt.execute();
            
        }
        catch(SQLException e){
        
            System.out.println("invoice update infrmations faild");
        }
    }
    public void updateItemTable(){
    
        try{
            
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
            for(int i=0; i < dtm.getRowCount() ; i++){
                
                String quntity = itemList.getValueAt(i, 4).toString(); 
                String idiscount  = itemList.getValueAt(i, 5).toString();
                String itax = itemList.getValueAt(i, 6).toString();
                String note = itemList.getValueAt(i, 7).toString();
                String netamount = itemList.getValueAt(i, 8).toString();
                String discountammount = itemList.getValueAt(i, 9).toString();
                String taxedammount = itemList.getValueAt(i, 10).toString();
                String finaleammount = itemList.getValueAt(i, 11).toString();
                String status ="updated";
                String invoice = invoice_number;
                int RowID = (i);
                String REF = gReference;
                
                String Query5 = "UPDATE `invoice_item` SET `quantity` = ?, `discount` = ?, `tax` = ?, `note` = ?, `net_amount` = ?, "
                        + "`disconted_ammount` = ?, `taxed_ammount` = ?, `finale_ammount` = ?, `status` = ? ,`reference` = ? WHERE `invoice_no` = ? AND `row_id` = ?;";

                PreparedStatement preparedStmt2 = con.prepareStatement(Query5);

                preparedStmt2.setString(1, quntity);
                preparedStmt2.setString(2, idiscount);
                preparedStmt2.setString(3, itax);
                preparedStmt2.setString(4, note);
                preparedStmt2.setString(5, netamount);
                preparedStmt2.setString(6, discountammount);
                preparedStmt2.setString(7, taxedammount);
                preparedStmt2.setString(8, finaleammount);
                preparedStmt2.setString(9, status);
                preparedStmt2.setString(10, REF);
                preparedStmt2.setString(11, invoice);
                preparedStmt2.setInt(12, RowID);
                preparedStmt2.execute();

            }
        }
        catch(SQLException e){
        
            System.out.println("error moving item loop");
        }
    }
    

    
    public void invoceDetailsUpdate(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();

            double newTot =  dbTotal;
            double newNetAmmount =  dbNetAmmount;
            double newDiscount =  dbDiscount;
            double newTax =  dbTax;
            String stat = "Updated";
            double newOutstand = dboutStand;
            String paymentStatement = paymentStat.getSelectedItem().toString();
            String inv = invoice_number;

            String Query6 = "UPDATE `invoice_details` SET `invoice_total` = ? , `sub_total` =? , `total_discounted_ammount` = ? , `total_taxed_ammount` = ? , `invoice_status` = ? , `outstand_balance` = ? , `outstand_status` = ?  WHERE `invoice_number` = ?;";

            PreparedStatement preparedStmt = con.prepareStatement(Query6);

            preparedStmt.setDouble(1, newTot);
            preparedStmt.setDouble(2, newNetAmmount);
            preparedStmt.setDouble(3, newDiscount);
            preparedStmt.setDouble(4, newTax);
            preparedStmt.setString(5, stat);
            preparedStmt.setDouble(6, newOutstand);
            preparedStmt.setString(7, paymentStatement);
            preparedStmt.setString(8, inv);
             System.out.println("5");
            preparedStmt.execute();
            System.out.println("6");
        
        }
        catch(SQLException e){
        
            System.out.println("Error in invoice details update");
        }
    }
    
    public void invoiceBasicDetails(){
    
        try{  
            stmt = con.createStatement();
            
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String newDueDate = date.format(dueDate.getDate());
            String newID = customer.getSelectedItem().toString();
            String newClientName = client_name.getText();
            String newAddress = address.getText();
            String newcontact_number = contact_number.getText();
            String Query = "UPDATE `invoice_details` SET `due_date` = ? , `customer_id` = ? , `fullname` = ? , `address` = ? , `phone_number` = ? WHERE `invoice_number` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, newDueDate);
            preparedStmt.setString(2, newID);
            preparedStmt.setString(3, newClientName);
            preparedStmt.setString(4, newAddress);
            preparedStmt.setString(5, newcontact_number);
            preparedStmt.setString(6, invoice_number);
            preparedStmt.execute();

        }
        catch(SQLException e){
            System.out.println("Basic information update faild");
        }
    }
    
    public void cellEmptycheck(){

        try{
            
            DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
            for(int i=0; i < dtm.getRowCount() ; i++){
                
                String rowID = String.valueOf(i);
                
                String quntity = itemList.getValueAt(i, 4).toString(); 
                String idiscount  = itemList.getValueAt(i, 5).toString();
                String itax = itemList.getValueAt(i, 6).toString();
                String note = itemList.getValueAt(i, 7).toString();
                
                con = Database_connection_CLASS.connection();
                stmt = con.createStatement();
                String query = "SELECT * FROM invoice_item ";
                ResultSet rsd = stmt.executeQuery(query);
           
                while(rsd.next())
                {
                    if (invoice_number.equals(rsd.getString(2)) && rowID.equals(rsd.getString(14)))
                    {
                        this.checkQuntity = rsd.getInt(6);
                        this.checkDiscount = rsd.getDouble(7);
                        this.checkTax = rsd.getDouble(8);
                        this.checkNote = rsd.getString(9);
                    }
                }

                String qq = String.valueOf(checkQuntity);
                String dd = Double.toString(checkDiscount);
                String tt = Double.toString(checkTax);

                 if( quntity.equals(qq)&& idiscount.equals(dd) && itax.equals(tt) && note.equals(checkNote) ){

                     System.out.println("Equal");
                     this.cell = false;
                    }
                 else{
                     System.out.println("not Equal");
                     this.cell = true;
                     break;
                 }

                }
        }
        catch(SQLException e){
         System.out.println("Error in cell check");
        }
    }
    
    public void basicInformationcheck (){
    
        try{
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String newDueDate = date.format(dueDate.getDate());
            if( customer.getSelectedItem().toString().equals(PUBclientid)  && client_name.getText().equals(PUBcientName) && address.getText().equals(PUBaddress) && contact_number.getText().equals(PUBcontact) && newDueDate.equals(PUBduedate) ){
                
                System.out.println("Equal");
                this.basicInf = false;
            }
            else{
                System.out.println("not changed ");
                this.basicInf = true;
            }
        }
        catch(Exception e){
        
        }
    }
    
    public void quntityCorrection(){
    
        try{
            
            String INV = invoice_number;
            stmt = con.createStatement();
            DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
            for(int i=0; i < dtm.getRowCount() ; i++){
                String rowID = String.valueOf(i);
//               get values from invoice item details 
               
                String query = "SELECT * FROM invoice_item ";
                ResultSet rsd = stmt.executeQuery(query);
                while(rsd.next())
                {
                    if (INV.equals(rsd.getString(2)) && rowID.equals(rsd.getString(14)))
                    {
                        this.correctQuntityt = rsd.getInt(6);
                        this.correctItemID = rsd.getString(3);
                    }
                }
//                get quntity from product database
                String query2 = "SELECT * FROM products ";
                ResultSet rsd2 = stmt.executeQuery(query2);

                while(rsd2.next())
                {
                    if (correctItemID.equals(rsd2.getString(4)))
                    {
                       this.CRdbQun = rsd2.getInt(8);
                    }
                }
                
                String SOITDQuntity = itemList.getValueAt(i, 4).toString(); 
                int tbVAl=Integer.parseInt(SOITDQuntity);  

                int currentQun = (correctQuntityt + CRdbQun);
                int newQun = currentQun - tbVAl;
            
                System.out.println(correctQuntityt);
                System.out.println(correctItemID);
                System.out.println(CRdbQun);
                System.out.println(newQun);
                
                String Query3 = "UPDATE `products` SET `quantity` = ? WHERE `product_id` = ?; ";
                PreparedStatement preparedStmt2 = con.prepareStatement(Query3);
                preparedStmt2.setInt(1, newQun);
                preparedStmt2.setString(2, correctItemID);
                preparedStmt2.execute();
                
            }

        }
        catch(Exception e){
        
            System.out.println("Error correcting quntity od DB");
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemList = new javax.swing.JTable();
        payment_method = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        issued_date = new javax.swing.JLabel();
        Inv_No = new javax.swing.JLabel();
        contact_number = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        client_name = new javax.swing.JTextField();
        dueDate = new com.toedter.calendar.JDateChooser();
        outstandbalance = new javax.swing.JLabel();
        customer = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        updateData = new accessories.Button();
        sub_total = new javax.swing.JLabel();
        discount = new javax.swing.JLabel();
        total_tax = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        new_subTotal = new javax.swing.JLabel();
        newDiscountedAmmount = new javax.swing.JLabel();
        newTaxedAmmount = new javax.swing.JLabel();
        NewTotal = new javax.swing.JLabel();
        paymentStat = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        itemList.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        itemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
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
        itemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemListMouseClicked(evt);
            }
        });
        itemList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemListKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemListKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                itemListKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(itemList);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 1530, 280));

        payment_method.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        payment_method.setForeground(new java.awt.Color(0, 102, 102));
        payment_method.setText("jLabel2");
        jPanel2.add(payment_method, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 230, 40));

        status.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        status.setForeground(new java.awt.Color(255, 0, 0));
        status.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        status.setText("jLabel3");
        jPanel2.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 640, 30));

        issued_date.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        issued_date.setForeground(new java.awt.Color(0, 102, 102));
        issued_date.setText("jLabel2");
        jPanel2.add(issued_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 230, 40));

        Inv_No.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        Inv_No.setForeground(new java.awt.Color(0, 102, 102));
        Inv_No.setText("jLabel2");
        jPanel2.add(Inv_No, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 230, 40));

        contact_number.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        contact_number.setForeground(new java.awt.Color(0, 102, 102));
        contact_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact_numberActionPerformed(evt);
            }
        });
        jPanel2.add(contact_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 70, 230, 40));

        address.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        address.setForeground(new java.awt.Color(0, 102, 102));
        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });
        jPanel2.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, 230, 40));

        client_name.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        client_name.setForeground(new java.awt.Color(0, 102, 102));
        client_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                client_nameKeyReleased(evt);
            }
        });
        jPanel2.add(client_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 230, 40));

        dueDate.setDateFormatString("yyyy-MM-dd");
        dueDate.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jPanel2.add(dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 230, 40));

        outstandbalance.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        outstandbalance.setForeground(new java.awt.Color(0, 102, 102));
        outstandbalance.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        outstandbalance.setText("00.00");
        jPanel2.add(outstandbalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 780, 230, 40));

        customer.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        customer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual" }));
        customer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                customerItemStateChanged(evt);
            }
        });
        customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerActionPerformed(evt);
            }
        });
        jPanel2.add(customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 230, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Forms/update-04.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -110, 1510, 480));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 950, -1, -1));

        updateData.setBackground(new java.awt.Color(0, 102, 102));
        updateData.setForeground(new java.awt.Color(255, 255, 255));
        updateData.setText("Update Invoice");
        updateData.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        updateData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDataActionPerformed(evt);
            }
        });
        jPanel2.add(updateData, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 900, 440, 50));

        sub_total.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        sub_total.setForeground(new java.awt.Color(0, 102, 102));
        sub_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sub_total.setText("jLabel2");
        jPanel2.add(sub_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 580, 230, 40));

        discount.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        discount.setForeground(new java.awt.Color(0, 102, 102));
        discount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        discount.setText("jLabel2");
        jPanel2.add(discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 680, 230, 40));

        total_tax.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        total_tax.setForeground(new java.awt.Color(0, 102, 102));
        total_tax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total_tax.setText("jLabel2");
        jPanel2.add(total_tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 630, 230, 40));

        total.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        total.setForeground(new java.awt.Color(0, 102, 102));
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("00.00");
        jPanel2.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 730, 230, 40));

        new_subTotal.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        new_subTotal.setForeground(new java.awt.Color(0, 102, 102));
        new_subTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        new_subTotal.setText("00.00");
        jPanel2.add(new_subTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 590, 230, 30));

        newDiscountedAmmount.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        newDiscountedAmmount.setForeground(new java.awt.Color(0, 102, 102));
        newDiscountedAmmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        newDiscountedAmmount.setText("00.00");
        jPanel2.add(newDiscountedAmmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 630, 230, 40));

        newTaxedAmmount.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        newTaxedAmmount.setForeground(new java.awt.Color(0, 102, 102));
        newTaxedAmmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        newTaxedAmmount.setText("00.00");
        jPanel2.add(newTaxedAmmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 680, 230, 40));

        NewTotal.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        NewTotal.setForeground(new java.awt.Color(0, 102, 102));
        NewTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NewTotal.setText("00.00");
        jPanel2.add(NewTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 720, 230, 60));

        paymentStat.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        paymentStat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paid", "Refunded", "Pending" }));
        paymentStat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentStatActionPerformed(evt);
            }
        });
        jPanel2.add(paymentStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 830, 210, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Forms/updateBalnce-05.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, 800, 390));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 940, -1, 60));

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1585, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemListMouseClicked
        // TODO add your handling code here:
        status.setVisible(false);

    }//GEN-LAST:event_itemListMouseClicked

    private void itemListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemListKeyReleased
        // TODO add your handling code here:
        try{
            status.setVisible(false);
            int SelectedRow = itemList.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
            
            String ItemDbRowNo = (dtm.getValueAt(SelectedRow, 0).toString());
            this.tabledbRowNO = ItemDbRowNo;
            
            String ItemIdFromTable = (dtm.getValueAt(SelectedRow, 1).toString());
            this.tableItemID = ItemIdFromTable;

//            String UnitPriceFromTable =(dtm.getValueAt(SelectedRow, 3).toString());
//            this.UnitPrice = Double.parseDouble(UnitPriceFromTable);  
            
            String QuntityFromTable = (dtm.getValueAt(SelectedRow, 4).toString());
            this.TableQuntity = Integer.parseInt(QuntityFromTable);
            
            String DicountFromTable = (dtm.getValueAt(SelectedRow, 5).toString());
            this.TableDicount = Double.parseDouble(DicountFromTable);
            
            String TaxFromTable = (dtm.getValueAt(SelectedRow, 6).toString());
            this.TableTax = Double.parseDouble(TaxFromTable);
            
            DbValues();

            
            if(ExQun < TableQuntity){
                status.setVisible(true);
                status.setText("Grater Than Item on our System");

            }
            else if (TableDicount > 100){
                status.setVisible(true);
                status.setText("Discount cannont be greater than 100");
            }
            
            else if(TableTax > 100){
                status.setVisible(true);
                status.setText("Tax cannont be greater than 100"); 

                }
            else{
                
                cal();
                
            }

        }
        catch(NumberFormatException e){
        
            System.out.println("Error in item list view");
        }
    }//GEN-LAST:event_itemListKeyReleased

    private void itemListKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemListKeyTyped
        // TODO add your handling code here:
        this.exitOnCallTrigger = true;
        itemList.editCellAt(1, 1);
        status.setVisible(false);
        
    }//GEN-LAST:event_itemListKeyTyped

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void updateDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDataActionPerformed
        // TODO add your handling code here:
        try{
            
            cellEmptycheck();
            basicInformationcheck();
            
            if(basicInf || cell){
            
                System.out.println("Something edited");
                quntityCorrection();
                invoiceupdateInformations();
                moveitemDetails();
                moveinvoiceDetails();
                invoiceBasicDetails();
                updateItemTable();
                invoceDetailsUpdate();
                activity();
                JOptionPane.showMessageDialog (null, "Invoice Successfully Updated!", "successful", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UI.put("OptionPane.background", Color.white);
                UI.put("Panel.background", Color.white);
            }
            else{
                
                System.out.println("nothing changed!");
                JOptionPane.showMessageDialog (null, "Nothing updated", "WARNING!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UI.put("OptionPane.background", Color.white);
                UI.put("Panel.background", Color.white);
            }

        }
        catch(HeadlessException e){
        
            System.out.println("Updaing faild");
        }
    }//GEN-LAST:event_updateDataActionPerformed

    private void contact_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact_numberActionPerformed

    private void paymentStatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentStatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentStatActionPerformed

    private void client_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_client_nameKeyReleased
        // TODO add your handling code here:
        try{
        
            customer.setSelectedIndex(0);
        }
        catch(Exception e){
        
            
        }
    }//GEN-LAST:event_client_nameKeyReleased

    private void itemListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemListKeyPressed
        // TODO add your handling code here:
        status.setVisible(false);
    }//GEN-LAST:event_itemListKeyPressed

    private void customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_customerActionPerformed

    private void customerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_customerItemStateChanged
        // TODO add your handling code here:
        try{
            String CUSTOMERID = customer.getSelectedItem().toString();
            if("Individual".equals(CUSTOMERID)){
                
                client_name.setText("");
                address.setText("");
                contact_number.setText("");
                
            }
            else{
            
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
                while(rs.next()){
                    if (customer.getSelectedItem().toString().equals(rs.getString(2)))
                    {
                        client_name.setText(rs.getString(5) + " " + rs.getString(6));
                        address.setText(rs.getString(8));
                        contact_number.setText(rs.getString(7));
                    }  
                }
            }
        }
        catch(SQLException e){
        }
    }//GEN-LAST:event_customerItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Inv_No;
    private javax.swing.JLabel NewTotal;
    private javax.swing.JTextField address;
    private javax.swing.JTextField client_name;
    private javax.swing.JTextField contact_number;
    private javax.swing.JComboBox<String> customer;
    private javax.swing.JLabel discount;
    private com.toedter.calendar.JDateChooser dueDate;
    private javax.swing.JLabel issued_date;
    private javax.swing.JTable itemList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel newDiscountedAmmount;
    private javax.swing.JLabel newTaxedAmmount;
    private javax.swing.JLabel new_subTotal;
    private javax.swing.JLabel outstandbalance;
    private javax.swing.JComboBox<String> paymentStat;
    private javax.swing.JLabel payment_method;
    private javax.swing.JLabel status;
    private javax.swing.JLabel sub_total;
    private javax.swing.JLabel total;
    private javax.swing.JLabel total_tax;
    private accessories.Button updateData;
    // End of variables declaration//GEN-END:variables
}
