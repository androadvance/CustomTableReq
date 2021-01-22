package com.example.customtable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterHeader extends BaseAdapter {

    private Context mContext;
    ArrayList<ListItemHeader> arrayList;

    public ListAdapterHeader(Context mContext, ArrayList<ListItemHeader> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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

        convertView = LayoutInflater.from(mContext).inflate(R.layout.leave_request_header, parent, false);
        TextView text1 = convertView.findViewById(R.id.text1);
        TextView text2 = convertView.findViewById(R.id.text2);
        TextView text3 = convertView.findViewById(R.id.text3);
        TextView text4 = convertView.findViewById(R.id.text4);
        TextView text5 = convertView.findViewById(R.id.text5);
        TextView text6 = convertView.findViewById(R.id.text6);
        TextView text7 = convertView.findViewById(R.id.text7);
        TextView text8 = convertView.findViewById(R.id.text8);
        TextView text9 = convertView.findViewById(R.id.text9);
        TextView text10 = convertView.findViewById(R.id.text10);

        final ListItemHeader listItem = arrayList.get(position);

        text1.setText(arrayList.get(position).getEmpno());
        text2.setText(arrayList.get(position).getName());
        text3.setText(arrayList.get(position).getTeam());
        text4.setText(arrayList.get(position).getDept());
        text5.setText(arrayList.get(position).getDesignation());
        text6.setText(arrayList.get(position).getUnit());
        text7.setText(arrayList.get(position).getLeavedate());
        text8.setText(arrayList.get(position).getSession());
        text9.setText(arrayList.get(position).getPurpose());
        text10.setText(arrayList.get(position).getRemarks());

        return convertView;
    }
}
