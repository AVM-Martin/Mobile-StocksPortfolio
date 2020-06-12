package id.my.avmmartin.stocksportfolio.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.DatabaseManager;
import id.my.avmmartin.stocksportfolio.data.model.Broker;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class AddPortfolio extends AppCompatActivity {
    private StocksPortfolio mainApp;

    AutoCompleteTextView atvBrokerID;
    EditText etBuyFee, etSellFee;
    EditText etPortfolioName;
    TextView tvCreatedDate;
    ImageButton ivAdd, ivCancel;

    Calendar calendar = Calendar.getInstance();
    List<String> listBrokerID = new ArrayList<>();
    ArrayAdapter<String> brokerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portfolio);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.navPortfolio);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    Intent intent = new Intent(AddPortfolio.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(AddPortfolio.this,ListPortfolioActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navTransaction){
                    Intent intent = new Intent(AddPortfolio.this,TransactionActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.navProfile) {
                    Intent intent = new Intent(AddPortfolio.this,ProfileActivity.class);
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

        atvBrokerID = findViewById(R.id.atvBrokerID);
        etBuyFee = findViewById(R.id.etBuyFee);
        etSellFee = findViewById(R.id.etSellFee);
        etPortfolioName = findViewById(R.id.etPortfolioName);
        tvCreatedDate = findViewById(R.id.tvCreatedDate);
        ivAdd = findViewById(R.id.ivAdd);
        ivCancel = findViewById(R.id.ivCancel);
    }

    private void loadData() {
        showListBrokerID();

        tvCreatedDate.setText(CommonUtils.toDateFormat(calendar));
    }

    private void setEvents() {
        tvCreatedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(calendar, tvCreatedDate);
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtBrokerID = atvBrokerID.getText().toString();
                String txtPortfolioName = etPortfolioName.getText().toString();
                String txtBuyFee = etBuyFee.getText().toString();
                String txtSellFee = etSellFee.getText().toString();

                if(txtBrokerID.isEmpty()) {
                    Toast.makeText(AddPortfolio.this, "BrokerID must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(txtBuyFee.isEmpty()) {
                    Toast.makeText(AddPortfolio.this, "Buy Fee must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(txtSellFee.isEmpty()) {
                    Toast.makeText(AddPortfolio.this, "Sell Fee must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(txtPortfolioName.isEmpty()) {
                    Toast.makeText(AddPortfolio.this, "Portfolio Name must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                Portfolio portfolio = new Portfolio(
                    txtBrokerID,
                    txtPortfolioName,
                    calendar,
                    CommonUtils.toIntFee(txtBuyFee),
                    CommonUtils.toIntFee(txtSellFee)
                );


                mainApp.getDataManager().insertPortfolio(portfolio);

                Intent intent = new Intent(AddPortfolio.this, ListPortfolioActivity.class);
                startActivity(intent);

                Toast.makeText(AddPortfolio.this, "Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }


    private void showDatePickerDialog(final Calendar calendar, final TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    textView.setText(CommonUtils.toDateFormat(calendar));
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void showListBrokerID() {
        listBrokerID.clear();
        int brokerSize = mainApp.getDataManager().brokerSize();

        for(int i=0; i<brokerSize; i++) {
            listBrokerID.add(mainApp.getDataManager().getBrokerByPosition(i).getId());
        }

        brokerAdapter = new ArrayAdapter<>(AddPortfolio.this, android.R.layout.simple_list_item_1, listBrokerID);
        atvBrokerID.setAdapter(brokerAdapter);
    }
}
