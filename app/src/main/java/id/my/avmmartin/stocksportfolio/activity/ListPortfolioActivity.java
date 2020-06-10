package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Spinner;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.activity.components.PortfolioSpinnerAdapter;
import id.my.avmmartin.stocksportfolio.activity.components.TransactionListAdapter;

public class ListPortfolioActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    private Spinner spPortfolio;
    private RecyclerView rvTransaction;

    private PortfolioSpinnerAdapter portfolioSpinnerAdapter;
    private TransactionListAdapter transactionListAdapter;

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

        portfolioSpinnerAdapter = new PortfolioSpinnerAdapter(mainApp, this);
        transactionListAdapter = new TransactionListAdapter(mainApp, this);
    }

    private void loadData() {
        spPortfolio.setAdapter(portfolioSpinnerAdapter);

        rvTransaction.setLayoutManager(new LinearLayoutManager(this));
        rvTransaction.setAdapter(transactionListAdapter);
    }

    private void setEvents() {
        // none
    }
}
