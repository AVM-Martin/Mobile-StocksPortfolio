package id.my.avmmartin.stocksportfolio.data.manager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import id.my.avmmartin.stocksportfolio.utils.Constants;
import id.my.avmmartin.stocksportfolio.utils.OnlineDataLoaderUtils;

public class StockPriceManager extends HashMap<String, Integer> {
    private static final String URL = Constants.JSON_URL_STOCK_PRICE;

    public void reloadOnlineData(final OnlineDataLoaderUtils loaderUtils, final String stockId) {
        loaderUtils.onPreExecute();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            String.format(URL, stockId),
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray prices = response
                            .getJSONObject("chart")
                            .getJSONArray("result")
                            .getJSONObject(0)
                            .getJSONObject("indicators")
                            .getJSONArray("quote")
                            .getJSONObject(0)
                            .getJSONArray("close");

                        for (int i = 0; i < prices.length(); i++) {
                            try {
                                put(stockId, prices.getInt(i));
                            } catch (Exception e) {
                                //
                            }
                        }

                    } catch (JSONException e) {
                        //
                    } finally {
                        loaderUtils.onSuccessExecute();
                    }

                    loaderUtils.onPostExecute();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    loaderUtils.onErrorExecute();
                }
            }
        );

        requestQueue.add(jsonObjectRequest);
    }

    public Integer getPrice(String stockId) {
        return get(stockId);
    }

    // constructor

    private RequestQueue requestQueue;

    public StockPriceManager(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;

        clear();
    }
}
