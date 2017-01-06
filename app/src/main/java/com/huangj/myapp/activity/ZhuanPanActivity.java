package com.huangj.myapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huangj.myapp.R;
import com.huangj.myapp.utils.ZhuanPanView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Random;

public class ZhuanPanActivity extends AppCompatActivity {
    TextView star;
    ZhuanPanView mView;
    AlertDialog.Builder builder;
    @ViewInject(R.id.degree)
    EditText degree;
    @ViewInject(R.id.time)
    EditText time;
    @ViewInject(R.id.zhuanpan_btn)
    Button zhuangpan_btn;
    int s,times=1800,degrees=8888;
    String [] data = new String[]{"一等奖","二等奖","三等奖","四等奖","五等奖","六等奖","七等奖","八等奖"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanpan);
        x.view().inject(this);
        star=(TextView) findViewById(R.id.start);
        mView=(ZhuanPanView) findViewById(R.id.myview);
        mView.setData(data);

        //创建对话框
        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("提示......");

        star.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int min=1,max=359;
               s =(new Random()).nextInt(max)%(max-min-1)+min;
                RotateAnimation rotate = new RotateAnimation(0,degrees+s,
                        Animation.ABSOLUTE,  mView.getWidth() / 2,
                        Animation.ABSOLUTE, mView.getHeight() / 2);
                rotate.setDuration(times);
                rotate.setFillAfter(true);
                DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
                rotate.setInterpolator(decelerateInterpolator);
                mView.startAnimation(rotate);
                rotate.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub 13591774480
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // TODO Auto-generated method stub
                        int a=8-s/45;
                        builder.setMessage("恭喜您"+data[a-1]+"-->"+s);
                        builder.create().show();
//                        Toast.makeText(getApplicationContext(), "恭喜您"+a+"等奖"+s, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "确认", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
            }
        });

        zhuangpan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                times = Integer.parseInt(time.getText().toString());
                degrees = Integer.parseInt(degree.getText().toString());
                Toast.makeText(ZhuanPanActivity.this,"时间："+times+"，度数："+degrees,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

