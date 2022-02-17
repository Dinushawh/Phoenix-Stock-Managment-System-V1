/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Reversed_history_CLASS {
    
    private String INVOICENUMBER;
    private Double BALANCE;
    private String BALANCESTATUS;
    private String REVERSEDUSER;
    private String REVERSEDTIME;
    private String REVERSEDDATE;
    private String REASON;
    private String REF;

     public Reversed_history_CLASS( String invoice_number, double balance ,String balance_status, String reversed_user,String reversed_date, String reversed_time,String reason,String ref)
             {
            this.INVOICENUMBER = invoice_number;
            this.BALANCE = balance;
            this.BALANCESTATUS = balance_status;
            this.REVERSEDUSER = reversed_user;
            this.REVERSEDTIME = reversed_date;
            this.REVERSEDDATE = reversed_time;
            this.REASON =reason;
            this.REF = ref;
    }
     
     public String getIN()
    {
        return INVOICENUMBER;
    }
    
    public Double getBalance()
    {
        return BALANCE;
    }
    
    public String getBalanceStatus()
    {
        return BALANCESTATUS;
    } 
    
    public String getReversedUser()
    {
        return REVERSEDUSER;
    }
    public String getReversedDate()
    {
        return REVERSEDDATE;
    }
    public String getReversedTime()
    {
        return REVERSEDTIME;
    }
    public String getREason()
    {
        return REASON;
    }
    
    public String getRef()
    {
        return REF;
    }

}
