package id.my.avmmartin.stocksportfolio.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.List;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;

public class ProfileActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    EditText etPassword, etConfirmPassword;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        btnUpdate = findViewById(R.id.btnUpdate);


        LinearLayout navProfile = findViewById(R.id.navProfile);
        navProfile.setClickable(false);
        ImageView ivProfile = findViewById(R.id.ivProfile);
        TextView tvProfile = findViewById(R.id.tvProfile);
        ivProfile.setColorFilter(getColor(R.color.colorPrimaryDark));
        tvProfile.setTextColor(getColor(R.color.colorPrimaryDark));
    }

    private void loadData() {

    }

    private void setEvents() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtPassword = etPassword.getText().toString();
                String txtConfirmPassword = etConfirmPassword.getText().toString();

                if(txtPassword.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!txtConfirmPassword.equals(txtPassword)) {
                    Toast.makeText(ProfileActivity.this, "Password is not the same", Toast.LENGTH_SHORT).show();
                    return;
                }

                mainApp.getDataManager().setPassword(txtPassword);

                Toast.makeText(ProfileActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
