package id.my.avmmartin.stocksportfolio.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.activity.components.PortfolioSpinnerAdapter;
import id.my.avmmartin.stocksportfolio.activity.components.TransactionListAdapter;

public class ListPortfolioActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    private Spinner spPortfolio;
    private RecyclerView rvTransaction;
    private ImageButton imgBtnAdd;

    private PortfolioSpinnerAdapter portfolioSpinnerAdapter;
    private TransactionListAdapter transactionListAdapter;

    private com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_portofolio);
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

        portfolioSpinnerAdapter.notifyDataSetChanged();
        transactionListAdapter.notifyDataSetChanged();
    }

    private void initComponents() {
        mainApp = (StocksPortfolio) getApplication();

        spPortfolio = findViewById(R.id.spPortfolio);
        rvTransaction = findViewById(R.id.rvTransactions);
        imgBtnAdd = findViewById(R.id.imgBtnAdd);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        portfolioSpinnerAdapter = new PortfolioSpinnerAdapter(mainApp, this);
        transactionListAdapter = new TransactionListAdapter(mainApp, this);
    }

    private void loadData() {
        spPortfolio.setAdapter(portfolioSpinnerAdapter);

        rvTransaction.setLayoutManager(new LinearLayoutManager(this));
        rvTransaction.setAdapter(transactionListAdapter);
    }

    private void setEvents() {
        spPortfolio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transactionListAdapter.setPortfolioId(
                    mainApp.getDataManager().getPortfolioByPosition(position).getId()
                );
                transactionListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // none
            }
        });

        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListPortfolioActivity.this, AddPortfolio.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    Intent intent = new Intent(ListPortfolioActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(ListPortfolioActivity.this,ListPortfolioActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navTransaction){
                    Intent intent = new Intent(ListPortfolioActivity.this,TransactionActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navProfile) {
                    Intent intent = new Intent(ListPortfolioActivity.this,ProfileActivity.class);
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
}
