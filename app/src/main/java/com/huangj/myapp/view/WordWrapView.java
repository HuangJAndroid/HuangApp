package com.huangj.myapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huangj.myapp.myinterface.WordWrapListener;
import com.huangj.myapp.utils.ColorUtils;

/**
 * Created by Administrator on 2017/2/8 9:52
 * 功能描述:
 */
public class WordWrapView extends ViewGroup {
    private static final int PADDING_HOR = 10;//水平方向padding
    private static final int PADDING_VERTICAL = 5;//垂直方向padding
    private static final int SIDE_MARGIN = 10;//左右间距
    private static final int TEXT_MARGIN = 10;
    private WordWrapListener mWordWrapListener;

    public void setWordWrapListener(WordWrapListener wordWrapListener) {
        mWordWrapListener = wordWrapListener;
    }

    /**
     * @param context
     */
    public WordWrapView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public WordWrapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    /**
     * @param context
     * @param attrs
     */
    public WordWrapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        int x = SIDE_MARGIN;// 横坐标开始
        int y = 0;//纵坐标开始
        int rows = 1;
        for(int i=0;i<childCount;i++){
            View view = getChildAt(i);
            view.setBackgroundColor(ColorUtils.RandomColor());
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            x += width+TEXT_MARGIN;
            if(x>autualWidth){
                x = width+SIDE_MARGIN;
                rows++;
            }
            y = rows*(height+TEXT_MARGIN);
            if(i==0){
                view.layout(x-width-TEXT_MARGIN, y-height, x-TEXT_MARGIN, y);
            }else{
                view.layout(x-width, y-height, x, y);
            }
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;//横坐标
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - SIDE_MARGIN * 2;//实际宽度
        int childCount = getChildCount();
        for(int index = 0;index<childCount;index++){
            final View child = getChildAt(index);
            final int finalIndex = index;
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                  mWordWrapListener.onClickListener(v,finalIndex);
                }
            });
            child.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mWordWrapListener.onLongClickListener(v,finalIndex);
                    return true;
                }
            });
            child.setPadding(PADDING_HOR, PADDING_VERTICAL, PADDING_HOR, PADDING_VERTICAL);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width+TEXT_MARGIN;
            if(x>actualWidth){//换行
                x = width;
                rows++;
            }
            y = rows*(height+TEXT_MARGIN);
        }
        setMeasuredDimension(actualWidth, y);
    }

}
