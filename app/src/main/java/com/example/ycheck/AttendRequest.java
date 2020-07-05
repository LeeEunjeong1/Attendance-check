package com.example.ycheck;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class AttendRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/QrCheck.php";
    private Map<String, String> parameters;

    public AttendRequest(String classToStuNum,Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters=new HashMap<>();
        parameters.put("classToStuNum", classToStuNum);
    }

    @Override
    public Map<String, String> getParams(){return parameters;}
}