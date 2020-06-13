package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.data.model.TransactionSummary;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class HomeActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    private TextView tvTotalBuyValue;
    private TextView tvTotalSellValue;
    private TextView tvTotalProfitValue;

    private long totalBuyValue;
    private long totalSellValue;
    private long totalCurrentValue;

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
        int size = mainApp.getDataManager().transactionSummarySize();
        for (int i=0; i<size; i++) {
            TransactionSummary summary = mainApp
                .getDataManager()
                .getTransactionSummaryByPosition(i);

            totalCurrentValue += (long) (summary.getLot() * summary.getAvgPrice());
        }

        tvTotalBuyValue.setText(CommonUtils.separator_comma(totalBuyValue * 10000));
        tvTotalSellValue.setText(CommonUtils.separator_comma(totalSellValue * 10000));
        tvTotalProfitValue.setText(CommonUtils.separator_comma(
            (totalSellValue - totalBuyValue + totalCurrentValue) * 10000
        ));
    }

    private void setEvents() {
        // none
    }
}
