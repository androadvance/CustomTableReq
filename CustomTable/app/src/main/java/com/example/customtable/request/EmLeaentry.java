package com.example.customtable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EmLeaentry extends AppCompatActivity {

    private static final String NAMESPACE = "";
    EditText eT_name, eT_empno, eT_team, eT_unit, eT_fromdate, eT_todate, eT_appno;
    TextView username, t_appno;
    AutoCompleteTextView et_caretaker;
    ImageView emp_photo;
    Button show, viewdetail;
    DatePickerDialog datePickerDialog;
    public JSONArray jsonArray = null;
    String[] s_employeeno;
    private static ArrayAdapter<String> empno = null;
    ArrayList<String> empnolist = new ArrayList<String>();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_entry);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        t_appno = findViewById(R.id.appnoid);
        username = findViewById(R.id.usernameid);
        eT_name = findViewById(R.id.nameid);
        eT_empno = findViewById(R.id.empnoid);
        eT_team = findViewById(R.id.teamid);
        eT_unit = findViewById(R.id.unitid);
        eT_fromdate = findViewById(R.id.fromdateid);
        eT_todate = findViewById(R.id.todateid);
        emp_photo = findViewById(R.id.imageid);
        eT_appno = findViewById(R.id.appno);
        et_caretaker = findViewById(R.id.caretakerid);
        show = findViewById(R.id.showid);
        viewdetail = findViewById(R.id.viewdetail);

        getSupportActionBar().setTitle("");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdLeftApplication() {

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        (new getEmpDetail()).execute(SplashScreen.EmpCode);

        Bitmap bitmap = downloadBitmap("");
        emp_photo.setImageBitmap(bitmap);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        eT_fromdate.setText(currentDateandTime);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime1 = sdf1.format(new Date());
        eT_todate.setText(currentDateandTime1);

        viewdetail.setOnClickListener(v -> {

            Intent intent = new Intent(EmpLeaveEntry.this, LeaveReport.class);
            startActivity(intent);

        });

        eT_fromdate.setOnClickListener(view -> {

            final Calendar c1 = Calendar.getInstance();
            int mYear = c1.get(Calendar.YEAR);
            int mMonth = c1.get(Calendar.MONTH);
            int mDay = c1.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(EmpLeaveEntry.this,
                    (view12, year, monthOfYear, dayOfMonth) -> {

                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, monthOfYear, year);
                        long dtDob = chosenDate.toMillis(true);
                        strDate = DateFormat.format("yyyy-MM-dd", dtDob);
                        eT_fromdate.setText(strDate);
                        chosenDate.set(dayOfMonth, monthOfYear, year);
                    }, mYear, mMonth, mDay);

            datePickerDialog.show();

        });


        eT_todate.setOnClickListener(view -> {

            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(EmpLeaveEntry.this,
                    (view1, year, monthOfYear, dayOfMonth) -> {

                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, monthOfYear, year);
                        long dtDob = chosenDate.toMillis(true);
                        strDate = DateFormat.format("yyyy-MM-dd", dtDob);
                        eT_todate.setText(strDate);
                        chosenDate.set(dayOfMonth, monthOfYear, year);
                    }, mYear, mMonth, mDay);

            datePickerDialog.show();
        });


        show.setOnClickListener(v -> {

            String res = eT_name.getText().toString() + "-" + eT_empno.getText().toString();

            if (et_caretaker.getText().toString().equals(res)) {

                Toast.makeText(EmpLeaveEntry.this, "", Toast.LENGTH_SHORT).show();

            } else {

                if (et_caretaker.getText().toString().isEmpty()) {

                    Toast.makeText(EmLeaentry.this, "", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(ghvjh.this, j.class);
                    intent.putExtra("FromDate", eT_fromdate.getText().toString());
                    intent.putExtra("ToDate", eT_todate.getText().toString());
                    intent.putExtra("Empnum", eT_empno.getText().toString());
                    intent.putExtra("Team", eT_team.getText().toString());
                    intent.putExtra("Unit", eT_unit.getText().toString());
                    intent.putExtra("AppNo", eT_appno.getText().toString());
                    intent.putExtra("Name", eT_name.getText().toString());
                    intent.putExtra("CareTaker", et_caretaker.getText().toString());
                    intent.putExtra("Username", username.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            final int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Errore durante il download da " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }


    public class getEmpDetail extends AsyncTask<String, String, String> {

        SweetAlertDialog p;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new SweetAlertDialog(EmpLeaveEntry.this, SweetAlertDialog.PROGRESS_TYPE);
            p.setTitleText("Loading, Please Wait...");
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String[] paras = {""};
            String[] values = {};
            String methodname = "";
            String URL = "";
            return WebService.WebServiceCall(paras, values, methodname, NAMESPACE, URL);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            p.dismiss();
            try {
                jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonobj = new JSONObject(jsonArray.get(i).toString());

                    String Appno = jsonobj.getString("Appno");
                    String Name = jsonobj.getString("Name");
                    String Empno = jsonobj.getString("Empno");
                    String Team = jsonobj.getString("Team");
                    String Unit = jsonobj.getString("Unit");
                    String UserName = jsonobj.getString("Username");
                    eT_appno.setText(Appno);
                    eT_name.setText(Name);
                    eT_empno.setText(Empno);
                    eT_team.setText(Team);
                    eT_unit.setText(Unit);
                    username.setText(UserName);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            (new getCaretakerEmployeeNumber()).execute(eT_unit.getText().toString(), eT_team.getText().toString());

        }
    }

    public class getCaretakerEmployeeNumber extends AsyncTask<String, String, String> {

        SweetAlertDialog p;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new SweetAlertDialog(EmpLeaveEntry.this, SweetAlertDialog.PROGRESS_TYPE);
            p.setTitleText("Loading, Please Wait...");
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String[] paras = {""};
            String[] values = {params[0], params[1]};
            String methodname = "";
            String URL = "";
            return WebService.WebServiceCall(paras, values, methodname, NAMESPACE, URL);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            p.dismiss();

            if (result.equals("")) {

                Toast.makeText(EmpLeaveEntry.this, "Please give valid details", Toast.LENGTH_SHORT).show();

            } else {
                try {
                    empnolist.clear();
                    JSONArray jsonarray = new JSONArray(result);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobj = jsonarray.getJSONObject(i);

                        empnolist.add(jsonobj.getString("Empno"));
                    }
                    s_employeeno = empnolist.toArray(new String[empnolist.size()]);
                    empno = new ArrayAdapter<String>(getApplicationContext(), R.layout.autocomlist, R.id.autocomtext, empnolist);
                    et_caretaker.setThreshold(1);
                    et_caretaker.setAdapter(empno);

                } catch (Exception er) {
                    Toast.makeText(EmpLeaveEntry.this, "Waiting for Network", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_backmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.homeid:
                Intent intent = new Intent(EmpLeaveEntry.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.backid:
                Intent intent1 = new Intent(EmpLeaveEntry.this, HomeScreenActivity.class);
                startActivity(intent1);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

