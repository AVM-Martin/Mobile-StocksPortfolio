package id.my.avmmartin.stocksportfolio.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.data.model.Transaction;
import id.my.avmmartin.stocksportfolio.utils.CommonUtils;

public class AddTransaction extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private StocksPortfolio mainApp;
    List<String> listStockID = new ArrayList<>();
    List<String> listPortfolioName = new ArrayList<>();
    ArrayAdapter<String> stockAdapter;
    ArrayAdapter<String> portfolioAdapter;
    TextView tvCompanyNameValue;
    TextView tvTransactionDateValue;
    AutoCompleteTextView atvStockID;
    String item = "";
    int stockIndex;
    Spinner spPortfolioName;
    Calendar calendar = Calendar.getInstance();
    Button btnSubmit;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        mainApp = (StocksPortfolio) getApplication();

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
        tvTransactionDateValue = findViewById(R.id.tvTransactionDateValue);
        atvStockID = findViewById(R.id.atvStockID);
        tvCompanyNameValue = findViewById(R.id.tvCompanyNameValue);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        spPortfolioName = (Spinner) findViewById(R.id.spPortfolioName);

        ImageView ivTransaction = findViewById(R.id.ivTransaction);
        TextView tvTransaction = findViewById(R.id.tvTransaction);
        ivTransaction.setColorFilter(getColor(R.color.colorPrimaryDark));
        tvTransaction.setTextColor(getColor(R.color.colorPrimaryDark));
    }

    private void loadData() {
        showListStockID();
        showListPortfolio();
    }

    private void setEvents() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTransactionDateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(calendar,tvTransactionDateValue);
            }
        });

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
                    int portfolioPosition = listPortfolioName.indexOf(spPortfolioName.getSelectedItem().toString());
                    int fkPortfolioId = mainApp.getDataManager().getPortfolioByPosition(portfolioPosition).getId();
                    String fkStockId = item;
                    Calendar transactionDate = calendar;
                    int price = Integer.valueOf(etPrice.getText().toString());
                    int lot = Integer.valueOf(etShares.getText().toString());
                    int transactionType = Transaction.BUY;
                    int fee = mainApp.getDataManager().getPortfolioByPosition(portfolioPosition).getBuyFee();
                    if(rbTransaction.getText().equals("Sell")){
                        transactionType = Transaction.SELL;
                        fee = mainApp.getDataManager().getPortfolioByPosition(portfolioPosition).getSellFee();
                    }
                    fee *= lot * price;
                    fee /= 100;
                    int type = transactionType;
                    Transaction trx = new Transaction(fkPortfolioId, fkStockId, type, transactionDate, price, lot, fee);
                    mainApp.getDataManager().insertTransaction(trx);
                    Toast.makeText(AddTransaction.this, "Successfully Added New Transaction", Toast.LENGTH_SHORT).show();
                    finish();
                }

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

    private void showListStockID() {
        listStockID.clear();
        int stockSize = mainApp.getDataManager().stockSize();
        for(int i=0; i<stockSize; i++) {
            listStockID.add(mainApp.getDataManager().getStockByPosition(i).getId());
        }

        stockAdapter = new ArrayAdapter<>(AddTransaction.this, android.R.layout.simple_list_item_1, listStockID);
        atvStockID.setAdapter(stockAdapter);
        atvStockID.setOnItemClickListener(this);
    }

    private void showListPortfolio() {
        listPortfolioName.clear();
        int portfolioSize = mainApp.getDataManager().portfolioSize();
        for(int i=0; i<portfolioSize; i++) {
            listPortfolioName.add(mainApp.getDataManager().getPortfolioByPosition(i).getName());
        }
        portfolioAdapter = new ArrayAdapter<>(AddTransaction.this, android.R.layout.simple_spinner_item, listPortfolioName);
        portfolioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPortfolioName.setAdapter(portfolioAdapter);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        stockIndex = listStockID.indexOf(item);
        String companyName = mainApp.getDataManager().getStockByPosition(stockIndex).getName();
        tvCompanyNameValue.setText(companyName);
    }
}
