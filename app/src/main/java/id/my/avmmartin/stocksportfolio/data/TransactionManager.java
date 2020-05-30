package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.utils.Constants;

public class TransactionManager extends SQLiteOpenHelper {
    static final String TABLE_NAME = "portfolio";
    static final int VERSION = 1;

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

    int size() {
        try (SQLiteDatabase db = getReadableDatabase()) {
            // TODO: check this behaviour with onCreate(db);
            onCreate(db);
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        }
    }

    int sizeByPortfolio(int portfolioId) {
        String selection = (
            FK_PORTFOLIO_ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(portfolioId)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            // TODO: check this behaviour with onCreate(db);
            onCreate(db);
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, selection, selectionArgs);
        }
    }

    // create read update

    void insert(Transaction transaction) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.insert(TABLE_NAME, null, transaction.toContentValues());
        }
    }

    Transaction getById(int id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(id)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                return new Transaction(cursor);
            }
        }
    }

    Transaction getByPosition(int position) {
        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null)) {
                cursor.moveToPosition(position);
                return new Transaction(cursor);
            }
        }
    }

    Transaction getByPortfolioByPosition(int portfolioId, int position) {
        String selection = (
            FK_PORTFOLIO_ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(portfolioId)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                cursor.moveToPosition(position);
                return new Transaction(cursor);
            }
        }
    }

    void update(Transaction transaction) {
        String whereClause = (
            ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(transaction.getId())
        };

        try (SQLiteDatabase db = getWritableDatabase()) {
            db.update(TABLE_NAME, transaction.toContentValues(), whereClause, whereArgs);
        }
    }

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";"
            );
            onCreate(db);
        }
    }

    // constructor

    TransactionManager(Context context) {
        super(context, Constants.DB_NAME, null, VERSION);
    }
}
