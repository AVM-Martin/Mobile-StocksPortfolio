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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.List;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;

public class TransactionActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;
    public static RecyclerView rvTransactions;
    public static DataAdapter adapter;
    public static ArrayList<Transaction> trxList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        mainApp = (StocksPortfolio) getApplication();
        ImageButton imgBtnAddTransaction = findViewById(R.id.imgBtnAddTransaction);
        ImageButton imgBtnEditTransaction = findViewById(R.id.imgBtnEditTransaction);
        //ImageButton imgBtnDeleteTransaction;
        imgBtnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainApp.getDataManager().portfolioSize() == 0){
                    Toast.makeText(TransactionActivity.this, "Please Register Portfolio First", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(TransactionActivity.this, AddTransaction.class);
                    startActivity(intent);
                }

            }
        });
        imgBtnEditTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainApp.getDataManager().portfolioSize() == 0){
                    Toast.makeText(TransactionActivity.this, "Please Register Portfolio First", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(TransactionActivity.this, EditTransaction.class);
                    startActivity(intent);
                }
            }
        });


        // Transaction Table
        rvTransactions = findViewById(R.id.rvTransactions);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTransactions.setLayoutManager(linearLayoutManager);
        adapter = new DataAdapter(this,trxList);
        rvTransactions.setAdapter(adapter);


        // Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.navTransaction);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    Intent intent = new Intent(TransactionActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(TransactionActivity.this,ListPortfolioActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navTransaction){
                    return true;
                }
                else if(item.getItemId() == R.id.navProfile) {
                    Intent intent = new Intent(TransactionActivity.this, ProfileActivity.class);
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

        //imgBtnDeleteTransaction = findViewById(R.id.imgBtnDeleteTransaction);
    }

    private void loadData() {
        showListPortfolio();
    }

    private void setEvents() {
        // none
    }

    private void showListPortfolio() {
        trxList = new ArrayList<Transaction>();
        int trxSize = mainApp.getDataManager().transactionSize();
        Toast.makeText(this, String.valueOf(trxSize), Toast.LENGTH_SHORT).show();
        for(int i=0; i<trxSize; i++) {
            trxList.add(mainApp.getDataManager().getTransactionByPosition(i));
        }
    }
}
