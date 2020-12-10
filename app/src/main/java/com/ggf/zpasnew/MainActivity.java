package com.ggf.zpasnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void inputMenu(View view){
        Intent intent = new Intent(MainActivity.this,SpkWelcome.class);
        startActivity(intent);


    }

    public void ReadTk(View view){
        Intent intent = new Intent(MainActivity.this,halamantk.class);
        startActivity(intent);

    }

}