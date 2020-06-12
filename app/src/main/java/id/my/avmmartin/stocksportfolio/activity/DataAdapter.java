package id.my.avmmartin.stocksportfolio.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    Context ctx;
    ArrayList<Transaction> trx;

    public DataAdapter(Context ctx, ArrayList<Transaction> trx){
        this.ctx = ctx;
        this.trx = trx;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        Transaction transaction = trx.get(position);
        holder.tvTrxNumber.setText(transaction.getId()+1);
        holder.tvTrxDate.setText(transaction.getTransactionDate() + "");
        holder.tvStockName.setText(transaction.getFkStockId());
        holder.tvStockPrice.setText(transaction.getPrice());
        holder.tvStockLot.setText(transaction.getLot());
        if(transaction.getType() == Transaction.BUY)holder.tvTrxType.setText("B");
        else holder.tvTrxType.setText("S");
        int result = (transaction.getPrice()) * (transaction.getLot()) * 100;
        holder.tvTrxTotal.setText(result);
    }

    public int getItemCount(){
        return (trx == null) ? 0 : trx.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTrxNumber;
        TextView tvTrxDate;
        TextView tvStockName;
        TextView tvStockPrice;
        TextView tvStockLot;
        TextView tvTrxType;
        TextView tvTrxTotal;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvTrxNumber = (TextView) itemView.findViewById(R.id.tvTrxNumber);
            tvTrxDate = (TextView) itemView.findViewById(R.id.tvTrxDate);
            tvStockName = (TextView) itemView.findViewById(R.id.tvStockName);
            tvStockPrice = (TextView) itemView.findViewById(R.id.tvStockPrice);
            tvStockLot = (TextView) itemView.findViewById(R.id.tvStockLot);
            tvTrxType = (TextView) itemView.findViewById(R.id.tvTrxType);
            tvTrxTotal = (TextView) itemView.findViewById(R.id.tvTrxTotal);
        }


    }
}
