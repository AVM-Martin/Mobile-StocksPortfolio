package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;

import id.my.avmmartin.stocksportfolio.data.model.Broker;

public class DataManager {
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

    // constructor

    private BrokerManager brokerManager;

    public DataManager(Context context) {
        brokerManager = new BrokerManager(context);
    }
}
