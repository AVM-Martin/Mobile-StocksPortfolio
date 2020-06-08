package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;

public class Register extends AppCompatActivity {
    private StocksPortfolio mainApp;
    EditText etPassword, etConfirmPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void loadData() {

    }

    private void setEvents() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtPassword = etPassword.getText().toString();
                String txtConfirmPassword = etConfirmPassword.getText().toString();

                if(txtPassword.isEmpty()) {
                    Toast.makeText(Register.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!txtConfirmPassword.equals(txtPassword)) {
                    Toast.makeText(Register.this, "Password is not the same", Toast.LENGTH_SHORT).show();
                    return;
                }

                mainApp.getDataManager().setPassword(txtPassword);
                finish();
            }
        });
    }

}
