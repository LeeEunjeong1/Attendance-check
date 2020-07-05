package com.example.ycheck;
//ClassRequest 파일

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ClassNameRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/ClassNameCome.php";
    private Map<String, String> parameters;

    public ClassNameRequest(String stuId, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters=new HashMap<>();
        parameters.put("stuId", stuId);
    }

    @Override
    public Map<String, String> getParams(){return parameters;}
}