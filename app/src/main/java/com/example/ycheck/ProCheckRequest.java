package com.example.ycheck;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class ProCheckRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/proCheckAttend2.php";
    private Map<String, String> parameters;

    public ProCheckRequest(Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters=new HashMap<>();
    }

    @Override
    public Map<String, String> getParams(){return parameters;}
}
