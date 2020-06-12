package id.my.avmmartin.stocksportfolio.activity.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListViewHolder> {
    private StocksPortfolio mainApp;
    private int portfolioId;

    // overridden method

    @NonNull
    @Override
    public TransactionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.single_transaction_list, parent, false);

        return new TransactionListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionListViewHolder holder, int position) {
        holder.bindData(mainApp.getDataManager().getTransactionSummaryByPortfolioByPosition(portfolioId, position));
    }

    @Override
    public int getItemCount() {
        return mainApp.getDataManager().transactionSummarySizeByPortfolio(portfolioId);
    }

    // constructor

    public TransactionListAdapter(StocksPortfolio mainApp) {
        this.mainApp = mainApp;
    }

    // setter

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }
}
