package id.my.avmmartin.stocksportfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;

public class TransactionActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ImageButton imgBtnAddTransaction = findViewById(R.id.imgBtnAddTransaction);
        ImageButton imgBtnEditTransaction = findViewById(R.id.imgBtnEditTransaction);
        //ImageButton imgBtnDeleteTransaction;
        imgBtnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionActivity.this, AddTransaction.class);
                startActivity(intent);
            }
        });
        imgBtnEditTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionActivity.this, EditTransaction.class);
                startActivity(intent);
            }
        });

        // Bottom Navigation View
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.navTransaction);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    Intent intent = new Intent(TransactionActivity.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(TransactionActivity.this,PortfolioActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.navTransaction){
                    Intent intent = new Intent(TransactionActivity.this,TransactionActivity.class);
                    startActivity(intent);
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

        //imgBtnDeleteTransaction = findViewById(R.id.imgBtnDeleteTransaction);
    }

    private void loadData() {
        // none
    }

    private void setEvents() {
        // none
    }
}
