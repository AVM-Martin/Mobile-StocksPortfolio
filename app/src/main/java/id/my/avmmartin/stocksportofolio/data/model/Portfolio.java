package id.my.avmmartin.stocksportofolio.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Calendar;

import id.my.avmmartin.stocksportofolio.data.PortfolioManager;

public class Portfolio {
    private static final String ID = PortfolioManager.ID;
    private static final String FK_BROKER_ID = PortfolioManager.FK_BROKER_ID;
    private static final String NAME = PortfolioManager.NAME;
    private static final String CREATED_DATE = PortfolioManager.CREATED_DATE;
    private static final String STATUS = PortfolioManager.STATUS;

    private static final int STATUS_ACTIVE = PortfolioManager.STATUS_ACTIVE;
    private static final int STATUS_CLOSED = PortfolioManager.STATUS_CLOSED;

    private int id;
    private String fkBrokerId;
    private String name;
    private Calendar createdDate;
    private int status;

    public void closePortofolio() {
        setStatus(STATUS_CLOSED);
    }

    // database-related method

    public Portfolio(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(ID)));
        setFkBrokerId(cursor.getString(cursor.getColumnIndex(FK_BROKER_ID)));
        setName(cursor.getString(cursor.getColumnIndex(NAME)));

        Calendar createdDate = Calendar.getInstance();
        createdDate.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(CREATED_DATE)));
        setCreatedDate(createdDate);

        setStatus(cursor.getInt(cursor.getColumnIndex(STATUS)));
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(FK_BROKER_ID, getFkBrokerId());
        contentValues.put(NAME, getName());
        contentValues.put(CREATED_DATE, getCreatedDate().getTimeInMillis());
        contentValues.put(STATUS, getStatus());

        return contentValues;
    }

    // constructor

    public Portfolio(int id, String fkBrokerId, String name, Calendar createdDate) {
        setId(id);
        setFkBrokerId(fkBrokerId);
        setName(name);
        setCreatedDate(createdDate);
        setStatus(STATUS_ACTIVE);
    }

    // getter

    public int getId() {
        return id;
    }

    public String getFkBrokerId() {
        return fkBrokerId;
    }

    public String getName() {
        return name;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    private int getStatus() {
        return status;
    }

    // setter

    private void setId(int id) {
        this.id = id;
    }

    private void setFkBrokerId(String fkBrokerId) {
        this.fkBrokerId = fkBrokerId;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    private void setStatus(int status) {
        this.status = status;
    }
}
