package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;

public class Afterscsh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bitmap bitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap");
//        setContentView(R.layout.activity_afterscsh);
        ImageView imafscsh = (ImageView) findViewById(R.id.imafscsh);
//        imafscsh.setImageBitmap(bitmap);


//        Bundle extras = getIntent().getExtras();
//        byte[] b = extras.getByteArray("picture");
//        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
//        imafscsh.setImageBitmap(bmp);

    }
}
