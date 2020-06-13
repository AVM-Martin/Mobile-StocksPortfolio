package id.my.avmmartin.stocksportfolio.data.manager;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import id.my.avmmartin.stocksportfolio.data.model.Broker;

public class BrokerManager {
    static final String TABLE_NAME = "broker";
    public static final int VERSION = 1;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BUY_FEE = "buyfee";
    public static final String SELL_FEE = "sellfee";

    public int size() {
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    // create read

    protected void insert(Broker broker) {
        db.insert(TABLE_NAME, null, broker.toContentValues());
    }

    public Broker getById(String id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            id
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToFirst();
            return new Broker(cursor);
        } catch (CursorIndexOutOfBoundsException e) {
            return null;
        }
    }

    public Broker getByPosition(int position) {
        try (Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null)) {
            cursor.moveToPosition(position);
            return new Broker(cursor);
        }
    }

    // overridden method

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " TEXT PRIMARY KEY, "
                + NAME + " TEXT, "
                + BUY_FEE + " INTEGER, "
                + SELL_FEE + " INTEGER"
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

    public BrokerManager(SQLiteDatabase db) {
        this.db = db;
    }
}
