package com.example.dialogdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnQuit;
    Button btnPickDate;
    Button btnPickDateSpinnerDark;
    Button btnPickDateSpinnerLight;
    TextView tvDate;
    AlertDialog.Builder builder;
    DatePickerDialog.Builder dateBuilder;
    static final String TAG="DialogDemo";
    int day, month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getResources().getString(R.string.alerttDialogTitle));
                builder.setMessage(getResources().getString(R.string.alerttDialogMessage));
                builder.setCancelable(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"From AlertDialog MainActivity information");
                        Log.d(TAG,"From AlertDialog MainActivity information");
                        Log.e(TAG,"From AlertDialog MainActivity information");
                        MainActivity.this.finish();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog mydialog = builder.create();
                mydialog.show();
            }
        });

        btnPickDate = (Button) findViewById(R.id.btnPickADate);
        tvDate = (TextView) findViewById(R.id.tvDatePicked);
        btnPickDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                //tvDate.setText((month + 1) +"/" + day + "/" + year);

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, R.style.MyCalandarDatePickerStyle, new DatePickerDialog.OnDateSetListener() {
                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, R.style.MySpinnerDatePickerStyle, new DatePickerDialog.OnDateSetListener() {
                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;
                        tvDate.setText((month + 1) +"/" + day + "/" + year);
                    }
                }, year, month, day);
                myDateDialog.setTitle(getResources().getString(R.string.dateAlerttDialogTitle));
                myDateDialog.setMessage(getResources().getString(R.string.dateAlerttDialogMessage));
                myDateDialog.show();
            }
        });

        btnPickDateSpinnerDark = (Button) findViewById(R.id.btnPickADateSpinnerDark);
        tvDate = (TextView) findViewById(R.id.tvDatePicked);
        btnPickDateSpinnerDark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                //tvDate.setText((month + 1) +"/" + day + "/" + year);

                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, R.style.MySpinnerDatePickerStyle, new DatePickerDialog.OnDateSetListener() {
                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;
                        tvDate.setText((month + 1) +"/" + day + "/" + year);
                    }
                }, year, month, day);
                myDateDialog.setTitle(getResources().getString(R.string.dateAlerttDialogTitle));
                myDateDialog.setMessage(getResources().getString(R.string.dateAlerttDialogMessage));
                myDateDialog.show();
            }
        });

        btnPickDateSpinnerLight = (Button) findViewById(R.id.btnPickADateSpinnerLight);
        tvDate = (TextView) findViewById(R.id.tvDatePicked);
        btnPickDateSpinnerLight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                //tvDate.setText((month + 1) +"/" + day + "/" + year);

                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, R.style.MySpinnerDatePickerStyle, new DatePickerDialog.OnDateSetListener() {
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                //DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;
                        tvDate.setText((month + 1) +"/" + day + "/" + year);
                    }
                }, year, month, day);
                myDateDialog.setTitle(getResources().getString(R.string.dateAlerttDialogTitle));
                myDateDialog.setMessage(getResources().getString(R.string.dateAlerttDialogMessage));
                myDateDialog.show();
            }
        });

    }


}
