package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Pdfreader extends AppCompatActivity {



    public ImageView CutImg; //截圖的畫面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfreader);
        final FloatingActionButton fabpdfr = (FloatingActionButton)findViewById(R.id.fabpdfr);
        FloatingActionButton fabsearch = (FloatingActionButton)findViewById(R.id.searchbtn);
        FloatingActionButton fabpic = (FloatingActionButton)findViewById(R.id.picscreenbtn);
        FloatingActionButton fabchange = (FloatingActionButton)findViewById(R.id.changebtn);
        FloatingActionButton fabdownload = (FloatingActionButton)findViewById(R.id.downloadbtn);
        //com.example.myapplication.PaintBoard2 pb2 = findViewById(R.id.paint_board2);
        CutImg= (ImageView) findViewById(R.id.img);
        //pb2.setVisibility(View.INVISIBLE);
        fabsearch.setVisibility(View.INVISIBLE);
        fabpic.setVisibility(View.INVISIBLE);
        fabchange.setVisibility(View.INVISIBLE);
        fabdownload.setVisibility(View.INVISIBLE);


        int PageRange[] = new int []{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        //fabpdfr.setVisibility(View.VISIBLE);
        PDFView pdfView = findViewById(R.id.pdfView);
            pdfView.fromAsset("example.pdf")
                    .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                    .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                    .nightMode(false) // toggle night mode
                    .load();


        fabpdfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fabpdfr.setVisibility(View.INVISIBLE);
//                Animation animation = AnimationUtils.loadAnimation(Pdfreader.this,R.anim.rotate);
//                fabpdfr.setAnimation(animation);
                final RotateAnimation animation = new RotateAnimation(0.0f, 135.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(50);

                fabpdfr.startAnimation(animation);

                if (fabsearch.getVisibility() == View.INVISIBLE) {
                    fabpdfr.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                    fabpdfr.setRippleColor(Color.BLUE);
                    fabsearch.setVisibility(View.VISIBLE);
                    fabpic.setVisibility(View.VISIBLE);
                    fabchange.setVisibility(View.VISIBLE);
                    fabdownload.setVisibility(View.VISIBLE);

                }
                else if (fabsearch.getVisibility() == View.VISIBLE){
                    fabpdfr.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                    fabpdfr.setRippleColor(Color.MAGENTA);
                    fabsearch.setVisibility(View.INVISIBLE);
                    fabpic.setVisibility(View.INVISIBLE);
                    fabchange.setVisibility(View.INVISIBLE);
                    fabdownload.setVisibility(View.INVISIBLE);
                }

            }


        });

        fabsearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent mintent = new Intent(Pdfreader.this, Afterscsh.class);
                startActivity(mintent);
            }
        });

        fabpic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fabpdfr.setVisibility(View.INVISIBLE);
                fabsearch.setVisibility(View.INVISIBLE);
                fabpic.setVisibility(View.INVISIBLE);
                fabchange.setVisibility(View.INVISIBLE);
                fabdownload.setVisibility(View.INVISIBLE);


                Bitmap bitmap = null;
                bitmap = getScreenShot();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();


                //bitmap = BitmapFactory.decodeResource(getResources(),);
                //bitmap = getScreenShot();
                Intent intent = new Intent();
                intent.setClass(Pdfreader.this,Afterscsh.class);
                /*2*/intent.putExtra("picture", b);
                /*1*///intent.putExtra("Bitmap",bitmap);
                startActivity(intent);
                if(b == null){
                    Toast.makeText(Pdfreader.this,"NNN",Toast.LENGTH_LONG).show();
                }
                else if (b != null){
                Toast.makeText(Pdfreader.this,"YYY",Toast.LENGTH_LONG).show();
                }
                //Intent myintent = new Intent(Pdfreader.this, Afterscsh.class);
                //startActivity(intent);
                //將截圖Bitmap放入ImageView
                //CutImg.setImageBitmap(getScreenShot());

                //afscsh(CutImg);

                fabpdfr.setVisibility(View.VISIBLE);
                fabsearch.setVisibility(View.VISIBLE);
                fabpic.setVisibility(View.VISIBLE);
                fabchange.setVisibility(View.VISIBLE);
                fabdownload.setVisibility(View.VISIBLE);

            }
        });

    }

//    public  void afscsh(ImageView img){
//        Intent myintent = new Intent(Pdfreader.this, Afterscsh.class);
//        startActivity(myintent);
//        ImageView afsc = (ImageView)findViewById(R.id.imafscsh);
//        afsc = img;
//    }



    private Bitmap getScreenShot()
    {
        //藉由View來Cache全螢幕畫面後放入Bitmap
        View picView = getWindow().getDecorView();
        picView.setDrawingCacheEnabled(true);
        picView.buildDrawingCache();
        Bitmap picfullBitmap = picView.getDrawingCache();


        //取得系統狀態列高度
        Rect mRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
        int mStatusBarHeight = mRect.top;

        //取得手機螢幕長寬尺寸
        int mPhoneWidth = getWindowManager().getDefaultDisplay().getWidth();
        int mPhoneHeight = getWindowManager().getDefaultDisplay().getHeight();

        //將狀態列的部分移除並建立新的Bitmap
        Bitmap mBitmap = Bitmap.createBitmap(picfullBitmap, 0, mStatusBarHeight, mPhoneWidth, mPhoneHeight - mStatusBarHeight);
        //將Cache的畫面清除
        picView.destroyDrawingCache();
        //afscsh();
        saveImageToGallery(this,mBitmap);
        saveToInternalStorage(mBitmap);
        return mBitmap;


    }
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先儲存圖片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Jack");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把檔案插入到系統圖庫
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最後通知相簿更新
        //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("/sdcard/Jack/")));
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return directory.getAbsolutePath();
    }

}
