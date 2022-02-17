/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class User_info_CLASS {

    
    private int ID;
    private String USERID;
    private String USERFNAME;
    private String USERLNAME;
    private String USEREMAIL;
    private String USERNAME;
    private String USERROLE;
                  
    public User_info_CLASS( int id , String user_id , String fname , String lname,String email ,  String username ,
            String role ) 

    {
        this.ID = id;
        this.USERID = user_id;
        this.USERFNAME = fname;
        this.USERLNAME = lname;
        this.USEREMAIL = email;
        this.USERNAME = username;
        this.USERROLE = role;
      
       
    }
    
    public int getId()
    {
        return ID;
    }
    
    public String getUserID()
    {
        return USERID;
    }
    
    public String getFirtName()
    {
        return USERFNAME;
    } 
    public String getLastName()
    {
        return USERLNAME;
    }
    
    public String getEmail()
    {
        return USEREMAIL;
    }
    
    public String getUsername()
    {
        return USERNAME;
    }
    
    public String getRole()
    {
        return USERROLE;
    }

}
