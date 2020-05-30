package id.my.avmmartin.stocksportfolio.data.manager;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import id.my.avmmartin.stocksportfolio.data.model.Stock;

public class StockManager {
    static final String TABLE_NAME = "stock";
    public static final int VERSION = 1;

    public static final String ID = "id";
    public static final String NAME = "name";

    public int size() {
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    // create read

    protected void insert(Stock stock) {
        db.insert(TABLE_NAME, null, stock.toContentValues());
    }

    public Stock getById(String id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            id
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            return new Stock(cursor);
        }
    }

    public Stock getByPosition(int position) {
        try (Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null)) {
            cursor.moveToPosition(position);
            return new Stock(cursor);
        }
    }

    // overridden method

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " TEXT PRIMARY KEY, "
                + NAME + " TEXT"
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

    public StockManager(SQLiteDatabase db) {
        this.db = db;
    }
}
