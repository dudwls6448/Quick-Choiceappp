package com.example.leejaewon.quickchoice;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by LeeJaeWon on 2017-06-05.
 */

public class judgment extends AppCompatActivity{
    private float point;
    private EditText memo;
    private RatingBar ratingBar;
    private TextView start;
    private TextView desti;
    private TextView money;
    private TextView time;
    private TextView distance;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_judgment);

        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView)findViewById(R.id.editText4);
        TextView textView2 = (TextView)findViewById(R.id.editText9);
        TextView textView3 = (TextView)findViewById(R.id.editText8);
        TextView textView4 = (TextView)findViewById(R.id.editText7);
        TextView textView5 = (TextView)findViewById(R.id.editText6);
        TextView textView6 = (TextView)findViewById(R.id.judgment_memo);
        TextView textView7 = (TextView)findViewById(R.id.judgment_ok);
        TextView textView8 = (TextView)findViewById(R.id.judgment_time);
        TextView textView9 = (TextView)findViewById(R.id.judgment_money);
        TextView textView10 = (TextView)findViewById(R.id.textView9);
        TextView textView11 = (TextView)findViewById(R.id.judgment_start);
        TextView textView12 = (TextView)findViewById(R.id.judgment_distance);
        TextView textView13 = (TextView)findViewById(R.id.judgment_desti);
        TextView textView14 = (TextView)findViewById(R.id.textView20);

        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface1);
        textView4.setTypeface(typeface1);
        textView5.setTypeface(typeface1);
        textView6.setTypeface(typeface1);
        textView7.setTypeface(typeface1);
        textView8.setTypeface(typeface1);
        textView9.setTypeface(typeface1);
        textView10.setTypeface(typeface1);
        textView11.setTypeface(typeface1);
        textView12.setTypeface(typeface1);
        textView13.setTypeface(typeface1);
        textView14.setTypeface(typeface1);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        memo = (EditText) findViewById(R.id.judgment_memo);
        start = (TextView) findViewById(R.id.judgment_start);
        desti = (TextView) findViewById(R.id.judgment_desti);
        money = (TextView) findViewById(R.id.judgment_money);
        time = (TextView) findViewById(R.id.judgment_time);
        distance = (TextView) findViewById(R.id.judgment_distance);

        Button ok = (Button) findViewById(R.id.judgment_ok);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                point = rating;
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    CustomTask customTask = new CustomTask();
                    customTask.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });



    }




    private class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/join.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&id="+strings[0]+"&pw="+strings[1]+"&name="+strings[2]+"&phon="+strings[3];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }



    }


}
