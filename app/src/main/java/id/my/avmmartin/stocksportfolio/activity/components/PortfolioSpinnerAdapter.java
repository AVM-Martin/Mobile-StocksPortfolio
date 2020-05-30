package id.my.avmmartin.stocksportfolio.activity.components;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;

public class PortfolioSpinnerAdapter extends BaseAdapter {
    private StocksPortfolio mainApp;
    private Activity activity;

    // overridden method

    @Override
    public int getCount() {
        return mainApp.getDataManager().portfolioSize();
    }

    @Override
    public Portfolio getItem(int position) {
        return mainApp.getDataManager().getPortfolioByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return mainApp.getDataManager().getPortfolioByPosition(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View brokerView = convertView;
        if(brokerView == null) {
            brokerView = activity.getLayoutInflater().inflate(
                R.layout.single_broker_spinner,
                parent,
                false
            );


            PortfolioSpinnerViewHolder holder = new PortfolioSpinnerViewHolder(
                (TextView) brokerView.findViewById(R.id.tvPortfolioName),
                (TextView) brokerView.findViewById(R.id.tvPortfolioCreatedDate)
            );
            brokerView.setTag(holder);
        }

        PortfolioSpinnerViewHolder holder = (PortfolioSpinnerViewHolder) brokerView.getTag();
        holder.setPortfolio(getItem(position));
        holder.apply();

        return brokerView;
    }

    // constructor

    public PortfolioSpinnerAdapter(StocksPortfolio mainApp, Activity activity) {
        this.mainApp = mainApp;
        this.activity = activity;
    }
}
