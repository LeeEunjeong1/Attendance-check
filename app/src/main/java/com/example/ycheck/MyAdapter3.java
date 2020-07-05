package com.example.ycheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;

public class MyAdapter3 extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleDataProCheck> sample;


    public MyAdapter3(Context context, ArrayList<SampleDataProCheck> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SampleDataProCheck getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.fragment_check_list, null);

        TextView className = (TextView) view.findViewById(R.id.className);
        TextView stuName = (TextView) view.findViewById(R.id.stuName);
        TextView checkWhether = (TextView) view.findViewById(R.id.checkWhether);
        TextView checkTime = (TextView) view.findViewById(R.id.checkTime);

        className.setText(sample.get(position).getClassName());
        stuName.setText(sample.get(position).getStuName());
        checkWhether.setText(sample.get(position).getCheckWhether());
        checkTime.setText(sample.get(position).getCheckTime());

        return view;
    }
}