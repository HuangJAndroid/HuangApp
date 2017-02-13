package com.huangj.myapp.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.huangj.myapp.R;
import com.huangj.myapp.view.ScratchTextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ScratchActivity extends Activity {

    @ViewInject(R.id.rubbler)
    private ScratchTextView rubbler;
    private String[] str = new String[]{"谢谢参与","一等奖","二等奖","三等奖","四等奖"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new RubberView(this,str[i],new Rect(100, 200,300,250),5,1f,18));
        setContentView(R.layout.activity_scratch);
        x.view().inject(this);
        int i =(int)(Math.random()*5);
        rubbler.setText(str[i]);
        rubbler.initScratchCard(0xFFDA70D6, 8, 1f);

    }

    class RubberView extends View {

        private final int PAINT_STROKE_WIDTH;
        private final float TOUCH_TOLERANCE;
        private final int TEXT_SIZE;

        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Paint mPaint;
        private Path mPath;
        private float mX, mY;
        private final int X, Y, W, H;

        private final Rect touchRect;

        public RubberView(Context context, String bgText, Rect rect,
                          int paintStrokeWidth, float touchTolerance, int textSize) {
            super(context);
            setFocusable(true);
            touchRect = rect;
            W = rect.right - rect.left;
            H = rect.bottom - rect.top;
            X = rect.left;
            Y = rect.top;
            TEXT_SIZE = textSize;
            PAINT_STROKE_WIDTH = paintStrokeWidth;
            TOUCH_TOLERANCE = touchTolerance;
            setBackground(touchRect, bgText);
            initDrowTools();
        }

        private void setBackground(Rect rect, String bgText) {
            DisplayMetrics dm = new DisplayMetrics();
            dm = this.getResources().getDisplayMetrics();

            Bitmap bitmap = Bitmap.createBitmap(dm.widthPixels,
                    dm.heightPixels, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);

            Paint paint = new Paint();
            paint.setColor(0x88000000);
            // paint.setStyle(Style.STROKE);
            // paint.setTextAlign(Align.CENTER);
            paint.setTextSize(TEXT_SIZE);

            // paint.setTextScaleX(1.5f);
            canvas.drawColor(Color.WHITE);
            int x = rect.left + (rect.right - rect.left - bgText.length() * TEXT_SIZE) / 2;
            int y = rect.top + (rect.bottom - rect.top - TEXT_SIZE) / 2;
            // int y = 218+25;
            canvas.drawText(bgText, x, y, paint);
            Drawable drawable = new BitmapDrawable(bitmap);
            setBackgroundDrawable(drawable);
        }

        private void initDrowTools() {
            mPaint = new Paint();
            // mPaint.setAlpha(0);
            //          mPaint.setColor(Color.BLACK);
            //          mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

            mPaint.setAlpha(0);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(PAINT_STROKE_WIDTH);

            mPath = new Path();

            mBitmap = Bitmap.createBitmap(W, H, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(0x88000000);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mCanvas.drawPath(mPath, mPaint);
            // mCanvas.drawPoint(mX, mY, mPaint);
            canvas.drawBitmap(mBitmap, X, Y, null);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (!touchRect.contains((int) event.getX(), (int) event.getY())) {
                return false;
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchDown(event.getRawX(), event.getRawY());
                    touchDown(event.getX() - touchRect.left, event.getY()
                            - touchRect.top);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchMove(event.getX() - touchRect.left, event.getY()
                            - touchRect.top);

                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touchUp(event.getX() - touchRect.left, event.getY()
                            - touchRect.top);
                    invalidate();
                    break;
                default:
                    break;
            }
            return true;
        }

        private void touchDown(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touchMove(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }

        }

        private void touchUp(float x, float y) {
            mPath.lineTo(x, y);
            mCanvas.drawPath(mPath, mPaint);
            mPath.reset();
        }

    }
}