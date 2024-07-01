package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddStudPage extends AppCompatActivity {
EditText ea1,ea2,ea3,ea4,ea5,ea6,ea7,ea8;
AppCompatButton ba1,ba2;
String apiUrl="https://courseapplogix.onrender.com/addstudents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_stud_page);

        ea1=(EditText) findViewById(R.id.fname);
        ea2=(EditText) findViewById(R.id.lname);
        ea3=(EditText) findViewById(R.id.clgname);
        ea4=(EditText) findViewById(R.id.dob);
        ea5=(EditText) findViewById(R.id.crsename);
        ea6=(EditText) findViewById(R.id.mob);
        ea7=(EditText) findViewById(R.id.mail);
        ea8=(EditText) findViewById(R.id.adrss);
        ba1=(AppCompatButton) findViewById(R.id.addbut);
        ba2=(AppCompatButton) findViewById(R.id.backbut);

        ba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getFName= ea1.getText().toString();
                String getLName= ea2.getText().toString();
                String getClgName= ea3.getText().toString();
                String getDOB= ea4.getText().toString();
                String getCourseName= ea5.getText().toString();
                String getMobNum= ea6.getText().toString();
                String getEmailId= ea7.getText().toString();
                String getAddress= ea8.getText().toString();

                 //creating json object
                JSONObject stud= new JSONObject();
                try {
                    stud.put("firstname",getFName);
                    stud.put("lastname",getLName);
                    stud.put("college",getClgName);
                    stud.put("dob",getDOB);
                    stud.put("course",getCourseName);
                    stud.put("mobile",getMobNum);
                    stud.put("email",getEmailId);
                    stud.put("address",getAddress);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                //creating json object request

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                        Request.Method.POST,
                        apiUrl,
                        stud,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Student added successfully", Toast.LENGTH_SHORT).show();
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Some Error occured", Toast.LENGTH_SHORT).show();
                            }
                        }

                );


                //request queue creation
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);


            }
        });


        ba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ib = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ib);
            }
        });

    }
}