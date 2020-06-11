package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import org.w3c.dom.ProcessingInstruction;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
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
        Toast.makeText(this, String.valueOf(total_buy), Toast.LENGTH_LONG).show();
        //tvTotalBuyValue.setText(convertValueToString(total_buy));


        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(HomeActivity.this,PortfolioActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navTransaction){
                    Intent intent = new Intent(HomeActivity.this,TransactionActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navProfile) {
                    Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navExit){
                    finish();
                    return true;
                }
                return false;
            }
        });
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
                // For Debug Only
                String temp = "Stock Name : ";
                temp += trx.getFkStockId();
                temp += " - Price : ";
                temp += trx.getPrice();
                temp += " - Lot : ";
                temp += trx.getLot();
                temp += " - Fee : 0.";
                temp += trx.getFee();
                temp += "%";
                Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
            }
            else{
                total_sell += countTransaction(trx.getPrice(),trx.getLot(),trx.getFee(),trx.getType());
            }
        }
    }

    private String convertValueToString(int value){
        String str = "";
        String result = "";
        while(value > 0){
            str += String.valueOf(value%10);
            value /= 10;
        }
        int length = str.length();
        Toast.makeText(this, String.valueOf(length), Toast.LENGTH_SHORT).show();
        int cnt = 0;
        for(int i=length-1;i>1;i--){
            if(length-1 == 1){
                result += "0";
            }
            else{
                cnt+=1;
                result += str.charAt(i);
                if(cnt == 3){
                    result += ".";
                    cnt = 0;
                }
            }
        }
        result += ",";
        for(int i=1;i>=0;i--){
            result += str.charAt(i);
        }
        return result;
    }
}
