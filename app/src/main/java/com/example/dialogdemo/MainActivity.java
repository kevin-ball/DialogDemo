package com.example.dialogdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

/**
 *  Extended Dialog Demo - show various Styles of DatePickerDialog
 */
public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button btnQuit;

    TextView tvDate;
    AlertDialog.Builder builder;
    static final String TAG="DialogDemo";
    int day, month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDate = (TextView) findViewById(R.id.tvDatePicked);
        /*
         * Alert Dialog created by AlertDialog.Builder
         */
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getResources().getString(R.string.alertDialogTitle));
                builder.setMessage(getResources().getString(R.string.alertDialogMessage));
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

        // Setup Spinner to pick DatePicker options
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{
                "Default App Style",
                "My Calendar Style",
                "My Spinner Style",
                "Holo Spinner Dark",
                "Holo Spinner Light"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void showDatePickerDialogSpinner(View v) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog myDateDialog = null;
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        switch (dropdown.getSelectedItemPosition()) {
            case 0:  // Date Picker Dialog created with App Default Style
                myDateDialog = new DatePickerDialog(this, MainActivity.this, year, month, day);
                break;
            case 1: // Date Picker Dialog created with my R.style.MyCalendarDatePickerStyle
                myDateDialog = new DatePickerDialog(this, R.style.MyCalendarDatePickerStyle,MainActivity.this, year, month, day);
                break;
            case 2: // Date Picker Dialog created with my R.style.MySpinnerDatePickerStyle
                myDateDialog = new DatePickerDialog(this, R.style.MySpinnerDatePickerStyle,MainActivity.this, year, month, day);
                break;
            case 3: // Date Picker Dialog created with Android's android.R.style.Theme_Holo_Dialog
                myDateDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog,MainActivity.this, year, month, day);
                break;
            case 4: // Date Picker Dialog created with Android's android.R.style.Theme_Holo_Light_Dialog
                myDateDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,MainActivity.this, year, month, day);
                break;
            default:
                myDateDialog = new DatePickerDialog(this, MainActivity.this, year, month, day);
                break;
        }

        myDateDialog.setTitle(getResources().getString(R.string.dateAlertDialogTitle));
        myDateDialog.setMessage(getResources().getString(R.string.dateAlertDialogMessage));
        myDateDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        year = i;
        month = i1;
        day = i2;
        tvDate.setText("Selected date is " + (month + 1) +"/" + day + "/" + year);
    }
}
