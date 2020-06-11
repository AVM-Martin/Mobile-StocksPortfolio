package id.my.avmmartin.stocksportfolio.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Calendar;

import id.my.avmmartin.stocksportfolio.data.manager.TransactionManager;

public class Transaction {
    private static final String ID = TransactionManager.ID;
    private static final String FK_PORTFOLIO_ID = TransactionManager.FK_PORTFOLIO_ID;
    private static final String FK_STOCK_ID = TransactionManager.FK_STOCK_ID;
    private static final String TYPE = TransactionManager.TYPE;
    private static final String TRANSACTION_DATE = TransactionManager.TRANSACTION_DATE;
    private static final String PRICE = TransactionManager.PRICE;
    private static final String LOT = TransactionManager.LOT;
    private static final String FEE = TransactionManager.FEE;
    private static final String TOTAL = TransactionManager.TOTAL;

    public static final int BUY = TransactionManager.TYPE_BUY;
    public static final int SELL = TransactionManager.TYPE_SELL;

    private int id;
    private int fkPortfolioId;
    private String fkStockId;
    private int type;
    private Calendar transactionDate;
    private int price;
    private int lot;
    private int fee;
    private int total;

    // database-related method

    public Transaction(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(ID)));
        setFkPortfolioId(cursor.getInt(cursor.getColumnIndex(FK_PORTFOLIO_ID)));
        setFkStockId(cursor.getString(cursor.getColumnIndex(FK_STOCK_ID)));
        setType(cursor.getInt(cursor.getColumnIndex(TYPE)));

        Calendar transactionDate = Calendar.getInstance();
        transactionDate.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(TRANSACTION_DATE)));
        setTransactionDate(transactionDate);

        setPrice(cursor.getInt(cursor.getColumnIndex(PRICE)));
        setLot(cursor.getInt(cursor.getColumnIndex(LOT)));
        setFee(cursor.getInt(cursor.getColumnIndex(FEE)));
        setTotal(cursor.getInt(cursor.getColumnIndex(TOTAL)));
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(FK_PORTFOLIO_ID, getFkPortfolioId());
        contentValues.put(FK_STOCK_ID, getFkStockId());
        contentValues.put(TYPE, getType());
        contentValues.put(TRANSACTION_DATE, getTransactionDate().getTimeInMillis());
        contentValues.put(PRICE, getPrice());
        contentValues.put(LOT, getLot());
        contentValues.put(FEE, getFee());
        contentValues.put(TOTAL, getTotal());

        return contentValues;
    }

    // constructor

    public Transaction(
            int fkPortfolioId,
            String fkStockId,
            int type,
            Calendar transactionDate,
            int price,
            int lot,
            int fee
    ) {
        setFkPortfolioId(fkPortfolioId);
        setFkStockId(fkStockId);
        setType(type);
        setTransactionDate(transactionDate);
        setPrice(price);
        setFee(fee);

        if (type == BUY) {
            setLot(lot);
            setTotal(-lot * price + fee);
        } else {
            setLot(-lot);
            setTotal(lot * price + fee);
        }
    }

    // getter

    public int getId() {
        return id;
    }

    public int getFkPortfolioId() {
        return fkPortfolioId;
    }

    public String getFkStockId() {
        return fkStockId;
    }

    public int getType() {
        return type;
    }

    public Calendar getTransactionDate() {
        return transactionDate;
    }

    public int getPrice() {
        return price;
    }

    public int getLot() {
        return lot;
    }

    public int getFee() {
        return fee;
    }

    public int getTotal() {
        return total;
    }

    // setter

    private void setId(int id) {
        this.id = id;
    }

    private void setFkPortfolioId(int fkPortfolioId) {
        this.fkPortfolioId = fkPortfolioId;
    }

    private void setFkStockId(String fkStockId) {
        this.fkStockId = fkStockId;
    }

    private void setType(int type) {
        this.type = type;
    }

    private void setTransactionDate(Calendar transactionDate) {
        this.transactionDate = transactionDate;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    private void setLot(int lot) {
        this.lot = lot;
    }

    private void setFee(int fee) {
        this.fee = fee;
    }

    private void setTotal(int total) {
        this.total = total;
    }

    public static class Summary {
        private String fkStockId;
        private int lot;
        private int fee;
        private int total;

        public Summary(Cursor cursor) {
            setFkStockId(cursor.getString(cursor.getColumnIndex(FK_STOCK_ID)));
            setLot(cursor.getInt(cursor.getColumnIndex(LOT)));
            setFee(cursor.getInt(cursor.getColumnIndex(FEE)));
            setTotal(cursor.getInt(cursor.getColumnIndex(TOTAL)));
        }

        // getter

        public String getFkStockId() {
            return fkStockId;
        }

        public int getLot() {
            return lot;
        }

        public int getFee() {
            return fee;
        }

        public int getTotal() {
            return total;
        }

        // setter

        private void setFkStockId(String fkStockId) {
            this.fkStockId = fkStockId;
        }

        private void setLot(int lot) {
            this.lot = lot;
        }

        private void setFee(int fee) {
            this.fee = fee;
        }

        private void setTotal(int total) {
            this.total = total;
        }
    }
}
