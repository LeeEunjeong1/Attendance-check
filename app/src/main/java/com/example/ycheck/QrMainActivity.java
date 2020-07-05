package com.example.ycheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QrMainActivity extends AppCompatActivity {
    private Button scanQRBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        scanQRBtn = (Button) findViewById(R.id.scanQR);

        scanQRBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(QrMainActivity.this, ScanQrActivity.class);
                final String stuId = getIntent().getStringExtra("stuId");
                final String classId= getIntent().getStringExtra("classId");

                intent.putExtra("stuId",stuId);
                intent.putExtra("classId",classId);

                startActivity(intent);
            }
        });
    }
}