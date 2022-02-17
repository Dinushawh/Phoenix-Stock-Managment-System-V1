import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
public final class Item_update extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_supplier_delete_step
     */
    
    public static String productID,userid,userRole,FNA;
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
//    dbValues
    public String dbName,dbSupplierID,dbType,dbManufacture,dbManufacureDate,dbExpireDate,dbSize,dbDescription;
    public double dbWeight,dbSuppyPrice,dbRetailPrice;
    public int dbQuntity,newQun;
    
    public String pReference;

    public Item_update() throws ParseException {
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

        con = Database_connection_CLASS.connection();
        setValues();
        genarateReference();
        
        System.out.println(productID+" "+userid);
        
        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        jRadioButton1.setSelected(true);
        
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
            String Activity = (FNA+" ("+userid+") updated ("+productID+") in the system");
            
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
    
    public void setValues() throws ParseException{
    
        try{
            stmt = con.createStatement();
            ResultSet rsd2 = stmt.executeQuery("SELECT supplier_id FROM suppliers");
            while(rsd2.next()){
                String Value = rsd2.getString("supplier_id");
                suppliers.addItem(Value);
            }
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM products ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next())
            {
                if (productID.equals(rsd.getString(4)))
                {
                    this.dbName = rsd.getString(6);
                    this.dbSupplierID = rsd.getString(7);
                    this.dbType = rsd.getString(5);
                    this.dbManufacture = rsd.getString(12);
                    this.dbManufacureDate = rsd.getString(15);
                    this.dbExpireDate = rsd.getString(16);
                    this.dbSize = rsd.getString(14);
                    this.dbWeight = rsd.getDouble(13);
                    this.dbSuppyPrice = rsd.getDouble(10);
                    this.dbRetailPrice = rsd.getDouble(11);
                    this.dbQuntity = rsd.getInt(8);
                    this.dbDescription = rsd.getString(17);

                    System.out.println(dbName+" "+dbType+" "+dbManufacture+" "+dbManufacureDate+" "+dbExpireDate+" "+dbSize+" "+dbWeight+" "+dbSuppyPrice+" "+dbRetailPrice+" "+dbQuntity);
                    pr_name.setText(dbName);
                    TYPE.setText(dbType);
                    ID.setText(rsd.getString(4)+" | "+rsd.getString(2));
                    name.setText(dbName);
                    type.setSelectedItem(dbType);
                    manufacture.setText(dbManufacture);
                    description.setText(dbDescription);
                    
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dbManufacureDate);
                    manufactureDate.setDate(date);
                    
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dbExpireDate);
                    expireDate.setDate(date2);
                    
                    weight.setText(String.valueOf(dbWeight));
                    size.setText(dbSize);
                    jLabel9.setText(String.valueOf(dbQuntity)); 
//                    quntity.setText(String.valueOf(dbQuntity)); 
                    supplyPrice.setText(String.valueOf(dbSuppyPrice));
                    retailPrice.setText(String.valueOf(dbRetailPrice));

                }
                
            }
            supplierCheck();
        
        }
        catch(SQLException e){
        
            System.out.println("Error Showing data");
        }
        
        
        
    }
    public void supplierCheck(){
    
        try{
            boolean loopcheck = false;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT supplier_id FROM suppliers");
            while(rsd.next()){
                String Value = rsd.getString("supplier_id");
                if (dbSupplierID.equals(Value))
                {
                    loopcheck = true;
                    suppliers.setSelectedItem(dbSupplierID);  
                }
                
            }
            if(!loopcheck){

                    JOptionPane.showMessageDialog (null, "Product associating supplier removed from the system please update supplier or this will automatically be updated to default", "WARNING", JOptionPane.INFORMATION_MESSAGE);
                    UIManager UI=new UIManager();
                    UIManager.put("OptionPane.background", Color.white);
                    UIManager.put("Panel.background", Color.white);
            }
            
        }
        catch(HeadlessException | SQLException e){
        
            System.out.println("Error Supplyer check");
        }
    }

    public void genarateReference(){
    
        try {
            con = Database_connection_CLASS.connection();
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(`reference`) FROM `products_update_info`");
            rsd.next();
            rsd.getString("MAX(`reference`)");
            if (rsd.getString("MAX(`reference`)") == null) {
                itemidset = "PREF00001";
                
                this.pReference = itemidset;
                System.out.println(pReference);
            } 
            else {
                long id = Long.parseLong(rsd.getString("MAX(`reference`)").substring(4, rsd.getString("MAX(`reference`)").length()));
                id++;
                itemidset = ("PREF" + String.format("%05d", id));
                
                this.pReference = itemidset;
                System.out.println(pReference);
                }
            } 
        catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        } 
    }
    
    public void refUpdateandMove (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String ref = pReference;
            System.out.println(ref + "  "+ pReference+productID);
            String stat = "Updated";
            String Query = "UPDATE `products` SET `reference` = ?, `status` = ? WHERE `product_id` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, pReference);
            preparedStmt.setString(2, stat);
            preparedStmt.setString(3, productID);
            preparedStmt.execute();
            
            String Query1 = "INSERT INTO `products_history`(`item_listed_date`, `created_user`, `product_id`, `type`, `name`, `supplier_id`, `quantity`, `currency`, `supply_price`, `retail_price`, `manufacturer_or_brand`, `weight`, `size`, `manufacture_date`, `expiry date`, `description`, `total_quntity`, `reference`,`status`) SELECT `item_listed_date`, `created_user`, `product_id`, `type`, `name`, `supplier_id`, `quantity`, `currency`, `supply_price`, `retail_price`, `manufacturer_or_brand`, `weight`, `size`, `manufacture_date`, `expiry date`, `description`, `total_quntity`, `reference`,`status` FROM `products` WHERE `product_id` = ?;";
            
            PreparedStatement preparedStmt2 = con.prepareStatement(Query1);
            preparedStmt2.setString(1, productID);   
            preparedStmt2.execute();

        }
        catch(SQLException e){
        
            System.out.println("Error reference update and move");
        }
    } 
    
    public void updateValues(){
    
        try{
            

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String MANUFACTUREDATE = date.format(manufactureDate.getDate());
            String EXPIREDATE = date.format(expireDate.getDate());
            String NM = name.getText();
            String SU = suppliers.getSelectedItem().toString();
            String NT = description.getText(); 
            String TY = type.getSelectedItem().toString();
            

            String MN = manufacture.getText();
            double WE = Double.parseDouble(weight.getText());
            String SI = size.getText();
            int QN = Integer.parseInt(quntity.getText());
            double SP = Double.parseDouble(supplyPrice.getText());
            double RP = Double.parseDouble(retailPrice.getText());
            
            if (jRadioButton1.isSelected()) {
                
                this.newQun = dbQuntity + QN;
               
            }
            else{
            
                this.newQun = dbQuntity - QN;
            }
            

            String Query = "UPDATE `products` SET `type` = ?, `name` = ?, `supplier_id` =?, `description`= ? , `quantity` = ?, `supply_price` = ?, `retail_price` = ?, `manufacturer_or_brand` = ?, `weight` = ?, `size` = ?, `manufacture_date` = ?, `expiry date` = ?, `total_quntity` = ? WHERE `product_id` = ?;";

            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, TY);
            preparedStmt.setString(2, NM);
            preparedStmt.setString(3, SU);
            preparedStmt.setString(4, NT);
            preparedStmt.setInt(5, newQun);
            preparedStmt.setDouble(6, SP);
            preparedStmt.setDouble(7, RP);
            preparedStmt.setString(8, MN);
            preparedStmt.setDouble(9, WE);
            preparedStmt.setString(10, SI);
            preparedStmt.setString(11, MANUFACTUREDATE);
            preparedStmt.setString(12, EXPIREDATE);
            preparedStmt.setInt(13, newQun);
            preparedStmt.setString(14, productID);

            preparedStmt.execute();

        
        }
        catch(NumberFormatException | SQLException e){
        
            System.out.println("Error Updating data");
        }
    }
    
    public void updateInformationUpdate(){
        
        try{

            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            Date date2 = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  
            String strDate = dateFormat.format(date2);  
            
            System.out.println(productID + userid + pReference +strDate );
        
            String Query ="INSERT INTO `products_update_info`(`product_id`, `updated_user`, `time`, `reference`) VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, productID);
            preparedStmt.setString(2, userid);
            preparedStmt.setString(3, strDate);
            preparedStmt.setString(4, pReference);
            preparedStmt.execute();
        }
        catch(SQLException e){
    
            System.out.println("Error updating update information");
        
        }

    }
    
    public void ChangesCheck() throws SQLException, ParseException{
    
        try{
            
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String MANUFACTUREDATE = date.format(manufactureDate.getDate());
            String EXPIREDATE = date.format(expireDate.getDate());

            String NM = name.getText();
            String TY = type.getSelectedItem().toString();
            String MN = manufacture.getText();
            String SU = suppliers.getSelectedItem().toString();
            String NT = description.getText();
            double WE = Double.parseDouble(weight.getText());
            String SI = size.getText();
            int QN = Integer.parseInt(quntity.getText());
            double SP = Double.parseDouble(supplyPrice.getText());
            double RP = Double.parseDouble(retailPrice.getText());
        

            if(dbName.equals(NM) && dbType.equals(TY) && dbSupplierID.equals(SU) && dbDescription.equals(NT) && dbManufacture.equals(MN) && dbManufacureDate.equals(MANUFACTUREDATE) && dbExpireDate.equals(EXPIREDATE)  && dbSize.equals(SI) && dbWeight == WE && dbSuppyPrice == SP &&  dbRetailPrice == RP && QN == 0){
//                && dbQuntity == QN

                JOptionPane.showMessageDialog (null, "Nothing changed!", "FAILD", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
            }
            else{
                
                checkPermission();
            }
        
        }
        catch(HeadlessException | NumberFormatException e){
        
            System.out.println("Error In changes check");
        }
    }
    
    public void checkPermission() throws ParseException{
    
        try{
            
            if(null == userRole){

            }
            else switch (userRole) {
                case "admin":
                    dataProccess();
                    break;
                case "master":
                    dataProccess();
                    
                    break;
                default:
                    int res = JOptionPane.showOptionDialog(null, "Administrator password required for continue this process", "WARNING!",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
                    if (res == JOptionPane.YES_OPTION) {

                        con = Database_connection_CLASS.connection();
                        boolean loopcheck = false;
                        JPanel panel = new JPanel(new GridLayout(2, 2));
                        JLabel label = new JLabel("Enter username :");
                        JTextField username = new JTextField(10);

                        JLabel label2 = new JLabel("Enter a password :");
                        JPasswordField pass = new JPasswordField(10);

                        panel.add(label);
                        panel.add(username);
                        panel.add(label2);
                        panel.add(pass);

                        int action = JOptionPane.showConfirmDialog(null, panel,"Administrator permission required",JOptionPane.OK_CANCEL_OPTION);
                        if (action == JOptionPane.OK_OPTION){

                            String ussd = username.getText();
                            String passwd = new String(pass.getPassword());
                            stmt = con.createStatement();
                            String query = "SELECT * FROM users ";
                            ResultSet rsd = stmt.executeQuery(query);
                            while(rsd.next())
                            {
                                if (ussd.equals(rsd.getString(6)) && passwd.equals(rsd.getString(7)) &&("admin".equals(rsd.getString(8)) || "master".equals(rsd.getString(8))  )){

                                    loopcheck = true;
                                    dataProccess();
                                }
                            }
                            if(!loopcheck){
                                JOptionPane.showMessageDialog (null, "Incorrect Try Again!", "FAILD!", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else{
                             JOptionPane.showMessageDialog(null,"Canselled ");
                        }
                    }
                    break;
            }  
            
        
        }
        catch(HeadlessException | SQLException e){
            System.out.println("Error Checking permission");
        }
    }
    
    public void dataProccess() throws ParseException{
    
        try{
            
            refUpdateandMove ();
            updateValues();
            updateInformationUpdate();
            JOptionPane.showMessageDialog (null, "Product Details successfully updated", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            UIManager UI=new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
            
            activity();
            setValues();
            genarateReference();
        }
        catch(HeadlessException e){
        
            System.out.println("Error proccess data");
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
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pr_name = new javax.swing.JLabel();
        TYPE = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        type = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        suppliers = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        manufacture = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        manufactureDate = new com.toedter.calendar.JDateChooser();
        expireDate = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        weight = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        size = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        quntity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        supplyPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        retailPrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        button1 = new accessories.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pr_name.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        pr_name.setForeground(new java.awt.Color(0, 102, 102));
        pr_name.setText("Dinusha Weerakoon");

        TYPE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        TYPE.setText("Sri lanka");

        ID.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        ID.setForeground(new java.awt.Color(102, 102, 102));
        ID.setText("Company");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Name");

        name.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        type.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Appliances ", "Apps & Games", "Arts, Crafts, & Sewing", "Automotive Parts & Accessories", "Beauty & Personal Care", "Books", "CDs & Vinyl", "Mobilel Phones & Accessories", "Clothing, Shoes and Jewelry", "Collectibles & Fine Art", "Computers", "Electronics", "Garden & Outdoor", "Grocery & Gourmet Food", "Handmade", "Health, Household & Baby Care", "Home & Kitchen", "Industrial & Scientific", "Luggage & Travel Gear", "Movies & TV", "Musical Instruments", "Office Products", "Pet Supplies", "Sports & Outdoors", "Tools & Home Improvement", "Toys & Games", "Video Games" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Type");

        suppliers.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        suppliers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Supplier");

        manufacture.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Manufacturer");

        manufactureDate.setDateFormatString("yyyy-MM-dd");
        manufactureDate.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        expireDate.setDateFormatString("yyyy-MM-dd");
        expireDate.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Expire date");

        weight.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Weight");

        size.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Size");

        quntity.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        quntity.setText("0");
        quntity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quntityKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Quntity");

        supplyPrice.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Supply price");

        retailPrice.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Selling price");

        button1.setBackground(new java.awt.Color(255, 0, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Update");
        button1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Update products details");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Description");

        description.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Manufacture date");

        jRadioButton1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jRadioButton1.setText("+");

        jRadioButton2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jRadioButton2.setText("-");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Remaining Quntity");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(name)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(suppliers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(manufactureDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supplyPrice, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(weight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(expireDate, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(retailPrice)
                                    .addComponent(size)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(quntity, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(manufacture)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(description)
                    .addComponent(pr_name, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TYPE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(437, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(TYPE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pr_name, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manufacture, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(manufactureDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(expireDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(quntity)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplyPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(retailPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1591, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        try{
            
            try{
            int QN = Integer.parseInt(quntity.getText());
            if (jRadioButton1.isSelected()) {
                
                this.newQun = dbQuntity + QN;
                ChangesCheck();
                
            }
            else{
            
                if(dbQuntity < QN){
                
                    JOptionPane.showMessageDialog (null, "The number of items you are going to remove from the system is greater than the number of items in the system", "FAILD", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("The number of items you are going to remove from the system is greater than the number of items in the system");
                }
                else{
                    
                    this.newQun = dbQuntity - QN;
                    ChangesCheck();
                }
            }

        }
        catch(NumberFormatException e){
        
            System.out.println("Error checking data");
        }
            
            
            
        }
        catch(SQLException e){
            
        } catch (ParseException ex) {
            Logger.getLogger(Item_update.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }//GEN-LAST:event_button1ActionPerformed

    private void quntityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quntityKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_quntityKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JLabel TYPE;
    private accessories.Button button1;
    private javax.swing.JTextField description;
    private com.toedter.calendar.JDateChooser expireDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField manufacture;
    private com.toedter.calendar.JDateChooser manufactureDate;
    private javax.swing.JTextField name;
    private javax.swing.JLabel pr_name;
    private javax.swing.JTextField quntity;
    private javax.swing.JTextField retailPrice;
    private javax.swing.JTextField size;
    private javax.swing.JComboBox<String> suppliers;
    private javax.swing.JTextField supplyPrice;
    private javax.swing.JComboBox<String> type;
    private javax.swing.JTextField weight;
    // End of variables declaration//GEN-END:variables
}
