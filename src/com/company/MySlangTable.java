package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MySlangTable extends JTable {
    DefaultTableModel model ;

    public MySlangTable(DefaultTableModel dtm){
        super(dtm);
        model = (DefaultTableModel) getModel();
    }

    void clearData(){
        model.setRowCount(0);
    }

    void addData(String[] row){
        model.addRow(row);
    }

    public String[] getSelectedData(){
        int row = getSelectedRow();
        String[] data = {(String) model.getValueAt(row, 0), (String) model.getValueAt(row, 1)};
        return data;
    }

}
