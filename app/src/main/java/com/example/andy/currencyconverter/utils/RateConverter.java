package com.example.andy.currencyconverter.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RateConverter {

    private static DecimalFormat df2 = new DecimalFormat("#.##");



    public String getConvertedRate(long rate_in, long rate_out, long input_num){
        long input_rate = rate_out/rate_in;
        long converted_num = input_num * input_rate;

        df2.setRoundingMode(RoundingMode.UP);

        return df2.format(converted_num);
    }
}
