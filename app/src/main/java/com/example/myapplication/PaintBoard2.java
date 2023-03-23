package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

public class PaintBoard2 extends View {
    public Context mContext ;
    public Paint mPaint = null;
    public Bitmap mBitmap = null;
    public Canvas mBitmapCanvas = null;
    public  Bitmap tempBit = null;
    ImageView imgm = (ImageView)findViewById(R.id.mainimg);
    public PaintBoard2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Bitmap.createBitmap(1080,1920, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        mBitmapCanvas.drawColor(Color.GRAY);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);

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
                invalidate();//call onDraw()

                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
            mBitmapCanvas.drawColor(Color.TRANSPARENT);
        }
        mBitmapCanvas.drawColor(Color.TRANSPARENT);
    }

//    public void printBitmap() {
//        if (mBitmapCanvas != null) {
//            //mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            tempBit = mBitmap;
//
//            Toast toast = Toast.makeText(mContext, "簽名成功!",Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.BOTTOM,0,230);
//            toast.show();
//        }
//        else{
//            Toast toast = Toast.makeText(mContext, "Error!",Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.BOTTOM,0,230);
//            toast.show();
//        }
//    }
    public void clear(Context mContext) {
        if (mBitmapCanvas != null) {
            invalidate();
            Toast toast = Toast.makeText(mContext, "簽名已清除!",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,230);
            toast.show();
//            mBitmap = Bitmap.createBitmap(500,200, Bitmap.Config.ARGB_8888);
//            mBitmapCanvas.drawColor(Color.BLACK);
//            mPaint.setColor(Color.WHITE);
//            mPaint.setStrokeWidth(6);

        }

    }
    public void redraw() {

        mBitmap = Bitmap.createBitmap(1080,1920, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        mBitmapCanvas.drawColor(Color.TRANSPARENT);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);

    }

    public void turntran() {

        mBitmap = Bitmap.createBitmap(1080,1920, Bitmap.Config.ARGB_8888);
        mBitmapCanvas.drawColor(Color.GRAY);

    }

    public void saveBitmapAsPng(Context mContext) {
        if (mBitmapCanvas != null){

//            String filename = "Signature";
//            try (FileOutputStream out = new FileOutputStream(filename)) {
//                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

            byte[] b = baos.toByteArray();
            Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
            //mBitmapCanvas.drawColor(Color.TRANSPARENT);

            //imgm.setImageBitmap(bmp);
            if(bmp == null)
            {
                Toast.makeText(mContext, "Nothing",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(mContext, "Yoman",Toast.LENGTH_LONG).show();
            }
            //imgm.setImageBitmap();
            Toast toast = Toast.makeText(mContext, "簽名成功!",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,230);
            toast.show();


//            Toast toast = Toast.makeText(this, "Success!",Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.BOTTOM,0,0);
//            toast.show();
        }
        else{
            Toast toast = Toast.makeText(mContext, "尚未簽名!",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,230);
            toast.show();
        }
    }


}
