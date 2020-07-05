package com.example.ycheck;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleDataNotice> sampleDataNotice;

    public MyAdapter2(Context context, ArrayList<SampleDataNotice> data) {
        mContext = context;
        sampleDataNotice = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sampleDataNotice.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SampleDataNotice getItem(int position) {
        return sampleDataNotice.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_custom, null);

        TextView noticeCategorie = (TextView) view.findViewById(R.id.noticeCategorie);
        TextView proName= (TextView) view.findViewById(R.id.proName);
        TextView className= (TextView) view.findViewById(R.id.className);
        TextView noticeTitle = (TextView) view.findViewById(R.id.noticeTitle);
        TextView noticeContents = (TextView) view.findViewById(R.id.noticeContents);

        noticeCategorie.setText(sampleDataNotice.get(position).getNoticeCategorie());
        proName.setText(sampleDataNotice.get(position).getProName());
        className.setText(sampleDataNotice.get(position).getClassName());
        noticeTitle.setText(sampleDataNotice.get(position).getNoticeTitle());
        noticeContents.setText(sampleDataNotice.get(position).getNoticeContents());

        return view;
    }
}