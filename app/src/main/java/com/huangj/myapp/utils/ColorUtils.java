package com.huangj.myapp.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Administrator on 2017/1/21 11:50
 * 功能描述:
 */
public class ColorUtils {

    public static int RandomColor(){
        Random random = new Random();
        int r = 30 + random.nextInt(210);
        int g = 30 + random.nextInt(210);
        int b = 30 + random.nextInt(210);
        return Color.rgb(r,g,b);
    }
}
