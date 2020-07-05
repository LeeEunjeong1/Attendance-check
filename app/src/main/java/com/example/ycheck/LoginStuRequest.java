package com.example.ycheck;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


public class LoginStuRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/StudentLogin.php";
    private Map<String, String> parameters;

    public LoginStuRequest(String stuId, String stuPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters=new HashMap<>();
        parameters.put("stuId", stuId);
        parameters.put("stuPassword", stuPassword);
    }

    @Override
    public Map<String, String> getParams(){return parameters;}
}