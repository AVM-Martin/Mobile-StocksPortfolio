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
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class AddPortfolio extends AppCompatActivity {

    Spinner spBrokerID;
    EditText etBuyFee, etSellFee;
    EditText etPortfolioID;
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
        spBrokerID = findViewById(R.id.spBrokerID);
        etBuyFee = findViewById(R.id.etBuyFee);
        etSellFee = findViewById(R.id.etSellFee);
        etPortfolioID = findViewById(R.id.etPortfolioID);
        tvCreatedDate = findViewById(R.id.tvCreatedDate);
        ivAdd = findViewById(R.id.ivAdd);
        ivCancel = findViewById(R.id.ivCancel);
    }

    private void loadData() {

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
