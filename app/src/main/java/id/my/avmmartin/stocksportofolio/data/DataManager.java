package id.my.avmmartin.stocksportofolio.data;

import android.content.Context;

import id.my.avmmartin.stocksportofolio.data.model.Broker;

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
