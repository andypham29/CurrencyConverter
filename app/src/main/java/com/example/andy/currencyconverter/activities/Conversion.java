package com.example.andy.currencyconverter.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.currencyconverter.R;
import com.example.andy.currencyconverter.dataaccess.fixerQuoteRepository;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Conversion extends AppCompatActivity implements View.OnClickListener{

    fixerQuoteRepository quote = new fixerQuoteRepository();

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    //default to CAD
    boolean CAD_selected = true;
    int input_num;
    float converted_num;

    public static float VND_dataParsed;
    public static float CAD_dataParsed;
    public static float USD_dataParsed;
    float rate;

    /*
     json var displays the data, temp
     */
    public static TextView json;
    TextView CAD_textbox;
    TextView VND_textbox;

    CharSequence CAD_content;
    CharSequence VND_content;

    Button CAD_btn;
    Button VND_btn;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnClear;
    Button btnUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        json = findViewById(R.id.json);

        CAD_textbox = findViewById(R.id.CAD_textbox);
        VND_textbox = findViewById(R.id.VND_textbox);

        CAD_btn = findViewById(R.id.CAD_btn);
        VND_btn = findViewById(R.id.VND_btn);

        CAD_content = CAD_textbox.getText();
        VND_content = VND_textbox.getText();

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnClear = findViewById(R.id.btnClear);
        btnUpdate = findViewById(R.id.btnUpdate);

        btn0.setOnClickListener(Conversion.this);
        btn1.setOnClickListener(Conversion.this);
        btn2.setOnClickListener(Conversion.this);
        btn3.setOnClickListener(Conversion.this);
        btn4.setOnClickListener(Conversion.this);
        btn5.setOnClickListener(Conversion.this);
        btn6.setOnClickListener(Conversion.this);
        btn7.setOnClickListener(Conversion.this);
        btn8.setOnClickListener(Conversion.this);
        btn9.setOnClickListener(Conversion.this);
        btnClear.setOnClickListener(Conversion.this);
        btnUpdate.setOnClickListener(this);
        CAD_btn.setOnClickListener(Conversion.this);
        VND_btn.setOnClickListener(Conversion.this);


    }


    public void setTextBox(int num){

        if(CAD_selected){
            rate = VND_dataParsed/CAD_dataParsed;
            input_num = input_num*10 + num;
            converted_num = input_num * rate;
            df2.setRoundingMode(RoundingMode.UP);
            CAD_textbox.setText(String.valueOf(input_num));
            VND_textbox.setText(String.valueOf(df2.format(converted_num)));

            json.setText(String.valueOf(rate));
        }
        else{
            rate = CAD_dataParsed/VND_dataParsed;
            input_num = input_num*10 + num;
            converted_num = input_num *rate;
            df2.setRoundingMode(RoundingMode.UP);
            CAD_textbox.setText(String.valueOf(df2.format(converted_num)));
            VND_textbox.setText(String.valueOf(df2.format(input_num)));

            json.setText(String.valueOf(rate));
        }

    }
    public  void clearField(){
        CAD_textbox.setText("");
        VND_textbox.setText("");
        input_num = 0;
        converted_num = 0;
    }

    public void saveRate(){
        SharedPreferences sharedPreferences = getSharedPreferences("fixerData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("CAD_dataParsed", String.valueOf(CAD_dataParsed));
        editor.putString("VND_dataParsed", String.valueOf(VND_dataParsed));

        editor.apply();
    }
    public void onClick(View v) {
        if(v == CAD_btn){
            Toast.makeText(getApplicationContext(), "CAD Selected", Toast.LENGTH_SHORT).show();
            clearField();
            CAD_selected = true;
        }
        if(v == VND_btn){
            Toast.makeText(getApplicationContext(), "VND Selected", Toast.LENGTH_SHORT).show();
            clearField();
            CAD_selected = false;
        }
        if(v == btn0){
            setTextBox(0);
        }
        if(v == btn1){
            setTextBox(1);
        }
        if(v == btn2){
            setTextBox(2);
        }
        if(v == btn3){
            setTextBox(3);
        }
        if(v == btn4){
            setTextBox(4);
        }
        if(v == btn5){
            setTextBox(5);
        }
        if(v == btn6){
            setTextBox(6);
        }
        if(v == btn7){
            setTextBox(7);
        }
        if(v == btn8){
            setTextBox(8);
        }
        if(v == btn9){
            setTextBox(9);
        }
        if(v == btnClear){
            clearField();
        }
        if( v == btnUpdate){
            quote.execute();
            saveRate();
        }

    }



}
