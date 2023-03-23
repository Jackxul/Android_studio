package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private PaintBoard2 paintBoard2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final com.example.myapplication.PaintBoard2 pb2 = findViewById(R.id.paint_board2);
        ImageView imgm = findViewById(R.id.mainimg);
        TextView pts = findViewById(R.id.textpts);
        TextView edinput = findViewById(R.id.editText_input);
        Button btn_clean = findViewById(R.id.btn_clean);
        Button btn_save = findViewById(R.id.btn_save);
        Button btn_back = findViewById(R.id.btn_back);
        ToggleButton tbn = (ToggleButton)findViewById(R.id.toggleButton);
        //paintBoard2 = (PaintBoard2)findViewById(R.id.paint_board2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        imgm.bringToFront();
        pts.bringToFront();
        btn_back.setVisibility(View.INVISIBLE);
        btn_clean.setVisibility(View.INVISIBLE);
        btn_save.setVisibility(View.INVISIBLE);


        tbn.setOnClickListener((v) ->{
            imgm.setBackgroundColor(Color.TRANSPARENT);
            btn_back.setVisibility(View.VISIBLE);
            btn_clean.setVisibility(View.VISIBLE);
            btn_save.setVisibility(View.VISIBLE);
            pts.setVisibility(View.INVISIBLE);
            tbn.setVisibility(View.GONE);
            pb2.turntran();
            pb2.redraw();

        });

        btn_back.setOnClickListener((v) ->{
            Intent myP1 = new Intent(this,Page1.class);

            startActivity(myP1);
        });

        btn_clean.setOnClickListener((v) -> {
            //pb2.clear(this);

            pb2.turntran();
            pb2.redraw();
        });
        btn_save.setOnClickListener((v) -> {

            pb2.saveBitmapAsPng(this);
            btn_back.setVisibility(v.INVISIBLE);
            btn_clean.setVisibility(v.INVISIBLE);
            btn_save.setVisibility(v.INVISIBLE);
            edinput.setVisibility(v.INVISIBLE);

//            Toast toast = Toast.makeText(this, "Success!",Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.BOTTOM,0,0);
//            toast.show();
//            Intent myP1 = new Intent(MainActivity.this,Page3.class);
//
//            startActivity(myP1);
        });
//        btn_save.setOnClickListener((v) -> {
//
//        });
    }



    public void OnSaveClicked(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            OutputStream stream = new FileOutputStream(file);
            //paintBoard2.printBitmap();
            stream.close();
            // send broadcast to Media to update data
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment
                    .getExternalStorageDirectory()));
            sendBroadcast(intent);

            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "save failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}

