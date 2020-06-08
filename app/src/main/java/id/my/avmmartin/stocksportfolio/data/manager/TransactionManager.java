package id.my.avmmartin.stocksportfolio.data.manager;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import id.my.avmmartin.stocksportfolio.data.model.Transaction;

public class TransactionManager {
    static final String TABLE_NAME = "transactions";
    public static final int VERSION = 1;

    public static final String ID = "id";
    public static final String FK_PORTFOLIO_ID = "fk_portfolio_id";
    public static final String FK_STOCK_ID = "fk_stock_id";
    public static final String TYPE = "type";
    public static final String TRANSACTION_DATE = "transaction_date";
    public static final String PRICE = "price";
    public static final String LOT = "lot";
    public static final String FEE = "fee";
    public static final String TOTAL = "total";

    public static final int TYPE_BUY = 1;
    public static final int TYPE_SELL = 2;

    public int size() {
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    public int sizeByPortfolio(int portfolioId) {
        String selection = (
            FK_PORTFOLIO_ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(portfolioId)
        };

        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, selection, selectionArgs);
    }

    // create read update

    public void insert(Transaction transaction) {
        db.insert(TABLE_NAME, null, transaction.toContentValues());
    }

    public Transaction getById(int id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(id)
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToFirst();
            return new Transaction(cursor);
        }
    }

    public Transaction getByPosition(int position) {
        try (Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null)) {
            cursor.moveToPosition(position);
            return new Transaction(cursor);
        }
    }

    public Transaction getByPortfolioByPosition(int portfolioId, int position) {
        String selection = (
            FK_PORTFOLIO_ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(portfolioId)
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToPosition(position);
            return new Transaction(cursor);
        }
    }

    public void update(Transaction transaction) {
        String whereClause = (
            ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(transaction.getId())
        };

        db.update(TABLE_NAME, transaction.toContentValues(), whereClause, whereArgs);
    }

    // overridden method

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FK_PORTFOLIO_ID + " INTEGER, "
                + FK_STOCK_ID + " TEXT, "
                + ""
                + TYPE + " INTEGER, "
                + TRANSACTION_DATE + " INTEGER, "
                + PRICE + " INTEGER, "
                + LOT + " INTEGER, "
                + FEE + " INTEGER, "
                + TOTAL + " INTEGER, "
                + ""
                + "FOREIGN KEY (" + FK_PORTFOLIO_ID + ") "
                + "REFERENCES " + PortfolioManager.TABLE_NAME + " (" + PortfolioManager.ID + ") "
                + "ON DELETE CASCADE "
                + "ON UPDATE NO ACTION, "
                + ""
                + "FOREIGN KEY (" + FK_STOCK_ID + ") "
                + "REFERENCES " + StockManager.TABLE_NAME + " (" + StockManager.ID + ") "
                + "ON DELETE CASCADE "
                + "ON UPDATE NO ACTION"
                + ");"
        );
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";"
            );
            onCreate(db);
        }
    }

    // constructor

    private SQLiteDatabase db;

    public TransactionManager(SQLiteDatabase db) {
        this.db = db;
    }
}
