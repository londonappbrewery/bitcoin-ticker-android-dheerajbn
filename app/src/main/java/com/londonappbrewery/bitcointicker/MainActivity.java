package com.londonappbrewery.bitcointicker;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.londonappbrewery.bitcointicker.databinding.ActivityMainBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    // X-ba-key: Njc0MTA3N2QwNjZkNDI2ZDk3NGFiZDE5N2YzZjg2YzQ

    // Member Variables:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        mActivityMainBinding.currencySpinner.setAdapter(adapter);

        // TODO: Set an OnItemSelected listener on the spinner

        mActivityMainBinding.currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View veiw, int position, long id) {
                Log.d("Bitcoin: ", "" + mActivityMainBinding.currencySpinner.getItemAtPosition(position));
                letsDoSomeNetworking(BASE_URL + mActivityMainBinding.currencySpinner.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Log.d("Bitcoin:", "Nothing Selected");
            }

        });
    }

    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Bitcoin", "Response: " + response.toString());
                mActivityMainBinding.priceLabel.setText(Model.fromJson(response).getCurrency());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.d("Bitcoin",  "Status code: " + statusCode + "Failed with error " + e.toString());
            }
        });

    }

}
