package id.my.avmmartin.stocksportofolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.my.avmmartin.stocksportofolio.R;
import id.my.avmmartin.stocksportofolio.StocksPortofolio;

public class HomeActivity extends AppCompatActivity {
    private StocksPortofolio mainApp;

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
        mainApp = (StocksPortofolio) getApplication();
    }

    private void loadData() {
        // none
    }

    private void setEvents() {
        // none
    }
}
