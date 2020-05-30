package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;

public class DataFactory {
    public static void generate(Context context) {
        new BrokerFactory(context);
        new StockFactory(context);
    }

    // constructor

    private DataFactory() {
        // none
    }
}
