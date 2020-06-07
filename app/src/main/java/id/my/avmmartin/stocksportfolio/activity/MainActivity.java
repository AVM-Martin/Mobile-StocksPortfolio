package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;
import id.my.avmmartin.stocksportfolio.activity.dialog.LoginDialog;
import id.my.avmmartin.stocksportfolio.data.PreferencesManager;
import id.my.avmmartin.stocksportfolio.exception.GeneralException;

public class MainActivity extends AppCompatActivity implements LoginDialog.Listener {
    private StocksPortfolio mainApp;

    TextView tvStockPortfolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        tvStockPortfolio = findViewById(R.id.tvStockPortfolio);
    }

    private void loadData() {

    }

    private void setEvents() {
        tvStockPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainApp.getDataManager().isRegistered()) {
                    //login
                    onClickLogin();
                }
                else {
                    //register
                    Intent intent = new Intent(MainActivity.this, Register.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void onClickLogin() {
        LoginDialog dialog = new LoginDialog();
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void clickLogin(LoginDialog dialog) {
        String txtPassword = dialog.getEtPassword().getText().toString();
        try {
            if(mainApp.getDataManager().checkPassword(txtPassword)) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        } catch(GeneralException e) {

        }
    }
}
