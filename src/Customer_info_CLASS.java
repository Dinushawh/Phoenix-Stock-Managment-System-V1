/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Customer_info_CLASS {

    
    private String CUSTOMERID;
    private String FIRSTNAME;
    private String LASTNAME;
    private String PHONE;
    private String ADDRESS1;
    private String ADDRESS2;
    private String ZIP;
    private String COUNTRY;
    private String CITY;
    private String EMAIL;
                  
    public Customer_info_CLASS(String customer_id , String fname , String lname,String phone ,  String address1 , String address2 ,String zip ,String country, String city,String email ) 

    {
        this.CUSTOMERID = customer_id;
        this.FIRSTNAME = fname;
        this.LASTNAME = lname;
        this.PHONE = phone;
        this.ADDRESS1 = address1;
        this.ADDRESS2 = address2;
        this.ZIP = zip;
        this.COUNTRY = country;
        this.CITY = city;
        this.EMAIL = email;
      
       
    }

    public String getCustomerID()
    {
        return CUSTOMERID;
    }
    public String getFname()
    {
        return FIRSTNAME;
    }
    public String getLname()
    {
        return LASTNAME;
    }
    public String getPhone()
    {
        return PHONE;
    }
    public String getAddress1()
    {
        return ADDRESS1;
    }
    public String getAddress2()
    {
        return ADDRESS2;
    }
    public String getZip()
    {
        return ZIP;
    }
    public String getCountry()
    {
        return COUNTRY;
    }
    public String getCity()
    {
        return CITY;
    }
    public String getEmail()
    {
        return EMAIL;
    }

}
