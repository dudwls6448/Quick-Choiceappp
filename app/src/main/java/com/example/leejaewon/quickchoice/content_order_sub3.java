package com.example.leejaewon.quickchoice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by LeeJaeWon on 2017-05-01.
 */

public class content_order_sub3 extends Fragment {

    String pickup;
    String memo;
    int fast;

    static final int AA=0;
    Button btnSelectDate,btnSelectTime;
    EditText dt_date;
    EditText dt_time;
    EditText text_memo;

    CheckBox check_fast;


    public int year,month,day,hour,minute;
    public int myear,mmonth,mday,mhour,mminute;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View view=inflater.inflate(R.layout.content_order_sub3,container,false);

        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView) view.findViewById(R.id.textView3);
        TextView textView2 = (TextView) view.findViewById(R.id.textView4);
        TextView textView3 = (TextView) view.findViewById(R.id.textView6);
        TextView textView4 = (TextView) view.findViewById(R.id.textView27);
        TextView textView5 = (TextView) view.findViewById(R.id.order_pickuptime);
        TextView textView6 = (TextView) view.findViewById(R.id.order_pickupdate);
        TextView textView7 = (TextView) view.findViewById(R.id.textView7);
        TextView textView8 = (TextView) view.findViewById(R.id.button4);
        TextView textView9 = (TextView) view.findViewById(R.id.textView8);
        TextView textView10 = (TextView) view.findViewById(R.id.order_memo);
        TextView textView11 = (TextView) view.findViewById(R.id.order_fast);
        TextView textView12 = (TextView) view.findViewById(R.id.select_date);
        TextView textView13 = (TextView) view.findViewById(R.id.select_time);

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

        text_memo=(EditText) view.findViewById(R.id.order_memo);

        check_fast=(CheckBox) view.findViewById(R.id.order_fast);

        dt_date=(EditText) view.findViewById(R.id.order_pickupdate);
        dt_time=(EditText) view.findViewById(R.id.order_pickuptime);

        btnSelectDate=(Button) view.findViewById(R.id.select_date) ;
        btnSelectTime =(Button) view.findViewById(R.id.select_time) ;
        btnSelectDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onCreateDialog(0);
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onCreateDialog(1);
            }
        });

        return view;
    }

    @Override
    public void onPause(){
        super.onPause();

        if(check_fast.isChecked()){
            fast=1;
        }else{
            fast=0;
        }
        ((main)getActivity()).fast=this.fast;
        ((main)getActivity()).pickup = dt_date.getText().toString()+dt_time.getText().toString();
        ((main)getActivity()).memo = text_memo.getText().toString();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int yearSelected, int monthOfYeaar, int dayOfMonth){
            year=yearSelected;
            month=monthOfYeaar;
            day=dayOfMonth;
            Toast.makeText(getActivity(),"날짜 : "+year+month+day,Toast.LENGTH_LONG).show();
            String date =  String.valueOf(year)+String.valueOf(month)+String.valueOf(day);

            dt_date.setText(date);
        }

    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view, int hourOfDay, int min){
            hour=hourOfDay;
            minute=min;
            Toast.makeText(getActivity(),"시간 : "+hour+minute,Toast.LENGTH_LONG).show();
            String time = String.valueOf(hour)+String.valueOf(minute);
            dt_time.setText(time);
        }

    };


    protected Dialog onCreateDialog (int id){
        Calendar c = Calendar.getInstance();
        switch (id){
            case 0:

                myear = c.get(Calendar.YEAR);
                mmonth = c.get(Calendar.MONTH);
                mday = c.get(Calendar.DAY_OF_MONTH);
                 new DatePickerDialog(getActivity(),mDateSetListener,myear,month,mday).show();

                break;

            case 1:
                mhour = c.get(Calendar.HOUR_OF_DAY);
                mminute = c.get(Calendar.MINUTE);
                 new TimePickerDialog(getActivity(),mTimeSetListener,mhour,mminute,false).show();
                break;

        }
        return null;
    }

}
