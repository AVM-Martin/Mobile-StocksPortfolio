package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.stocksportfolio.data.model.Stock;
import id.my.avmmartin.stocksportfolio.utils.Constants;

public class StockManager extends SQLiteOpenHelper {
    static final String TABLE_NAME = "stock";

    public static final String ID = "id";
    public static final String NAME = "name";

    int size() {
        try (SQLiteDatabase db = getReadableDatabase()) {
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        }
    }

    // read-only method

    Stock getById(String id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            id
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                return new Stock(cursor);
            }
        }
    }

    Stock getByPosition(int position) {
        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null)) {
                cursor.moveToPosition(position);
                return new Stock(cursor);
            }
        }
    }

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " TEXT PRIMARY KEY, "
                + NAME + " TEXT"
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

    StockManager(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }
}
