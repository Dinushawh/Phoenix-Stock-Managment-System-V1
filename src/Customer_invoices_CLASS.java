/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Customer_invoices_CLASS {

    
    private String INVOICE_NO;
    private String DATE;
    private String TOTAL;
    
                  
    public Customer_invoices_CLASS( String invoice_no , String date , String total  ) 

    {
        this.INVOICE_NO = invoice_no;
        this.DATE = date;
        this.TOTAL = total;

    }
    
    public String getInvoiceNo()
    {
        return INVOICE_NO;
    }
    
    public String getDate()
    {
        return DATE;
    }
    
    public String getTotal()
    {
        return TOTAL;
    } 

}
