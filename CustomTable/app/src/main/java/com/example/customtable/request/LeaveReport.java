package com.example.customtable;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class LeaveReport extends AppCompatActivity {

    EditText eT_Empno,eT_Team,eT_Unit,eT_FromDate,eT_ToDate;
    Button btn_Search;
    DatePickerDialog datePickerDialog;
    String s_Empno,s_team,s_Unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_report);

        eT_Empno = findViewById(R.id.empid);
        eT_Team = findViewById(R.id.teamid);
        eT_Unit = findViewById(R.id.unitid);
        eT_FromDate = findViewById(R.id.datefromid);
        eT_ToDate = findViewById(R.id.todateid);
        btn_Search = findViewById(R.id.viewdetailsid);

        s_Empno = SplashScreen.EmpCode;
        eT_Empno.setText(s_Empno);
        s_team = SplashScreen.Team;
        eT_Team.setText(s_team);
        s_Unit = SplashScreen.WorkingUnit;
        eT_Unit.setText(s_Unit);

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LeaveReport.this,LeaveReport_DetailView.class);
                intent.putExtra("fromdate", eT_FromDate.getText().toString());
                intent.putExtra("todate", eT_ToDate.getText().toString());
                startActivity(intent);

            }
        });

        //before date
        final Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_MONTH, -7);
        c1.add(Calendar.MONTH, 0);
        c1.add(Calendar.YEAR, 0);

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        final String date = sdf1.format(c1.getTime());
        eT_FromDate.setText(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        eT_ToDate.setText(currentDateandTime);

        eT_FromDate.setOnClickListener(view -> {

            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            datePickerDialog = new DatePickerDialog(LeaveReport.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            eT_FromDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            datePickerDialog.show();
        });


        eT_ToDate.setOnClickListener(view -> {

            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(LeaveReport.this,
                    (view1, year, monthOfYear, dayOfMonth) -> eT_ToDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });
    }
}