/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoice;

import java.util.ArrayList;

/**
 *
 * @author rfekry
 */
public class InvoiceDetails {
   private int invoiceNo;
   private String invoiceDate;
   private String customerName;
   private ArrayList<ItemDetails> items;
   

    public InvoiceDetails() {
    }
    
    
  public InvoiceDetails(int invoiceNo, String invoiceDate, String customerName) {
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }
    public InvoiceDetails(int invoiceNo, String invoiceDate, String customerName, ArrayList<ItemDetails> items) {
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.items = items;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<ItemDetails> getItems() {
        if(items == null)
            items = new ArrayList<>();
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }
 

 public double getInvTotal()
   {
       double total= 0.0;
       for(ItemDetails item: getItems())
       {
           total =total + item.getItemTotal();
       }
       return total;
   }

    public String getAsCSV() {
          return invoiceNo + "," + invoiceDate + "," + customerName;
    }

    
}
