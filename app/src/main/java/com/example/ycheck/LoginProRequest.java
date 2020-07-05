package com.example.ycheck;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class LoginProRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/ProfessorLogin.php";
    private Map<String, String> parameters;

    public LoginProRequest(String proId, String proPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters=new HashMap<>();
        parameters.put("proId", proId);
        parameters.put("proPassword", proPassword);
    }

    @Override
    public Map<String, String> getParams(){return parameters;}
}