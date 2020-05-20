package com.example.sh;

import android.app.ProgressDialog;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {
    EditText emailUser, password;
    Button registerButton;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        findView();
        Firebase.setAndroidContext(this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                email = emailUser.getText().toString().replace(".", ",");
                pass = password.getText().toString();

                if (email.equals("")) {
                    emailUser.setError("can't be blank");

                } else if (pass.equals("")) {
                    password.setError("can't be blank");

                } else if (email.length() < 13) {
                    emailUser.setError("at least 5 characters long");
                } else if (!pass.matches("[A-Za-z0-9]+")) {
                    password.setError("only alphabet or number allowed");
                } else if (pass.length() < 5) {
                    password.setError("at least 5 characters long");

                } else {
                    final ProgressDialog pd = new ProgressDialog(Registration.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://smarth-1d455.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://smarth-1d455.firebaseio.com/users");

                            if (s.equals("null")) {


                                reference.child(email).child("password").setValue(pass);
                                UserDetails.username = email;

                                Toast.makeText(Registration.this, "registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Registration.this, MainActivity.class));
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(email)) {
                                        reference.child(email).child("password").setValue(pass);

                                        UserDetails.username = email;
                                        Toast.makeText(Registration.this, "registration successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Registration.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(Registration.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Registration.this);
                    rQueue.add(request);
                }

            }
        });
    }

    public void findView() {

        emailUser = findViewById(R.id.eMail);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.regButton);

    }
}
