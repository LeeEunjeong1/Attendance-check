package com.example.ycheck.ui.notice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ycheck.MyAdapter2;
import com.example.ycheck.NoticeRequest;
import com.example.ycheck.R;
import com.example.ycheck.SampleDataNotice;

import org.json.JSONArray;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    private NoticeViewModel noticeViewModel;

    ArrayList<SampleDataNotice> movieDataList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        noticeViewModel =
                ViewModelProviders.of(this).get(NoticeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notice, container, false);

        //final String stuId = root.getContext().getString(Integer.parseInt("stuId"));

       final String stuId = "201733031";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    String[] classToText = new String[jsonResponse.length()];
                    String[] array = new String[5];

                    String[] noticeCategorie = new String[jsonResponse.length()];
                    String[] proName = new String[jsonResponse.length()];
                    String[] className = new String[jsonResponse.length()];
                    String[] noticeTitle = new String[jsonResponse.length()];
                    String[] noticeContents = new String[jsonResponse.length()];

                    for (int i = 0; i < jsonResponse.length(); i++) {
                        classToText[i] = jsonResponse.get(i).toString();
                        Log.d("classToText : ", "" + classToText[i]);
                        array = classToText[i].split("\",");

                        noticeCategorie[i] = array[0].substring(2, array[0].length());
                        proName[i] = array[1].substring(1, array[1].length());
                        className[i] = array[2].substring(1, array[2].length());
                        noticeTitle[i] = array[3].substring(1, array[3].length());
                        noticeContents[i] = array[4].substring(1, array[4].length() - 2);


                    }
                    InitializeMovieData(noticeCategorie, proName, className, noticeTitle, noticeContents);

                    ListView listView = (ListView) root.findViewById(R.id.listView);


                    final MyAdapter2 myAdapter = new MyAdapter2(root.getContext(), movieDataList);

                    listView.setAdapter(myAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        NoticeRequest noticeRequest = new NoticeRequest(stuId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        queue.add(noticeRequest);
        return root;
    }






    public void InitializeMovieData(String[] noticeCategorie,String[] proName,String[] className,String[] noticeTitle,String[] noticeContents)
    {
        movieDataList = new ArrayList<SampleDataNotice>();

        for(int i = 0 ; i<noticeTitle.length;i++){
            movieDataList.add(new SampleDataNotice(noticeCategorie[i], proName[i], className[i], noticeTitle[i],noticeContents[i]));
        }

    }

}