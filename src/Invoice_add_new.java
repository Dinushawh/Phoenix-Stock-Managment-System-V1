
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
public final class Invoice_add_new extends javax.swing.JInternalFrame {

    /**
     * Creates new form invoice_add_new
     * 
     */
    static String userid;
    private static String invoice_number;
    private static String item_id;
    private static String TA;
    
    public String ChangingID,ChanginDbQuntity,ChangingTableQuntity;
    public int No = 1;
    
    public String FNA;


    private static void Refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    
    private double  total_net_ammount, total_discounted_ammount,total_taxed_ammount,total_ammount;
    private String originalQun,newQuntity;
    
    public Invoice_add_new() {
        initComponents();
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

//      Remove edges
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
//        DATABASE CONNECTION SEETING UP
        con = Database_connection_CLASS.connection();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date = new Date(); 
        issued_date.setDate(date);

//        Table Border Remove
        cart_window.getViewport().setBackground(Color.WHITE);
        cart_window.setBorder(BorderFactory.createEmptyBorder());
        cart_window.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = Color.white;
        }
        });

//            un-display all error icons
        error_icon_01.setVisible(false);
        error_icon_02.setVisible(false);
        error_icon_03.setVisible(false);

//        Genarate New Invoice Number
        genarateInvoice();
        
//        Add Product to Dropdown
        try {
            
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT name FROM products");
            while(rsd.next()){
                String Value = rsd.getString("name");
                product_list.addItem(Value);
            
            }
            
                }
        catch (SQLException e) {
            System.out.println(e);
        }
        //        Add customer Dropdown
        try {
            
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT customer_id FROM customer");
            while(rsd.next()){
                String Value = rsd.getString("customer_id");
                customer_id.addItem(Value);
            }
        }
        catch (SQLException e) {
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
            String Activity = (FNA+" ("+userid+") added new invoice ("+invoice_number+") to the system");
            
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
    
    public void genarateInvoice(){
    
    try {
            String itemidset;
            stmt = con.createStatement();
            ResultSet QueryRun = stmt.executeQuery("SELECT MAX(invoice_number) FROM  invoice_details");
            QueryRun.next();
            QueryRun.getString("MAX(invoice_number)");
            if (QueryRun.getString("MAX(invoice_number)") == null) {
                itemidset = "INV00001";
                invoice_no.setText(itemidset);
                Invoice_add_new.invoice_number = itemidset;

            } else {
                long id = Long.parseLong(QueryRun.getString("MAX(invoice_number)").substring(3, QueryRun.getString("MAX(invoice_number)").length()));
                id++;
                itemidset = ("INV" + String.format("%05d", id));
                invoice_no.setText(itemidset);  
                Invoice_add_new.invoice_number = itemidset;
                }
            } 
        catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        }
        
        
    }
    
    public void clearAll(){
        quantity.setText("");
        discount.setText("0");
        tax.setText("0");
        note.setText("");
        tot_ammount.setText("00.00");
        tot_tax.setText("00.00");
        tot_discount.setText("00.00");
        tot_sub_total.setText("00.00");
        genarateInvoice();
        
    }
     
    public void caculate_totals(double net_ammount,double disccouned_ammount,double taxed_ammount,double ammount){
        

                
        this.total_net_ammount = total_net_ammount + net_ammount;
        String total_net_ammount_string = Double.toString(total_net_ammount);
        tot_sub_total.setText(total_net_ammount_string);
        
        this.total_discounted_ammount = total_discounted_ammount + disccouned_ammount;
        String total_discounted_ammount_string = Double.toString(total_discounted_ammount);
        tot_discount.setText(total_discounted_ammount_string);
        
        this.total_taxed_ammount = total_taxed_ammount + taxed_ammount;
        String total_taxed_ammount_string = Double.toString(total_taxed_ammount);
        tot_tax.setText(total_taxed_ammount_string);
        
        this.total_ammount = total_ammount + ammount;
        String total_ammount_string = Double.toString(total_ammount);
        this.TA = total_ammount_string;
        tot_ammount.setText(total_ammount_string);
 
    }
    
    public void addToinvoiceDetails(){
        
        try{
            
            Date date2 = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  
            String strDate = dateFormat.format(date2);  

            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();

//              Adding ivoice Basic data
            String IN = invoice_number;
            String UI = userid;
            String CUSTOMERID = customer_id.getSelectedItem().toString();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String ISSUE = date.format(issued_date.getDate());
            String DUEDATE = date.format(due_date.getDate());
            String FULLNAME = fullname.getText();
            jLabel3.setText(UI);
            String ADDRESS = address.getText();
            String PHONE = contact_number.getText();
            double TOTAL = total_ammount;
            String PAYMENTMETHOD = payment.getSelectedItem().toString();
            double SUBTOTAL = total_net_ammount;
            double TOTDICOUNTAMMOUNT = total_discounted_ammount;
            double TOTTAXEDAMMOUNT = total_taxed_ammount;
            String STATUS = "Raw";

            String Query = "INSERT INTO `invoice_details` (`handle_user`,`invoice_number`, `date`, `due_date`, `customer_id`, "
                    + "`fullname`, `address`, `phone_number`, `invoice_total`, `payment_method`,`invoiced_time`, `sub_total`, "
                    + "`total_discounted_ammount`, `total_taxed_ammount`,`invoice_status`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, UI);
            preparedStmt.setString(2, IN);
            preparedStmt.setString(3, ISSUE);
            preparedStmt.setString(4, DUEDATE);
            preparedStmt.setString(5, CUSTOMERID);
            preparedStmt.setString(6, FULLNAME);
            preparedStmt.setString(7, ADDRESS);
            preparedStmt.setString(8, PHONE);
            preparedStmt.setDouble(9, TOTAL);
            preparedStmt.setString(10, PAYMENTMETHOD);
            preparedStmt.setString(11, strDate);
            preparedStmt.setDouble(12, SUBTOTAL);
            preparedStmt.setDouble(13, TOTDICOUNTAMMOUNT);
            preparedStmt.setDouble(14, TOTTAXEDAMMOUNT);
            preparedStmt.setString(15, STATUS);
            preparedStmt.execute();    
        }
        catch(SQLException e){
        } 
    }
    
    public void QuntityCalculation(){
        try{
            
            String id = ChangingID;
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT * FROM products");
            while(rsd.next()){
                if (ChangingID.equals(rsd.getString(4)))
                {
                    this.ChanginDbQuntity = rsd.getString(8);
                }  
            }
            
            int Qn = Integer.parseInt(ChanginDbQuntity);
            int Qn2 = Integer.parseInt(ChangingTableQuntity);
            String Qn3 = String.valueOf(Qn - Qn2);

            stmt = con.createStatement();
            String query ="UPDATE products SET  quantity = ?  WHERE product_id = ? ";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, Qn3);
            preparedStmt.setString(2, id);
            preparedStmt.execute();
        }
        catch(NumberFormatException | SQLException e){

        }
    }
    

    public void addToinvoiceData(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
                
            DefaultTableModel dtm = (DefaultTableModel) cart_data.getModel();
            for(int i=0; i < dtm.getRowCount() ; i++){
                int RowID = (i);
                con = Database_connection_CLASS.connection();
                stmt = con.createStatement();
                String invoice = invoice_number;
                String id = cart_data.getValueAt(i, 1).toString();this.ChangingID =id;
                String name = cart_data.getValueAt(i, 2).toString();
                String unitprice = cart_data.getValueAt(i, 3).toString();
                String quntity = cart_data.getValueAt(i, 4).toString(); this.newQuntity = quntity;this.ChangingTableQuntity = quntity;
                String idiscount  = cart_data.getValueAt(i, 5).toString();
                String itax = cart_data.getValueAt(i, 6).toString();
                String note = cart_data.getValueAt(i, 7).toString();
                String netamount = cart_data.getValueAt(i, 8).toString();
                String discountammount = cart_data.getValueAt(i, 9).toString();
                String taxedammount = cart_data.getValueAt(i, 10).toString();
                String finaleammount = cart_data.getValueAt(i, 11).toString();
                String Query2 = "INSERT INTO `invoice_item`(`invoice_no`, `item_id`, `item_name`, "
                        + "`unit_price`, `quantity`, `discount`, `tax`, `note`, `net_amount`, "
                        + "`disconted_ammount`, `taxed_ammount`, `finale_ammount`, `row_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement preparedStmt2 = con.prepareStatement(Query2);
                preparedStmt2.setString(1, invoice);
                preparedStmt2.setString(2, id);
                preparedStmt2.setString(3, name);
                preparedStmt2.setString(4, unitprice);
                preparedStmt2.setString(5, quntity);
                preparedStmt2.setString(6, idiscount);
                preparedStmt2.setString(7, itax);
                preparedStmt2.setString(8, note);
                preparedStmt2.setString(9, netamount);
                preparedStmt2.setString(10, discountammount);
                preparedStmt2.setString(11, taxedammount);
                preparedStmt2.setString(12, finaleammount);
                preparedStmt2.setInt(13, RowID);
                preparedStmt2.execute();
                QuntityCalculation();

            }  
        }
        catch(SQLException e){
                    
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
        jLabel2 = new javax.swing.JLabel();
        error_icon_01 = new javax.swing.JLabel();
        error_icon_03 = new javax.swing.JLabel();
        error_icon_02 = new javax.swing.JLabel();
        cart_window = new javax.swing.JScrollPane();
        cart_data = new javax.swing.JTable();
        product_list = new javax.swing.JComboBox<>();
        quantity = new javax.swing.JTextField();
        tot_ammount = new javax.swing.JLabel();
        customer_id = new javax.swing.JComboBox<>();
        tot_sub_total = new javax.swing.JLabel();
        tot_discount = new javax.swing.JLabel();
        tot_tax = new javax.swing.JLabel();
        note = new javax.swing.JTextField();
        discount = new javax.swing.JTextField();
        tax = new javax.swing.JTextField();
        invoice_no = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        add_to_cart = new accessories.Button();
        Invoice_form = new javax.swing.JLabel();
        due_date = new com.toedter.calendar.JDateChooser();
        issued_date = new com.toedter.calendar.JDateChooser();
        contact_number = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        fullname = new javax.swing.JTextField();
        payment = new javax.swing.JComboBox<>();
        print = new accessories.Button();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 390, 30));

        error_icon_01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        error_icon_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N
        jPanel1.add(error_icon_01, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 30, 40));

        error_icon_03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        error_icon_03.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N
        jPanel1.add(error_icon_03, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 120, 30, 40));

        error_icon_02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        error_icon_02.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_20px.png"))); // NOI18N
        jPanel1.add(error_icon_02, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 30, 40));

        cart_data.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cart_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Item ID", "Item Name", "Unit Price", "Quantity", "Discount", "Tax", "Additional Notes", "Net Ammount", "Discounted Ammount", "Taxed Ammount", "Ammoint", "DatabaseQuntity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cart_data.setRowHeight(35);
        cart_data.setSelectionBackground(new java.awt.Color(0, 102, 102));
        cart_data.getTableHeader().setReorderingAllowed(false);
        cart_window.setViewportView(cart_data);
        if (cart_data.getColumnModel().getColumnCount() > 0) {
            cart_data.getColumnModel().getColumn(0).setResizable(false);
            cart_data.getColumnModel().getColumn(0).setPreferredWidth(30);
            cart_data.getColumnModel().getColumn(1).setResizable(false);
            cart_data.getColumnModel().getColumn(2).setResizable(false);
            cart_data.getColumnModel().getColumn(2).setPreferredWidth(200);
            cart_data.getColumnModel().getColumn(3).setResizable(false);
            cart_data.getColumnModel().getColumn(3).setPreferredWidth(80);
            cart_data.getColumnModel().getColumn(4).setResizable(false);
            cart_data.getColumnModel().getColumn(4).setPreferredWidth(80);
            cart_data.getColumnModel().getColumn(5).setResizable(false);
            cart_data.getColumnModel().getColumn(5).setPreferredWidth(80);
            cart_data.getColumnModel().getColumn(6).setResizable(false);
            cart_data.getColumnModel().getColumn(6).setPreferredWidth(80);
            cart_data.getColumnModel().getColumn(7).setResizable(false);
            cart_data.getColumnModel().getColumn(7).setPreferredWidth(250);
            cart_data.getColumnModel().getColumn(8).setResizable(false);
            cart_data.getColumnModel().getColumn(8).setPreferredWidth(80);
            cart_data.getColumnModel().getColumn(9).setResizable(false);
            cart_data.getColumnModel().getColumn(9).setPreferredWidth(80);
            cart_data.getColumnModel().getColumn(10).setResizable(false);
            cart_data.getColumnModel().getColumn(10).setPreferredWidth(80);
            cart_data.getColumnModel().getColumn(11).setResizable(false);
            cart_data.getColumnModel().getColumn(12).setMinWidth(0);
            cart_data.getColumnModel().getColumn(12).setPreferredWidth(0);
            cart_data.getColumnModel().getColumn(12).setMaxWidth(0);
        }

        jPanel1.add(cart_window, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 1530, 290));

        product_list.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        product_list.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(product_list, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 240, 40));

        quantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quantity.setForeground(new java.awt.Color(0, 153, 153));
        quantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
        });
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 150, 40));

        tot_ammount.setFont(new java.awt.Font("SansSerif", 1, 40)); // NOI18N
        tot_ammount.setForeground(new java.awt.Color(255, 51, 51));
        tot_ammount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tot_ammount.setText("00.00");
        jPanel1.add(tot_ammount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 600, 200, 60));

        customer_id.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        customer_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual" }));
        customer_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_idActionPerformed(evt);
            }
        });
        jPanel1.add(customer_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 210, 40));

        tot_sub_total.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        tot_sub_total.setForeground(new java.awt.Color(0, 102, 102));
        tot_sub_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tot_sub_total.setText("00.00");
        jPanel1.add(tot_sub_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 480, 190, 40));

        tot_discount.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        tot_discount.setForeground(new java.awt.Color(0, 102, 102));
        tot_discount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tot_discount.setText("00.00");
        jPanel1.add(tot_discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 520, 210, 40));

        tot_tax.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        tot_tax.setForeground(new java.awt.Color(0, 102, 102));
        tot_tax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tot_tax.setText("00.00");
        jPanel1.add(tot_tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 560, 200, 40));

        note.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(note, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, 380, 40));

        discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        discount.setForeground(new java.awt.Color(0, 153, 153));
        discount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discount.setText("0");
        discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountKeyReleased(evt);
            }
        });
        jPanel1.add(discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 150, 40));

        tax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tax.setForeground(new java.awt.Color(0, 153, 153));
        tax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax.setText("0");
        tax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                taxKeyReleased(evt);
            }
        });
        jPanel1.add(tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 150, 40));

        invoice_no.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        invoice_no.setForeground(new java.awt.Color(0, 102, 102));
        invoice_no.setText("jLabel3");
        jPanel1.add(invoice_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 30, 170, 40));

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, -1, -1));

        add_to_cart.setBackground(new java.awt.Color(0, 102, 102));
        add_to_cart.setForeground(new java.awt.Color(255, 255, 255));
        add_to_cart.setText("Add to cart");
        add_to_cart.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        add_to_cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_cartActionPerformed(evt);
            }
        });
        jPanel1.add(add_to_cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 120, 190, 40));

        Invoice_form.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Forms/sell_form01-03.png"))); // NOI18N
        jPanel1.add(Invoice_form, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, -10, 1540, 224));

        due_date.setForeground(new java.awt.Color(0, 153, 153));
        due_date.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jPanel1.add(due_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 210, 40));

        issued_date.setForeground(new java.awt.Color(0, 153, 153));
        issued_date.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jPanel1.add(issued_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 640, 210, 40));

        contact_number.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        contact_number.setForeground(new java.awt.Color(0, 153, 153));
        contact_number.setText("NULL");
        jPanel1.add(contact_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 560, 210, 40));

        address.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        address.setForeground(new java.awt.Color(0, 153, 153));
        address.setText("NULL");
        jPanel1.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 560, 230, 40));

        fullname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        fullname.setForeground(new java.awt.Color(0, 153, 153));
        fullname.setText("NULL");
        jPanel1.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 560, 210, 40));

        payment.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Checque", "Debit / Credit Card" }));
        jPanel1.add(payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, 240, 40));

        print.setBackground(new java.awt.Color(0, 102, 102));
        print.setForeground(new java.awt.Color(255, 255, 255));
        print.setText("Proceed to pay and print");
        print.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        jPanel1.add(print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 670, 440, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Forms/form02-05.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 1580, 240));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased
        // TODO add your handling code here:
        try{

            String productname = product_list.getSelectedItem().toString();
            int product_quantity = Integer.parseInt(quantity.getText());
 
            ResultSet rsd = stmt.executeQuery("SELECT * FROM products");
            while(rsd.next()){
                if (productname.equals(rsd.getString(6)))
                {
                    
                      if(product_quantity > rsd.getInt(8) )
                      {
                          error_icon_01.setVisible(true);
                          add_to_cart.setEnabled(false);
                      
                      }
                      else{
                            error_icon_01.setVisible(false);
                            error_icon_02.setVisible(false);
                            error_icon_03.setVisible(false); 
                            add_to_cart.setEnabled(true);
                    }                      
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_quantityKeyReleased

    private void discountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountKeyReleased
        // TODO add your handling code here:
        try{

            if(Double.parseDouble(discount.getText()) > 100)
            {
                error_icon_02.setVisible(true);
                
            }
            else{
                
                error_icon_01.setVisible(false);
                error_icon_02.setVisible(false);
                error_icon_03.setVisible(false);
                add_to_cart.setEnabled(true);
            }
        
        }
        catch(NumberFormatException e){
            
            System.out.println(e);
        }
    }//GEN-LAST:event_discountKeyReleased

    private void taxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taxKeyReleased
        // TODO add your handling code here:
        try{
            
            int chck = Integer.parseInt(tax.getText());
            
            if(Double.parseDouble(tax.getText()) > 100){
                
                error_icon_03.setVisible(true);
            
            }
            else{
                
                error_icon_01.setVisible(false);
                error_icon_02.setVisible(false);
                error_icon_03.setVisible(false);
            }
        
        }
        catch(NumberFormatException e){
            
            error_icon_03.setVisible(true);
        }
    }//GEN-LAST:event_taxKeyReleased

    private void customer_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_idActionPerformed
        // TODO add your handling code here:
        try{
            String CUSTOMERID = customer_id.getSelectedItem().toString();
            if("Indivdual".equals(CUSTOMERID)){
                
                fullname.setText("NULL");
                address.setText("NULL");
                contact_number.setText("NULL");
                
            }
            else{
            
                stmt = con.createStatement();
                ResultSet rsd = stmt.executeQuery("SELECT * FROM customer");
                while(rsd.next()){
                    if (customer_id.getSelectedItem().toString().equals(rsd.getString(2)))
                    {
                        fullname.setText(rsd.getString(5) + " " + rsd.getString(6));
                        address.setText(rsd.getString(8));
                        contact_number.setText(rsd.getString(7));
                    }  
                }
            }
        }
        catch(SQLException e){
        }
    }//GEN-LAST:event_customer_idActionPerformed

    private void add_to_cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_cartActionPerformed
        // TODO add your handling code here:
        try{
        
            if(quantity.getText().equals("")){
                
                error_icon_01.setVisible(true);
                error_icon_02.setVisible(true);
                error_icon_03.setVisible(true); 
                add_to_cart.setEnabled(false);
            }
            else{
                
                add_to_cart.setEnabled(true);
                double dbunitprice2 = 0;
                stmt = con.createStatement();
                ResultSet rsd = stmt.executeQuery("SELECT * FROM products");
                while(rsd.next()){
                    if (product_list.getSelectedItem().toString().equals(rsd.getString(6)))
                    {
                        this.item_id = rsd.getString(4);
                        double dbunitprice = (rsd.getDouble(11));
                        dbunitprice2 = dbunitprice;
                        
                        }  
                }
                String ii = item_id;

                int Quntity = Integer.parseInt(quantity.getText());
                double Disccount = Double.parseDouble(discount.getText());
                double Tax = Double.parseDouble(tax.getText());
                
                double tem_net_ammount = dbunitprice2 * Quntity;
                double tem_disccouned_ammount = tem_net_ammount * (Disccount/100);
                double tem_taxed_ammount = tem_net_ammount * (Tax/100);
                double tem_ammount = (tem_net_ammount - tem_disccouned_ammount) + tem_taxed_ammount;
                String cart_net_ammount = Double.toString(tem_net_ammount);
                String cart_disccouned_ammount = Double.toString(tem_disccouned_ammount);
                String cart_taxed_ammount = Double.toString(tem_taxed_ammount);
                String cart_ammount = Double.toString(tem_ammount);
                caculate_totals(tem_net_ammount,tem_disccouned_ammount,tem_taxed_ammount,tem_ammount);
                
                String no = String.valueOf(No);
                this.No++;
                String cart_unit_price = Double.toString(dbunitprice2);
           
                String Table_Data[] = {no,ii,product_list.getSelectedItem().toString(),cart_unit_price,quantity.getText(),discount.getText(),tax.getText(),note.getText(),cart_net_ammount,
                    cart_disccouned_ammount,cart_taxed_ammount,cart_ammount};
                DefaultTableModel DTM = (DefaultTableModel)cart_data.getModel();
                DTM.addRow(Table_Data);
                quantity.setText("");
                discount.setText("0");
                tax.setText("0");
                note.setText("");
            }
        }
        catch(NumberFormatException | SQLException e){
        
        }
    }//GEN-LAST:event_add_to_cartActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
         DefaultTableModel dtm = (DefaultTableModel) cart_data.getModel();
        if(dtm.getRowCount()== 0){
            JOptionPane.showMessageDialog(this, "Nothing Inputed");
        }
        else{
            try{
                
                addToinvoiceDetails();
                addToinvoiceData();
                activity();
                JOptionPane.showMessageDialog (null, "product successfully added to the system", "successful", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                Object put = UIManager.put("Panel.background", Color.white);
                clearAll();
                DefaultTableModel tMOdel = (DefaultTableModel) cart_data.getModel();
                tMOdel.setRowCount(0);
                
                
            }
            catch(HeadlessException e){
            
            }
 
        } 
    }//GEN-LAST:event_printActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Invoice_form;
    private accessories.Button add_to_cart;
    private javax.swing.JTextField address;
    private javax.swing.JTable cart_data;
    private javax.swing.JScrollPane cart_window;
    private javax.swing.JTextField contact_number;
    private javax.swing.JComboBox<String> customer_id;
    private javax.swing.JTextField discount;
    private com.toedter.calendar.JDateChooser due_date;
    private javax.swing.JLabel error_icon_01;
    private javax.swing.JLabel error_icon_02;
    private javax.swing.JLabel error_icon_03;
    private javax.swing.JTextField fullname;
    private javax.swing.JLabel invoice_no;
    private com.toedter.calendar.JDateChooser issued_date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField note;
    private javax.swing.JComboBox<String> payment;
    private accessories.Button print;
    private javax.swing.JComboBox<String> product_list;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField tax;
    private javax.swing.JLabel tot_ammount;
    private javax.swing.JLabel tot_discount;
    private javax.swing.JLabel tot_sub_total;
    private javax.swing.JLabel tot_tax;
    // End of variables declaration//GEN-END:variables
}
