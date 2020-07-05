package com.example.ycheck;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;

import java.util.ArrayList;

public class AlarmListActivity extends AppCompatActivity {

    ArrayList<SampleDataAlarm> classDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alarm);

        final String stuId = getIntent().getStringExtra("stuId");

        // class 정보 불러오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    String[] classToText = new String[jsonResponse.length()];
                    final String classNameText[]= new String[jsonResponse.length()];


                    for (int i = 0; i < jsonResponse.length(); i++) {
                        classToText[i] = jsonResponse.get(i).toString();
                        classNameText[i]= classToText[i].substring(2,classToText.length-2);
                    }
                    InitializeMovieData(classNameText);

                    ListView listView = (ListView) findViewById(R.id.listView);

                    final MyAdapterAlarm myAdapter = new MyAdapterAlarm(getApplicationContext(), classDataList);

                    listView.setAdapter(myAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            Intent intent = new Intent(AlarmListActivity.this, AlarmActivity.class);
                            intent.putExtra("stuId", stuId);
                            Log.d("이거야: ",classNameText[position]);
                            intent.putExtra("className",classNameText[position]);
                            AlarmListActivity.this.startActivity(intent);
                            finish();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ClassNameRequest classNameRequest = new ClassNameRequest(stuId,responseListener);
        RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
        queue.add(classNameRequest);
    }

    public void InitializeMovieData(String[] className)
    {
        classDataList = new ArrayList<SampleDataAlarm>();

        for(int i = 0 ; i<className.length;i++){
            classDataList.add(new SampleDataAlarm(className[i]));
        }

    }
}