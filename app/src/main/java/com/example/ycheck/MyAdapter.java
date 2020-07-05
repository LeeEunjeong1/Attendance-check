package com.example.ycheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public MyAdapter(Context context, ArrayList<SampleData> data) {
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
    public SampleData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.fragment_class_list, null);

        TextView className = (TextView) view.findViewById(R.id.className);
        TextView lectureRoom = (TextView) view.findViewById(R.id.classRoom);
        TextView professorName = (TextView) view.findViewById(R.id.professorName);
        TextView classStart = (TextView) view.findViewById(R.id.classStart);
        TextView classEnd = (TextView) view.findViewById(R.id.classEnd);

        className.setText(sample.get(position).getClassName());
        lectureRoom.setText(sample.get(position).getLectureRoom());
        professorName.setText(sample.get(position).getProfessorName());
        classStart.setText(sample.get(position).getClassStart());
        classEnd.setText(sample.get(position).getClassEnd());

        return view;
    }
}