package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;

public class HomeActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;
    int total_buy = 0;
    int total_sell = 0;
    TextView tvTotalBuyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initComponents();
        loadData();
        setEvents();
    }

    private void initComponents() {
        mainApp = (StocksPortfolio) getApplication();
        tvTotalBuyValue = findViewById(R.id.tvTotalBuyValue);

        LinearLayout navHome = findViewById(R.id.navHome);
        navHome.setClickable(false);
        ImageView ivHome = findViewById(R.id.ivHome);
        TextView tvHome = findViewById(R.id.tvHome);
        ivHome.setColorFilter(getColor(R.color.colorPrimaryDark));
        tvHome.setTextColor(getColor(R.color.colorPrimaryDark));
    }

    private void loadData() {
        getTotalTransaction();
    }

    private void setEvents() {
        // none
    }

    private int countTransaction(int stockPrice, int stockLot, int transactionFee, int transactionType){
        int total = 0;
        if(transactionType == Transaction.BUY){
            total = stockPrice * stockLot * (10000 );
        }
        else{
            total = stockPrice * stockLot * (10000);
        }
        return total;
    }

    private void getTotalTransaction() {
        int transactionSize = mainApp.getDataManager().transactionSize();
        //Toast.makeText(this, String.valueOf(transactionSize), Toast.LENGTH_SHORT).show();
        for(int i=0; i<transactionSize; i++) {
            Transaction trx = mainApp.getDataManager().getTransactionByPosition(i);
            if(trx.getType() == Transaction.BUY){
                total_buy += countTransaction(trx.getPrice(),trx.getLot(),trx.getFee(),trx.getType());
            }
            else{
                total_sell += countTransaction(trx.getPrice(),trx.getLot(),trx.getFee(),trx.getType());
            }
        }
    }
}
