package com.netcracker;

import com.netcracker.library.Book;
import com.netcracker.tablemodel.BookModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumnModel;

public class Main extends JFrame {
    BookModel m;

    public Main() {

        super("Library");
        setSize(350, 250);
        setLocation(150, 100);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        m=deser();
       final JTable table = new JTable(m);
        JScrollPane jScrollPane = new JScrollPane(table);
        //add(jScrollPane);
        setVisible(true);
        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JPanel jpanel = new JPanel();

        add(jpanel);
        JPanel menuPanel = new JPanel();
        menuPanel.add(add);
        menuPanel.add(update);
        menuPanel.add(delete);
        menuPanel.setLayout(new FlowLayout());
        jpanel.add(jScrollPane);
        jpanel.add(menuPanel);
        jpanel.setLayout(new GridLayout(0, 1, 5, 12));

        delete.addActionListener((e)->{
            if(table.getSelectedRow()!=-1){
            int i=table.getSelectedRow();
            ((BookModel)table.getModel()).removeRow(i);}
        });
        add.addActionListener((x)->{
            new Dialog(table);

        });
        update.addActionListener((x)->{
            if(table.getSelectedRow()!=-1)
            new Dialog(table,table.getSelectedRow());
        });

        this.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                       try{ser();}
                       catch (IOException ex){
                           ex.printStackTrace();
                       }
                       System.exit(0);
                    }
                }
        );


    }
    void ser() throws IOException {
        ObjectOutputStream out =
                new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream("object.ser")));
        out.writeObject(m.getList());
        out.flush();
        out.close();
    }
    BookModel deser()  {
        BookModel b=null;
       try{ ObjectInputStream in =
                new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("object.ser")));

       b = new BookModel((ArrayList<Book>) in.readObject()); // downcast
        in.close();}
        catch (IOException| ClassNotFoundException e){
           e.printStackTrace();
        }
        return (b==null)?new BookModel():b;
    }

    public static void main(String[] args) {
	// write your code here
       SwingUtilities.invokeLater(()->new Main());

    }
}
