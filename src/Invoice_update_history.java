
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
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
public class Invoice_update_history extends javax.swing.JInternalFrame {

    /**
     * Creates new form admin_invoice_history
     */
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public Invoice_update_history() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        
        con = Database_connection_CLASS.connection();

        
        UHTable.getTableHeader().setOpaque(false);
        UHTable.getTableHeader().setBackground(Color.WHITE);
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        ViewAll();
        
    }
    
    public ArrayList<Update_history_CLASS> UPDATEHISTORY (String ValToSearch)
    {
        ArrayList<Update_history_CLASS> updatehistory = new ArrayList<Update_history_CLASS>();
        

        try{
        
            stmt = con.createStatement();
            String searchQuery = "SELECT * FROM `invoice_update_info` WHERE CONCAT (`invoice_no`, `updated_user`, `updated_date`, `updated_time`, `update_reference`, `total`, `outstand_balance`, `outstand_status`) LIKE '%"+ValToSearch+"%'";
            
            
            rs = stmt.executeQuery(searchQuery);
            
            Update_history_CLASS displayUpdateHistory;
            
            while(rs.next())
            {
                            
                displayUpdateHistory = new Update_history_CLASS(

                        rs.getString("invoice_no"),
                        rs.getString("updated_user"),
                        rs.getString("updated_date"),
                        rs.getString("updated_time"),
                        rs.getString("update_reference"),
                        rs.getDouble("total"),
                        rs.getDouble("outstand_balance"),
                        rs.getString("outstand_status")
        
         
                                );
                updatehistory.add(displayUpdateHistory);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return updatehistory;
    }
    
    public void ViewAll()
    {
        ArrayList<Update_history_CLASS> UH = UPDATEHISTORY(searchButton.getText());
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(new Object[]{"No","Invoice Number","Updated user","Updated date","Updated time","Total","Outstand balance","Outstand status","Reference No"});

        Object[] row = new Object[9];
        
        for(int i = 0; i < UH.size(); i++)
        {
            int b = i;b = b+1;
            
            row[0] = b;
            row[1] = UH.get(i).getIN();
            row[2] = UH.get(i).getupdateUser();
            row[3] = UH.get(i).getupdateDate();
            row[4] = UH.get(i).getupdateTime();
            row[5] = UH.get(i).getTOT();
            row[6] = UH.get(i).getOUTSTAND();
            row[7] = UH.get(i).getSTATUS();
            row[8] = UH.get(i).getRef();

            model.addRow(row);
        }
       UHTable.setModel(model);
       
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
        searchButton = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        UHTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchButton.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        searchButton.setForeground(new java.awt.Color(0, 102, 102));
        searchButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchButtonKeyReleased(evt);
            }
        });
        jPanel1.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 400, 40));

        UHTable.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        UHTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Invoice Number", "Client", "Invoiced Date", "Due Date", "Invoce Total", "Payment Method", "Handled user"
            }
        ));
        UHTable.setGridColor(new java.awt.Color(255, 255, 255));
        UHTable.setRowHeight(35);
        UHTable.setSelectionBackground(new java.awt.Color(0, 102, 102));
        jScrollPane1.setViewportView(UHTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1550, 510));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/History/update-06.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -12, 750, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1649, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchButtonKeyReleased
        // TODO add your handling code here:
        try{

            ViewAll();
        }
        catch(Exception e){
        
            
        }
    }//GEN-LAST:event_searchButtonKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable UHTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchButton;
    // End of variables declaration//GEN-END:variables
}
