package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;

import id.my.avmmartin.stocksportfolio.data.factory.BrokerFactory;
import id.my.avmmartin.stocksportfolio.data.factory.StockFactory;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;

public class DatabaseFactory extends DatabaseManager {
    public static void generate(Context context) {
        DatabaseFactory instance = new DatabaseFactory(context);

        try (SQLiteDatabase db = instance.getWritableDatabase()) {
            new BrokerFactory(db);
            new StockFactory(db);

            instance.generatePortfolio();
            instance.generateTransaction();
        }
    }

    // private generator

    private void generatePortfolio() {
        if (getPortfolioManager().size() == 0) {
            getPortfolioManager().insert(new Portfolio("LG", "Trima AVM", Calendar.getInstance()));
            getPortfolioManager().insert(new Portfolio("LG", "Trima AVM 2", Calendar.getInstance()));
        }
    }

    private void generateTransaction() {
        if (getTransactionManager().size() == 0) {
            for (int i = 0; i < 100; i++) {
                getTransactionManager().insert(
                    new Transaction(1, "BTPS", 1, Calendar.getInstance(), i, i, i)
                );
            }
        }
    }

    // constructor

    private DatabaseFactory(Context context) {
        super(context);
    }
}
