
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class Profile_view extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_profile_profile
     */
    
    public static String passedusername;
    public static String role;
    
    
    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Profile_view() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        con = Database_connection_CLASS.connection();
        try
        {
            stmt = con.createStatement();

            String query = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                if(passedusername.equals(rs.getString(2)))
                {
                    headname.setText(rs.getString(3) + " " + rs.getString(4));
                    userid.setText(rs.getNString(8) + "  " + passedusername  );
                    
                    fname.setText(rs.getString(3));
                    lname.setText(rs.getString(4));
                    username.setText(rs.getString(6));
                    email.setText(rs.getString(5));
                    this.role = rs.getString(8);

                }
            }
            
            if ("master".equals(role)){
            
                acc_type.setText("Master");
                
            }
            else if("admin".equals(role)){
            
                acc_type.setText("Administrator");
            }
            else{
            
                acc_type.setText("User");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
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
        headname = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userid = new javax.swing.JLabel();
        userid1 = new javax.swing.JLabel();
        lname1 = new javax.swing.JLabel();
        lname2 = new javax.swing.JLabel();
        lname3 = new javax.swing.JLabel();
        lname4 = new javax.swing.JLabel();
        lname5 = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        lname = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        lname6 = new javax.swing.JLabel();
        acc_type = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        headname.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        headname.setForeground(new java.awt.Color(102, 102, 102));
        headname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        headname.setText("Dinusha");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Profile/icons8_male_user_60px.png"))); // NOI18N

        userid.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        userid.setForeground(new java.awt.Color(102, 102, 102));
        userid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userid.setText("Dinusha");

        userid1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        userid1.setForeground(new java.awt.Color(102, 102, 102));
        userid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userid1.setText("Account Status : Active");

        lname1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 19)); // NOI18N
        lname1.setForeground(new java.awt.Color(51, 51, 51));
        lname1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname1.setText("First name");

        lname2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        lname2.setForeground(new java.awt.Color(102, 102, 102));
        lname2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname2.setText("Account informations");

        lname3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 19)); // NOI18N
        lname3.setForeground(new java.awt.Color(51, 51, 51));
        lname3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname3.setText("Email");

        lname4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 19)); // NOI18N
        lname4.setForeground(new java.awt.Color(51, 51, 51));
        lname4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname4.setText("Last name");

        lname5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 19)); // NOI18N
        lname5.setForeground(new java.awt.Color(51, 51, 51));
        lname5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname5.setText("username");

        fname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        fname.setForeground(new java.awt.Color(102, 102, 102));
        fname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fname.setText("First name");

        lname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lname.setForeground(new java.awt.Color(102, 102, 102));
        lname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname.setText("Last name");

        username.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        username.setForeground(new java.awt.Color(102, 102, 102));
        username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username.setText("username");

        email.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        email.setText("email");

        lname6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 19)); // NOI18N
        lname6.setForeground(new java.awt.Color(51, 51, 51));
        lname6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lname6.setText("Account Type");

        acc_type.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        acc_type.setForeground(new java.awt.Color(102, 102, 102));
        acc_type.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        acc_type.setText("Administrator");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lname6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acc_type, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lname3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(headname, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userid1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lname2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lname1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lname4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lname5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(847, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userid1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lname2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lname1)
                            .addComponent(lname4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fname)
                            .addComponent(lname)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lname5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username)))
                .addGap(18, 18, 18)
                .addComponent(lname3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email)
                .addGap(18, 18, 18)
                .addComponent(lname6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_type)
                .addContainerGap(280, Short.MAX_VALUE))
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
    private javax.swing.JLabel acc_type;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fname;
    private javax.swing.JLabel headname;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lname;
    private javax.swing.JLabel lname1;
    private javax.swing.JLabel lname2;
    private javax.swing.JLabel lname3;
    private javax.swing.JLabel lname4;
    private javax.swing.JLabel lname5;
    private javax.swing.JLabel lname6;
    private javax.swing.JLabel userid;
    private javax.swing.JLabel userid1;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}