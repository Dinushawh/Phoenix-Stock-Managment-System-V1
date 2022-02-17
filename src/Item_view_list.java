
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;
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
public class Item_view_list extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewallItems
     */
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
   public int No;
    
    
    public Item_view_list() {
            initComponents();
        
            /* remobe Border */
            this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
            UIE.setNorthPane(null);
        
            con = Database_connection_CLASS.connection();

            findUsers();
            
            scrollWindow.getViewport().setBackground(Color.WHITE);
            scrollWindow.setBorder(BorderFactory.createEmptyBorder());
            
            
                    
//        cart_data.getTableHeader().setOpaque(false);
//        cart_data.getTableHeader().setBackground(Color.decode("#FCE7FC"));
//        cart_data.getTableHeader().setForeground(new Color(30, 232, 110 ));
        showTable.setRowHeight(30);
        
    }
    
    public ArrayList<Product_info_CLASS> ListProducts(String ValToSearch)
    {
        ArrayList<Product_info_CLASS> productlist = new ArrayList<Product_info_CLASS>();
        

        try{

            stmt = con.createStatement();
            String searchQuery = "SELECT * FROM `products` WHERE CONCAT(`id`, `product_id`, `type`, `name`, `supplier_id`, `quantity`, "
                    + "`currency`, `supply_price`, `retail_price`, `manufacturer_or_brand`, `weight`, `size`, `manufacture_date`, "
                    + "`expiry date`, `description`) LIKE '%"+ValToSearch+"%'";
            
            
            rs = stmt.executeQuery(searchQuery);
            
            Product_info_CLASS DisplayTableProducts;
            
            while(rs.next())
            {
                            
                DisplayTableProducts = new Product_info_CLASS(
                        
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("supplier_id"),
                        rs.getInt("quantity"),
                        rs.getString("currency"),
                        rs.getInt("supply_price"),
                        rs.getInt("retail_price"),
                        rs.getString("manufacturer_or_brand"),
                        rs.getInt("weight"),
                        rs.getString("size"),
                        rs.getString("manufacture_date"),
                        rs.getString("expiry date"),
                        rs.getString("description")
                        
                        
                                );
                productlist.add(DisplayTableProducts);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return productlist;
    }
    
    public void findUsers()
    {
        ArrayList<Product_info_CLASS> users = ListProducts(search.getText());
        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{"id","Product ID","Type","Name","Supplier ID","Quntity","Currency",
            "Supply Price","Retail Price","Brand","Weight",
            "Size","Manufacture date","Expire date","Description"});

        Object[] row = new Object[15];
        
        for(int i = 0; i < users.size(); i++)
        {
            this.No = i;
            No++;
            row[0] = No;
            row[1] = users.get(i).getProductID();
            row[2] = users.get(i).getType();
            row[3] = users.get(i).getName();
            row[4] = users.get(i).getSuPID();
            row[5] = users.get(i).getQuntity();
            row[6] = users.get(i).getCurrency();
            row[7] = users.get(i).getUnit_buy_price();
            row[8] = users.get(i).getUnit_sell_price();
            row[9] = users.get(i).getBrand();
            row[10] = users.get(i).getWeight();
            row[11] = users.get(i).getSize();
            row[12] = users.get(i).getManufacture_date();
            row[13] = users.get(i).getExpire_date();
            row[14] = users.get(i).getDescription();
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

        scrollWindow.setBackground(new java.awt.Color(255, 255, 255));

        showTable.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        showTable.setForeground(new java.awt.Color(51, 51, 51));
        showTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        showTable.setGridColor(new java.awt.Color(255, 255, 255));
        showTable.setRowHeight(30);
        showTable.setSelectionBackground(new java.awt.Color(0, 204, 51));
        showTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        showTable.getTableHeader().setResizingAllowed(false);
        showTable.getTableHeader().setReorderingAllowed(false);
        scrollWindow.setViewportView(showTable);

        jPanel1.add(scrollWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 1530, 490));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        jPanel2.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 430, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Products/list-06.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -30, 690, 160));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 790, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1640, Short.MAX_VALUE)
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
