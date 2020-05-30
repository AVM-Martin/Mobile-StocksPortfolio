package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.stocksportfolio.utils.Constants;

public class DatabaseManager extends SQLiteOpenHelper {
    void checkDatabase() {
        try (SQLiteDatabase db = getWritableDatabase()){
            onCreate(db);
        }
    }

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
        brokerManager.onCreate(db);
        stockManager.onCreate(db);
        portfolioManager.onCreate(db);
        transactionManager.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            brokerManager.onUpgrade(db, oldVersion, newVersion);
            stockManager.onUpgrade(db, oldVersion, newVersion);
            portfolioManager.onUpgrade(db, oldVersion, newVersion);
            transactionManager.onUpgrade(db, oldVersion, newVersion);
        }
    }

    // constructor

    private BrokerManager brokerManager;
    private PortfolioManager portfolioManager;
    private StockManager stockManager;
    private TransactionManager transactionManager;

    DatabaseManager(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);

        brokerManager = new BrokerManager(context);
        portfolioManager = new PortfolioManager(context);
        stockManager = new StockManager(context);
        transactionManager = new TransactionManager(context);
    }

    // getter

    BrokerManager getBrokerManager() {
        return brokerManager;
    }

    PortfolioManager getPortfolioManager() {
        return portfolioManager;
    }

    StockManager getStockManager() {
        return stockManager;
    }

    TransactionManager getTransactionManager() {
        return transactionManager;
    }
}
