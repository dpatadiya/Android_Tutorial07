package com.example.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {


    EditText edtUsername;
    EditText edtPassword;
    Button btn;
    DatabaseHelper helper;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername=(EditText) findViewById(R.id.edtRUsername);
        edtPassword=(EditText) findViewById(R.id.edtPassword);
        btn=(Button) findViewById(R.id.btn);
        helper= new DatabaseHelper(this);


        preferences =getSharedPreferences("college",MODE_PRIVATE);
        editor = preferences.edit();

        String userPreference = preferences.getString("username","");

        if(!userPreference.equals("")){
            Intent intent= new Intent(Login.this, display.class);
            intent.putExtra("userdata",userPreference);
            startActivity(intent);
            finish();
        }

        btn.setOnClickListener(new View.OnClickListener() {
                                   private Object Message;

                                   @Override

                                   public void onClick(View view) {

                                       String valusername = edtUsername.getText().toString();
                                       String valpassword = edtPassword.getText().toString();
                                       String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                                       // Database Operation
                                       if(helper.validateUser(valusername,valpassword)){
                                           Log.i("LoginScreen","In OnClick If");

                                               editor.putString("username",valusername);
                                               editor.commit();

                                               Intent intent= new Intent(Login.this,display.class);
                                               intent.putExtra("userdata",valusername);
                                               startActivity(intent);
                                               finish();
                                           Toast.makeText(getApplicationContext(),"Login Successfully...", Toast.LENGTH_SHORT).show();
                                       }
                                           else {
                                               Toast.makeText(getApplicationContext(),"Username or Password is Wrong..", Toast.LENGTH_SHORT).show();
                                                Log.i("LoginScreen","In OnClick If");
                                              }
                                       }
                                }
        );
    }
    public void getRegistration(View view) {
        startActivity(new Intent(Login.this,MainActivity.class));
    }
}