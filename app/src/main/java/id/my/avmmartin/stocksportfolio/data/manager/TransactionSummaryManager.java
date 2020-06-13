package id.my.avmmartin.stocksportfolio.data.manager;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import id.my.avmmartin.stocksportfolio.data.model.TransactionSummary;

public class TransactionSummaryManager {
    static final String TABLE_NAME = "transactions_summary";
    public static final int VERSION = 1;

    public static final String FK_PORTFOLIO_ID = "fk_portfolio_id";
    public static final String FK_STOCK_ID = "fk_stock_id";
    public static final String AVG_PRICE = "price";
    public static final String LOT = "lot";
    public static final String TOTAL = "total";

    public int sizeByPortfolio(int portfolioId) {
        String selection = (
            FK_PORTFOLIO_ID + " = ?"
                + "AND " + LOT + " > 0"
        );
        String[] selectionArgs = {
            Integer.toString(portfolioId)
        };

        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, selection, selectionArgs);
    }

    public int size() {
        String selection = (
            LOT + " > 0"
        );

        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, selection, null);
    }

    // create read update

    public void insertOrUpdate(TransactionSummary transactionSummary) {
        String whereClause = (
            FK_PORTFOLIO_ID + " = ? "
                + "AND " + FK_STOCK_ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(transactionSummary.getFkPortfolioId()),
            transactionSummary.getFkStockId()
        };

        try {
            db.insertOrThrow(TABLE_NAME, null, transactionSummary.toContentValues());
        } catch (SQLiteConstraintException e) {
            db.update(TABLE_NAME, transactionSummary.toContentValues(), whereClause, whereArgs);
        }
    }

    public TransactionSummary getByPortfolioByStock(int portfolioId, String stockId) {
        String selection = (
            FK_PORTFOLIO_ID + " = ? "
                + "AND " + FK_STOCK_ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(portfolioId),
            stockId
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToFirst();
            return new TransactionSummary(cursor);
        } catch (CursorIndexOutOfBoundsException e) {
            return new TransactionSummary(portfolioId, stockId);
        }
    }

    public TransactionSummary getByPortfolioByPosition(int portfolioId, int position) {
        String selection = (
            FK_PORTFOLIO_ID + " = ? "
                + "AND " + LOT + " > 0"
        );
        String[] selectionArgs = {
            Integer.toString(portfolioId)
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToPosition(position);
            return new TransactionSummary(cursor);
        }
    }

    public TransactionSummary getByPosition(int position) {
        String selection = (
            LOT + " > 0"
        );

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, null, null, null, null)) {
            cursor.moveToPosition(position);
            return new TransactionSummary(cursor);
        }
    }

    // overridden method

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + FK_PORTFOLIO_ID + " INTEGER, "
                + FK_STOCK_ID + " TEXT, "
                + ""
                + AVG_PRICE + " INTEGER, "
                + LOT + " INTEGER, "
                + TOTAL + " INTEGER, "
                + ""
                + "PRIMARY KEY (" + FK_PORTFOLIO_ID + ", " + FK_STOCK_ID + "), "
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

    public TransactionSummaryManager(SQLiteDatabase db) {
        this.db = db;
    }
}
