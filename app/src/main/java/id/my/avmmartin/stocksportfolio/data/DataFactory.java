package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;

import java.util.Calendar;

import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;

public class DataFactory extends DataManager {
    public static void generate(Context context) {
        new BrokerFactory(context);
        new StockFactory(context);

        DataFactory instance = new DataFactory(context);
        instance.generatePortfolio();
        instance.generateTransaction();
    }

    // private generator

    private void generatePortfolio() {
        if (portfolioSize() == 0) {
            insertPortfolio(new Portfolio("LG", "Trima AVM", Calendar.getInstance()));
            insertPortfolio(new Portfolio("LG", "Trima AVM 2", Calendar.getInstance()));
        }
    }

    private void generateTransaction() {
        if (transactionSize() == 0) {
            for (int i = 0; i < 100; i++) {
                insertTransaction(new Transaction(
                    1, "BTPS", 1, Calendar.getInstance(), i, i, i
                ));
            }
        }
    }

    // constructor

    private DataFactory(Context context) {
        super(context);
    }
}
