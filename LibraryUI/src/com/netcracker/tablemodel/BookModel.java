package com.netcracker.tablemodel;

import com.netcracker.library.Book;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class BookModel extends AbstractTableModel implements java.io.Serializable{

    private List<Book> books = new ArrayList<>();
    public  List<Book> getList(){return books;}


    public BookModel() {
       // books.add(new Book("123",100,10.5,"Tolstoy"));
    }
    public BookModel(List<Book> books) {
        this.books=books;
    }

    public void addBook(Book b){
       books.add(b);
        fireTableDataChanged();
    }

    public  void setBook(Book b,int index){
        books.set(index,b);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public void removeRow(int row){
        books.remove(row);
        fireTableDataChanged();

    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur=books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cur.getName();
            case 1:
                return cur.getAuthor().toString();
            case 2:
                return cur.getPrice();
            case 3:
                return cur.getQty();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Book name";
            case 1:
                return "Author";
            case 2:
                return "Price";
            case 3:
                return "Count";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Integer.class;
        }
        return Object.class;
    }
}
