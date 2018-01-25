package com.example.angoo.register;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton extends AppCompatActivity {

    private static MySingleton mInstances;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getmInstances(Context context) {
        if (mInstances == null) {
            mInstances = new MySingleton(context);
        }
        return mInstances;

    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T>void addTorequestque(Request<T> request)
    {
        requestQueue.add(request);
    }
}
