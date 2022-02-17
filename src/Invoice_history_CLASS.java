/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Invoice_history_CLASS {
    
    private String INVOICENUMBER;
    private String CLIENT;
    private String INVOCEDATE;
    private String INVOCETIME;
    private String DUEDATE;
    private double INVOICTOTAL;
    private String PAYMENTMETHOD;
    private String HANDLEDUSER; 
    private String INVSTATUS; 
    
     public Invoice_history_CLASS( String invoice_number, String client, String invoive_date,String invoice_time, String due_date, double invoice_total, String payment_method, String Handled_user,String inv_status)
             {
            this.INVOICENUMBER = invoice_number;
            this.CLIENT = client;
            this.INVOCEDATE = invoive_date;
            this.INVOCETIME = invoice_time;
            this.DUEDATE = due_date;
            this.INVOICTOTAL = invoice_total;
            this.PAYMENTMETHOD = payment_method;
            this.HANDLEDUSER = Handled_user;
            this.INVSTATUS = inv_status;
    }
     
     public String getIN()
    {
        return INVOICENUMBER;
    }
    
    public String getClient()
    {
        return CLIENT;
    }
    
    public String getInDate()
    {
        return INVOCEDATE;
    } 
    
    public String getInTime()
    {
        return INVOCETIME;
    }
    public String getInDue()
    {
        return DUEDATE;
    }
    
    public double getInTotal()
    {
        return INVOICTOTAL;
    }
    
    public String getPayMethod()
    {
        return PAYMENTMETHOD;
    }
    
    public String getHandledUser()
    {
        return HANDLEDUSER;
    }
    
    public String getInvStatus()
    {
        return INVSTATUS;
    }
}
