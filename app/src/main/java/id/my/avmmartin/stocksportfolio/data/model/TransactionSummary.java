package id.my.avmmartin.stocksportfolio.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import id.my.avmmartin.stocksportfolio.data.manager.TransactionSummaryManager;

public class TransactionSummary {
    private static final String FK_PORTFOLIO_ID = TransactionSummaryManager.FK_PORTFOLIO_ID;
    private static final String FK_STOCK_ID = TransactionSummaryManager.FK_STOCK_ID;
    private static final String AVG_PRICE = TransactionSummaryManager.AVG_PRICE;
    private static final String LOT = TransactionSummaryManager.LOT;
    private static final String TOTAL = TransactionSummaryManager.TOTAL;

    private int fkPortfolioId;
    private String fkStockId;
    private double avgPrice;
    private int lot;
    private double total;

    private int currentPrice;

    public void insertTransaction(Transaction transaction) {
        setLot(getLot() + transaction.getLot());

        if (transaction.getType() == Transaction.BUY) {
            setTotal(getTotal() + -transaction.getLot() * transaction.getPrice());
        } else {
            setTotal(getAvgPrice() * -getLot());
        }

        try {
            setAvgPrice(getTotal() / (double) -getLot());
        } catch (ArithmeticException e) {
            setAvgPrice(0);
            setTotal(0);
        }
    }

    // database-related method

    public TransactionSummary(Cursor cursor) {
        setFkPortfolioId(cursor.getInt(cursor.getColumnIndex(FK_PORTFOLIO_ID)));
        setFkStockId(cursor.getString(cursor.getColumnIndex(FK_STOCK_ID)));
        setAvgPrice(cursor.getDouble(cursor.getColumnIndex(AVG_PRICE)));
        setLot(cursor.getInt(cursor.getColumnIndex(LOT)));
        setTotal(cursor.getDouble(cursor.getColumnIndex(TOTAL)));

        setCurrentPrice(0);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(FK_PORTFOLIO_ID, getFkPortfolioId());
        contentValues.put(FK_STOCK_ID, getFkStockId());
        contentValues.put(AVG_PRICE, getAvgPrice());
        contentValues.put(LOT, getLot());
        contentValues.put(TOTAL, getTotal());

        return contentValues;
    }

    // constructor

    public TransactionSummary(int fkPortfolioId, String fkStockId) {
        setFkPortfolioId(fkPortfolioId);
        setFkStockId(fkStockId);
        setAvgPrice(0);
        setLot(0);
        setTotal(0);

        setCurrentPrice(0);
    }

    // getter

    public int getFkPortfolioId() {
        return fkPortfolioId;
    }

    public String getFkStockId() {
        return fkStockId;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public int getLot() {
        return lot;
    }

    public double getTotal() {
        return total;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    // setter

    private void setFkPortfolioId(int fkPortfolioId) {
        this.fkPortfolioId = fkPortfolioId;
    }

    private void setFkStockId(String fkStockId) {
        this.fkStockId = fkStockId;
    }

    private void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    private void setLot(int lot) {
        this.lot = lot;
    }

    private void setTotal(double total) {
        this.total = total;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
}
