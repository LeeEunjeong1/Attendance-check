package com.example.ycheck;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProQRupdateRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/proQRcodeUpdate.php";
    private Map<String, String> parameters;

    public ProQRupdateRequest(String classId, String classQRcode, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener,null);
        parameters=new HashMap<>();
        parameters.put("classQRcode", classQRcode);
        parameters.put("classId", classId);
    }
    @Override
    public Map<String, String> getParams(){return parameters;}
}
