package com.example.angoo.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url="http://134.208.34.184:8080/Android/Register.php"; //index.php
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerbtn = (Button)findViewById(R.id.btnSendData);

        final EditText Email = (EditText)findViewById(R.id.email);
        final EditText Password = (EditText)findViewById(R.id.password);
        builder = new AlertDialog.Builder(MainActivity.this);

        registerbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String email,password;
                email = Email.getText().toString();
                password = Password.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("註冊成功");
                        builder.setMessage("已發送認證信至信箱");
                        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //點下就清空Text
                                Email.setText("");
                                Password.setText("");
                                startActivity(new Intent(MainActivity.this,Logon.class));
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Error...",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("email",email); //(key是php $_POST上的那個鍵值 而不是自訂,43行宣告email)
                        params.put("password",password);
                        return params;
                    }
                };
                //stringRequest 在以下創的class
                MySingleton.getmInstances(MainActivity.this).addTorequestque(stringRequest);



            }
        });
    }
    public void gotoLogon(View v) {
        startActivity(new Intent(this,Logon.class));
    }
}
