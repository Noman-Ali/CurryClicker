package com.example.a10008858.curryclicker;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.example.a10008858.curryclicker.R;

//import static com.example.a10008858.curryclicker.R.layout.activity_main;

//import static com.example.a10008858.curryclicker.R.id.activity_main;

public class MainActivity extends AppCompatActivity {

    ImageView curry, steph, aunty;
    int points, upgrade1, upgrade2, cps;
    TextView score;
    TextView aunties;
    TextView stephs;
    TextView rate;
    double randomOffset;
    boolean checkpoint1, checkpoint2, threadTrue, animationInit1, animationInit2;
    Context context;
    RelativeLayout rL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        cps = 0;
        //RelativeLayout rL = new RelativeLayout(this);
        rate = (TextView)findViewById(R.id.textView4);
        curry = (ImageView) findViewById(R.id.imageView);
        aunty = (ImageView) findViewById(R.id.imageView2);
        steph = (ImageView) findViewById(R.id.imageView3);
        score = (TextView) findViewById(R.id.textView);
        aunties = (TextView) findViewById(R.id.textView2);
        stephs = (TextView) findViewById(R.id.textView3);
        rL = (RelativeLayout)findViewById(R.id.activity_main);
        animationInit1 = true;
        animationInit2 = true;

        final MediaPlayer upgradeSound = MediaPlayer.create(this, R.raw.scu);

        //plusOne = (TextView) findViewById(R.id.textView2);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ABOVE, R.id.imageView);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);


        curry.setImageResource(R.drawable.c);
        //plusOne.setText("+1");
        randomOffset = -3 + (Math.random() * 6);
        final TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2, Animation.RELATIVE_TO_SELF, -4);
        translateAnimation.setDuration(1000);
        final ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(1250);
        final ScaleAnimation upgradeAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upgradeAnimation.setDuration(1250);
        final ScaleAnimation upgradeAnimationOut = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upgradeAnimationOut.setDuration(500);
        points = 0;
        steph.setImageResource(R.drawable.sc);
        aunty.setImageResource(R.drawable.ia);
        steph.setVisibility(View.INVISIBLE);
        aunty.setVisibility(View.INVISIBLE);
        curry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                curry.startAnimation(scaleAnimation);
                //plusOne.startAnimation(translateAnimation);
                points += 1;
                score.setText(String.valueOf(points));

                TextView textView = new TextView(context);
                textView.setPadding((int) randomOffset, 0, 0, 70);
                textView.setText("+1");
                textView.setTextColor(Color.parseColor("#FFFFFF"));

                rL.addView(textView, params);
                textView.startAnimation(translateAnimation);
                textView.setVisibility(View.INVISIBLE);
                if (points < 100) {
                    steph.setVisibility(View.INVISIBLE);
                } else {
                    steph.setVisibility(View.VISIBLE);
                    if(animationInit1){
                        steph.startAnimation(upgradeAnimation);
                        animationInit1=false;
                    }

                }
                if (points < 30) {
                    aunty.setVisibility(View.INVISIBLE);
                } else {
                    aunty.setVisibility(View.VISIBLE);
                    //aunty.startAnimation(upgradeAnimation);
                    if(animationInit2){
                        aunty.startAnimation(upgradeAnimation);
                        animationInit2=false;
                    }
                }

            }
        });
        steph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upgradeSound.start();
                upgrade2 += 1;
                threadTrue = true;
                points = (points - 100);
                stephs.setText("Steph Curry(s): " + String.valueOf(upgrade2));
                if (points < 30) {
                    aunty.setVisibility(View.INVISIBLE);
                } else {
                    aunty.setVisibility(View.VISIBLE);
                    //aunty.startAnimation(upgradeAnimation);
                }
                if (points < 100) {
                    steph.setVisibility(View.INVISIBLE);
                } else {
                    steph.setVisibility(View.VISIBLE);
                    //steph.startAnimation(upgradeAnimation);
                }
                score.setText(String.valueOf(points));
                steph.startAnimation(upgradeAnimationOut);
                cps = upgrade1 + (upgrade2 * 3);
            }
        });
        aunty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upgrade1 += 1;
                threadTrue = true;
                Thread t1 = new newThread();
                t1.start();
                points = (points - 30);
                aunties.setText("Aunties: " + String.valueOf(upgrade1));
                if (points < 30) {
                    aunty.setVisibility(View.INVISIBLE);
                } else {
                    aunty.setVisibility(View.VISIBLE);
                    //aunty.startAnimation(upgradeAnimation);
                }
                if (points < 100) {
                    steph.setVisibility(View.INVISIBLE);
                } else {
                    steph.setVisibility(View.VISIBLE);
                    //steph.startAnimation(upgradeAnimation);
                }
                score.setText(String.valueOf(points));
                aunty.startAnimation(upgradeAnimationOut);
                cps = upgrade1 + (upgrade2 * 3);
            }
        });



    }


    class newThread extends Thread {

        @Override
        public void run() {
            super.run();
            while(threadTrue==true)
                try {
                    newThread.sleep(1000);
                        runOnUiThread(new Runnable(){

                                          @Override
                                          public void run() {
                                            score.setText(String.valueOf(points));
                                            rate.setText("Rate: "+String.valueOf(cps));

                                          }
                                      });
                    points = points + upgrade1 + (upgrade2 * 3);
                    animationInit1=false;
                    animationInit2=false;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }


        }
    }
}
