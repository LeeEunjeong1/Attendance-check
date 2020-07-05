package com.example.ycheck;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ycheck.ui.home.HomeFragment;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {



    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //id와 password 받기
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passText);
        final Button loginButton = (Button) findViewById(R.id.login_button);
        //학부생, 교직원 구분 radioGroup
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        final int check = radioGroup.getCheckedRadioButtonId();







        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rg_btn1: {
                        final String stuId = idText.getText().toString();
                        final String stuPassword = passwordText.getText().toString();
                        Log.d("학생페이지 ",stuId+stuPassword);
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();

                                                Intent intent = new Intent(LoginActivity.this, StuMainActivity.class);
                                                intent.putExtra("stuId", stuId);

                                                LoginActivity.this.startActivity(intent);
                                                finish();

                                            }
                                        });
                                        builder.create();
                                        builder.setMessage("로그인에 성공했습니다.");
                                        builder.show();

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                        dialog = builder.setMessage("계정을 다시 확인하세요.")
                                                .setNegativeButton("다시시도", null)
                                                .create();
                                        dialog.show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        };
                        LoginStuRequest loginStuRequest = new LoginStuRequest(stuId, stuPassword, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        queue.add(loginStuRequest);
                        break;
                    }
                    case R.id.rg_btn2: {

                        final String proId = idText.getText().toString();
                        final String proPassword = passwordText.getText().toString();
                        Log.d("교수님페이지 ",proId+proPassword);
                        Response.Listener<String> responseListener = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();

                                                Intent intent = new Intent(LoginActivity.this, ProMainActivity.class);
                                                intent.putExtra("proId", proId);
                                                LoginActivity.this.startActivity(intent);
                                                finish();

                                            }
                                        });
                                        builder.create();
                                        builder.setMessage("로그인에 성공했습니다.");
                                        builder.show();

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                        dialog = builder.setMessage("계정을 다시 확인하세요.")
                                                .setNegativeButton("다시시도", null)
                                                .create();
                                        dialog.show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        };
                        LoginProRequest loginProRequest = new LoginProRequest(proId, proPassword, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        queue.add(loginProRequest);
                        break;
                    }
                }
            }
        });
    }




    @Override
    protected void onStop(){
        super.onStop();
        if(dialog != null)
        {
            dialog.dismiss();
            dialog = null;
        }
    }

}