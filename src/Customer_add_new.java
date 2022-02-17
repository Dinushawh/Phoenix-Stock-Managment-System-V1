
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
public final class Customer_add_new extends javax.swing.JInternalFrame {

    /**
     * Creates new form customer_add_new
     */
    public static String userid,FNA;
    
    private static String customerID;
    
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public Customer_add_new() {
        initComponents();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        con = Database_connection_CLASS.connection();

        scroll_window.getViewport().setBackground(Color.WHITE);
        scroll_window.setBorder(BorderFactory.createEmptyBorder());
        
        
        feilds_error_icon.setVisible(false);
        feilds_error_massage.setVisible(false);
        
        genarateRef();
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
            String Activity = (FNA+" ("+userid+") add new customer ("+customerID+") to the system");
            
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

    public void genarateRef(){
    
        try 
        {
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(customer_id) FROM customer");
            rsd.next();
            rsd.getString("MAX(customer_id)");
            if (rsd.getString("MAX(customer_id)") == null) {
                itemidset = "CUS00001";
                supplier_id.setText("Customer id : "+itemidset);
                this.customerID = itemidset;

            } else {
                long id = Long.parseLong(rsd.getString("MAX(customer_id)").substring(3, rsd.getString("MAX(customer_id)").length()));
                id++;
                itemidset = ("CUS" + String.format("%05d", id));
                supplier_id.setText("Customer id : "+itemidset);  
                this.customerID = itemidset;
                    }
            } 
        catch (Exception e) {
            System.out.println(e);
        }    
    }
    
    public void clearAll(){
    
        first_name.setText("");
        last_name.setText("");
        email.setText("");
        mobile_phone.setText("");
        address_line_1.setText("");
        address_line_2.setText("");
        city.setText("");
        zip_code.setText("");

    }
    
    public void saveCustomerDetails(){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String DBFIRSTNAME = first_name.getText();
            String DBLASTNAME = last_name.getText();
            String DBEMAIL = email.getText();
            String DBPHONE= mobile_phone.getText();
            String DBADDRESSLINE1 = address_line_1.getText();
            String DBADDRESSLINE2 = address_line_2.getText();
            String DBCITY = city.getText();
            String DBZIPCODE = zip_code.getText();
            String DBCOUNTRY = country.getSelectedItem().toString();

            String query2 = "INSERT INTO customer (`customer_id`, `created_user`, `first_name`, `last_name`, `phone_number`, `address_line1`, `address_line2`, `zip`,`city`, `country`, `email`) VALUES (?,?,?,?,?,?,?,?,?,?,? )";

            PreparedStatement preparedStmt = con.prepareStatement(query2);

            preparedStmt.setString(1, customerID);
            preparedStmt.setString(2, userid);
            preparedStmt.setString(3, DBFIRSTNAME);
            preparedStmt.setString(4, DBLASTNAME);
            preparedStmt.setString(5, DBPHONE);
            preparedStmt.setString(6, DBADDRESSLINE1);
            preparedStmt.setString(7, DBADDRESSLINE2);
            preparedStmt.setString(8, DBZIPCODE);
            preparedStmt.setString(9, DBCITY);
            preparedStmt.setString(10, DBCOUNTRY);
            preparedStmt.setString(11, DBEMAIL);
            preparedStmt.execute();

            JOptionPane.showMessageDialog (null, "Successfully supplier added to the system!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            UIManager UI=new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
        
        }
        catch(Exception e){
        
            System.out.println("error saving data in the database");
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
        scroll_window = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        supplier_id = new javax.swing.JLabel();
        zip_code = new javax.swing.JTextField();
        first_name = new javax.swing.JTextField();
        last_name = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        mobile_phone = new javax.swing.JTextField();
        address_line_1 = new javax.swing.JTextField();
        address_line_2 = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        country = new javax.swing.JComboBox<>();
        feilds_error_massage = new javax.swing.JLabel();
        feilds_error_icon = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        button1 = new accessories.Button();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        scroll_window.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_window.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scroll_window.setDoubleBuffered(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        supplier_id.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        supplier_id.setText("jLabel1");
        jPanel2.add(supplier_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 850, 40));

        zip_code.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(zip_code, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 400, 40));

        first_name.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 400, 40));

        last_name.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 400, 40));

        email.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 400, 40));

        mobile_phone.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(mobile_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 400, 40));

        address_line_1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(address_line_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 400, 40));

        address_line_2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(address_line_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 400, 40));

        city.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel2.add(city, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 460, 400, 40));

        country.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        country.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));
        jPanel2.add(country, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, 400, 40));

        feilds_error_massage.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        feilds_error_massage.setText("jLabel1");
        jPanel2.add(feilds_error_massage, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 620, 30));

        feilds_error_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_28px.png"))); // NOI18N
        jPanel2.add(feilds_error_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("First name");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Customer/newCustomer-06.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 670, 140));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("First name");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, 40));

        jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setText("Email");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 120, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 102));
        jLabel20.setText("Physical Address");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 300, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 102));
        jLabel22.setText("Phone number");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 300, 40));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("Address Line 1");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 300, 40));

        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("Address Line 2");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 240, 40));

        jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 102));
        jLabel25.setText("City");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 420, 300, 40));

        jLabel26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 102));
        jLabel26.setText("Country");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, 240, 40));

        jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 102));
        jLabel27.setText("Zip code / Postal code");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 240, 40));

        button1.setBackground(new java.awt.Color(0, 102, 102));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Save");
        button1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, 180, 40));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 755, 220, 80));

        scroll_window.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_window, javax.swing.GroupLayout.DEFAULT_SIZE, 1590, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_window, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        try
            {
                

                if( first_name.getText().equals("")|| last_name.getText().equals("")|| email.getText().equals("")|| mobile_phone.getText().equals("")||
                         address_line_1.getText().equals("")|| address_line_2.getText().equals("")|| city.getText().equals("")||
                        zip_code.getText().equals("")|| country.getSelectedItem().toString().equals(""))
                {
                    feilds_error_massage.setVisible(true);
                    feilds_error_icon.setVisible(true);
                    Color orange = new Color(255,102,0);
                    feilds_error_massage.setForeground(orange);
                    feilds_error_massage.setText("some feilds are misssing!");
                }
                else 
                {
                    saveCustomerDetails();
                    activity();
                    genarateRef();
                    clearAll();
                }

            }
            catch(HeadlessException e)
            {
                System.out.println(e);
            }
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address_line_1;
    private javax.swing.JTextField address_line_2;
    private accessories.Button button1;
    private javax.swing.JTextField city;
    private javax.swing.JComboBox<String> country;
    private javax.swing.JTextField email;
    private javax.swing.JLabel feilds_error_icon;
    private javax.swing.JLabel feilds_error_massage;
    private javax.swing.JTextField first_name;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField last_name;
    private javax.swing.JTextField mobile_phone;
    private javax.swing.JScrollPane scroll_window;
    private javax.swing.JLabel supplier_id;
    private javax.swing.JTextField zip_code;
    // End of variables declaration//GEN-END:variables
}
