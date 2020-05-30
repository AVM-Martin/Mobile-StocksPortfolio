package id.my.avmmartin.stocksportfolio.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String FILENAME = "StockPortfolioSharedPrefs";
    private static final String USER_HASH_PASSWORD = "userHashPassword";

    private SharedPreferences sharedPreferences;

    // username

    String getHashPassword() {
        return sharedPreferences.getString(USER_HASH_PASSWORD, null);
    }

    void setHashPassword(String password) {
        sharedPreferences.edit().putString(USER_HASH_PASSWORD, password).apply();
    }

    // constructor

    PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }
}
