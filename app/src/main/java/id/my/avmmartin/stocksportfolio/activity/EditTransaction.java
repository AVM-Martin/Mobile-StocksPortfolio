package id.my.avmmartin.stocksportfolio.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Portfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class EditTransaction extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private StocksPortfolio mainApp;
    List<String> listStockID = new ArrayList<>();
    List<String> listPortfolioName = new ArrayList<>();
    ArrayAdapter<String> stockAdapter;
    ArrayAdapter<String> portfolioAdapter;
    TextView tvCompanyNameValue;
    String item = "";
    //Spinner spPortfolioName = findViewById(R.id.spPortfolioName);

    AutoCompleteTextView atvStockID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);
        if(mainApp.getDataManager().portfolioSize() == 0){
            Toast.makeText(this, "No Portfolio Available. Please Register First", Toast.LENGTH_LONG).show();
        }
        //EditText etPortfolioName = findViewById(R.id.etPortfolioName);
        //etPortfolioName.setText(mainApp.getDataManager().portfolioSize());


        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare Property
                EditText etPrice = findViewById(R.id.etPrice);
                EditText etShares = findViewById(R.id.etShares);
                RadioGroup rgTransaction = findViewById(R.id.rgTransaction);
                int selectedRB = rgTransaction.getCheckedRadioButtonId();
                RadioButton rbTransaction = findViewById(selectedRB);


                // Validation Form Process
                if(item.contentEquals("")){
                    atvStockID.setError("Please Fill the Stock Name");
                    atvStockID.requestFocus();
                }
                else if(etPrice.getText().toString().length() == 0){
                    etPrice.setError("Please Fill the Stock Price");
                    etPrice.requestFocus();
                }
                else if(etPrice.getText().toString().length() > 0 && Integer.valueOf(etPrice.getText().toString()) == 0){
                    etPrice.setError("Please Fill the Valid Stock Price");
                    etPrice.requestFocus();
                }
                else if(etShares.getText().toString().length() == 0){
                    etShares.setError("Please Fill the Stock Shares");
                    etShares.requestFocus();
                }
                else if(etShares.getText().toString().length() > 0 && Integer.valueOf(etShares.getText().toString()) == 0){
                    etShares.setError("Please Fill the Valid Stock Shares");
                    etShares.requestFocus();
                }
                else{
                    int transactionType = Transaction.TYPE_BUY;
                    if(rbTransaction.getText().equals("Sell")){
                        transactionType = Transaction.TYPE_SELL;
                    }
                    //int fkPortfolioId = ;
                    String fkStockId = item;
                    int type = transactionType;
                    //Calendar transactionDate = ;
                    //int price = Integer.valueOf(etPrice.getText().toString());
                    //int lot = Integer.valueOf(etShares.getText().toString());;
                    //int fee = ;
                    //Transaction trx = new Transaction(fkPortfolioId, fkStockId, type, transactionDate, price, lot, fee);
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Bottom Nav View
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.navTransaction);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    Intent intent = new Intent(EditTransaction.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(EditTransaction.this,PortfolioActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.navTransaction){
                    Intent intent = new Intent(EditTransaction.this,TransactionActivity.class);
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
        atvStockID = findViewById(R.id.atvStockID);
        tvCompanyNameValue = findViewById(R.id.tvCompanyNameValue);
    }

    private void loadData() {
        showListStockID();
    }

    private void setEvents() {

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

    private void showListStockID() {
        listStockID.clear();
        int stockSize = mainApp.getDataManager().stockSize();
        for(int i=0; i<stockSize; i++) {
            listStockID.add(mainApp.getDataManager().getStockByPosition(i).getId());
        }

        stockAdapter = new ArrayAdapter<>(EditTransaction.this, android.R.layout.simple_list_item_1, listStockID);
        atvStockID.setAdapter(stockAdapter);
        atvStockID.setOnItemClickListener(this);
    }

//    private void showListPortfolio() {
//        listPortfolioName.clear();
//        int portfolioSize = mainApp.getDataManager().portfolioSize();
//        for(int i=0; i<portfolioSize; i++) {
//            listPortfolioName.add(mainApp.getDataManager().getPortfolioByPosition(i).getName());
//        }
//        portfolioAdapter = new ArrayAdapter<>(AddTransaction.this, android.R.layout.simple_list_item_1, listPortfolioName);
//        spPortfolioName.setAdapter(portfolioAdapter);
//        spPortfolioName.setOnItemClickListener(this);
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        int idx = listStockID.indexOf(item);
        String companyName = mainApp.getDataManager().getStockByPosition(idx).getName();
        tvCompanyNameValue.setText(companyName);
    }
}
