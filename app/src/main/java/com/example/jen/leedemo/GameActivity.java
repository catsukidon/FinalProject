package com.example.jen.leedemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jen.leedemo.util.UtilLog;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ValueAnimator repeatAnimator;
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    ValueAnimator animator;

    final Context context = this;
    TextView text;
    Button dialogButtonYes;
    Button dialogButtonNo;

    AnimationDrawable nyanCatAnimation;
    AnimationDrawable rainbowAnimation;
    ImageView catImage;
    ImageView rainImage;

    ObjectAnimator animator1;
    ObjectAnimator animator2;

    TextView text_shown;
    Handler seekHandler = new Handler();
    Button play_button, pause_button;

    private int sumY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getInit();
        seekUpdation();

        mDetector = new GestureDetectorCompat(this,this);

        catImage = (ImageView) findViewById(R.id.cat);
        catImage.setBackgroundResource(R.drawable.nyancat_animation);
        nyanCatAnimation = (AnimationDrawable) catImage.getBackground();

        rainImage = (ImageView) findViewById(R.id.rainbow);
        rainImage.setBackgroundResource(R.drawable.rainbow_animation);
        rainbowAnimation = (AnimationDrawable) rainImage.getBackground();

        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);
        final ImageView backgroundThree = (ImageView) findViewById(R.id.background_three);

        animator = ValueAnimator.ofFloat(0.0f, -2.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(5000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX + width);
                backgroundThree.setTranslationX(translationX + width + width);
            }
        });

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        final Button button = (Button) findViewById(R.id.background_change);
        final Button btn = (Button) findViewById(R.id.select);

        RadioButton rb1 = (RadioButton) findViewById(R.id.RadioButton1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.RadioButton2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.RadioButton3);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image1 = (ImageView) findViewById(R.id.background_one);
                ImageView image2 = (ImageView) findViewById(R.id.background_two);
                ImageView image3 = (ImageView) findViewById(R.id.background_three);
                image1.setImageResource(R.drawable.gamebackground1);
                image2.setImageResource(R.drawable.gamebackground2);
                image3.setImageResource(R.drawable.gamebackground1);
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image1 = (ImageView) findViewById(R.id.background_one);
                ImageView image2 = (ImageView) findViewById(R.id.background_two);
                ImageView image3 = (ImageView) findViewById(R.id.background_three);
                image1.setImageResource(R.drawable.game2background1);
                image2.setImageResource(R.drawable.game2background2);
                image3.setImageResource(R.drawable.game2background1);
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image1 = (ImageView) findViewById(R.id.background_one);
                ImageView image2 = (ImageView) findViewById(R.id.background_two);
                ImageView image3 = (ImageView) findViewById(R.id.background_three);
                image1.setImageResource(R.drawable.game3background1);
                image2.setImageResource(R.drawable.game3background2);
                image3.setImageResource(R.drawable.game3background1);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.INVISIBLE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void getInit() {
        play_button = (Button) findViewById(R.id.play_button);
        pause_button = (Button) findViewById(R.id.pause_button);
        text_shown = (TextView) findViewById(R.id.text_shown);
        play_button.setOnClickListener(this);
        pause_button.setOnClickListener(this);
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    public void seekUpdation() {
        seekHandler.postDelayed(run, 1000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_button:
                text_shown.setText("Playing...");
                nyanCatAnimation.start();
                rainbowAnimation.start();
                animator.start();
                break;
            case R.id.pause_button:
                nyanCatAnimation.stop();
                rainbowAnimation.stop();
                animator.pause();
                text_shown.setText("Paused...");
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

  /*  private void jump(final View view) {

        float x = view.getX();
        float y = view.getY();
        Path path = new Path();

        path.moveTo(x, y);
        path.lineTo(x+100, -150);
        path.lineTo(x+150, -250);
        path.lineTo(x+200, -150);
        path.lineTo(x+300, y);
        ObjectAnimator objectAnimator =
                ObjectAnimator.ofFloat(view, View.X,
                        View.Y, path);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    } */

    @Override
    public boolean onDown(MotionEvent event) {
        UtilLog.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        UtilLog.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        UtilLog.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        UtilLog.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());

        sumY += distanceY;

        if (sumY < 0) {
            if (Math.abs(sumY) > 1000) {
                //jump(catImage);
                //ObjectAnimator animator1 = ObjectAnimator.ofFloat(catImage, "translationX", catImage.getX()-80, 0);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(catImage, "translationY", 0, 500);
                animator1.setDuration(2000);
                animator1.setRepeatMode(ValueAnimator.REVERSE);
                animator1.setRepeatCount(1);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(rainImage, "translationY", 0, 500);
                animator2.setDuration(2000);
                animator2.setRepeatMode(ValueAnimator.REVERSE);
                animator2.setRepeatCount(1);
                //animator1.setStartDelay(2010);
                animator1.start();
                animator2.start();

            }
        }
        if (sumY > 0) {
            if (Math.abs(sumY) > 1000) {
                animator1 = ObjectAnimator.ofFloat(catImage, "translationY", 0, -500);
                animator1.setDuration(2000);
                animator1.setRepeatMode(ValueAnimator.REVERSE);
                animator1.setRepeatCount(1);
                animator2 = ObjectAnimator.ofFloat(rainImage, "translationY", 0, -500);
                animator2.setDuration(2000);
                animator2.setRepeatMode(ValueAnimator.REVERSE);
                animator2.setRepeatCount(1);
                //animator1.setStartDelay(2010);
                animator1.start();
                animator2.start();

            }
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        UtilLog.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        UtilLog.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        UtilLog.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        UtilLog.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        nyanCatAnimation.stop();
        rainbowAnimation.stop();
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        UtilLog.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}