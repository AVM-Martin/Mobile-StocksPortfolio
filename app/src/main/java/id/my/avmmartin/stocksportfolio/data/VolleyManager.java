package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import id.my.avmmartin.stocksportfolio.data.manager.StockPriceManager;

public class VolleyManager {
    private StockPriceManager stockPriceManager;

    // singleton constructor

    private static VolleyManager instance = null;

    static VolleyManager getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyManager(context);
        }

        return instance;
    }

    private RequestQueue requestQueue;

    private VolleyManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        stockPriceManager = new StockPriceManager(requestQueue);
    }

    // getter

    StockPriceManager getStockPriceManager() {
        return stockPriceManager;
    }
}
