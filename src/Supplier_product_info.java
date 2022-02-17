/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Supplier_product_info {
     
    private String NAME;
    private String TYPE;
    private String QUNTITY;
    private String SUPPLYPRICE;
    private String MANUFACTUREDATE;
    private String EXPIREDATE;
    private String DESCRIPTION;
    
    public Supplier_product_info ( String name , String type , String quntity , String price ,String manufacture ,  String expire , String description )
            
        {
        this.NAME = name;
        this.TYPE = type;
        this.QUNTITY = quntity;
        this.SUPPLYPRICE = price;
        this.MANUFACTUREDATE = manufacture;
        this.EXPIREDATE = expire;
        this.DESCRIPTION = description;
      
       
    }
    
    public String getName()
    {
        return NAME;
    }
    public String getType()
    {
        return TYPE;
    }
    public String getQuntity()
    {
        return QUNTITY;
    }
    public String getSupplyPRice()
    {
        return SUPPLYPRICE;
    }
    public String getManufactureDate()
    {
        return MANUFACTUREDATE;
    }
    public String getExpireDate()
    {
        return EXPIREDATE;
    }
    public String getDescription()
    {
        return DESCRIPTION;
    }
    
    
}
