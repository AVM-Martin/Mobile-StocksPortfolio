package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;

import id.my.avmmartin.stocksportfolio.data.model.Broker;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.data.model.Stock;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.exception.GeneralException;
import id.my.avmmartin.stocksportfolio.utils.OnlineDataLoaderUtils;

public class DataManager {
    private DatabaseManager databaseManager;
    private PreferencesManager preferencesManager;
    private VolleyManager volleyManager;

    public String printPassword() {
        return preferencesManager.getHashPassword();
    }

    public boolean isRegistered() {
        return preferencesManager.getHashPassword() != null;
    }

    public boolean checkPassword(String hashPassword) throws GeneralException {
        try {
            return preferencesManager.getHashPassword().equals(hashPassword);
        } catch (NullPointerException e) {
            throw new GeneralException("Password is null");
        }
    }

    public void setPassword(String hashPassword) {
        preferencesManager.setHashPassword(hashPassword);
    }

    // broker

    public int brokerSize() {
        return databaseManager.getBrokerManager().size();
    }

    public Broker getBrokerById(String id) {
        return databaseManager.getBrokerManager().getById(id);
    }

    public Broker getBrokerByPosition(int position) {
        return databaseManager.getBrokerManager().getByPosition(position);
    }

    // portfolio

    public int portfolioSize() {
        return databaseManager.getPortfolioManager().size();
    }

    public void insertPortfolio(Portfolio portfolio) {
        databaseManager.getPortfolioManager().insert(portfolio);
    }

    public Portfolio getPortfolioById(int id) {
        return databaseManager.getPortfolioManager().getById(id);
    }

    public Portfolio getPortfolioByPosition(int position) {
        return databaseManager.getPortfolioManager().getByPosition(position);
    }

    public void updatePortfolio(Portfolio portfolio) {
        databaseManager.getPortfolioManager().update(portfolio);
    }

    // stock

    public int stockSize() {
        return databaseManager.getStockManager().size();
    }

    public Stock getStockById(String id) {
        return databaseManager.getStockManager().getById(id);
    }

    public Stock getStockByPosition(int position) {
        return databaseManager.getStockManager().getByPosition(position);
    }

    // transaction

    public int transactionSize() {
        return databaseManager.getTransactionManager().size();
    }

    public int transactionSizeByPortfolio(int portfolioId) {
        return databaseManager.getTransactionManager().sizeByPortfolio(portfolioId);
    }

    public void insertTransaction(Transaction transaction) {
        databaseManager.getTransactionManager().insert(transaction);
    }

    public Transaction getTransactionById(int id) {
        return databaseManager.getTransactionManager().getById(id);
    }

    public Transaction getTransactionByPosition(int position) {
        return databaseManager.getTransactionManager().getByPosition(position);
    }

    public Transaction getTransactionByPortfolioByPosition(int portfolioId, int position) {
        return databaseManager.getTransactionManager().getByPortfolioByPosition(portfolioId, position);
    }

    public void updateTransaction(Transaction transaction) {
        databaseManager.getTransactionManager().update(transaction);
    }

    // transaction summary

    public int transactionSummarySizeByPortfolio(int portfolioId) {
        return databaseManager.getTransactionManager().sizeSummaryByPortfolio(portfolioId);
    }

    public Transaction.Summary getTransactionSummaryByPortfolioByPosition(int portfolioId, int position) {
        return databaseManager
            .getTransactionManager()
            .getSummaryByPortfolioByPosition(portfolioId, position);
    }

    // stock price

    public void reloadOnlineStockPrice(OnlineDataLoaderUtils loaderUtils, String stockId) {
        volleyManager.getStockPriceManager().reloadOnlineData(loaderUtils, stockId);
    }

    public int getStockPrice(String stockId) {
        if (volleyManager.getStockPriceManager().getPrice(stockId) == null) {
            return 0;
        } else {
            return volleyManager.getStockPriceManager().getPrice(stockId);
        }
    }

    // singleton constructor

    private static DataManager instance = null;

    public static DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context.getApplicationContext());
        }

        return instance;
    }

    private DataManager(Context context) {
        databaseManager = new DatabaseManager(context);
        preferencesManager = new PreferencesManager(context);
        volleyManager = VolleyManager.getInstance(context);
    }
}
