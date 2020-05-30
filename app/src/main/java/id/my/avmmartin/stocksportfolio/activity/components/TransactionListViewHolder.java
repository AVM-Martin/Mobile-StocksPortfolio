package id.my.avmmartin.stocksportfolio.activity.components;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class TransactionListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTransactionDate;

    private Transaction transaction;

    public void bindData(Transaction transaction) {
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
        tvTransactionDate = itemView.findViewById(R.id.tvTransactionDate);
    }

    private void loadData() {
        tvTransactionDate.setText(CommonUtils.toDateFormat(transaction.getTransactionDate()));
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
