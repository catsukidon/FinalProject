package com.example.jen.leedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonPlayNow;
    private ImageButton buttonScore;
    private ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayNow = (ImageButton) findViewById(R.id.buttonPlayNow);
        buttonScore = (ImageButton) findViewById(R.id.buttonScore);
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);

        buttonPlayNow.setOnClickListener(this);
        buttonScore.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonPlayNow:
                startActivity(new Intent(this, GameActivity.class));
                break;
            case R.id.buttonScore:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.buttonPlay:
                Intent mIntent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(mIntent);
                break;
        }

    }
}
