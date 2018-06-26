package com.example.kunalraghav.youtubeplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        Button btnsingle =findViewById(R.id.playvideo);
        Button btnStand = findViewById(R.id.StandAlone);
        btnsingle.setOnClickListener(this);
        btnStand.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent =null;

        switch (v.getId()){
            case R.id.StandAlone:
                intent= new Intent(this,StandAloneActivity.class);
                break;
            case R.id.playvideo:
                intent= new Intent(this,YoutubeActivity.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }

    }
}
