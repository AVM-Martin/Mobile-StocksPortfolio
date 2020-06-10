package id.my.avmmartin.stocksportfolio.activity.components;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class TransactionListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTransactionItem, tvTransactionAvg, tvTransactionLast, tvTransactionLot;

    private Transaction transaction;

    void bindData(Transaction transaction) {
        this.transaction = transaction;
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
        tvTransactionItem.setText(transaction.getFkStockId());
        tvTransactionAvg.setText(String.valueOf(transaction.getPrice()));
        tvTransactionLast.setText("0");
        tvTransactionLot.setText(String.valueOf(transaction.getLot()));
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
