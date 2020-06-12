package id.my.avmmartin.stocksportfolio.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.activity.components.PortfolioSpinnerAdapter;
import id.my.avmmartin.stocksportfolio.activity.components.TransactionListAdapter;
import id.my.avmmartin.stocksportfolio.utils.OnlineDataLoaderUtils;

public class ListPortfolioActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    private Spinner spPortfolio;
    private RecyclerView rvTransaction;
    private ImageButton imgBtnAdd;

    private PortfolioSpinnerAdapter portfolioSpinnerAdapter;
    private TransactionListAdapter transactionListAdapter;

    private int portfolioId;

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

        loadOnlineData();
        portfolioSpinnerAdapter.notifyDataSetChanged();
        transactionListAdapter.notifyDataSetChanged();
    }

    private void initComponents() {
        mainApp = (StocksPortfolio) getApplication();

        spPortfolio = findViewById(R.id.spPortfolio);
        rvTransaction = findViewById(R.id.rvTransactions);
        imgBtnAdd = findViewById(R.id.imgBtnAdd);

        portfolioSpinnerAdapter = new PortfolioSpinnerAdapter(mainApp, this);
        transactionListAdapter = new TransactionListAdapter(mainApp);


        LinearLayout navPortfolio = findViewById(R.id.navPortfolio);
        navPortfolio.setClickable(false);
        ImageView ivPortfolio = findViewById(R.id.ivPortfolio);
        TextView tvPortfolio = findViewById(R.id.tvPortfolio);
        ivPortfolio.setColorFilter(getColor(R.color.colorPrimaryDark));
        tvPortfolio.setTextColor(getColor(R.color.colorPrimaryDark));
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
                portfolioId = mainApp.getDataManager().getPortfolioByPosition(position).getId();
                transactionListAdapter.setPortfolioId(portfolioId);
                loadOnlineData();
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

    }

    private void loadOnlineData() {
        OnlineDataLoaderUtils loaderUtils = new OnlineDataLoaderUtils(this) {
            @Override
            public void onPostExecute() {
                super.onPostExecute();
                transactionListAdapter.notifyDataSetChanged();
            }
        };

        int size = mainApp.getDataManager().transactionSummarySizeByPortfolio(portfolioId);
        for (int i=0; i<size; i++) {
            mainApp.getDataManager().reloadOnlineStockPrice(
                loaderUtils,
                mainApp
                    .getDataManager()
                    .getTransactionSummaryByPortfolioByPosition(portfolioId, i)
                    .getFkStockId()
            );
        }
    }

}
