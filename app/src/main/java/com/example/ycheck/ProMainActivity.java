package com.example.ycheck;

//ProMainActivity
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

import java.util.Random;

public class ProMainActivity extends AppCompatActivity {
    private static String proId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //proId 값 받아오기 getIntent().getStringExtra
        proId = getIntent().getStringExtra("proId");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promain);
        //id값 잘 넘겨졌는지 확인하는 TextView


        //editText에 url 넣음 -> 큐알코드 생성 -> 큐알 인식하면 넣은 url로 넘어감
        final Context context=this;

        Button qr = (Button) findViewById(R.id.QR);
        Button nt = (Button) findViewById(R.id.pronotice);

        qr.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //   String text2Qr = editText.getText().toString();

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode("http://sep1198.cafe24.com/QrCheck.php", BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(context, QrActivity.class);
                    intent.putExtra("pic",bitmap);
                    context.startActivity(intent);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        Button proCheck = (Button) findViewById(R.id.procheck);

        proCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProCheckActivity.class);
                context.startActivity(intent);
            }
        });

        nt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProNoticeActivity.class);
                intent.putExtra("proId", proId);
                context.startActivity(intent);
            }
        });
    }
}