package com.huangj.myapp.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.huangj.myapp.R;
/*
*利用Preferenceactivity快速创建布局
*/
public class MyPreferenceActivity extends PreferenceActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.my_preference);
       sp =  PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("autoBack")){
                    Boolean autoBack = sharedPreferences.getBoolean("autoBack", false);
                    if(autoBack){
                        Log.e("TAG","autoBack: true!");
                    }
                    else {
                        Log.e("TAG","autoBack: false!");
                    }
                }
            }
        });
    }
}
