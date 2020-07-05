package com.example.ycheck;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class ClassStuNumRequest extends StringRequest {

    final static private String URL = "http://sep1198.cafe24.com/ClassToStuNum.php";
    private Map<String, String> parameters;

    public ClassStuNumRequest(String stuId,String classId,Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters=new HashMap<>();
        parameters.put("stuId", stuId);
        parameters.put("classId", classId);

    }

    @Override
    public Map<String, String> getParams(){return parameters;}
}