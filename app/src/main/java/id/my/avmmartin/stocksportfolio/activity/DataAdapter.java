package id.my.avmmartin.stocksportfolio.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private StocksPortfolio mainApp;

    public DataAdapter(StocksPortfolio mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        Transaction transaction = mainApp.getDataManager().getTransactionByPosition(position);

        holder.tvTrxNumber.setText(Integer.toString(transaction.getId()));
        holder.tvTrxDate.setText(CommonUtils.toDateFormat(transaction.getTransactionDate()));
        holder.tvStockName.setText(transaction.getFkStockId());
        holder.tvStockPrice.setText(Integer.toString(transaction.getPrice()));
        holder.tvStockLot.setText(Integer.toString(transaction.getLot()));
        if(transaction.getType() == Transaction.BUY)holder.tvTrxType.setText("B");
        else holder.tvTrxType.setText("S");
        int result = (transaction.getPrice()) * (transaction.getLot()) * 100;
        holder.tvTrxTotal.setText(Integer.toString(result));
    }

    public int getItemCount(){
        return mainApp.getDataManager().transactionSize();
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
