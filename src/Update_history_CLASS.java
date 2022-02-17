/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Update_history_CLASS {
    
    private String INVOICENUMBER;
    private String UPDATEDUSER;
    private String UPDATEDDATE;
    private String UPDATEDTIME;
    private String REF;
    private Double TOT;
    private Double OUTSTND;
    private String STAT;
    

     public Update_history_CLASS( String invoice_number, String updated_user, String updated_date,String updated_time, String ref,double tot,double outstand,String stat)
             {
            this.INVOICENUMBER = invoice_number;
            this.UPDATEDUSER = updated_user;
            this.UPDATEDDATE = updated_date;
            this.UPDATEDTIME = updated_time;
            this.REF = ref;
            this.TOT = tot;
            this.OUTSTND =outstand;
            this.STAT = stat;
    }
     
     public String getIN()
    {
        return INVOICENUMBER;
    }
    
    public String getupdateUser()
    {
        return UPDATEDUSER;
    }
    
    public String getupdateDate()
    {
        return UPDATEDDATE;
    } 
    
    public String getupdateTime()
    {
        return UPDATEDTIME;
    }
    public String getRef()
    {
        return REF;
    }
    public Double getTOT()
    {
        return TOT;
    }
    public Double getOUTSTAND()
    {
        return OUTSTND;
    }
    
    public String getSTATUS()
    {
        return STAT;
    }

}
