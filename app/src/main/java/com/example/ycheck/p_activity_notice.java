package com.example.ycheck;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ycheck.ui.notice.NoticeFragment;

public class p_activity_notice extends AppCompatActivity {

    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_notice);


        Button button3 = (Button) findViewById(R.id.button3);
        Button button = (Button) findViewById(R.id.button);

        // 버튼 리스너 추가
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context); // 빌더 얻기

                // 다이얼로그 메세지 생성
                alertDialogBuilder
                        .setMessage("수정하시겠습니까?")
                        .setPositiveButton("예", // Positive 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent registerIntent = new Intent(p_activity_notice.this, p_activity_notice_add.class);
                                        p_activity_notice.this.startActivity(registerIntent);
                                    }
                                })
                        .setNegativeButton("아니오", //Negative 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel(); // 다이얼로그 취소
                                    }
                                });

                // 다이럴로그 객체 얻어오기
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context); // 빌더 얻기

                // 다이얼로그 메세지 생성
                alertDialogBuilder
                        .setMessage("돌아가시겠습니까?")
                        .setPositiveButton("예", // Positive 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent registerIntent = new Intent(p_activity_notice.this, NoticeFragment.class);
                                        p_activity_notice.this.startActivity(registerIntent);
                                    }
                                })
                        .setNegativeButton("아니오", //Negative 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel(); // 다이얼로그 취소
                                    }
                                });

                // 다이럴로그 객체 얻어오기
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });
    }
}

