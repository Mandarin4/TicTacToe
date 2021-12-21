package com.crestikinoliki.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.crestikinoliki.tictactoe.R;


public class MainActivity extends AppCompatActivity {
    private long backPressedtimer;
    private Toast backToast;
    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        relative = findViewById(R.id.relative);

        WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = window.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();


        Bitmap copy = Bitmap.createBitmap (width,height, Bitmap.Config.ARGB_8888); // важный
        Canvas canvas = new Canvas (copy); // Создать холст
        Paint paint = new Paint (); // кисть
        paint.setStrokeWidth (1); // Установить ширину линии. Единицы пикселей
        paint.setAntiAlias(true); // Сглаживание
        paint.setColor (Color.RED); // Цвет кисти
      //  Bitmap bitmap = Drawable.createFromPath(background);
        canvas.drawBitmap (copy, new Matrix(), paint); // Рисуем картинку точно так же, как растровое изображение на холсте
        // Рисуем линии сетки в соответствии с размером растрового изображения
        // Рисуем горизонтальные линии
        int pixInterval = 10;
        for (int i = 0; i < copy.getHeight() / pixInterval; i++) {
            canvas.drawLine(0, i * pixInterval, copy.getWidth(), i * pixInterval, paint);
        }
        // Рисуем вертикальные линии
        for (int i = 0; i < copy.getWidth() / pixInterval; i++) {
            canvas.drawLine(i * pixInterval, 0, i * pixInterval, copy.getHeight(), paint);
        }




    }

    public void playButtonClick (View view){
        Intent intent = new Intent(this, PlayerSetup.class);
        startActivity(intent); finish();
    }

    public void rulesButtonClick (View view){
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent); finish();
    }

    // Системная кнопка назад
    @Override
    public void onBackPressed(){
        if (backPressedtimer + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти!", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedtimer = System.currentTimeMillis();
    }



}