/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoice;

/**
 *
 * @author rfekry
 */
public class ItemDetails { 
   // private int invoiceNumber;
    private String itemName;
    private double itemPrice;
    private int noOfItems;
    private InvoiceDetails inv;
    

    public ItemDetails() {
    }

    public ItemDetails(String itemName, double itemPrice, int noOfItems, InvoiceDetails inv) {
       // this.invoiceNumber = invoiceNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.noOfItems = noOfItems;
        this.inv = inv;
        
    }

   /* public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }*/

    public InvoiceDetails getInv() {
        return inv;
    }

    public void setInv(InvoiceDetails inv) {
        this.inv = inv;
    }



    public String getItemName() {
        return itemName;
    }

    public void setItem(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }
    
    
    public double getItemTotal()
   {
         
       return itemPrice * noOfItems;
   }

    public String getAsCSV() {
        return inv.getInvoiceNo() + "," + itemName + "," + itemPrice + "," + noOfItems;
    }
    
    
}
