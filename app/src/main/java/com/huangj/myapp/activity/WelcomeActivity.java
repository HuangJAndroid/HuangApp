package com.huangj.myapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.huangj.myapp.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class WelcomeActivity extends Activity {

    @ViewInject(R.id.iv_welcome)
    ImageView iv_welcome;
    private static final int ANIMATION_TIME = 2000;

    private static final float SCALE_END = 1.13F;

    private static final int[] IMAGES = {
            R.mipmap.ic_screen_default,
            R.mipmap.splash0,
            R.mipmap.splash1,
            R.mipmap.splash2,
            R.mipmap.splash3,
            R.mipmap.splash4,
            R.mipmap.splash5,
            R.mipmap.splash6,
            R.mipmap.splash7,
            R.mipmap.splash8,
            R.mipmap.splash9,
            R.mipmap.splash10,
            R.mipmap.splash11,
            R.mipmap.splash12,
            R.mipmap.splash13,
            R.mipmap.splash14,
            R.mipmap.splash15,
            R.mipmap.splash16,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        x.view().inject(this);

        Random random = new Random(SystemClock.elapsedRealtime());
        iv_welcome.setImageResource(IMAGES[random.nextInt(IMAGES.length)]);
        startAnim();

    }

    private void startAnim()
    {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv_welcome, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv_welcome, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
