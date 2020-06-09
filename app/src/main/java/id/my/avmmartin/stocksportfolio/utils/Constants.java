package id.my.avmmartin.stocksportfolio.utils;

public class Constants {
    public static final String PACKAGE_NAME = "id.my.avmmartin.stocksportfolio";

    public static final String DATE_FORMAT ="dd/MM/yyyy";

    public static final String JSON_URL_STOCK_PRICE =
        "https://query1.finance.yahoo.com/v8/finance/chart/%s.JK?range=1d&interval=1h&includePrePost=false&indicators=close";

    // constructor

    private Constants() {
        // none
    }
}
