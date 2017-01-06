package com.huangj.myapp.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huangj.myapp.R;
import com.huangj.myapp.fragment.HomeFragment;
import com.huangj.myapp.fragment.OneFragment;
import com.huangj.myapp.fragment.ThreeFragment;
import com.huangj.myapp.fragment.TwoFragment;
import com.huangj.myapp.utils.StatusBarCompat;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private TextView main_tv_login,main_tv_login2;
    private SlidingMenu mSlidingMenu;
    private RadioGroup radioGroup;

    private FragmentManager mFragmentManager;
    private HomeFragment homeFragment;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //不让状态栏被占用
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //不让虚拟键遮盖布局
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        //状态栏颜色设置
        StatusBarCompat.compat(MainActivity.this,getResources().getColor(R.color.colorMain));

        main_tv_login = (TextView) findViewById(R.id.main_tv_login);
        main_tv_login2 = (TextView) findViewById(R.id.main_tv_login2);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mFragmentManager = getSupportFragmentManager();
        if (homeFragment==null){
            homeFragment = new HomeFragment();
        }
        mFragmentManager.beginTransaction().add(R.id.frameLayout,homeFragment).commit();

        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.RIGHT);
        mSlidingMenu.setMenu(R.layout.fragment_slide);
        mSlidingMenu.setBehindWidth((getWindowManager().getDefaultDisplay().getWidth())/2);
        mSlidingMenu.setFadeDegree(0.6f);
        mSlidingMenu.setBehindScrollScale(0.6f);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_WINDOW);
        main_tv_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlidingMenu.showSecondaryMenu();
            }
        });
        mSlidingMenu.findViewById(R.id.slide_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"slide点击成功",Toast.LENGTH_SHORT).show();
            }
        });
        main_tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
                mDrawerLayout.setFocusable(true);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                hideFragment(transaction);
                switch (i){
                    case R.id.button_home:
                        if (homeFragment!=null&&homeFragment.isAdded()){
                            transaction.show(homeFragment);
                        }else {
                            homeFragment = new HomeFragment();
                            transaction.add(R.id.frameLayout,homeFragment);
                        }
                        break;
                    case R.id.button_one:
                        if (oneFragment!=null&&oneFragment.isAdded()){
                            transaction.show(oneFragment);
                        }else {
                            oneFragment = new OneFragment();
                            transaction.add(R.id.frameLayout,oneFragment);
                        }
                        break;
                    case R.id.button_two:
                        if (twoFragment!=null&&twoFragment.isAdded()){
                            transaction.show(twoFragment);
                        }else {
                            twoFragment = new TwoFragment();
                            transaction.add(R.id.frameLayout,twoFragment);
                        }
                        break;
                    case R.id.button_three:
                        if (threeFragment!=null&&threeFragment.isAdded()){
                            transaction.show(threeFragment);
                        }else {
                            threeFragment = new ThreeFragment();
                            transaction.add(R.id.frameLayout,threeFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    public void hideFragment(FragmentTransaction transaction){
        if (homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if (oneFragment!=null){
            transaction.hide(oneFragment);
        }
        if (twoFragment!=null){
            transaction.hide(twoFragment);
        }
        if (threeFragment!=null){
            transaction.hide(threeFragment);
        }
    }

    @Override
    public void onAttachedToWindow() {
        // TODO Auto-generated method stub

        super.onAttachedToWindow();
        }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_HOME) {
             //不做任何操作
             }if (keyCode == KeyEvent.KEYCODE_MENU) {

            }
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return true;
    }

}
