package id.my.avmmartin.stocksportfolio.activity.components;

import android.widget.TextView;

import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class PortfolioSpinnerViewHolder {
    private TextView tvPortfolioName;
    private TextView tvPortfolioCreatedDate;

    private Portfolio portfolio;

    public void apply() {
        tvPortfolioName.setText(portfolio.getName());
        tvPortfolioCreatedDate.setText(CommonUtils.toDateFormat(portfolio.getCreatedDate()));
    }

    // constructor

    PortfolioSpinnerViewHolder(TextView tvPortfolioName, TextView tvPortfolioCreatedDate) {
        setTvPortfolioName(tvPortfolioName);
        setTvPortfolioCreatedDate(tvPortfolioCreatedDate);
    }

    // getter

    public Portfolio getPortfolio() {
        return portfolio;
    }

    // setter

    private void setTvPortfolioName(TextView tvPortfolioName) {
        this.tvPortfolioName = tvPortfolioName;
    }

    private void setTvPortfolioCreatedDate(TextView tvPortfolioCreatedDate) {
        this.tvPortfolioCreatedDate = tvPortfolioCreatedDate;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
