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

        // Toggle visibility of DatePicker Style options on LongClick
        Button button = (Button) findViewById(R.id.btnPickADateSpinner);
        final TextView tvPickStyle = (TextView)  findViewById(R.id.tvPickStyle);
        final Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        dropdown.setVisibility(View.GONE);
        tvPickStyle.setVisibility(View.GONE);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (tvPickStyle.getVisibility() == View.GONE)
                    tvPickStyle.setVisibility(View.VISIBLE);
                else
                    tvPickStyle.setVisibility(View.GONE);

                if (dropdown.getVisibility() == View.GONE)
                    dropdown.setVisibility(View.VISIBLE);
                else
                    dropdown.setVisibility(View.GONE);

                return true;
            }
        });

        // Setup Spinner to pick DatePicker options

        String[] items = new String[]{
                "Default App Style",
                "My Calendar Style",
                "My Spinner Style",
                "Holo Spinner Dark",
                "Holo Spinner Light",
                "DatePickerDialog.Builder Calendar",
                "DatePickerDialog.Builder Spinner"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    /*
     * Display Alert Dialog created by AlertDialog.Builder
     */
    public void showAlertDialog(View v) {
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

    /*
     * Display DisplayPicker Dialog in various styles
     */
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
            case 5:
                Button btnBuilderCalendar = (Button) findViewById(R.id.btnPickADateBuilderCalendar);
                showDatePickerDialogBuilderCalendar(btnBuilderCalendar);
                break;
            case 6:
                Button btnBuilderSpinner = (Button) findViewById(R.id.btnPickADateBuilderSpinner);
                showDatePickerDialogBuilderSpinner(btnBuilderSpinner);
                break;
            default:
                myDateDialog = new DatePickerDialog(this, MainActivity.this, year, month, day);
                break;
        }
        if (dropdown.getSelectedItemPosition() < 5) {
            myDateDialog.setTitle(getResources().getString(R.string.dateAlertDialogTitle));
            myDateDialog.setMessage(getResources().getString(R.string.dateAlertDialogMessage));
            myDateDialog.show();
        }
    }

    /*
     * Date Picker Listener - sets the tvDate text to selected date
     */
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        year = i;
        month = i1;
        day = i2;
        tvDate.setText("Selected date is " + (month + 1) +"/" + day + "/" + year);
    }

    /*
     * Date Picker Dialog created by DatePickerDialog.Builder using my R.layout.date_picker_calendar
     */
    public void showDatePickerDialogBuilderCalendar (View v) {
        tvDate = (TextView) findViewById(R.id.tvDatePicked);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dpview = inflater.inflate(R.layout.date_picker_calendar, null, false);
        //View dpview = inflater.inflate(R.layout.date_picker_spinner, null, false);

        showDatePickerDialogBuilder(dpview);
    }

    /*
     * Date Picker Dialog created by DatePickerDialog.Builder using my R.layout.date_picker_spinner
     */
    public void showDatePickerDialogBuilderSpinner (View v) {
        tvDate = (TextView) findViewById(R.id.tvDatePicked);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View dpview = inflater.inflate(R.layout.date_picker_calendar, null, false);
        View dpview = inflater.inflate(R.layout.date_picker_spinner, null, false);

        showDatePickerDialogBuilder(dpview);
    }

    /*
     * Display Date Picker Dialog using DatePickerDialog.Builder from passed in View dpview
     */
    public void showDatePickerDialogBuilder(View dpview) {
        final DatePicker datePicker = dpview.findViewById(R.id.myDatePicker);

        DatePickerDialog.Builder dateBuilder = new DatePickerDialog.Builder(MainActivity.this);
        dateBuilder.setView(datePicker);
        dateBuilder.setTitle(getResources().getString(R.string.dateAlertDialogTitle));
        dateBuilder.setMessage(R.string.dateAlertDialogMessage);
        dateBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                month = datePicker.getMonth();
                day = datePicker.getDayOfMonth();
                year = datePicker.getYear();
                tvDate.setText((month + 1) + "/" + day + "/" + year);
                dialog.cancel();
            }
        });
        dateBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        AlertDialog myDateDialog = dateBuilder.create();
        myDateDialog.show();
    }

}
