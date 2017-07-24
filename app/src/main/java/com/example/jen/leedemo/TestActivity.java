package com.example.jen.leedemo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class TestActivity extends BaseActivity implements View.OnClickListener{

    AnimationDrawable nyanCatAnimation;
    AnimationDrawable rainbowAnimation;
    ImageView nyanCatImage;
    ImageView rainbowImage;

    TextView text;
    Button dialogButtonYes;
    Button dialogButtonNo;

    private ImageButton buttonSound;
    private ImageButton buttonQuit;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        buttonQuit = (ImageButton) findViewById(R.id.buttonPause);
        buttonQuit.setOnClickListener(this);

        buttonSound = (ImageButton) findViewById(R.id.buttonSound);
        buttonSound.setOnClickListener(this);

        nyanCatImage = (ImageView) findViewById(R.id.nyanCat);
        nyanCatImage.setBackgroundResource(R.drawable.nyancat_animation);
        nyanCatAnimation = (AnimationDrawable) nyanCatImage.getBackground();

        rainbowImage = (ImageView) findViewById(R.id.nyanRainbow);
        rainbowImage.setBackgroundResource(R.drawable.rainbow_animation);
        rainbowAnimation = (AnimationDrawable) rainbowImage.getBackground();
    }

    public void onCheckboxClicked(View v) throws IOException {
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case R.id.checkbox_nyan:
                if (checked) {
                    nyanCatAnimation.start();
                }
                else {
                    nyanCatAnimation.stop();
                }
                break;
            case R.id.checkbox_rainbow:
                if (checked) {
                    rainbowAnimation.start();
                }
                else {
                    rainbowAnimation.stop();
                }
                break;
        }
    }

    @Override
    public void onClick(View v){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Custom Dialog");

        switch (v.getId()) {
            case R.id.buttonPause:
                text = (TextView) dialog.findViewById(R.id.text_dialog);
                text.setText("Do you want to quit?");

                dialogButtonNo = (Button) dialog.findViewById(R.id.btn_dialog_no);
                // if button is clicked, close the custom dialog
                dialogButtonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialogButtonYes = (Button) dialog.findViewById(R.id.btn_dialog_yes);
                dialogButtonYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent mIntent = new Intent(TestActivity.this, MainActivity.class);
                        //startActivity(mIntent);
                        ImageView background1 = (ImageView)findViewById(R.id.background_one);
                        ImageView background2 = (ImageView)findViewById(R.id.background_two);
                        ImageView background3 = (ImageView)findViewById(R.id.background_three);
                        background1.setBackgroundResource(R.drawable.game2background1);
                        background2.setBackgroundResource(R.drawable.game2background2);
                        background3.setBackgroundResource(R.drawable.game2background1);
                    }
                });

                dialog.show();
                break;
            case R.id.buttonSound:
                startActivity(new Intent(this, TestAudioActivity.class));
                break;

        }


    }

}

