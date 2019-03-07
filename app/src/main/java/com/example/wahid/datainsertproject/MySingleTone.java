package com.example.wahid.datainsertproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleTone {
    public static MySingleTone mInstance;
    public RequestQueue requestQueue;
    public Context mCon;

    public MySingleTone(Context context)
    {
        mCon = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            requestQueue  = Volley.newRequestQueue(mCon.getApplicationContext());
        }
        return  requestQueue;
    }

    public static synchronized MySingleTone getmInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new MySingleTone(context);
        }

        return mInstance;
    }

    public <T> void addToRequestQue(Request <T> request)
    {
        getRequestQueue().add(request);
    }
}
