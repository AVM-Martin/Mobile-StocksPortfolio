package id.my.avmmartin.stocksportfolio.utils;

import android.app.DatePickerDialog;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import id.my.avmmartin.stocksportfolio.utils.Constants;

public class CommonUtils {

    public static String toDateFormat(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);
        return sdf.format(calendar.getTime());
    }
}
