package com.example.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText edtRUsername,edtRPassword;
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtRUsername=findViewById(R.id.edtRUsername);
        edtRPassword=findViewById(R.id.edtRPasswod);
        helper=new DatabaseHelper(this);

    }

    public void saveRecord(View view) {
        String valUsername=edtRUsername.getText().toString();
        String valPassword=edtRPassword.getText().toString();

        // validation
        if(valUsername.equals("") || valPassword.equals("") ||
                valPassword.length()<6 || !Patterns.EMAIL_ADDRESS.matcher(valUsername).matches())
        {
            Toast.makeText(this,"Enter Validate Values",Toast.LENGTH_SHORT).show();
            return;
        }

        // Database Operation
        if(helper.inserdata(valUsername,valPassword)){
            Toast.makeText(this,"Registration Successfully... ",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();
        }else {
            Toast.makeText(this,"Registration Failed... ",Toast.LENGTH_SHORT).show();
            edtRPassword.requestFocus();

        }


    }
}