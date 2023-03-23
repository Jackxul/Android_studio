package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

public class PaintBoard extends View {
    private Context mContext;

    private Paint mPaint = null;
    private Bitmap mBitmap = null;
    private Canvas mBitmapCanvas = null;
    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Bitmap.createBitmap(1080,700, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        mBitmapCanvas.drawColor(Color.TRANSPARENT);
//        mPaint = new Paint();
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(6);

    }
    private float startX;
    private float startY ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float stopX = event.getX();
                float stopY = event.getY();
                Log.e(TAG, "onTouchEvent-ACTION_MOVE\nstartX is " + startX +
                        " startY is " + startY + " stopX is " + stopX + " stopY is " + stopY);
                mBitmapCanvas.drawLine(startX, startY, stopX, stopY, mPaint);
                startX = event.getX();
                startY = event.getY();
                //invalidate();//call onDraw()
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmapCanvas != null) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        }
    }

    public void saveBitmap(OutputStream stream) {
        if (mBitmapCanvas != null) {
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }
    }
    public void clear() {
        if (mBitmapCanvas != null) {

//            mBitmap = Bitmap.createBitmap(500,200, Bitmap.Config.ARGB_8888);
//            mBitmapCanvas.drawColor(Color.BLACK);
//            mPaint.setColor(Color.WHITE);
//            mPaint.setStrokeWidth(6);

        }

    }
    public void redraw() {
        invalidate();
        mBitmap = Bitmap.createBitmap(1080,700, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        mBitmapCanvas.drawColor(Color.GRAY);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
    }
    public void saveBitmapAsPng() {
        if (mBitmapCanvas != null){
            String filename = "Signature";
            try (FileOutputStream out = new FileOutputStream(filename)) {
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(mContext.getApplicationContext(), "SUCCESS！", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mContext.getApplicationContext(), "尚未簽名！", Toast.LENGTH_SHORT).show();
        }
    }
}
