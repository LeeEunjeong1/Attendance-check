package com.example.ycheck.ui.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ycheck.AlarmActivity;
import com.example.ycheck.AlarmListActivity;
import com.example.ycheck.ClassNameRequest;
import com.example.ycheck.MyAdapter;
import com.example.ycheck.MyAdapterAlarm;
import com.example.ycheck.R;
import com.example.ycheck.SampleData;
import com.example.ycheck.SampleDataAlarm;

import org.json.JSONArray;

import java.util.ArrayList;


public class AlarmFragment extends Fragment {
    ArrayList<SampleDataAlarm> classDataList;
    private AlarmViewModel alarmViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_alarm, container, false);
        final String stuId ="201733031"; //임의로 넣어줌

        // class 정보 불러오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    String[] classToText = new String[jsonResponse.length()];
                    String classNameText[]= new String[jsonResponse.length()];


                    for (int i = 0; i < jsonResponse.length(); i++) {
                        classToText[i] = jsonResponse.get(i).toString();
                        Log.d("test1용 출력",classToText[i]);
                    }
                    InitializeMovieData(classToText);

                    ListView listView = (ListView) root.findViewById(R.id.listView);

                    final MyAdapterAlarm myAdapter = new MyAdapterAlarm(root.getContext(), classDataList);

                    listView.setAdapter(myAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            Intent intent = new Intent(root.getContext(), AlarmActivity.class);
                            intent.putExtra("stuId", stuId);
                            root.getContext().startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ClassNameRequest classNameRequest = new ClassNameRequest(stuId,responseListener);
        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        queue.add(classNameRequest);
        return root;
    }
    public void InitializeMovieData(String[] className)
    {
        classDataList = new ArrayList<SampleDataAlarm>();

        for(int i = 0 ; i<className.length;i++){
            classDataList.add(new SampleDataAlarm(className[i]));
        }

    }
}
