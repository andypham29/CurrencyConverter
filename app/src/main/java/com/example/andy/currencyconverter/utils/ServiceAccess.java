package com.example.andy.currencyconverter.utils;

//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceAccess {

    public static JSONObject getData(String real_url) throws IOException, JSONException {

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


        return new JSONObject(data);
    }

}
