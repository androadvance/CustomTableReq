package com.example.customtable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LeaveReportAdapter extends BaseAdapter {

    private List<LeaveReportList> arraylist;
    private Context mcontext;

    public LeaveReportAdapter(List<LeaveReportList> arraylist, Context mcontext) {
        this.arraylist = arraylist;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mcontext, R.layout.leave_detailviewlistitem, null);

        TextView Empno = v.findViewById(R.id.Empno);
        TextView LeaveDate = v.findViewById(R.id.LeaveDate);
        TextView Session = v.findViewById(R.id.Session);
        TextView Reason = v.findViewById(R.id.Reason);
        TextView IsApproved = v.findViewById(R.id.IsApproved);
        TextView IsRejected = v.findViewById(R.id.IsRejected);

        final LeaveReportList listItem = arraylist.get(position);

        Empno.setText(arraylist.get(position).getEmpno());
        LeaveDate.setText(arraylist.get(position).getLeaveDate());
        Session.setText(arraylist.get(position).getSeesion());
        Reason.setText(arraylist.get(position).getRaeson());
        IsApproved.setText(arraylist.get(position).getIsApproved());
        IsRejected.setText(arraylist.get(position).getIsRejected());

        if (arraylist.get(position).getIsApproved().equals("true")){
            IsApproved.setText("");
            IsRejected.setText("-");
            IsApproved.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickoranges, 0, 0, 0);
        }
        if (arraylist.get(position).getIsRejected().equals("true")){
            IsApproved.setText("-");
            IsRejected.setText("");
            IsRejected.setCompoundDrawablesWithIntrinsicBounds(R.drawable.wrong, 0, 0, 0);
        }
        if (arraylist.get(position).getIsRejected().equals("false") && arraylist.get(position).getIsApproved().equals("false")){
            IsApproved.setText("");
            IsRejected.setText("");
            IsApproved.setCompoundDrawablesWithIntrinsicBounds(R.drawable.alertt, 0, 0, 0);
            IsRejected.setCompoundDrawablesWithIntrinsicBounds(R.drawable.alertt, 0, 0, 0);
        }

        return v;
    }
}
