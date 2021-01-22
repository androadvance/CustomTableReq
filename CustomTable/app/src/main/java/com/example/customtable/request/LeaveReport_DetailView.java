package com.example.customtable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LeaveReport_DetailView extends AppCompatActivity {

    private static final String NAMESPACE = "";
    public JSONArray jsonArray = null;
    String s_fromdate,s_todate;
    public ListView lv_listView;
    List<LeaveReportList> listitems;
    LeaveReportAdapter leaveReportAdapter;
    public int listposition;
    String isapproved,IsRejected,LeaveRequestDate,Dept,Designation,Status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_report__detail_view);

        lv_listView = findViewById(R.id.listview);

        Intent intent = getIntent();
        s_fromdate = intent.getStringExtra("fromdate");
        s_todate = intent.getStringExtra("todate");

        (new getLeaveRequestDetail()).execute(SplashScreen.EmpCode,s_fromdate,s_todate);



        lv_listView.setOnItemClickListener((parent, view, position, id) -> {

            listposition = position;

            JSONObject jsonobj;

            try {
                jsonobj = new JSONObject(jsonArray.get(listposition).toString());

                LeaveRequestDate = jsonobj.getString("LeaveRequestDate");
                isapproved = jsonobj.getString("IsApproved");
                IsRejected = jsonobj.getString("IsRejected");
                Status = jsonobj.getString("Status");

                if (Status.equalsIgnoreCase("cancel")){

                    Toast.makeText(this, "Leave Already Cancelled", Toast.LENGTH_SHORT).show();

                } else {

                    if (isapproved.equalsIgnoreCase("false") && IsRejected.equalsIgnoreCase("false")) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(LeaveReport_DetailView.this);
                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.leave_cancel, null);
                        builder.setCancelable(true);
                        builder.setView(dialogView);
                        final AlertDialog dialog = builder.create();
                        dialog.show();

                        final EditText empno = (EditText) dialogView.findViewById(R.id.empnumid);
                        empno.setText(SplashScreen.EmpCode);
                        final TextView date = (TextView) dialogView.findViewById(R.id.date);
                        date.setText(LeaveRequestDate);
                        final RadioButton status = (RadioButton) dialogView.findViewById(R.id.radiobuttonid);
                        final Button cancel = (Button) dialogView.findViewById(R.id.showid);

                        cancel.setOnClickListener(v -> {

                            if (!status.isChecked()) {

                                Toast.makeText(LeaveReport_DetailView.this, "Please select status to cancel Leave", Toast.LENGTH_SHORT).show();

                            } else {

                                if (Status.equalsIgnoreCase("open")){

                                    String[] s = {SplashScreen.AppNo,SplashScreen.EmpCode,LeaveRequestDate,SplashScreen.Username,SplashScreen.deviceid,SplashScreen.EmpName,SplashScreen.Team,Dept,Designation,SplashScreen.WorkingUnit};
                                    (new U_LeaveStatus()).execute(s);

                                } else {

                                    Toast.makeText(LeaveReport_DetailView.this, "Selected Entry is Approved, You can't update Status..", Toast.LENGTH_SHORT).show();
                                }
                            }
                            dialog.dismiss();
                        });

                    } else {

                        Toast.makeText(LeaveReport_DetailView.this, "Selected Entry is Approved, You can't update Status..", Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public class U_LeaveStatus extends AsyncTask<String,String,String>{

        SweetAlertDialog p;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new SweetAlertDialog(LeaveReport_DetailView.this, SweetAlertDialog.PROGRESS_TYPE);
            p.setTitleText("Loading, Please Wait...");
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected String doInBackground(String... params) {
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

            if (result.equalsIgnoreCase("false")){

                Toast.makeText(LeaveReport_DetailView.this, result, Toast.LENGTH_SHORT).show();

            } else {

                Intent intent = new Intent(LeaveReport_DetailView.this, HomeScreenActivity.class);
                Toast.makeText(LeaveReport_DetailView.this, "Leave Requition cancelled Successfully..", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

            }
        }
    }

    public class getLeaveRequestDetail extends AsyncTask<String,String,String> {

        SweetAlertDialog p;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new SweetAlertDialog(LeaveReport_DetailView.this, SweetAlertDialog.PROGRESS_TYPE);
            p.setTitleText("Loading, Please Wait...");
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String[] paras = {"", "fromdate", "todate"};
            String[] values = {params[0], params[1], params[2]};
            String methodname = "";
            String URL = "";
            return WebService.WebServiceCall(paras, values, methodname, NAMESPACE, URL);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            p.dismiss();

            if (result.equals("False")){

                Helper.dialog_error(LeaveReport_DetailView.this,"Warning","No Data Found","ok");

            } else {

                listitems = new ArrayList<>();
                try {
                    jsonArray = new JSONArray(result);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                        String EmpNo = jsonObject.getString("Empno");
                        String Session = jsonObject.getString("Session");
                        String Reason = jsonObject.getString("Reason");
                        String IsApproved = jsonObject.getString("IsApproved");
                        String IsRejected = jsonObject.getString("IsRejected");
                        String LeaveRequestDate = jsonObject.getString("LeaveRequestDate");


                        listitems.add(new LeaveReportList(EmpNo, LeaveRequestDate, Session, Reason, IsApproved, IsRejected));
                    }

                    leaveReportAdapter = new LeaveReportAdapter(listitems, getApplicationContext());
                    lv_listView.setAdapter(leaveReportAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}