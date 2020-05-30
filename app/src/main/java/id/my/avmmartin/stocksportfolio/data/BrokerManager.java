package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.stocksportfolio.data.model.Broker;
import id.my.avmmartin.stocksportfolio.utils.Constants;

public class BrokerManager extends SQLiteOpenHelper {
    static final String TABLE_NAME = "broker";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BUY_FEE = "buyfee";
    public static final String SELL_FEE = "sellfee";

    int size() {
        try (SQLiteDatabase db = getWritableDatabase()) {
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        }
    }

    // read-only method

    Broker getById(String id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            id
        };

        try (SQLiteDatabase db = getWritableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                return new Broker(cursor);
            }
        }
    }

    Broker getByPosition(int position) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null)) {
                cursor.moveToPosition(position);
                return new Broker(cursor);
            }
        }
    }

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " TEXT PRIMARY KEY, "
                + NAME + " TEXT, "
                + BUY_FEE + " INTEGER, "
                + SELL_FEE + " INTEGER"
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

    BrokerManager(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }
}
