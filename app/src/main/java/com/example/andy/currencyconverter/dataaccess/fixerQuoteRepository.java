package com.example.andy.currencyconverter.dataaccess;

import android.os.AsyncTask;
import com.example.andy.currencyconverter.activities.Conversion;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Andy on 2018-07-19.
 */

public class fixerQuoteRepository extends AsyncTask<Void, Void, Void>{

    /*
        Currency data provided by fixer.io via its API
     */

    private static final String BASE_URL = "http://data.fixer.io/api/latest?access_key=";
    private static final String access_key = "f57c335831c9335c2ecafce41c1969a0";

    String real_url = BASE_URL + access_key;


    float VND_dataParsed;
    float CAD_dataParsed;

    //async task still running in background
    boolean FlagCancelled = false;

    @Override
    protected Void doInBackground(Void... voids) {
        try {

//            while (FlagCancelled) {
                URL url = new URL(real_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(15000);

                String data = "";
                String line = "";

                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                inputStream.close();
                bufferedReader.close();


                JSONObject jsonObject = new JSONObject(data);

                VND_dataParsed = Float.valueOf(jsonObject.getJSONObject("rates").getString("VND"));
                CAD_dataParsed = Float.valueOf(jsonObject.getJSONObject("rates").getString("CAD"));
//            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Conversion.VND_dataParsed = this.VND_dataParsed;
        Conversion.CAD_dataParsed = this.CAD_dataParsed;
        Conversion.json.setText(    "VND: " + VND_dataParsed + " | CAD: " + CAD_dataParsed);

//        onCancelled();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
//        FlagCancelled = true;
    }
}
