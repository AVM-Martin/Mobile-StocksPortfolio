package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;

import id.my.avmmartin.stocksportfolio.data.model.Broker;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.data.model.Stock;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.exception.GeneralException;

public class DataManager {
    public boolean isRegistered() {
        return preferencesManager.getHashPassword() == null;
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

    // constructor

    private DatabaseManager databaseManager;
    private PreferencesManager preferencesManager;

    public DataManager(Context context) {
        databaseManager = new DatabaseManager(context);
        preferencesManager = new PreferencesManager(context);

        databaseManager.checkDatabase();
    }
}
