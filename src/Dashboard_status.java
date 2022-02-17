
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
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
public final class Dashboard_status extends javax.swing.JInternalFrame {

    /**
     * Creates new form dashboard_status
     */
    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public String itmid;
    public double supplyPrice,stockValue,netsales,dbRetail,dbSupply,netProfit,proft;
    public int Productcount,userCount,supplierCount,customerCount,count1,count2;
    
    public Dashboard_status() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        init();
        scrollWindow.getViewport().setBackground(Color.WHITE);
        scrollWindow.setBorder(BorderFactory.createEmptyBorder());
        setstockValue();
        setnetSales ();
        setnetProfit();
        setProducts ();
        setUsercount();
        setSuppliercount();
        setCustomercount();
        stockAvailability();
    }
    
    public void setstockValue (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM products ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                double 	quantity = rsd.getDouble(18);
                this.supplyPrice = rsd.getDouble(10);
                double pri = quantity * supplyPrice;
                stockValue = stockValue + pri;
            }
            stockvalue.setText(String.valueOf(stockValue));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    public void setnetSales (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM invoice_details ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                double 	totbalance = rsd.getDouble(10);;
                netsales = netsales + totbalance;
            }
            netSales.setText(String.valueOf(netsales));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    public void setnetProfit (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM invoice_item ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                this.itmid = rsd.getString(3);
                int Qun = rsd.getInt(6);
                getItemDetails();
                double q1 = proft * Qun;
                System.out.println(q1);
                this.netProfit = netProfit + q1;
            }
            netprofit.setText(String.valueOf(netProfit));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    public void getItemDetails(){
        try{
            boolean loopcheck = false;
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query2 = "SELECT * FROM products ";
            ResultSet rsd = stmt.executeQuery(query2);
            while(rsd.next()){
                if (itmid.equals(rsd.getString(4)))
                    {
                        loopcheck = true;
                        dbSupply = rsd.getDouble(10);
                        System.out.println(dbSupply);
                        dbRetail = rsd.getDouble(11);
                        System.out.println(dbRetail);
                        this.proft = dbRetail - dbSupply;
                }
            }
            if(!loopcheck){}
        }
        
        catch(SQLException e){
        
            
        }
    }
    
    public void setProducts (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM products ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                Productcount++;
            }
            product.setText(String.valueOf(Productcount));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    public void setUsercount (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM users ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                userCount++;
            }
            user.setText(String.valueOf(userCount));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    public void setSuppliercount (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM suppliers ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                supplierCount++;
            }
            supplier.setText(String.valueOf(supplierCount));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    public void setCustomercount (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM customer ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                customerCount++;
            }
            customer.setText(String.valueOf(customerCount));
        }
        catch(SQLException e){
            System.out.println("Error getting data");
        }
    }
    
    public void stockAvailability(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM products ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next()){
                int tt = rsd.getInt(8);
                int tt2 = rsd.getInt(18);
                count1 = count1 + tt;
                count2 = count2 + tt2;
            }
            int countVal = (count1 * 100)/ count2;
            progress1.setValue(countVal);
            progress1.start();
        }
        catch(Exception e){
        
        }
    }
    
    
    private void init(){
    
//        String value01 = ("RS."+ 25000);
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/stock.png")), "Available Stock", value01, " "));
//        
////        String value02 = ("RS."+ 25000);
////        card5.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/stock.png")), "Total Profit", value01, " "));
//        
//        String value03 = ("25000");
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/stock.png")), "Current Stock (units)", value01, " "));
////        progress2.setValue(Integer.parseInt(jTextField1.getText()));
//        progress2.start();
        

        
        
//        int a = 100;
//        int b = 300;
//        int c = 500;
//        int d = 700;
//////        
//        curveChart1.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));
//        curveChart1.addLegend("Expense", new Color(54, 4, 143), new Color(104, 49, 200));
//        curveChart1.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
//        curveChart1.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
//        curveChart1.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
//        curveChart1.addData(new ModelChart("February", new double[]{a, b, c, d}));
//        curveChart1.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
//        curveChart1.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
//        curveChart1.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
//        curveChart1.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
//        curveChart1.start();
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollWindow = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stockvalue = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        netprofit = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        customer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        supplier = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        product = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        netSales = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        product1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        progress1 = new accessories.Progress();
        jLabel24 = new javax.swing.JLabel();
        progress2 = new accessories.Progress();
        jLabel25 = new javax.swing.JLabel();
        progress3 = new accessories.Progress();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_bonds_48px_2.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel4.setText("Stock value");

        stockvalue.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        stockvalue.setText("Rs.25000");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel6.setText("Net sales");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_total_sales_48px.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel9.setText("Total profit");

        netprofit.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        netprofit.setText("Rs.25000");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_profit_48px.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel12.setText("users");

        user.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        user.setText("Rs.25000");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_staff_48px.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel14.setText("Customers");

        customer.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        customer.setText("Rs.25000");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_user_groups_48px.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel16.setText("Suppliers");

        supplier.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        supplier.setText("supplier");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_supplier_48px.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel19.setText("Number of products");

        product.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        product.setText("Rs.25000");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_aliexpress_48px.png"))); // NOI18N

        netSales.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        netSales.setText("Rs.25000");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel23.setText("Stock Availability");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel20.setText("Invoice issued");

        product1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        product1.setText("Rs.25000");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Dashboard/icons8_invoice_48px.png"))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel24.setText("Stock Availability");

        jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel25.setText("Stock Availability");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(stockvalue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(netSales, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(netprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(progress1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(progress2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(progress3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(customer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel3)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(netSales, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(stockvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(netprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(product1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel12))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progress1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progress2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progress3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(666, 666, 666))
        );

        scrollWindow.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollWindow, javax.swing.GroupLayout.DEFAULT_SIZE, 1633, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollWindow, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel customer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel netSales;
    private javax.swing.JLabel netprofit;
    private javax.swing.JLabel product;
    private javax.swing.JLabel product1;
    private accessories.Progress progress1;
    private accessories.Progress progress2;
    private accessories.Progress progress3;
    private javax.swing.JScrollPane scrollWindow;
    private javax.swing.JLabel stockvalue;
    private javax.swing.JLabel supplier;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
