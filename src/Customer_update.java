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
import javax.swing.BorderFactory;
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
public final class Customer_update extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_supplier_delete_step
     */
    
    public static String customerid,userid,userRole,FNA;
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
//    dbValues
    public String dbFname,dbLname,dbPhone,dbEmail,dbAddress1,dbAddress2,dbCity,dbZip,dbCountry;

    
    public String cReference;
    
    public Customer_update() throws ParseException {
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
       
        
        System.out.println(customerid+" "+userid);

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
            String Activity = (FNA+" ("+userid+") updated customer ("+customerid+") details in the system");
            
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
            
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String query = "SELECT * FROM customer ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next())
            {
                if (customerid.equals(rsd.getString(2)))
                {
                    
                    
                    this.dbFname = rsd.getString(5);
                    this.dbLname = rsd.getString(6);
                    this.dbPhone = rsd.getString(7);
                    this.dbAddress1 = rsd.getString(8);
                    this.dbAddress2 = rsd.getString(9);
                    this.dbZip = rsd.getString(10);
                    this.dbCountry = rsd.getString(12);
                    this.dbCity = rsd.getString(11);
                    this.dbEmail = rsd.getString(12);
                    

                    fname.setText(dbFname);
                    last_name.setText(dbLname);
                    email.setText(dbEmail);
                    mobile_phone.setText(dbPhone);
                    address_line_1.setText(dbAddress1);
                    address_line_2.setText(dbAddress2);
                    city.setText(dbCity);
                    zip_code.setText(dbZip);
                            
                    pr_name.setText(dbFname+ " "+dbLname);
                    TYPE.setText(dbCountry);
                    country.setSelectedItem(dbCountry);

                }
                
            }

        
        }
        catch(SQLException e){
        
            System.out.println("Error Showing data");
        }
        

    }

    public void genarateReference(){
    
        try {
            con = Database_connection_CLASS.connection();
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(`reference`) FROM `customer_update_info`");
            rsd.next();
            rsd.getString("MAX(`reference`)");
            if (rsd.getString("MAX(`reference`)") == null) {
                itemidset = "CREF00001";
                
                this.cReference = itemidset;
                System.out.println(cReference);
            } 
            else {
                long id = Long.parseLong(rsd.getString("MAX(`reference`)").substring(4, rsd.getString("MAX(`reference`)").length()));
                id++;
                itemidset = ("CREF" + String.format("%05d", id));
                
                this.cReference = itemidset;
                System.out.println(cReference);
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
            System.out.println(cReference+customerid);
            String stat = "Updated";
            String Query = "UPDATE `customer` SET `reference` = ?, `status` = ? WHERE `customer_id` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, cReference);
            preparedStmt.setString(2, stat);
            preparedStmt.setString(3, customerid);
            preparedStmt.execute();
            
            String Query1 = "INSERT INTO `customer_history`(`customer_id`, `date_created`, `created_user`, `first_name`, `last_name`, `phone_number`, `address_line1`, `address_line2`, `zip`, `city`, `country`, `email`, `reference`, `status`) SELECT `customer_id`, `date_created`, `created_user`, `first_name`, `last_name`, `phone_number`, `address_line1`, `address_line2`, `zip`, `city`, `country`, `email`, `reference`, `status` FROM `customer` WHERE `customer_id` = ?;";
            
            PreparedStatement preparedStmt2 = con.prepareStatement(Query1);
            preparedStmt2.setString(1, customerid);   
            preparedStmt2.execute();

        }
        catch(SQLException e){
        
            System.out.println("Error reference update and move");
        }
    } 
    
    public void updateValues(){
    
        try{
            

            String FN = fname.getText();
            String LN = last_name.getText();
            String EM = email.getText();
            String MP = mobile_phone.getText();
            String AD1 = address_line_1.getText();
            String AD2 = address_line_2.getText();
            String CI = city.getText();
            String ZI = zip_code.getText();
            String CR = country.getSelectedItem().toString();

            String Query = "UPDATE `customer` SET `first_name` = ?, `last_name` = ?, `phone_number` =?, `address_line1` = ? , `address_line2` = ?, `zip` = ?, `city` = ?, `country` = ?, `email` = ? WHERE `customer_id` = ?;";

            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, FN);
            preparedStmt.setString(2, LN);
            preparedStmt.setString(3, MP);
            preparedStmt.setString(4, AD1);
            preparedStmt.setString(5, AD2);
            preparedStmt.setString(6, ZI);
            preparedStmt.setString(7, CI);
            preparedStmt.setString(8, CR);
            preparedStmt.setString(9, EM);
            preparedStmt.setString(10, customerid);
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
            
            String Query ="INSERT INTO `customer_update_info`(`customer_id`, `updated_user`,`time`, `reference`) VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, customerid);
            preparedStmt.setString(2, userid);
            preparedStmt.setString(3, strDate);
            preparedStmt.setString(4, cReference);
            preparedStmt.execute();
        }
        catch(SQLException e){
    
            System.out.println("Error updating update information");
        
        }

    }
    
    public void ChangesCheck() throws ParseException{
    
        try{

            String FN = fname.getText();
            String LN = last_name.getText();
            String EM = email.getText();
            String MP = mobile_phone.getText();
            String AD1 = address_line_1.getText();
            String AD2 = address_line_2.getText();
            String CI = city.getText();
            String ZI = zip_code.getText();
            String CR = country.getSelectedItem().toString();

          
            if(dbFname.equals(FN) && dbLname.equals(LN) && dbPhone.equals(MP) && dbEmail.equals(EM) && dbAddress1.equals(AD1) && dbAddress2.equals(AD2) && dbCity.equals(CI)  && dbZip.equals(ZI) && dbCountry.equals(CR)){
               
                JOptionPane.showMessageDialog (null, "Nothing changed!", "FAILD", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
            }
            
            else{
//              
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
            
            activity();
            refUpdateandMove ();
            updateValues();
            updateInformationUpdate();
            JOptionPane.showMessageDialog (null, "Customer Details successfully updated", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            UIManager UI=new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
            
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
        jLabel3 = new javax.swing.JLabel();
        button1 = new accessories.Button();
        jLabel1 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        last_name = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        mobile_phone = new javax.swing.JTextField();
        address_line_1 = new javax.swing.JTextField();
        address_line_2 = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        zip_code = new javax.swing.JTextField();
        country = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pr_name.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        pr_name.setForeground(new java.awt.Color(0, 102, 102));
        pr_name.setText("Dinusha Weerakoon");

        TYPE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        TYPE.setText("Sri lanka");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Email");

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
        jLabel1.setText("Update customer details");

        fname.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        last_name.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        email.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        mobile_phone.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        address_line_1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        address_line_2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        city.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        zip_code.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        country.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        country.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Last name");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("First name");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Phone number");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Physical Address");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Address line 1");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Address line 2");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("City");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Zip code");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Country");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(50, 50, 50)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(last_name, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(50, 50, 50))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(200, 200, 200)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mobile_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(address_line_1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(200, 200, 200)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(address_line_2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(zip_code, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(200, 200, 200)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pr_name, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TYPE, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(226, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(TYPE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pr_name, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(last_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address_line_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(address_line_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zip_code, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
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
            
            ChangesCheck();
        }
        catch(Exception e){

            System.out.println("Error Saving data");
        }
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TYPE;
    private javax.swing.JTextField address_line_1;
    private javax.swing.JTextField address_line_2;
    private accessories.Button button1;
    private javax.swing.JTextField city;
    private javax.swing.JComboBox<String> country;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField last_name;
    private javax.swing.JTextField mobile_phone;
    private javax.swing.JLabel pr_name;
    private javax.swing.JTextField zip_code;
    // End of variables declaration//GEN-END:variables
}
