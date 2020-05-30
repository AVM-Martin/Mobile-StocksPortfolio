package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.utils.Constants;

public class PortfolioManager extends SQLiteOpenHelper {
    static final String TABLE_NAME = "portfolio";

    public static final String ID = "id";
    public static final String FK_BROKER_ID = "fk_broker_id";
    public static final String NAME = "name";
    public static final String CREATED_DATE = "created_date";
    public static final String STATUS = "status";

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_CLOSED = 2;

    int size() {
        String selection = (
            STATUS + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(STATUS_ACTIVE)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, selection, selectionArgs);
        }
    }

    // create read update

    void insert(Portfolio portfolio) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.insert(TABLE_NAME, null, portfolio.toContentValues());
        }
    }

    Portfolio getById(int id) {
        String selection = (
            ID + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(id)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                return new Portfolio(cursor);
            }
        }
    }

    Portfolio getByPosition(int position) {
        String selection = (
            STATUS + " = ?"
        );
        String[] selectionArgs = {
            Integer.toString(STATUS_ACTIVE)
        };

        try (SQLiteDatabase db = getReadableDatabase()) {
            try (Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
                cursor.moveToPosition(position);
                return new Portfolio(cursor);
            }
        }
    }

    void update(Portfolio portfolio) {
        String whereClause = (
            ID + " = ?"
        );
        String[] whereArgs = {
            Integer.toString(portfolio.getId())
        };

        try (SQLiteDatabase db = getWritableDatabase()) {
            db.update(TABLE_NAME, portfolio.toContentValues(), whereClause, whereArgs);
        }
    }

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    PortfolioManager(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }
}
