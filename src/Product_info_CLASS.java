/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Product_info_CLASS {

    
    private int ID;
    private String PRODUCTID;
    private String TYPE;
    private String NAME;
    private String SUP_ID;
    private int QUNTITY;
    private String CURRENCY;
    private int UNIT_BUY_PRICE;
    private int UNIT_SELL_PRICE;
    private String BRAND;
    private int WEIGHT;
    private String SIZE;
    private String MANUFACTURE_DATE;
    private String EXPIRE_DATE;
    private String DESCRIPTION;


                    
    public Product_info_CLASS( int id , String product_id , String type , String name,String sup_id , int quntity, String currency ,
            int supply_price , int retail_price , String manufacturer_or_brand , int weight , String size ,
            String manufacture_date , String expire_date , String description) 

    {
        this.ID = id;
        this.PRODUCTID = product_id;
        this.TYPE = type;
        this.NAME = name;
        this.SUP_ID = sup_id;
        this.QUNTITY = quntity;
        this.CURRENCY = currency;
        this.UNIT_BUY_PRICE = supply_price;
        this.UNIT_SELL_PRICE = retail_price;
        this.BRAND = manufacturer_or_brand;
        this.WEIGHT = weight;
        this.SIZE = size;
        this.MANUFACTURE_DATE = manufacture_date;
        this.EXPIRE_DATE = expire_date;
        this.DESCRIPTION = description;
       
    }
    
    public int getId()
    {
        return ID;
    }
    
    public String getProductID()
    {
        return PRODUCTID;
    }
    
    public String getType()
    {
        return TYPE;
    } 
    public String getName()
    {
        return NAME;
    }
    
    public String getSuPID()
    {
        return SUP_ID;
    }
    
    public int getQuntity()
    {
        return QUNTITY;
    }
    
    public String getCurrency()
    {
        return CURRENCY;
    }
    
    public int getUnit_buy_price()
    {
        return UNIT_BUY_PRICE;
    }

    public int getUnit_sell_price()
    {
        return UNIT_SELL_PRICE;
    }
        
        public String getBrand()
    {
        return BRAND;
    }
      
       public int getWeight()
    {
        return WEIGHT;
    }
       
       
    public String getSize()
    {
        return SIZE;
    }

    public String getManufacture_date()
    {
        return MANUFACTURE_DATE;
    }
    
    public String getExpire_date()
    {
        return EXPIRE_DATE;
    }
    
    public String getDescription()
    {
        return DESCRIPTION;
    }

}
