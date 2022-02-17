/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class Invoice_item_CLASS {
    
    private String ROWNO;
    private String INVOICENUM;
    private String ITEMID;
    private String ITEMNAME;
    private double UNITPRICE;
    private int QUNTITY;
    private double DISCOUNT;
    private double TAX;
    private String NOTE;
    private double NETAMMOUNT;
    private double DISCOUNTEAMMOUNT;
    private double TAXAMMOUNT;
    private double FINALEAMMOUNT;
    
    public Invoice_item_CLASS(String rowNo,String invoice_num,String item_id, String item_name, double unit_price, int quntity,double discout,
            double tax, String note,double net_ammount,double dicount_ammount,double tax_ammount,double finale_ammount)
    {
        this.ROWNO = rowNo;
        this.INVOICENUM = invoice_num;
        this.ITEMID = item_id;
        this.ITEMNAME = item_name;
        this.UNITPRICE = unit_price;
        this.QUNTITY = quntity;
        this.DISCOUNT = discout;
        this.TAX = tax;
        this.NOTE = note;
        this.NETAMMOUNT = net_ammount;
        this.DISCOUNTEAMMOUNT = dicount_ammount;
        this.TAXAMMOUNT = tax_ammount;
        this.FINALEAMMOUNT = finale_ammount;

    }
    public String getRowNo()
    {
        return ROWNO;
    }
    public String getInvoiceID()
    {
        return INVOICENUM;
    }
    public String getItemID()
    {
        return ITEMID;
    }
    public String getItemName()
    {
        return ITEMNAME;
    }
    public double getUniprice()
    {
        return UNITPRICE;
    }
    public int getQuntity()
    {
        return QUNTITY;
    }
    public double getDiscount()
    {
        return DISCOUNT;
    }
    public double getTax()
    {
        return TAX;
    }
    public String getNote()
    {
        return NOTE;
    }
    public double getNetAmmount()
    {
        return NETAMMOUNT;
    }
    public double getDiscountAmmount()
    {
        return DISCOUNTEAMMOUNT;
    }
    public double getTaxAmmount()
    {
        return TAXAMMOUNT;
    }
    public double getFinaleAmmount()
    {
        return FINALEAMMOUNT;
    }
    
}
