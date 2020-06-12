package id.my.avmmartin.stocksportfolio.activity.components;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.data.model.TransactionSummary;

public class TransactionListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTransactionItem, tvTransactionAvg, tvTransactionLast, tvTransactionLot;

    private TransactionSummary transactionSummary;

    void bindData(TransactionSummary transactionSummary) {
        this.transactionSummary = transactionSummary;
        loadData();
    }

    // constructor

    TransactionListViewHolder(@NonNull View itemView) {
        super(itemView);

        initComponents();
        setEvents();
    }

    private void initComponents() {
        tvTransactionItem = itemView.findViewById(R.id.tvTransactionItem);
        tvTransactionAvg = itemView.findViewById(R.id.tvTransactionAvg);
        tvTransactionLast = itemView.findViewById(R.id.tvTransactionLast);
        tvTransactionLot = itemView.findViewById(R.id.tvTransactionLot);
    }

    private void loadData() {
        tvTransactionItem.setText(transactionSummary.getFkStockId());
        tvTransactionAvg.setText(String.format("%.2lf", transactionSummary.getAvgPrice()));
        tvTransactionLast.setText("0");
        tvTransactionLot.setText(String.valueOf(transactionSummary.getLot()));
    }

    private void setEvents() {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: TBD
            }
        });
    }
}
