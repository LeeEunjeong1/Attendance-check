package com.example.ycheck;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ycheck.R;

public class AlarmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmsetting);

        TextView textView = (TextView)findViewById(R.id.subjectName);

        // 실질 맵핑
        Intent intent = getIntent();
        String name = intent.getExtras().getString("className");
        textView.setText(name);


        setup();
    }
    private void setup()
    {
        Switch switch1 = (Switch)findViewById(R.id.classStartAlarm);
        Switch switch2 = (Switch)findViewById(R.id.noticeAlarm);
        Switch switch3 = (Switch)findViewById(R.id.assignmentAlarm);

        switch1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"개발중",Toast.LENGTH_LONG).show();
            }
        });
        switch2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"개발중",Toast.LENGTH_LONG).show();
            }
        });
        switch3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"개발중",Toast.LENGTH_LONG).show();
            }
        });
    }
}
