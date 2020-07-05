package com.example.ycheck;

//StuMainActivity.java

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

public class ProCheckActivity extends AppCompatActivity {

    ArrayList<SampleDataProCheck> movieDataList;
    int textColorId = R.color.black;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procheck);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    String[] classToText= new String[jsonResponse.length()];
                    String[] array=new String[4];

                    final String[] className= new String[jsonResponse.length()];
                    final String[] stuName= new String[jsonResponse.length()];
                    final String[] checkWhether = new String[jsonResponse.length()];
                    final String[] checkTime= new String[jsonResponse.length()];


                    for(int i = 0;i<jsonResponse.length();i++){
                        classToText[i]= jsonResponse.get(i).toString();
                        Log.d("classToText : ",""+classToText[i]);
                        array = classToText[i].split("\",");

                        className[i]= array[0].substring(2,array[0].length());
                        stuName[i]= array[1].substring(1,array[1].length());
                        checkWhether[i]=array[2].substring(1,array[2].length());
                        checkTime[i] = array[3].substring(1,array[3].length()-2);

                    }
                    InitializeMovieData(className, stuName, checkWhether, checkTime);

                    ListView listView = (ListView)findViewById(R.id.listViewProfessor);


                    final MyAdapter3 myAdapter = new MyAdapter3(getApplicationContext(),movieDataList){
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent){
                            View view=super.getView(position,convertView,parent);
                            for(int i=0; i<className.length; i++){
                                if(position==i&&checkWhether[i].equals("지각")){
                                    view=super.getView(position,convertView,parent);
                                    view.setBackgroundColor(Color.parseColor("#FDF5DC"));
//
                                }
                                else if(position==i&&checkWhether[i].equals("결석")){
                                    view =super.getView(position,convertView,parent);
                                    view.setBackgroundColor(Color.parseColor("#FFEBF0"));
                                }
                                else if(position==i&&checkWhether[i].equals("출석")){
                                    view =super.getView(position,convertView,parent);
                                    view.setBackgroundColor(Color.parseColor("#E8F5FF"));
                                }
                            }
                            return view;
                        }
                    };



                    listView.setAdapter(myAdapter);

                  /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){

                            Intent intent = new Intent(ProCheckActivity.this, QrMainActivity.class);
                            ProCheckActivity.this.startActivity(intent);
                            finish();
                        }
                    });*/
                    Log.d("클래스 네임" , ""+ classToText);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        ProCheckRequest proCheckRequest = new ProCheckRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(ProCheckActivity.this);
        queue.add(proCheckRequest);

    }

    public void InitializeMovieData(String[] className,String[] stuName,String[] checkWhether,String[] checkTime)
    {
        movieDataList = new ArrayList<SampleDataProCheck>();

        for(int i = 0 ; i<className.length;i++){
            movieDataList.add(new SampleDataProCheck(className[i], stuName[i], checkWhether[i], checkTime[i]));
        }

    }
}




