package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Page1 extends AppCompatActivity {
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        //final com.example.myapplication.PaintBoard2 pb2 = (com.example.myapplication.PaintBoard2)findViewById(R.id.paint_board2);
        //final com.github.barteksc.pdfviewer.PDFView pdr = findViewById(R.id.pdfView);


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        Button btn_nextpage = findViewById(R.id.btn_next);
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.LTGRAY);
        iv_canvas = findViewById(R.id.iv_canvas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Page1.this, Pdfreader.class);
                startActivity(myintent);
            }
        });

        Button btn_d=findViewById(R.id.btn_draw);
        btn_d.post(new Runnable(){
            @Override
            public void run() {
                btn_d.performClick();
            }
        });



        btn_d.setOnClickListener((v) -> {

                drawTria((float) 960, (float) 1050, (float) 960, (float) 1500,
                        30, 10);

        });

        btn_nextpage.setOnClickListener((v) -> {
            Intent myintent = new Intent(Page1.this, MainActivity.class);

            startActivity(myintent);
        });


    }


    protected void drawTria(float fromX, float fromY, float toX, float toY,
                            int heigth, int bottom) {
// heigth和bottom分别为三角形的高与底的一半,调节三角形大小
        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(baseBitmap);
        canvas.drawColor(Color.TRANSPARENT);// 设置底色
        canvas.drawLine(fromX, fromY, toX, toY, paint);
        float juli = (float) Math.sqrt((toX - fromX) * (toX - fromX)
                + (toY - fromY) * (toY - fromY));// 获取线段距离
        float juliX = toX - fromX;// 有正负，不要取绝对值
        float juliY = toY - fromY;// 有正负，不要取绝对值
        float dianX = toX - (heigth / juli * juliX);
        float dianY = toY - (heigth / juli * juliY);
        float dian2X = fromX + (heigth / juli * juliX);
        float dian2Y = fromY + (heigth / juli * juliY);
//终点的箭头
        Path path = new Path();
        path.moveTo(toX, toY);// 此点为三边形的起点
        path.lineTo(dianX + (bottom / juli * juliY), dianY
                - (bottom / juli * juliX));
        path.lineTo(dianX - (bottom / juli * juliY), dianY
                + (bottom / juli * juliX));
        path.close(); // 使这些点构成封闭的三边形

        canvas.drawPath(path, paint);
//显示图像
        iv_canvas.setImageBitmap(baseBitmap);
    }
}
