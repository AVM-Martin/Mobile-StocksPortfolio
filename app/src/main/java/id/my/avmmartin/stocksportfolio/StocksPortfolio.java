package id.my.avmmartin.stocksportfolio;

import android.app.Application;

import id.my.avmmartin.stocksportfolio.data.DatabaseFactory;
import id.my.avmmartin.stocksportfolio.data.DataManager;

public class StocksPortfolio extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        dataManager = new DataManager(this);
        DatabaseFactory.generate(this);
    }

    // getter

    private DataManager dataManager;

    public DataManager getDataManager() {
        return dataManager;
    }
}
