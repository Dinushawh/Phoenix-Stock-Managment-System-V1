/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Supplier_info_CLASS {

    
    private int ID;
    private String SUPPLIERID;
    private String FIRSTNAME;
    private String LASTNAME;
    private String COMPANY;
    private String EMAIL;
    private String PHONENUMBER;
    private String WEBSITE;
    private String ADRESSLINE1;
    private String ADRESSLINE2;
    private String CITY;
    private String ZIPCODE;
    private String COUNTRY;


                    
    public Supplier_info_CLASS( int id , String supplier_id , String first_name , String last_name,String company ,  String email ,
            String phone_number , String website, String adress_line_1, String adress_line_2,
            String city ,String zip_code ,String country) 

    {
        this.ID = id;
        this.SUPPLIERID = supplier_id;
        this.FIRSTNAME = first_name;
        this.LASTNAME = last_name;
        this.COMPANY = company;
        this.EMAIL = email;
        this.PHONENUMBER = phone_number;
        this.WEBSITE = website;
        this.ADRESSLINE1 = adress_line_1;
        this.ADRESSLINE2 = adress_line_2;
        this.CITY = city;
        this.ZIPCODE = zip_code;
        this.COUNTRY = country;

       
    }

    public int getId()
    {
        return ID;
    }
    
    public String getSupplierID()
    {
        return SUPPLIERID;
    }
    
    public String getFirstName()
    {
        return FIRSTNAME;
    } 
    public String getLastName()
    {
        return LASTNAME;
    }
    
    public String getCompany()
    {
        return COMPANY;
    }
    
    public String getEmail()
    {
        return EMAIL;
    }
    
    public String getPhoneNumber()
    {
        return PHONENUMBER;
    }
    
    public String getWebsite()
    {
        return WEBSITE;
    }
    
     public String getAddressLine1()
    {
        return ADRESSLINE1;
    }
     
    public String getAddressLine2()
    {
        return ADRESSLINE2;
    }
    
    public String getCity()
    {
        return CITY;
    }
    
    public String getZipCode()
    {
        return ZIPCODE;
    }
    
    public String getCountry()
    {
        return COUNTRY;
    }
    
    

}
