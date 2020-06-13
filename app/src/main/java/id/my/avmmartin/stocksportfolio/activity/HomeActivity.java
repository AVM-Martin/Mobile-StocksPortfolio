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
import id.my.avmmartin.stocksportfolio.data.model.TransactionSummary;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;
import id.my.avmmartin.stocksportfolio.utils.OnlineDataLoaderUtils;

public class HomeActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    private TextView tvTotalBuyValue;
    private TextView tvTotalSellValue;
    private TextView tvTotalProfitValue;

    private int totalBuyValue;
    private int totalSellValue;
    private int totalCurrentValue;

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
        tvTotalSellValue = findViewById(R.id.tvTotalSellValue);
        tvTotalProfitValue = findViewById(R.id.tvTotalProfitValue);

        LinearLayout navHome = findViewById(R.id.navHome);
        navHome.setClickable(false);
        ImageView ivHome = findViewById(R.id.ivHome);
        TextView tvHome = findViewById(R.id.tvHome);
        ivHome.setColorFilter(getColor(R.color.colorPrimaryDark));
        tvHome.setTextColor(getColor(R.color.colorPrimaryDark));
    }

    private void loadData() {
        totalBuyValue = mainApp.getDataManager().getTransactionTotalByType(Transaction.BUY);
        totalSellValue = mainApp.getDataManager().getTransactionTotalByType(Transaction.SELL);
        totalCurrentValue = 0;

        tvTotalBuyValue.setText(CommonUtils.separator_comma(totalBuyValue));
        tvTotalSellValue.setText(CommonUtils.separator_comma(totalSellValue));
        tvTotalProfitValue.setText(CommonUtils.separator_comma(totalCurrentValue));

        int size = mainApp.getDataManager().transactionSummarySize();
        for (int i=0; i<size; i++) {
            TransactionSummary summary = mainApp
                .getDataManager()
                .getTransactionSummaryByPosition(i);

            totalCurrentValue += summary.getLot() * summary.getAvgPrice();
            tvTotalProfitValue.setText(CommonUtils.separator_comma(totalCurrentValue));
        }
    }

    private void setEvents() {
        // none
    }
}
