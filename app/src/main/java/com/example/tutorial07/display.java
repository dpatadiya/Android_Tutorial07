package com.example.tutorial07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class display extends AppCompatActivity {

    TextView txtWelcome;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        txtWelcome = findViewById(R.id.textWelcome);
        preferences =getSharedPreferences("college",MODE_PRIVATE);
        editor =preferences.edit();

        Intent intent= getIntent();
        String userdata= intent.getStringExtra("userdata");
        txtWelcome.setText("Welcome "+ userdata);

        Toast.makeText(this,userdata,Toast.LENGTH_SHORT).show();
    }
    public void logout() {
        editor.putString("username","");
        editor.remove("username");
        editor.commit();

        Intent intent=new Intent(display.this,Login.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {

            case R.id.logout:
                logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}