package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.stocksportfolio.data.manager.BrokerManager;
import id.my.avmmartin.stocksportfolio.data.manager.PortfolioManager;
import id.my.avmmartin.stocksportfolio.data.manager.StockManager;
import id.my.avmmartin.stocksportfolio.data.manager.TransactionManager;
import id.my.avmmartin.stocksportfolio.data.manager.TransactionSummaryManager;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "stocksportfolio";
    private static final int DB_VERSION = (
        BrokerManager.VERSION
            + StockManager.VERSION
            + PortfolioManager.VERSION
            + TransactionManager.VERSION
    );

    private BrokerManager brokerManager;
    private PortfolioManager portfolioManager;
    private StockManager stockManager;
    private TransactionManager transactionManager;
    private TransactionSummaryManager transactionSummaryManager;

    // overridden method

    @Override
    public void onCreate(SQLiteDatabase db) {
        BrokerManager.onCreate(db);
        StockManager.onCreate(db);
        PortfolioManager.onCreate(db);
        TransactionManager.onCreate(db);
        TransactionSummaryManager.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        BrokerManager.onUpgrade(db, oldVersion, newVersion);
        StockManager.onUpgrade(db, oldVersion, newVersion);
        PortfolioManager.onUpgrade(db, oldVersion, newVersion);
        TransactionManager.onUpgrade(db, oldVersion, newVersion);
        TransactionSummaryManager.onUpgrade(db, oldVersion, newVersion);
    }

    // constructor

    private SQLiteDatabase db;

    DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();

        brokerManager = new BrokerManager(db);
        portfolioManager = new PortfolioManager(db);
        stockManager = new StockManager(db);
        transactionManager = new TransactionManager(db);
        transactionSummaryManager = new TransactionSummaryManager(db);
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

    TransactionSummaryManager getTransactionSummaryManager() {
        return transactionSummaryManager;
    }
}
