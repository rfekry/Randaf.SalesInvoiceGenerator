/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;


import GraphicalInterface.AddNewInvoice;
import GraphicalInterface.AddNewItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import SalesInvoice.InvoiceDetails;
import SalesInvoice.ItemDetails;
import GraphicalInterface.Invoice;
import SalesInvoice.InvoiceDetailsTable;
import SalesInvoice.ItemDetailsTable;
import java.io.FileWriter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author rfekry
 */
public class Action implements ActionListener, ListSelectionListener{
    
    private final Invoice i;
    public Action(Invoice i1)
    {
        i=i1;
    }
    private AddNewInvoice newInv;
    private AddNewItem newItem;

    @Override
    public void actionPerformed(ActionEvent e) {
       
        String command = e.getActionCommand();
       
  
        switch (command) {
            case "Load File":
                readFile();
                break;
            case "Save File":
                writeFile();
                break;
            case "Create New Invoice":
                createInvoice();
                break;
            case "Delete Invoice":
                deleteCurrentInvoice();
                break;
            case "Create New Item":
                createItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "createNewInvoiceOK":
                createNewInvoiceOK();
                break;
            case "createNewInvoiceCancel":
                createNewInvoiceCancel();
                break;
             case "createNewItemOK":
                createNewItemOK();
                break;
            case "createNewItemCancel":
                createNewItemCancel();
                break;
                           
                       
            default:
                System.out.println("Unknown Command");
                break;
        }
            
    }
 @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = i.getInvoiceTable().getSelectedRow();
        if(index != -1)
        {
        InvoiceDetails invoiceSelected = i.getAllInvoices().get(index);
       
        i.getInvoiceNo_label().setText(String.valueOf(invoiceSelected.getInvoiceNo()));
        i.getInvoiceDateTxt().setText(invoiceSelected.getInvoiceDate());
        i.getCutomerNameTxt().setText(invoiceSelected.getCustomerName());
        i.getInvioceTotal_Label().setText(String.valueOf(invoiceSelected.getInvTotal()));
        ItemDetailsTable itemTable = new ItemDetailsTable(invoiceSelected.getItems());
        i.getInvoicesItemsTable().setModel(itemTable);
        itemTable.fireTableDataChanged();
        }
      
    }
    private void readFile() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JFileChooser chooseFile = new JFileChooser();
        try
        {
        if(chooseFile.showOpenDialog(i)== JFileChooser.APPROVE_OPTION)
        {
            File f = chooseFile.getSelectedFile();
            Path p = Paths.get(f.getAbsolutePath());
            List<String> lines;
            lines = Files.readAllLines(p);
            ArrayList<InvoiceDetails> invList = new ArrayList<>();
            for (String line: lines)
            {
                String[] columns = line.split(",");
                int invNo = Integer.parseInt(columns[0]);
                String invDate = columns[1];
                String custName = columns[2];
                InvoiceDetails invoice = new InvoiceDetails(invNo,invDate,custName);
                invList.add(invoice);
            }
           
         
         if(chooseFile.showOpenDialog(i)== JFileChooser.APPROVE_OPTION)
        {
            File itemsFile = chooseFile.getSelectedFile();
            Path itemsPath = Paths.get(itemsFile.getAbsolutePath());
            List<String> itemNo;
            itemNo = Files.readAllLines(itemsPath);
            for (String iNo: itemNo)
            {
                String[] col = iNo.split(",");
                int invoiceNumber = Integer.parseInt(col[0]);
                String item = col[1];
                double itemPrice = Double.parseDouble(col[2]);
                int noOfItems = Integer.parseInt(col[3]);
                InvoiceDetails inv = null;
                for(InvoiceDetails i: invList)
                {
                    if(i.getInvoiceNo() == invoiceNumber)
                    {
                        inv=i;
                    break;
                    }
                }
                ItemDetails itemDetail = new ItemDetails(item, itemPrice,noOfItems, inv);
                inv.getItems().add(itemDetail);
            }
            
        }
         i.setAllInvoices(invList);
         InvoiceDetailsTable invTable = new InvoiceDetailsTable(invList);
         i.setInvTable(invTable);
         i.getInvoiceTable().setModel(invTable);
         i.getInvTable().fireTableDataChanged();
         
        
        }
       
        }
          catch(IOException ex)
                {
                    //e.printStackTrace();
                }
        }

    private void writeFile() {
          ArrayList<InvoiceDetails> invList = i.getAllInvoices();
        String headers = "";
        String lines = "";
        for (InvoiceDetails inv : invList) {
            String invCSV = inv.getAsCSV();
            headers += invCSV;
            headers += "\n";

            for (ItemDetails item : inv.getItems()) {
                String itemCSV = item.getAsCSV();
                lines += itemCSV;
                lines += "\n";
            }
        }
        
        try {
            JFileChooser chooseFile = new JFileChooser();
            int result = chooseFile.showSaveDialog(i);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = chooseFile.getSelectedFile();
                FileWriter writeFile = new FileWriter(headerFile);
                writeFile.write(headers);
                writeFile.flush();
                writeFile.close();
                result = chooseFile.showSaveDialog(i);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File f = chooseFile.getSelectedFile();
                    FileWriter fw = new FileWriter(f);
                    fw.write(lines);
                    fw.flush();
                    fw.close();
                }
            }
        } catch (Exception ex) {

        }
    }

    private void createInvoice() {
       newInv = new AddNewInvoice(i);
       newInv.setVisible(true);
    }

    private void deleteCurrentInvoice() {
      int row = i.getInvoiceTable().getSelectedRow();
       if(row != -1)
       {
           i.getAllInvoices().remove(row);
           i.getInvTable().fireTableDataChanged();
       }
    }

    private void createItem() {
      newItem = new AddNewItem(i);
      newItem.setVisible(true);
    }

    private void deleteItem() {
        int invRow = i.getInvoiceTable().getSelectedRow();
        int itemRow = i.getInvoicesItemsTable().getSelectedRow();
       if(invRow != -1 && itemRow != -1)
       {
           InvoiceDetails inv = i.getAllInvoices().get(invRow);
           inv.getItems().remove(itemRow);
           ItemDetailsTable itemsTable = new ItemDetailsTable(inv.getItems());
           i.getInvoicesItemsTable().setModel(itemsTable);
           itemsTable.fireTableDataChanged();
           i.getInvTable().fireTableDataChanged();
       }
      
    }  

    private void createNewInvoiceOK() {
      String invDate = newInv.getInvoiceDateField().getText();
      String custName = newInv.getCustomerNameField().getText();
      int invNum = i.getNextNum();
      InvoiceDetails inv = new InvoiceDetails(invNum+1, invDate,custName);
      i.getAllInvoices().add(inv);
      i.getInvTable().fireTableDataChanged();
      newInv.setVisible(false);
      newInv.dispose();
      newInv = null;
     
     
    }

    private void createNewInvoiceCancel() {
        newInv.setVisible(false);
        newInv.dispose();
        newInv = null;
    }

    private void createNewItemOK() {
       String itemName = newItem.getItemNameField().getText();
        String noOfItems = newItem.getItemCountField().getText();
        String itemPrice = newItem.getItemPriceField().getText();
        int count = Integer.parseInt(noOfItems);
        double price = Double.parseDouble(itemPrice);
        int currentInvoice = i.getInvoiceTable().getSelectedRow();
        if (currentInvoice != -1) {
            InvoiceDetails inv = i.getAllInvoices().get(currentInvoice);
            ItemDetails item = new ItemDetails(itemName, price, count, inv);
            inv.getItems().add(item);
            ItemDetailsTable itemTable = (ItemDetailsTable) i.getInvoicesItemsTable().getModel();
            itemTable.fireTableDataChanged();
            i.getInvTable().fireTableDataChanged();
           
        }
        newItem.setVisible(false);
        newItem.dispose();
        newItem = null;
    
    }

    private void createNewItemCancel() {
        newItem.setVisible(false);
        newItem.dispose();
        newItem = null;
        
    }
        

}
