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

    public static int toIntFee(String fee) {
        return (int)(Float.valueOf(fee) * 100);
    }

    public static String toStringFee(int fee) {
        return String.valueOf(fee / 100.0);
    }

    public static String separator_comma(long nominal){
        if (nominal == 0) {
            return "0.00";
        }

        String temp = "";
        String res = "";
        boolean decimal = false;
        int cnt = 0;
        if(nominal < 0) {
            nominal *= -1;
            res += "-";
        }
        while(nominal > 0){
            temp += String.valueOf(nominal%10);
            nominal /= 10;
            cnt += 1;
            if(!decimal && cnt == 2){
                temp += '.';
                decimal = true;
                cnt = 0;
            }
            else if(cnt == 3 && nominal>0){
                temp += ',';
                cnt = 0;
            }
        }
        for(int i=temp.length()-1;i>=0;i--){
            res += temp.charAt(i);
        }
        return res;
    }

    public static String separator(int nominal){
        if (nominal == 0) {
            return "0";
        }

        String temp = "";
        String res = "";
        int cnt = 0;
        if(nominal < 0) {
            nominal *= -1;
            res += "-";
        }
        while(nominal > 0){
            temp += String.valueOf(nominal%10);
            nominal /= 10;
            cnt += 1;
            if(cnt == 3 && nominal>0){
                temp += ',';
                cnt = 0;
            }
        }
        for(int i=temp.length()-1;i>=0;i--){
            res += temp.charAt(i);
        }
        return res;
    }
}
