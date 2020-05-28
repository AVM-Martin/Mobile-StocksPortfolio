package id.my.avmmartin.stocksportofolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import id.my.avmmartin.stocksportofolio.R;

public class AddPortfolio extends AppCompatActivity {

    Spinner spBrokerID;
    EditText etPortfolioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portfolio);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initComponents();
        loadData();
        setEvents();
    }

    private void initComponents() {
        spBrokerID = findViewById(R.id.spBrokerID);
        etPortfolioID = findViewById(R.id.etPortfolioID);
    }

    private void loadData() {

    }

    private void setEvents() {

    }
}
