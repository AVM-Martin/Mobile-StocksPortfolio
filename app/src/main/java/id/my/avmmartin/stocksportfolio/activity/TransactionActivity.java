package id.my.avmmartin.stocksportfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;

public class TransactionActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;
    public static RecyclerView rvTransactions;
    public static DataAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        mainApp = (StocksPortfolio) getApplication();
        ImageButton imgBtnAddTransaction = findViewById(R.id.imgBtnAddTransaction);
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


        // Transaction Table
        rvTransactions = findViewById(R.id.rvTransactions);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTransactions.setLayoutManager(linearLayoutManager);
        adapter = new DataAdapter(this,mainApp);
        rvTransactions.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        initComponents();
        loadData();
        setEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void initComponents() {
        mainApp = (StocksPortfolio) getApplication();

        //imgBtnDeleteTransaction = findViewById(R.id.imgBtnDeleteTransaction);

        LinearLayout navTransaction = findViewById(R.id.navTransaction);
        navTransaction.setClickable(false);
        ImageView ivTransaction = findViewById(R.id.ivTransaction);
        TextView tvTransaction = findViewById(R.id.tvTransaction);
        ivTransaction.setColorFilter(getColor(R.color.colorPrimaryDark));
        tvTransaction.setTextColor(getColor(R.color.colorPrimaryDark));
    }

    private void loadData() {
        //
    }

    private void setEvents() {
        // none
    }
}
