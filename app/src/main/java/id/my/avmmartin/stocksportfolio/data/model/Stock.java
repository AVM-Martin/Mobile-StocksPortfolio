package id.my.avmmartin.stocksportfolio.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import id.my.avmmartin.stocksportfolio.data.StockManager;

public class Stock {
    private static final String ID = StockManager.ID;
    private static final String NAME = StockManager.NAME;

    private String id;
    private String name;

    // database-related method

    public Stock(Cursor cursor) {
        setId(cursor.getString(cursor.getColumnIndex(ID)));
        setName(cursor.getString(cursor.getColumnIndex(NAME)));
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, getId());
        contentValues.put(NAME, getName());

        return contentValues;
    }

    // constructor

    public Stock(String id, String name) {
        setId(id);
        setName(name);
    }

    // getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // setter

    private void setId(String id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }
}
