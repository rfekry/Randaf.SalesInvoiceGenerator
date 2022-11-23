/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoice;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rfekry
 */
public class InvoiceDetailsTable extends AbstractTableModel{
    private ArrayList<InvoiceDetails> allInvoices;
    private String[] cols = {"No.", "Date" ,"Customer", "Total"};

    public InvoiceDetailsTable(ArrayList<InvoiceDetails> allInvoices) {
        this.allInvoices = allInvoices;
    }
    
    

    @Override
    public int getRowCount() {
        return allInvoices.size();
    }

    @Override
    public int getColumnCount() {
       
       return cols.length;
    }
   
    @Override
     public String getColumnName(int column) {
      
        return cols[column];
    }
     
      @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceDetails inv = allInvoices.get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return inv.getInvoiceNo();
            case 1:
                return inv.getInvoiceDate();
            case 2:
                return inv.getCustomerName();
            case 3:
                return inv.getInvTotal();
            default:
                return "Unknown Column Index!";
        }
        
    }
}
