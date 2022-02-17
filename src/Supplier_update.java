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
public final class Supplier_update extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_supplier_update_step01
     */
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public static String userid,supplierID,userRole,FNA;
    
//    public compare data
    public String fname,lname,company2,email2,phone,web,address1,address2,city1,zip;
    
    public String sReference;
    
    public Supplier_update() {
        initComponents();
        
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        
        errorClose();
        setValues();
        genarateReference();
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
            String Activity = (FNA+" ("+userid+") updated supplier ("+supplierID+") details in the system");
            
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
    
    public void errorClose(){
    
        feilds_error_massage.setVisible(false);
        feilds_error_icon.setVisible(false);
    }
    
    public void setValues(){
    
        try{

            System.out.println(userid);
            con = Database_connection_CLASS.connection();
            boolean loopcheck = false;
            stmt = con.createStatement();
            String query = "SELECT * FROM suppliers ";
            ResultSet rsd = stmt.executeQuery(query);
            while(rsd.next())
            {
                if (supplierID.equals(rsd.getString(4)))
                {
                    pr_name.setText(rsd.getString(5)+ " "+ rsd.getString(6));
                    cr.setText(rsd.getString(15));
                    id.setText(rsd.getString(4));
                    this.fname = rsd.getString(5);first_name.setText(fname);
                    this.lname = rsd.getString(6);last_name.setText(lname);
                    this.company2 = rsd.getString(7);company.setText(company2);
                    this.email2 = rsd.getString(8);email.setText(email2);
                    this.phone = rsd.getString(9);mobile_phone.setText(phone);
                    this.web = rsd.getString(10);website.setText(web);
                    this.address1 = rsd.getString(11);address_line_1.setText(address1);
                    this.address2 = rsd.getString(12);address_line_2.setText(address2);
                    this.city1 = rsd.getString(13);city.setText(city1);
                    this.zip = rsd.getString(14);zip_code.setText(zip);
                    contry.setSelectedItem(rsd.getString(15));

                }
            }
        }
        catch(SQLException e){
        
            System.out.println("Update value setting faild");
        }
    }
    
    public void genarateReference(){
    
        try {
            con = Database_connection_CLASS.connection();
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(`reference`) FROM `supplier_update_info`");
            rsd.next();
            rsd.getString("MAX(`reference`)");
            if (rsd.getString("MAX(`reference`)") == null) {
                itemidset = "SREF00001";
                
                this.sReference = itemidset;
                System.out.println(sReference);
            } 
            else {
                long id = Long.parseLong(rsd.getString("MAX(`reference`)").substring(4, rsd.getString("MAX(`reference`)").length()));
                id++;
                itemidset = ("SREF" + String.format("%05d", id));
                
                this.sReference = itemidset;
                System.out.println(sReference);
                }
            } 
        catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        } 
    }
    
    public void checkChanges() throws ParseException{
    
        String a1 = first_name.getText();
        String a2 = last_name.getText();
        String a3 = company.getText();
        String a4 = email.getText();
        String a5 = mobile_phone.getText();
        String a6 = website.getText();
        String a7 = address_line_1.getText();
        String a8 = address_line_2.getText();
        String a9 = city.getText();
        String a10 = zip_code.getText();
        

        if(a1.equals(fname) && a2.equals(lname) && a3.equals(company2) && a4.equals(email2) && a5.equals(phone) && a6.equals(web) && a7.equals(address1) && a8.equals(address2) && a9.equals(city1) && a10.equals(zip) ){
        
            JOptionPane.showMessageDialog (null, "Nothing Changed!", "Faild", JOptionPane.INFORMATION_MESSAGE);
            UIManager UI=new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
        }
        else{
            checkPermission();
        }
        
    }
    
    public void refUpdateandMove (){
    
        try{
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            String ref = sReference;
            System.out.println(ref + "  "+ supplierID);
            String Query = "UPDATE `suppliers` SET `reference` = ? WHERE `supplier_id` = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, ref);
            preparedStmt.setString(2, supplierID);
            preparedStmt.execute();
            
            String Query1 = "INSERT INTO `supplier_update_history`(`created_date`, `created_user`, `supplier_id`, `first_name`, `last_name`, `company_name`, `email`, `phone_number`, `website`, `address_line_1`, `address_line_2`, `city`, `zip_code`, `country`, `reference`) "
                    + "SELECT `date_created`, `created_user`, `supplier_id`, `first_name`, `last_name`, `company_name`, `email`, `phone_number`, `website`, `address_line_1`, `address_line_2`, `city`, `zip_code`, `country`, `reference` FROM `suppliers` WHERE `supplier_id` = ? ;";
            
            PreparedStatement preparedStmt2 = con.prepareStatement(Query1);
            preparedStmt2.setString(1, supplierID);   
            preparedStmt2.execute();
        
        }
        catch(SQLException e){
        
            System.out.println("Error reference update and move");
        }
    }    
    
    public void updateInformationUpdate(){
    
        try{
            
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            Date date2 = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  
            String strDate = dateFormat.format(date2);  
        
            String Query ="INSERT INTO `supplier_update_info`(`supplier_id`, `updated_user`,`time`, `reference`) VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, supplierID);
            preparedStmt.setString(2, userid);
            preparedStmt.setString(3, strDate);
            preparedStmt.setString(4, sReference);
            preparedStmt.execute();
        }
        catch(SQLException e){
    
            System.out.println("Error updating update information");
        
        }
    }
    
    public void updateData(){
    
        try{
            System.out.println("1");
            con = Database_connection_CLASS.connection();
            stmt = con.createStatement();
            System.out.println("1");
            String a1 = first_name.getText();
            String a2 = last_name.getText();
            String a3 = company.getText();
            String a4 = email.getText();
            String a5 = mobile_phone.getText();
            String a6 = website.getText();
            String a7 = address_line_1.getText();
            String a8 = address_line_2.getText();
            String a9 = city.getText();
            String a10 = zip_code.getText();
            String a11 = contry.getSelectedItem().toString();
            System.out.println("1");
            String Query = "UPDATE `suppliers` SET `first_name` = ?,`last_name`= ?,`company_name` = ?,`email` = ?,`phone_number` = ?,`website` = ?,`address_line_1` = ?,`address_line_2` = ?,`city` = ?,`zip_code` = ?,`country` = ? WHERE `supplier_id` = ?;";
            System.out.println("1");
            PreparedStatement preparedStmt = con.prepareStatement(Query);
            preparedStmt.setString(1, a1);
            preparedStmt.setString(2, a2);
            preparedStmt.setString(3, a3);
            preparedStmt.setString(4, a4);
            preparedStmt.setString(5, a5);
            preparedStmt.setString(6, a6);
            preparedStmt.setString(7, a7);
            preparedStmt.setString(8, a8);
            preparedStmt.setString(9, a9);
            preparedStmt.setString(10, a10);
            preparedStmt.setString(11, a11);
            preparedStmt.setString(12, supplierID);
            System.out.println("1");
            preparedStmt.execute();
            System.out.println("1");
            
 
        }
        catch(SQLException e){
        
            System.out.println("Error Updating data");
        }
    }
    
    public void checkPermission() throws ParseException{
    
        try{
            
            if(null == userRole){

            }
            else switch (userRole) {
                case "admin":
                    processData();
                    break;
                case "master":
                    processData();
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
                                    processData();

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
    
    public void processData(){
    
        try{
            
            errorClose();
            activity();
            updateInformationUpdate();
            refUpdateandMove();
            updateData();

            JOptionPane.showMessageDialog (null, "Supplier details sucsessfully updated!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            UIManager UI=new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);

            genarateReference();
            setValues();
        
        }
        catch(HeadlessException e){
        
            System.out.println("Error processing data");
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
        id = new javax.swing.JLabel();
        zip_code = new javax.swing.JTextField();
        first_name = new javax.swing.JTextField();
        last_name = new javax.swing.JTextField();
        company = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        mobile_phone = new javax.swing.JTextField();
        website = new javax.swing.JTextField();
        address_line_1 = new javax.swing.JTextField();
        address_line_2 = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        contry = new javax.swing.JComboBox<>();
        feilds_error_massage = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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
        button1 = new accessories.Button();
        feilds_error_icon = new javax.swing.JLabel();
        cr = new javax.swing.JLabel();
        pr_name = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        id.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        id.setText("jLabel1");

        zip_code.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        first_name.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        last_name.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        company.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        email.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        mobile_phone.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        website.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        address_line_1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        address_line_2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        city.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        contry.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        contry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));

        feilds_error_massage.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        feilds_error_massage.setText("jLabel1");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("Last name");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Suppliers/update02-06.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Supplier id");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("First name");

        jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setText("Company");

        jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setText("Email");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 102));
        jLabel20.setText("Physical Address");

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setText("Website");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 102));
        jLabel22.setText("Phone number");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("Address Line 1");

        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("Address Line 2");

        jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 102));
        jLabel25.setText("City");

        jLabel26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 102));
        jLabel26.setText("Country");

        jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 102));
        jLabel27.setText("Zip code / Postal code");

        button1.setBackground(new java.awt.Color(0, 102, 102));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Save");
        button1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        feilds_error_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Errors/icons8_cancel_28px.png"))); // NOI18N

        cr.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        cr.setText("Sri lanka");

        pr_name.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        pr_name.setForeground(new java.awt.Color(0, 102, 102));
        pr_name.setText("Dinusha Weerakoon");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cr, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pr_name, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(330, 330, 330)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(first_name, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(last_name, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(50, 50, 50)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mobile_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(150, 150, 150)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(address_line_1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(zip_code, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(feilds_error_icon)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(feilds_error_massage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGap(50, 50, 50)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(address_line_2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(63, 63, 63)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(company, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(website, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(contry, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(cr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pr_name, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(first_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(mobile_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address_line_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(address_line_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(company, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(website, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zip_code, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contry, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(feilds_error_massage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(feilds_error_icon))
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1584, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        try{
        
            checkChanges();
        }
        catch(ParseException e){
        
            System.out.println("Error Saving details");
        }

    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address_line_1;
    private javax.swing.JTextField address_line_2;
    private accessories.Button button1;
    private javax.swing.JTextField city;
    private javax.swing.JTextField company;
    private javax.swing.JComboBox<String> contry;
    private javax.swing.JLabel cr;
    private javax.swing.JTextField email;
    private javax.swing.JLabel feilds_error_icon;
    private javax.swing.JLabel feilds_error_massage;
    private javax.swing.JTextField first_name;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField last_name;
    private javax.swing.JTextField mobile_phone;
    private javax.swing.JLabel pr_name;
    private javax.swing.JTextField website;
    private javax.swing.JTextField zip_code;
    // End of variables declaration//GEN-END:variables
}
