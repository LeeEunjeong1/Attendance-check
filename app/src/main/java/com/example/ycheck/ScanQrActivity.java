package com.example.ycheck;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ScanQrActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    private AlertDialog dialog;
    String classToStuNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        final String stuId = getIntent().getStringExtra("stuId");
        final String classId= getIntent().getStringExtra("classId");
        //String []className ={"opeation.php","arduino.php"};->나중에 url부분

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response){
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    classToStuNum = jsonResponse.getString("classToStuNum").toString();

                    Log.d("테스중입니다",""+classToStuNum);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        ClassStuNumRequest classStuNumRequest = new ClassStuNumRequest(stuId,classId,responseListener);
        RequestQueue queue = Volley.newRequestQueue(ScanQrActivity.this);
        queue.add(classStuNumRequest);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false); // d.efault가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // todo
            } else {
                final String stuId = getIntent().getStringExtra("stuId");
                final String classId= getIntent().getStringExtra("classId");

                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));

                String urltest="http://sep1198.cafe24.com/QrCheck.php";

                if(result.getContents().equals(urltest)){
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response){
                            try{
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanQrActivity.this);
                                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    builder.create();
                                    builder.setMessage("확인되었습니다.");
                                    builder.show();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanQrActivity.this);
                                    dialog = builder.setMessage("확인이 안됩니다.")
                                            .setNegativeButton("다시시도", null)
                                            .create();
                                    dialog.show();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    };
                    AttendRequest attendRequest = new AttendRequest(classToStuNum,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ScanQrActivity.this);
                    queue.add(attendRequest);

                    Intent intent1 = new Intent(ScanQrActivity.this, ProCheckActivity.class);
                    intent1.putExtra("classToStuNum", classToStuNum);

                    ScanQrActivity.this.startActivity(intent);
                    finish();

                 }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}