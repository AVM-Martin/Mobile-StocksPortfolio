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
        return brokerManager.size();
    }

    public Broker getBrokerById(String id) {
        return brokerManager.getById(id);
    }

    public Broker getBrokerByPosition(int position) {
        return brokerManager.getByPosition(position);
    }

    // portfolio

    public int portfolioSize() {
        return portfolioManager.size();
    }

    public void insertPortfolio(Portfolio portfolio) {
        portfolioManager.insert(portfolio);
    }

    public Portfolio getPortfolioById(int id) {
        return portfolioManager.getById(id);
    }

    public Portfolio getPortfolioByPosition(int position) {
        return portfolioManager.getByPosition(position);
    }

    public void updatePortfolio(Portfolio portfolio) {
        portfolioManager.update(portfolio);
    }

    // stock

    public int stockSize() {
        return stockManager.size();
    }

    public Stock getStockById(String id) {
        return stockManager.getById(id);
    }

    public Stock getStockByPosition(int position) {
        return stockManager.getByPosition(position);
    }

    // transaction

    public int transactionSize() {
        return transactionManager.size();
    }

    public int transactionSizeByPortfolio(int portfolioId) {
        return transactionManager.sizeByPortfolio(portfolioId);
    }

    public void insertTransaction(Transaction transaction) {
        transactionManager.insert(transaction);
    }

    public Transaction getTransactionById(int id) {
        return transactionManager.getById(id);
    }

    public Transaction getTransactionByPosition(int position) {
        return transactionManager.getByPosition(position);
    }

    public Transaction getTransactionByPortfolioByPosition(int portfolioId, int position) {
        return transactionManager.getByPortfolioByPosition(portfolioId, position);
    }

    public void updateTransaction(Transaction transaction) {
        transactionManager.update(transaction);
    }

    // constructor

    private BrokerManager brokerManager;
    private PreferencesManager preferencesManager;
    private PortfolioManager portfolioManager;
    private StockManager stockManager;
    private TransactionManager transactionManager;

    public DataManager(Context context) {
        brokerManager = new BrokerManager(context);
        portfolioManager = new PortfolioManager(context);
        preferencesManager = new PreferencesManager(context);
        stockManager = new StockManager(context);
        transactionManager = new TransactionManager(context);
    }
}
