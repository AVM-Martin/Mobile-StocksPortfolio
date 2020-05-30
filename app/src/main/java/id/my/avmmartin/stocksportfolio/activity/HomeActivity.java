package id.my.avmmartin.stocksportfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.StocksPortfolio;

public class HomeActivity extends AppCompatActivity {
    private StocksPortfolio mainApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navHome){
                    Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.navPortfolio){
                    Intent intent = new Intent(HomeActivity.this,AddPortfolio.class);
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
    }

    private void loadData() {
        // none
    }

    private void setEvents() {
        // none
    }
}
