package com.netcracker;

import com.netcracker.library.Book;
import com.netcracker.tablemodel.BookModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

public class Dialog extends JDialog {
    Book book=null;
    JLabel name=new JLabel("Name");
    JLabel author=new JLabel("Author");
    JLabel price=new JLabel("Price");
    JLabel qty=new JLabel("qty");
    JTextField nameTf=new JTextField();
    JTextField authorTf=new JTextField();
    JTextField priceTf=new JFormattedTextField(new DecimalFormat("##0.###"));
    JTextField qtyTf=new JFormattedTextField(new DecimalFormat("##0"));
    JButton ok=new JButton("Ok");
    JButton cancel=new JButton("Cancel");


    public Dialog(JTable table){
        super();
        setSize(350, 250);
        setLocation(150, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(5,2));

        add(name);
        add(nameTf);
        add(author);
        add(authorTf);
        add(price);
        add(priceTf);
        add(qty);
        add(qtyTf);

        add(ok);
        add(cancel);
        setVisible(true);
        cancel.addActionListener((x)->{
            book=null;
            Dialog.this.setVisible(false);
            Dialog.this.dispatchEvent(new WindowEvent(
                    Dialog.this, WindowEvent.WINDOW_CLOSING));

        });
        ok.addActionListener((x)->{
          try{  book=new Book(nameTf.getText(), authorTf.getText(),Double.parseDouble(priceTf.getText()),Integer.parseInt(qtyTf.getText()));}
          catch (NumberFormatException e)
          {
              e.printStackTrace();
              priceTf.setText("");
              qtyTf.setText("");
          }
           if(book!=null){
               ((BookModel)table.getModel()).addBook(book);
               Dialog.this.setVisible(false);
            Dialog.this.dispatchEvent(new WindowEvent(
                    Dialog.this, WindowEvent.WINDOW_CLOSING));
           }

        });

    }
    public Dialog(JTable table,int updateIndex){
        this(table);
        nameTf.setText(table.getModel().getValueAt(updateIndex,0).toString());
        authorTf.setText(table.getModel().getValueAt(updateIndex,1).toString());
        priceTf.setText(table.getModel().getValueAt(updateIndex,2).toString());
        qtyTf.setText(table.getModel().getValueAt(updateIndex,3).toString());
        ok.removeActionListener(ok.getActionListeners()[0]);
        ok.addActionListener((x)->{
            try{  book=new Book(nameTf.getText(), authorTf.getText(),Double.parseDouble(priceTf.getText()),Integer.parseInt(qtyTf.getText()));}
            catch (NumberFormatException e)
            {
                e.printStackTrace();
                priceTf.setText("");
                qtyTf.setText("");
            }
            if(book!=null){

                ((BookModel)table.getModel()).setBook(book,updateIndex);
                Dialog.this.setVisible(false);
                Dialog.this.dispatchEvent(new WindowEvent(
                        Dialog.this, WindowEvent.WINDOW_CLOSING));
            }

        });


    }
    public Book getBook(){
        return book;
    }

    public static void main(String[] args) {

    }

}
