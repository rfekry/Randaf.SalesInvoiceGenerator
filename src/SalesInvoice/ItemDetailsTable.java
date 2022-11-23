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
public class ItemDetailsTable extends AbstractTableModel{
    private ArrayList<ItemDetails> allItems;
    private String[] cols = {"No.", "Item Name" ,"Item Price", "Count", "Item Total"};

    public ItemDetailsTable(ArrayList<ItemDetails> allItems) {
        this.allItems = allItems;
    }

    public ArrayList<ItemDetails> getAllItems() {
        return allItems;
    }
    

    @Override
    public int getRowCount() {
        return allItems.size();
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
        ItemDetails item = allItems.get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return item.getInv().getInvoiceNo();
            case 1:
                return item.getItemName();
            case 2:
                return item.getItemPrice();
            case 3:
                return item.getNoOfItems();
            case 4:
                return item.getItemTotal();
            default:
                return "Unknown Column Index!";
        }
    
}
}
