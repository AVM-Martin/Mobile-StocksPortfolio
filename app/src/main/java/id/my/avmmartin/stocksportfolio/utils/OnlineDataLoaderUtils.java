package id.my.avmmartin.stocksportfolio.utils;

import android.app.ProgressDialog;
import android.content.Context;

import id.my.avmmartin.stocksportfolio.R;

public class OnlineDataLoaderUtils {
    private ProgressDialog progressDialog;

    // to-be-overridden method

    public void onPostExecute() {
        //
    }

    // default method

    public void onPreExecute() {
        progressDialog.show();
    }

    public void onSuccessExecute() {
        progressDialog.dismiss();
    }

    public void onErrorExecute() {
        progressDialog.dismiss();
    }

    // constructor

    public OnlineDataLoaderUtils(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.load_data));
    }
}
