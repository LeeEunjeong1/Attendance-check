package com.example.ycheck;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProUpdateNoticeRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/ProUpdateNotice.php";
    private Map<String, String> parameters;

    public ProUpdateNoticeRequest(String noticeCategorie,String proId,String classId,String noticeTitle,String noticeContents, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters=new HashMap<>();
        parameters.put("noticeCategorie", noticeCategorie);
        parameters.put("proId", proId);
        parameters.put("classId", classId);
        parameters.put("noticeTitle", noticeTitle);
        parameters.put("noticeContents", noticeContents);
    }

    @Override
    public Map<String, String> getParams(){return parameters;}
}