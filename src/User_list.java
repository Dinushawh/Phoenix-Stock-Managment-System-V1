
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
public class User_list extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewallItems
     */
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public int No;
    
    public User_list() {
            initComponents();
        
            /* remobe Border */
            this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
            UIE.setNorthPane(null);
        
            con = Database_connection_CLASS.connection();

            findUsers();
            
            scrollWindow.getViewport().setBackground(Color.WHITE);
            scrollWindow.setBorder(BorderFactory.createEmptyBorder());
            scrollWindow.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.gray;
            }
            });
        
    }
    
    public ArrayList<User_info_CLASS> ListUsers(String ValToSearch)
    {
        ArrayList<User_info_CLASS> UserList = new ArrayList<User_info_CLASS>();
        

        try{

            stmt = con.createStatement();
            String searchQuery = "SELECT * FROM `users` WHERE CONCAT(`id`, `user_id`, `fname`, `lname`, "
                    + "`email`, `username`, `user_role`) LIKE '%"+ValToSearch+"%'";
            
            
            rs = stmt.executeQuery(searchQuery);
            
            User_info_CLASS DisplayTableUsers;
            
            while(rs.next())
            {
           
                DisplayTableUsers = new User_info_CLASS(
                        
                        rs.getInt("id"),
                        rs.getString("user_id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("user_role")
                        

             );
                UserList.add(DisplayTableUsers);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return UserList;
    }
    
    public void findUsers()
    {
        ArrayList<User_info_CLASS> users = ListUsers(search.getText());
        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{"USER ID","First Name","Last Name","Email","Username","User role",
            });

        Object[] row = new Object[6];
        
        for(int i = 0; i < users.size(); i++)
        {
            this.No = i;
            No++;
//            row[0] = No;
            row[0] = users.get(i).getUserID();
            row[1] = users.get(i).getFirtName();
            row[2] = users.get(i).getLastName();
            row[3] = users.get(i).getEmail();
            row[4] = users.get(i).getUsername();
            row[5] = users.get(i).getRole();

            model.addRow(row);
        }
       showTable.setModel(model);
       
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
        scrollWindow = new javax.swing.JScrollPane();
        showTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        showTable.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        showTable.setForeground(new java.awt.Color(51, 51, 51));
        showTable.setModel(new javax.swing.table.DefaultTableModel(
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
        showTable.setGridColor(new java.awt.Color(255, 255, 255));
        showTable.setRowHeight(30);
        showTable.setSelectionBackground(new java.awt.Color(0, 102, 102));
        showTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        showTable.getTableHeader().setResizingAllowed(false);
        showTable.getTableHeader().setReorderingAllowed(false);
        scrollWindow.setViewportView(showTable);

        jPanel1.add(scrollWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 1180, 490));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        jPanel2.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 430, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/user/listview-06.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -40, 670, 180));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 790, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1298, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        try
        {
            findUsers();
        }
        catch(Exception e)
        {
            System.out.println(e);
            }
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane scrollWindow;
    private javax.swing.JTextField search;
    private javax.swing.JTable showTable;
    // End of variables declaration//GEN-END:variables
}
