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
import id.my.avmmartin.stocksportfolio.data.DataManager;
import id.my.avmmartin.stocksportfolio.data.PortfolioManager;
import id.my.avmmartin.stocksportfolio.data.model.Broker;
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

    private List<String> listBrokerID = new ArrayList<>();
    private BaseAdapter brokerAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listBrokerID.size();
        }

        @Override
        public Object getItem(int position) {
            return listBrokerID.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BrokerIDHolder holder;
            View brokerView = convertView;
            if(brokerView == null) {
                brokerView = getLayoutInflater().inflate(R.layout.single_broker_spinner, parent, false);

                holder = new BrokerIDHolder();
                holder.tvBrokerID = brokerView.findViewById(R.id.tvBrokerID);
                brokerView.setTag(holder);
            } else {
                holder = (BrokerIDHolder) brokerView.getTag();
            }

            String brokerID = listBrokerID.get(position);
            holder.tvBrokerID.setText(brokerID);
            return brokerView;
        }

        class BrokerIDHolder {
            private TextView tvBrokerID;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portfolio);

//        ImageButton imgBtnAdd = (ImageButton) findViewById(R.id.imgBtnAdd);
//        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AddPortfolio.this, "Add Transaction Button Pressed", Toast.LENGTH_SHORT).show();
//            }
//        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.navPortfolio);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    Intent intent = new Intent(AddPortfolio.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(AddPortfolio.this,PortfolioActivity.class);
                    startActivity(intent);
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
        showListBrokerID();
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

                Toast.makeText(AddPortfolio.this, txtBrokerID, Toast.LENGTH_LONG).show();
                String txtPortfolioName = etPortfolioName.getText().toString();
                Portfolio portfolio = new Portfolio(txtBrokerID,txtPortfolioName,calendar);
                //TODO: insert portfolio
            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: cancel portfolio

            }
        });

        spBrokerID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
//        Toast.makeText(AddPortfolio.this, String.valueOf(listBrokerID.get(1)), Toast.LENGTH_LONG).show();

        spBrokerID.setAdapter(brokerAdapter);

    }
}
