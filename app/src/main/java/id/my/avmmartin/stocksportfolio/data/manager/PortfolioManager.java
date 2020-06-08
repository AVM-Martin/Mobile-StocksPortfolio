package id.my.avmmartin.stocksportfolio.data.manager;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import id.my.avmmartin.stocksportfolio.data.model.Portfolio;

public class PortfolioManager {
    static final String TABLE_NAME = "portfolio";
    public static final int VERSION = 1;

    public static final String ID = "id";
    public static final String FK_BROKER_ID = "fk_broker_id";
    public static final String NAME = "name";
    public static final String CREATED_DATE = "created_date";
    public static final String STATUS = "status";

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_CLOSED = 2;

    public int size() {
        String selection = (
            STATUS + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(STATUS_ACTIVE)
        };

        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, selection, selectionArgs);
    }

    // create read update

    public void insert(Portfolio portfolio) {
        db.insert(TABLE_NAME, null, portfolio.toContentValues());
    }

    public Portfolio getById(int id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(id)
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToFirst();
            return new Portfolio(cursor);
        }
    }

    public Portfolio getByPosition(int position) {
        String selection = (
            STATUS + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(STATUS_ACTIVE)
        };

        try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            cursor.moveToPosition(position);
            return new Portfolio(cursor);
        }
    }

    public void update(Portfolio portfolio) {
        String whereClause = (
            ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(portfolio.getId())
        };

        db.update(TABLE_NAME, portfolio.toContentValues(), whereClause, whereArgs);
    }

    // overridden method

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FK_BROKER_ID + " TEXT, "
                + ""
                + NAME + " TEXT, "
                + CREATED_DATE + " INTEGER, "
                + STATUS + " INTEGER, "
                + ""
                + "FOREIGN KEY (" + FK_BROKER_ID + ") "
                + "REFERENCES " + BrokerManager.TABLE_NAME + " (" + BrokerManager.ID + ") "
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

    public PortfolioManager(SQLiteDatabase db) {
        this.db = db;
    }
}
