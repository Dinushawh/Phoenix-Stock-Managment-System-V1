
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Database_connection_CLASS {
     static Connection connection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "");
            return con;
        }
        catch(Exception e)
        {
            System.out.println(e);
            Database_error_popup view = new Database_error_popup();
            view.setVisible(true);
            return null;
        }
    }   
}
