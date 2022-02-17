/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Customer_purchesed_itms_CLASS {

    
    private String ITMNAME;
    private String QUNTITY;     
    
    public Customer_purchesed_itms_CLASS( String ITMNAME , String quntity) 

    {
        this.ITMNAME = ITMNAME;
        this.QUNTITY = quntity;
    }
    
    public String getItmName()
    {
        return ITMNAME;
    }
    
    public String getQuntity()
    {
        return QUNTITY;
    }

}
