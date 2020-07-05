package com.example.ycheck;

//ProMainActivity
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;

import java.util.ArrayList;

public class ProNoticeActivity extends AppCompatActivity {
    private static String proId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //proId 값 받아오기 getIntent().getStringExtra
        proId = getIntent().getStringExtra("proId");

        super.onCreate(savedInstanceState);
        final ArrayList arrayList = new ArrayList<>();
        setContentView(R.layout.activity_notice_add);
        final Spinner spinner = findViewById(R.id.spinner);
        //교수님 수업 불러오는 Request
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    String[] classToText = new String[jsonResponse.length()];

                    String[] array = new String[2];
                    String[] className = new String[jsonResponse.length()];
                    String[] classId = new String[jsonResponse.length()];

                    for (int i = 0; i < jsonResponse.length(); i++) {
                        classToText[i] = jsonResponse.get(i).toString();
                        Log.d("classToText : ", "" + classToText[i]);
                        array = classToText[i].split("\",");

                        className[i] = array[0].substring(2, array[0].length());
                        classId[i] = array[1].substring(0, array[1].length()-1);

                        arrayList.add(className[i]+","+classId[i]);

                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner.setAdapter(arrayAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ProClassRequest proClassRequest = new ProClassRequest(proId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ProNoticeActivity.this);
        queue.add(proClassRequest);




        //btn save가 클릭되었을 때
        Button button_save = (Button)findViewById(R.id.saveNotice);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //spinner 에서 무엇이 선택되었는지
               String classId= (String)spinner.getSelectedItem();
               String [] array =classId.split(",");

               //classId 가져오기
               classId= array[1];
               Log.d("공지사항 class 찍기",classId);

               //과제 인지 공지사항인지
               EditText proUpdateCategorie= (EditText) findViewById(R.id.editCategory);
               String proCategorie = proUpdateCategorie.getText().toString();

               //공지사항 제목
               EditText editUpdateTitle= (EditText) findViewById(R.id.editTitle);
               String editTitle = editUpdateTitle.getText().toString();

               //공지사항 내용
                EditText editUpdateContents= (EditText) findViewById(R.id.editContents);
                String editContents = editUpdateContents.getText().toString();


                //공지사항 등록 Request
                Response.Listener<String> res = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                ProUpdateNoticeRequest proUpdateNoticeRequest = new ProUpdateNoticeRequest(proCategorie,proId,classId,editTitle,editContents, res);
                RequestQueue queue = Volley.newRequestQueue(ProNoticeActivity.this);
                queue.add(proUpdateNoticeRequest);
            }
        });

    }
}
