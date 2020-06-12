package id.my.avmmartin.stocksportfolio.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private StocksPortfolio mainApp;
    Context ctx;

    public DataAdapter(Context ctx, StocksPortfolio mainApp) {
        this.mainApp = mainApp;
        this.ctx = ctx;
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
        holder.tvStockPrice.setText(separator(transaction.getPrice()));
        holder.tvStockLot.setText(separator(transaction.getLot()));
        if(transaction.getType() == Transaction.BUY)holder.tvTrxType.setText("B");
        else holder.tvTrxType.setText("S");
        long result = (long)transaction.getTotal() * 10000;
        holder.tvTrxTotal.setText(separator_comma(result));
    }

    public int getItemCount(){
        return mainApp.getDataManager().transactionSize();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
//            tvTrxNumber.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    Toast.makeText(ctx,String.valueOf(position),Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ctx,EditTransaction.class);
//                    intent.putExtra("position",position);
//                    ctx.startActivity(intent);
//                }
//            });
            tvTrxDate = (TextView) itemView.findViewById(R.id.tvTrxDate);
            tvStockName = (TextView) itemView.findViewById(R.id.tvStockName);
            tvStockPrice = (TextView) itemView.findViewById(R.id.tvStockPrice);
            tvStockLot = (TextView) itemView.findViewById(R.id.tvStockLot);
            tvTrxType = (TextView) itemView.findViewById(R.id.tvTrxType);
            tvTrxTotal = (TextView) itemView.findViewById(R.id.tvTrxTotal);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(ctx,String.valueOf(position),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx,EditTransaction.class);
            intent.putExtra("id",position);
            ctx.startActivity(intent);
        }

    }

    public String separator_comma(long nominal){
        String temp = "";
        String res = "";
        boolean decimal = false;
        int cnt = 0;
        while(nominal > 0){
            temp += String.valueOf(nominal%10);
            nominal /= 10;
            cnt += 1;
            if(!decimal && cnt == 2){
                temp += ',';
                decimal = true;
                cnt = 0;
            }
            else if(cnt == 3 && nominal>0){
                temp += '.';
                cnt = 0;
            }
        }
        for(int i=temp.length()-1;i>=0;i--){
            res += temp.charAt(i);
        }
        return res;
    }

    public String separator(int nominal){
        String temp = "";
        String res = "";
        int cnt = 0;
        if(nominal < 0)nominal *= -1;
        while(nominal > 0){
            temp += String.valueOf(nominal%10);
            nominal /= 10;
            cnt += 1;
            if(cnt == 3 && nominal>0){
                temp += '.';
                cnt = 0;
            }
        }
        for(int i=temp.length()-1;i>=0;i--){
            res += temp.charAt(i);
        }
        return res;
    }
}
