package id.my.avmmartin.stocksportfolio.activity.components;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import id.my.avmmartin.stocksportfolio.R;
import id.my.avmmartin.stocksportfolio.activity.HomeActivity;
import id.my.avmmartin.stocksportfolio.activity.ListPortfolioActivity;
import id.my.avmmartin.stocksportfolio.activity.MainActivity;
import id.my.avmmartin.stocksportfolio.activity.ProfileActivity;
import id.my.avmmartin.stocksportfolio.activity.TransactionActivity;

public class CustomBottomNavigation extends BaseLinearLayout{

    private Context context;
    private LinearLayout navHome;
    private LinearLayout navPortfolio;
    private LinearLayout navTransaction;
    private LinearLayout navProfile;
    private LinearLayout navExit;

    public void goToHome() {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void goToPortfolio() {
        Intent intent = new Intent(context, ListPortfolioActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void goToTransaction() {
        Intent intent = new Intent(context, TransactionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void goToProfile() {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void goToExit() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public CustomBottomNavigation(Context context) {
        super(context);
    }

    public CustomBottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void initComponents() {
        context = getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_bottom_navigation, this);

        navHome = view.findViewById(R.id.navHome);
        navPortfolio = view.findViewById(R.id.navPortfolio);
        navTransaction = view.findViewById(R.id.navTransaction);
        navProfile = view.findViewById(R.id.navProfile);
        navExit = view.findViewById(R.id.navExit);

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setEvents() {
        navHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });

        navPortfolio.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPortfolio();
            }
        });

        navTransaction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTransaction();
            }
        });

        navProfile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });

        navExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToExit();
            }
        });
    }
}
