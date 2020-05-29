package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.DataManager;
import id.my.avmmartin.stocksportfolio.data.PortfolioManager;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class AddPortfolio extends AppCompatActivity {
    private StocksPortfolio mainApp;

    Spinner spBrokerID;
    EditText etBuyFee, etSellFee;
    EditText etPortfolioName;
    TextView tvCreatedDate;
    ImageButton ivAdd, ivCancel;

    Calendar calendar;

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
        mainApp = (StocksPortfolio) getApplication();

        spBrokerID = findViewById(R.id.spBrokerID);
        etBuyFee = findViewById(R.id.etBuyFee);
        etSellFee = findViewById(R.id.etSellFee);
        etPortfolioName = findViewById(R.id.etPortfolioName);
        tvCreatedDate = findViewById(R.id.tvCreatedDate);
        ivAdd = findViewById(R.id.ivAdd);
        ivCancel = findViewById(R.id.ivCancel);
    }

    private void loadData() {
        //TODO: load brokerID and buy sell fee
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
                String txtBrokerID = spBrokerID.getSelectedItem().toString();
                String txtPortfolioName = etPortfolioName.getText().toString();
                Portfolio portfolio = new Portfolio(-1,txtBrokerID,txtPortfolioName,calendar);
                //TODO: insert portfolio
            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: cancel portfolio

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
}
