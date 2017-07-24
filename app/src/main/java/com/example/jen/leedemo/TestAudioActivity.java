package com.example.jen.leedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jen.leedemo.controller.TestAudioController;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAudioActivity extends BaseActivity {

    private TestAudioController controller;
    private String audioURL = "http://other.web.rh01.sycdn.kuwo.cn/resource/n3/77/1/1061700123.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_audio);
        ButterKnife.bind(this);

        final Button playBtn = (Button) findViewById(R.id.activity_test_audio_play);

        controller = new TestAudioController(this, new TestAudioController.AudioListener() {
            @Override
            public void onPlaying() {
                playBtn.setText("Pause");
                playBtn.setClickable(true);
            }

            @Override
            public void onLoading() {
                playBtn.setText("Loading");
                playBtn.setClickable(false);
            }

            @Override
            public void onPaused() {
                playBtn.setText("Play");
                playBtn.setClickable(true);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (TestAudioController.STATUS){
                    case TestAudioController.AudioPlaying:
                        controller.pause();
                        break;
                    case TestAudioController.AudioPaused:
                        controller.play(audioURL);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
