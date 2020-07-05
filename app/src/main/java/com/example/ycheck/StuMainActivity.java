package com.example.ycheck;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.ycheck.ui.notice.NoticeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

public class StuMainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    String className;
    ArrayList<SampleData> classDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stumain);

        final String stuId = getIntent().getStringExtra("stuId");

        // class 정보 불러오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response){
                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    String[] classToText= new String[jsonResponse.length()];
                    String[] array=new String[4];

                    String[] className= new String[jsonResponse.length()];
                    String[] lectureRoom= new String[jsonResponse.length()];
                    final String[] classId = new String[jsonResponse.length()];
                    String[] professorName= new String[jsonResponse.length()];
                    String[] classStart= new String[jsonResponse.length()];
                    String[] classEnd= new String[jsonResponse.length()];

                    for(int i = 0;i<jsonResponse.length();i++){
                        classToText[i]= jsonResponse.get(i).toString();
                        array = classToText[i].split("\",");

                        className[i]= array[0].substring(2,array[0].length());
                        classId[i]= array[1].substring(0,array[1].lastIndexOf(","));
                        classStart[i]=array[1].substring(array[1].indexOf("\"")+1,11);
                        classEnd[i] = array[2].substring(1,array[2].length());
                        lectureRoom[i]= array[3].substring(1,array[3].length());
                        professorName[i]= array[4].substring(1,array[4].length()-2);

                    }
                    InitializeMovieData(className, classId,classStart,classEnd,lectureRoom,professorName);

                    ListView listView = (ListView)findViewById(R.id.listView);

                    Log.d("테스중입니다",""+classToText);
                    final MyAdapter myAdapter = new MyAdapter(getApplicationContext(),classDataList);

                    listView.setAdapter(myAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            Intent intent = new Intent(StuMainActivity.this, QrMainActivity.class);
                            intent.putExtra("stuId", stuId);
                            intent.putExtra("classId",classId[position]);
                            StuMainActivity.this.startActivity(intent);
                            finish();
                        }
                    });
                    Log.d("클래스 네임" , ""+ classToText);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        ClassRequest classRequest = new ClassRequest(stuId,responseListener);
        RequestQueue queue = Volley.newRequestQueue(StuMainActivity.this);
        queue.add(classRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"안녕",Toast.LENGTH_LONG).show();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_notice, R.id.nav_schedule,
                //R.id.nav_class_list,
                R.id.nav_alarm)
                .setDrawerLayout(drawer)

                .build();
        //여기부터 바꿈
        /*Intent intent = new Intent(StuMainActivity.this, NoticeFragment.class);
        intent.putExtra("stuId", stuId);
        StuMainActivity.this.startActivity(intent);*/
        //여기까지
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    // 로그아웃 처리 넣기 (예정)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item =menu.findItem(R.id.action_settings1);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(StuMainActivity.this/* 해당 액티비티를 가르킴 */)
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(StuMainActivity.this/*현재 액티비티 위치*/, LoginActivity.class/*이동 액티비티 위치*/);

                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void InitializeMovieData(String[] className, String[]classId, String[] classStart, String[] classEnd, String[] lectureRoom, String[] professorName)
    {
        classDataList = new ArrayList<SampleData>();

        for(int i = 0 ; i<className.length;i++){
            classDataList.add(new SampleData(className[i], classId[i], classStart[i], classEnd[i], lectureRoom[i], professorName[i]));
        }

    }
}