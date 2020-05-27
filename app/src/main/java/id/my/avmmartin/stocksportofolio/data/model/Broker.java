package id.my.avmmartin.stocksportofolio.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import id.my.avmmartin.stocksportofolio.data.BrokerManager;

public class Broker {
    private static final String ID = BrokerManager.ID;
    private static final String NAME = BrokerManager.NAME;
    private static final String BUY_FEE = BrokerManager.BUY_FEE;
    private static final String SELL_FEE = BrokerManager.SELL_FEE;

    private String id;
    private String name;
    private int buyFee;
    private int sellFee;

    // database-related method

    public Broker(Cursor cursor) {
        setId(cursor.getString(cursor.getColumnIndex(ID)));
        setName(cursor.getString(cursor.getColumnIndex(NAME)));
        setBuyFee(cursor.getInt(cursor.getColumnIndex(BUY_FEE)));
        setSellFee(cursor.getInt(cursor.getColumnIndex(SELL_FEE)));
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, getId());
        contentValues.put(NAME, getName());
        contentValues.put(BUY_FEE, getBuyFee());
        contentValues.put(SELL_FEE, getSellFee());

        return contentValues;
    }

    // constructor

    public Broker(String id, String name, int buyFee, int sellFee) {
        setId(id);
        setName(name);
        setBuyFee(buyFee);
        setSellFee(sellFee);
    }

    // getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBuyFee() {
        return buyFee;
    }

    public int getSellFee() {
        return sellFee;
    }

    // setter

    private void setId(String id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setBuyFee(int buyFee) {
        this.buyFee = buyFee;
    }

    private void setSellFee(int sellFee) {
        this.sellFee = sellFee;
    }
}
