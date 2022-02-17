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
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
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
public final class Item_add_new extends javax.swing.JInternalFrame {

    /**
     * Creates new form AdminAddItems
     */
    
    public static String userid,FNA;
    private static String productID;

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    
    public Item_add_new() {
        initComponents();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        Scrollwindow.getViewport().setBackground(Color.WHITE);
        Scrollwindow.setBorder(BorderFactory.createEmptyBorder());

        UIManager.put( "ScrollBar.thumbArc", 999 );
        
        /* remobe Border */
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
            
        genrateReferenece();
        setSuplierList();
        RemoveAllErrors();

        con = Database_connection_CLASS.connection();

    }
    
    public void RemoveAllErrors(){
    
        try{
            
            product_error_01.setVisible(false);
            product_error_02.setVisible(false);
            
            feildErrorMassage.setVisible(false);
            feildErrorIcon.setVisible(false);
            
            supply_price_error_01.setVisible(false);
            supply_price_error_02.setVisible(false);
            
            retail_price_error_01.setVisible(false);
            retail_price_error_02.setVisible(false);
            
            quantity_error_01.setVisible(false);
            quantity_error_02.setVisible(false);
            
            quantity_error_01.setVisible(false);
            quantity_error_02.setVisible(false);
            
            weight_error_01.setVisible(false);
            weight_error_02.setVisible(false);
            
            size_error_01.setVisible(false);
            size_error_02.setVisible(false);
        
        }
        catch(Exception e){
            System.out.println("RemoveAllErrors");
        }
    }
    
    public void setSuplierList(){
    
        try {
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT supplier_id FROM suppliers");
            while(rs.next()){
                String Value = rs.getString("supplier_id");
                supplirID.addItem(Value);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            System.out.println("setSuplierList");
        } 
    }
    
    public void genrateReferenece(){
    
        try {
            con = Database_connection_CLASS.connection();
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(product_id) FROM products");
            rsd.next();
            rsd.getString("MAX(product_id)");
            if (rsd.getString("MAX(product_id)") == null) {
                itemidset = "ITM00001";
                dbProductID.setText(itemidset);
                this.productID = itemidset;

            } else {
                long id = Long.parseLong(rsd.getString("MAX(product_id)").substring(3, rsd.getString("MAX(product_id)").length()));
                id++;
                itemidset = ("ITM" + String.format("%05d", id));
                dbProductID.setText(itemidset);  
                this.productID = itemidset;
                    }
            } 
        catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        } 
        
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
            String Activity = (FNA+" ("+userid+") add new produt ("+productID+") to the system");
            
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
    
    public void NameCheck(){
    
        try
        {
            stmt = con.createStatement();
//            String userid = username.getText();

            if( name.getText().equals("") )
            {
                product_error_01.setVisible(true);
                product_error_02.setVisible(true);
                product_error_02.setText("You must have a product name. This is the unique identifier for this product.");  
            }
            else
            {
                boolean loopcheck = false;
                stmt = con.createStatement();
                String query = "SELECT * FROM products ";
                String ProductName = name.getText();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    if (ProductName.equals(rs.getString(4)))
                    {
                        loopcheck = true;
                        product_error_01.setVisible(true);
                        product_error_02.setVisible(true);
                        Color orange = new Color(255,102,0);
                        product_error_02.setForeground(orange);
                        product_error_02.setText("Duplicate products found do you want to update?");
                    }
                }
                if(!loopcheck){
            
                }   
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
            
    }
    public void clearall (){
    
        name.setText("");
        manufacturer_or_brand.setText("");
        description.setText("");
        supply_price.setText("");
        retail_price.setText("");
        quntity.setText("");
        weight.setText("");
        size.setText("");
        manufactureDate.setCalendar(null);
        expireDate.setCalendar(null);
 
    }
    
    public void clearAllerrors(){
    
        product_error_01.setVisible(false);
        product_error_02.setVisible(false);
        supply_price_error_01.setVisible(false);
        supply_price_error_02.setVisible(false);
        retail_price_error_01.setVisible(false);
        retail_price_error_02.setVisible(false);
        quantity_error_01.setVisible(false);
        quantity_error_02.setVisible(false);
        weight_error_01.setVisible(false);
        weight_error_02.setVisible(false);
        size_error_01.setVisible(false);
        size_error_02.setVisible(false);
        
        
    }
   
    public void ErrrMassages()
    {
        feildErrorMassage.setVisible(false);
        feildErrorIcon.setVisible(false);
    }
    
    public void AddProductToDatabase(){
    
        try{
            String DBPRODUCTID = productID;
            String DBNAME = name.getText();
            String DBTYPE = type.getSelectedItem().toString();
            String DBBRAND = manufacturer_or_brand.getText();
            String DBDESCRIPTION = description.getText();
            String DBSUPPLIERID = supplirID.getSelectedItem().toString();
            String DBCURRENCY = currency.getSelectedItem().toString();         
            float SUPPLYPRICE = Float.parseFloat(supply_price.getText());
            float RETAILPRICE = Float.parseFloat(retail_price.getText());
            int DBQUNTITY = Integer.parseInt(quntity.getText());
            float DBWEIGHT = Float.parseFloat(weight.getText());
            float DBSIZE = Float.parseFloat(size.getText());

            SimpleDateFormat Manufacture_Date = new SimpleDateFormat("yyyy-MM-dd");
            String DBMANUFACTUREDATE = Manufacture_Date.format(manufactureDate.getDate());

            SimpleDateFormat Expire_Date = new SimpleDateFormat("yyyy-MM-dd");
            String DBEXPIREDATE = Expire_Date.format(expireDate.getDate());


            String query2 = "INSERT INTO products (`product_id`, `created_user`,`type`, `name`, `supplier_id`, `quantity`, `currency`, `supply_price`, `retail_price`,"
                    + " `manufacturer_or_brand`, `weight`, `size`, `manufacture_date`, "
                    + "`expiry date`, `description`,`total_quntity`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            PreparedStatement preparedStmt = con.prepareStatement(query2);
            preparedStmt.setString(1, DBPRODUCTID);
            preparedStmt.setString(2, userid);
            preparedStmt.setString(3, DBTYPE);
            preparedStmt.setString(4, DBNAME);
            preparedStmt.setString(5, DBSUPPLIERID);
            preparedStmt.setInt(6, DBQUNTITY);
            preparedStmt.setString(7, DBCURRENCY);
            preparedStmt.setFloat(8, SUPPLYPRICE);
            preparedStmt.setFloat(9, RETAILPRICE);
            preparedStmt.setString(10, DBBRAND);
            preparedStmt.setFloat(11, DBWEIGHT);
            preparedStmt.setFloat(12, DBSIZE);
            preparedStmt.setString(13, DBMANUFACTUREDATE);
            preparedStmt.setString(14, DBEXPIREDATE);
            preparedStmt.setString(15, DBDESCRIPTION);
            preparedStmt.setInt(16, DBQUNTITY);
            preparedStmt.execute();
            JOptionPane.showMessageDialog (null, "product successfully added to the system", "successful", JOptionPane.INFORMATION_MESSAGE);

        
        }
        catch(HeadlessException | NumberFormatException | SQLException e){
        
            
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
        Scrollwindow = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        feildErrorMassage = new javax.swing.JLabel();
        dbProductID = new javax.swing.JLabel();
        product_error_01 = new javax.swing.JLabel();
        product_error_02 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        quntity = new javax.swing.JTextField();
        supply_price = new javax.swing.JTextField();
        retail_price = new javax.swing.JTextField();
        manufacturer_or_brand = new javax.swing.JTextField();
        weight = new javax.swing.JTextField();
        size = new javax.swing.JTextField();
        description = new javax.swing.JTextField();
        feildErrorIcon = new javax.swing.JLabel();
        currency = new javax.swing.JComboBox<>();
        supply_price_error_02 = new javax.swing.JLabel();
        supply_price_error_01 = new javax.swing.JLabel();
        retail_price_error_02 = new javax.swing.JLabel();
        retail_price_error_01 = new javax.swing.JLabel();
        quantity_error_02 = new javax.swing.JLabel();
        quantity_error_01 = new javax.swing.JLabel();
        reset_all = new javax.swing.JLabel();
        weight_error_01 = new javax.swing.JLabel();
        weight_error_02 = new javax.swing.JLabel();
        size_error_01 = new javax.swing.JLabel();
        size_error_02 = new javax.swing.JLabel();
        expireDate = new com.toedter.calendar.JDateChooser();
        type = new javax.swing.JComboBox<>();
        manufactureDate = new com.toedter.calendar.JDateChooser();
        supplirID = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        save = new accessories.Button();
        button2 = new accessories.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        feildErrorMassage.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        feildErrorMassage.setText("jLabel2");
        jPanel2.add(feildErrorMassage, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 1050, 590, 80));

        dbProductID.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        dbProductID.setText("jLabel1");
        jPanel2.add(dbProductID, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 500, 40));

        product_error_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_box_important_28px.png"))); // NOI18N
        jPanel2.add(product_error_01, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 30, 40));

        product_error_02.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        product_error_02.setText("jLabel2");
        jPanel2.add(product_error_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 420, 40));

        name.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameKeyReleased(evt);
            }
        });
        jPanel2.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 360, 40));

        quntity.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        quntity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quntityKeyReleased(evt);
            }
        });
        jPanel2.add(quntity, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 770, 450, 40));

        supply_price.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        supply_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supply_priceKeyReleased(evt);
            }
        });
        jPanel2.add(supply_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 640, 250, 40));

        retail_price.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        retail_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                retail_priceKeyReleased(evt);
            }
        });
        jPanel2.add(retail_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 640, 250, 40));

        manufacturer_or_brand.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        manufacturer_or_brand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                manufacturer_or_brandKeyReleased(evt);
            }
        });
        jPanel2.add(manufacturer_or_brand, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 240, 300, 40));

        weight.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        weight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                weightKeyReleased(evt);
            }
        });
        jPanel2.add(weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 890, 350, 40));

        size.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        size.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sizeKeyReleased(evt);
            }
        });
        jPanel2.add(size, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 890, 350, 40));

        description.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descriptionKeyReleased(evt);
            }
        });
        jPanel2.add(description, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 1060, 120));

        feildErrorIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_28px.png"))); // NOI18N
        jPanel2.add(feildErrorIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1050, 50, 80));

        currency.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        currency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LKR", "USD", "EUR", "JPY" }));
        jPanel2.add(currency, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 640, 140, 40));

        supply_price_error_02.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        supply_price_error_02.setText("jLabel2");
        jPanel2.add(supply_price_error_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 680, 420, 40));

        supply_price_error_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_box_important_28px.png"))); // NOI18N
        jPanel2.add(supply_price_error_01, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 680, 30, 40));

        retail_price_error_02.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        retail_price_error_02.setText("jLabel2");
        jPanel2.add(retail_price_error_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 680, 420, 40));

        retail_price_error_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_box_important_28px.png"))); // NOI18N
        jPanel2.add(retail_price_error_01, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 680, 30, 40));

        quantity_error_02.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        quantity_error_02.setText("jLabel2");
        jPanel2.add(quantity_error_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 810, 420, 40));

        quantity_error_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_box_important_28px.png"))); // NOI18N
        jPanel2.add(quantity_error_01, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 810, 30, 40));

        reset_all.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        reset_all.setText("Add, view and edit your products all in one place. Need help?");
        jPanel2.add(reset_all, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1140, 770, -1));

        weight_error_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_box_important_28px.png"))); // NOI18N
        jPanel2.add(weight_error_01, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 930, 30, 40));

        weight_error_02.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        weight_error_02.setText("jLabel2");
        jPanel2.add(weight_error_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 930, 350, 40));

        size_error_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_box_important_28px.png"))); // NOI18N
        jPanel2.add(size_error_01, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 930, 30, 40));

        size_error_02.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        size_error_02.setText("jLabel2");
        jPanel2.add(size_error_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 930, 420, 40));

        expireDate.setBackground(new java.awt.Color(255, 255, 255));
        expireDate.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(expireDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1010, 350, 40));

        type.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Appliances ", "Apps & Games", "Arts, Crafts, & Sewing", "Automotive Parts & Accessories", "Beauty & Personal Care", "Books", "CDs & Vinyl", "Mobilel Phones & Accessories", "Clothing, Shoes and Jewelry", "Collectibles & Fine Art", "Computers", "Electronics", "Garden & Outdoor", "Grocery & Gourmet Food", "Handmade", "Health, Household & Baby Care", "Home & Kitchen", "Industrial & Scientific", "Luggage & Travel Gear", "Movies & TV", "Musical Instruments", "Office Products", "Pet Supplies", "Sports & Outdoors", "Tools & Home Improvement", "Toys & Games", "Video Games" }));
        jPanel2.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 300, 40));

        manufactureDate.setBackground(new java.awt.Color(255, 255, 255));
        manufactureDate.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(manufactureDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1010, 360, 40));

        supplirID.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        supplirID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual" }));
        jPanel2.add(supplirID, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 400, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Product id");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 190, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel4.setText("Product Type");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 200, 230, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("The type of product we choose determines  ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 300, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Inventory");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 280, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("how we manage inventory and reporting.");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 290, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("for this product");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel11.setText("SKU");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 90, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel12.setText("Retail Price");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 600, 190, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel13.setText("Product Name");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 160, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel14.setText("Brand");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 200, 160, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("Genaral");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 270, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("Change general information ");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 190, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel17.setText("Description");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 160, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel18.setText("Supplier Code");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 160, 40));

        jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel19.setText("Size");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 850, 420, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel20.setText("Supply Price ");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 600, 190, 40));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel21.setText("Currency");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 600, 160, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel22.setText("Quantity");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 730, 160, 40));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel23.setText("Expiration Date");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 970, 350, 40));

        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel24.setText("Weight");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 850, 60, 40));

        jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel25.setText("Manufacturing Date");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 970, 350, 40));
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1250, 110, 80));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Products/productAdd-06.png"))); // NOI18N
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -10, 1030, 180));

        save.setBackground(new java.awt.Color(0, 102, 102));
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("Save");
        save.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel2.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 1130, 190, 40));

        button2.setBackground(new java.awt.Color(0, 102, 102));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Reset");
        button2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel2.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 1130, 190, 40));

        Scrollwindow.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Scrollwindow, javax.swing.GroupLayout.DEFAULT_SIZE, 1593, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Scrollwindow, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
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

    private void nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyReleased
        // TODO add your handling code here:
        try
        {
            product_error_01.setVisible(false);
            product_error_02.setVisible(false);

            feildErrorMassage.setVisible(false);
            feildErrorIcon.setVisible(false);
            
            NameCheck();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_nameKeyReleased

    private void quntityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quntityKeyReleased
        // TODO add your handling code here:
        try
        {
            if(quntity.getText().equals("")){
                quantity_error_01.setVisible(false);
                quantity_error_02.setVisible(false);   
            }
            else{
                quantity_error_01.setVisible(false);
                quantity_error_02.setVisible(false);
                
                int check = Integer.parseInt(quntity.getText());  
            }
        }
        catch(NumberFormatException e)
        {
            quantity_error_01.setVisible(true);
            quantity_error_02.setVisible(true);
            quantity_error_02.setText("please enter a valid number using only numbers and float");
            
            System.out.println(e);
        }
    }//GEN-LAST:event_quntityKeyReleased

    private void supply_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supply_priceKeyReleased
        // TODO add your handling code here:
        try
        {
            if(supply_price.getText().equals("")){
                supply_price_error_01.setVisible(false);
                supply_price_error_02.setVisible(false);   
            }
            else{
                supply_price_error_01.setVisible(false);
                supply_price_error_02.setVisible(false);
                float check = Float.parseFloat(supply_price.getText());  
            }
        }
        catch(NumberFormatException e)
        {
            supply_price_error_01.setVisible(true);
            supply_price_error_02.setVisible(true);
            supply_price_error_02.setText("please enter a valid number using only numbers and float");
            
            System.out.println(e);
        }
    }//GEN-LAST:event_supply_priceKeyReleased

    private void retail_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_retail_priceKeyReleased
        // TODO add your handling code here:
        try
        {
            if(retail_price.getText().equals("")){
                retail_price_error_01.setVisible(false);
                retail_price_error_02.setVisible(false);   
            }
            else{
                retail_price_error_01.setVisible(false);
                retail_price_error_02.setVisible(false); 
                float check = Float.parseFloat(retail_price.getText());  
            }
        }
        catch(NumberFormatException e)
        {
            retail_price_error_01.setVisible(true);
            retail_price_error_02.setVisible(true); 
            retail_price_error_02.setText("please enter a valid number using only numbers and float");
            
            System.out.println(e);
        }
        
    }//GEN-LAST:event_retail_priceKeyReleased

    private void manufacturer_or_brandKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_manufacturer_or_brandKeyReleased
        // TODO add your handling code here:
        try
        {
             ErrrMassages();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_manufacturer_or_brandKeyReleased

    private void weightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_weightKeyReleased
        // TODO add your handling code here:
        try
        {
            if(weight.getText().equals("")){
                weight_error_01.setVisible(false);
                weight_error_02.setVisible(false); 
            }
            else{
                weight_error_01.setVisible(false);
                weight_error_02.setVisible(false);
                float check = Float.parseFloat(weight.getText());  
            }
        }
        catch(NumberFormatException e)
        {
            weight_error_01.setVisible(false);
            weight_error_02.setVisible(false);  
            weight_error_02.setText("please enter a valid number using only numbers and float");
            
            System.out.println(e);
        }
        
    }//GEN-LAST:event_weightKeyReleased

    private void sizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sizeKeyReleased
        // TODO add your handling code here:
        try
        {
            if(size.getText().equals("")){
                size_error_01.setVisible(false);
                size_error_02.setVisible(false);
            }
            else{
                size_error_01.setVisible(false);
                size_error_02.setVisible(false);
                float check = Float.parseFloat(size.getText());  
            }
        }
        catch(NumberFormatException e)
        {
            size_error_01.setVisible(true);
            size_error_02.setVisible(true);
            retail_price_error_02.setText("please enter a valid number using only numbers and float");
            
            System.out.println(e);
        }
    }//GEN-LAST:event_sizeKeyReleased

    private void descriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionKeyReleased
        // TODO add your handling code here:
        try
        {
             ErrrMassages();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_descriptionKeyReleased

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        try{
        
            clearall ();
            clearAllerrors();
        }
        catch(Exception e){
        
        }
        
    }//GEN-LAST:event_button2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        try
            {
                stmt = con.createStatement();

                    if( name.getText().equals("")|| type.getSelectedItem().toString().equals("") ||  manufacturer_or_brand.getText().equals("") || description.getText().equals("")||
                            supplirID.getSelectedItem().toString().equals("") || currency.getSelectedItem().toString().equals("") || supply_price.getText().equals("") || 
                            retail_price.getText().equals("") || quntity.getText().equals("") || weight.getText().equals("") || size.getText().equals("") 
                            || manufactureDate.getDateFormatString().equals("") || expireDate.getDateFormatString().equals(""))

                    {
                        feildErrorMassage.setVisible(true);
                        feildErrorIcon.setVisible(true);
                        Color orange = new Color(255,102,0);
                        feildErrorMassage.setForeground(orange);
                        feildErrorMassage.setText("some feilds are misssing!");
                    }
                    else 
                        {
                            AddProductToDatabase();
                            clearAllerrors();
                            clearall ();
                             activity();
                            genrateReferenece();

                            }

                }

            catch(SQLException e)
            {
                System.out.println(e);
            }
    }//GEN-LAST:event_saveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scrollwindow;
    private accessories.Button button2;
    private javax.swing.JComboBox<String> currency;
    private javax.swing.JLabel dbProductID;
    private javax.swing.JTextField description;
    private com.toedter.calendar.JDateChooser expireDate;
    private javax.swing.JLabel feildErrorIcon;
    private javax.swing.JLabel feildErrorMassage;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser manufactureDate;
    private javax.swing.JTextField manufacturer_or_brand;
    private javax.swing.JTextField name;
    private javax.swing.JLabel product_error_01;
    private javax.swing.JLabel product_error_02;
    private javax.swing.JLabel quantity_error_01;
    private javax.swing.JLabel quantity_error_02;
    private javax.swing.JTextField quntity;
    private javax.swing.JLabel reset_all;
    private javax.swing.JTextField retail_price;
    private javax.swing.JLabel retail_price_error_01;
    private javax.swing.JLabel retail_price_error_02;
    private accessories.Button save;
    private javax.swing.JTextField size;
    private javax.swing.JLabel size_error_01;
    private javax.swing.JLabel size_error_02;
    private javax.swing.JComboBox<String> supplirID;
    private javax.swing.JTextField supply_price;
    private javax.swing.JLabel supply_price_error_01;
    private javax.swing.JLabel supply_price_error_02;
    private javax.swing.JComboBox<String> type;
    private javax.swing.JTextField weight;
    private javax.swing.JLabel weight_error_01;
    private javax.swing.JLabel weight_error_02;
    // End of variables declaration//GEN-END:variables
}
