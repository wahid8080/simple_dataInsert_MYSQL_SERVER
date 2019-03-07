package com.example.wahid.datainsertproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText mEmail,mUserName;

    public String url = "http://192.168.1.11/insertData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.editText);
        mUserName = findViewById(R.id.editText2);
    }

    public void insertData(View view) {
        upload();
    }

    void upload()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String respon = jsonObject.getString("response");
                    Toast.makeText(MainActivity.this,respon,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"not uploaded",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams()  {

                Map<String,String> prems = new HashMap<String,String>();
                prems.put("email",mEmail.getText().toString().trim());
                prems.put("user_name",mUserName.getText().toString().trim());
                return prems;
            }
        };

        MySingleTone.getmInstance(MainActivity.this).addToRequestQue(stringRequest);
    }
}
