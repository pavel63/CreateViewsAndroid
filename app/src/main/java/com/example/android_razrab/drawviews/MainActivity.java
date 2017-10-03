package com.example.android_razrab.drawviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}


    class MyView extends View {
        Paint paint;
        Path path;
        Matrix matrix;


        //в конструкторе инициализируем,устанавливаем значения кисти
        public MyView(Context context) {
            super(context);

            paint = new Paint();
            //стиль кисти-STROKE-по контуру фигуры,FILL-залита фигура
            paint.setStyle(Paint.Style.STROKE);
            //цвет кисти
            paint.setColor(Color.RED);
            //ширина кисти
            paint.setStrokeWidth(5);

            path = new Path();
            matrix = new Matrix();


        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //просто рисуем линию- задаем начало ее ху,конец ху и кисть
            canvas.drawLine(100, 100, 800, 800, paint);


//добавляем прямоугольник первые два значения икс и игрек верхнего левого угла,последние-нижний правый

            path.reset();
            path.addRect(100, 150, 400, 250, Path.Direction.CW);
            path.addRect(180, 50, 500, 350, Path.Direction.CW);

            //добавляем круг.задаем координаты икс игрек,диаметр и направление
            path.addCircle(300, 300, 100, Path.Direction.CW);

            //рисуем его
            canvas.drawPath(path, paint);


            //настраисаем матрицу на перемещение на 100 вправо и 300 вниз
            matrix.reset();
            matrix.setTranslate(300, 600);
            //а также повернуть на 180 градусов относительно точки 300,300
            //при этом эта точка и становится новым центром фигуры
            //  matrix.setRotate(180,300,300);

            //применяем матрицу к Path
            path.transform(matrix);


            //рисуем текст на путе

            paint.setTextSize(50);
            canvas.drawTextOnPath("my text on a path", path, 0, 0, paint);


//меняем цвет и рисуем еще раз с измененными местоположением и цветом
            paint.setColor(Color.GREEN);

            canvas.drawPath(path, paint);


//отрисовываем картинку
            canvas.drawBitmap(getCyanBitmap(), 200, 1000, paint);



            paint.setShader(createShader());
             paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(700,1100,200,paint);




            paint.setShader(createGradientShader());
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
            canvas.drawCircle(200,1200,200,paint);


        }



        Bitmap getCyanBitmap() {

            //просто создаем картинку для примера
            Bitmap bitmap2 = Bitmap.createBitmap(200, 100,
                    Bitmap.Config.ARGB_8888);
            bitmap2.eraseColor(Color.CYAN);

            return bitmap2;
        }




        private Shader createShader() {
          Bitmap  bitmap = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher);
            BitmapShader shader = new BitmapShader(bitmap,
                    Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
          /*  BitmapShader shader = new BitmapShader(bitmap,
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);*/
            return shader;
        }




        private Shader createGradientShader() {
            LinearGradient shader = new LinearGradient(0, 0, 100, 20,
                    Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
            return shader;
        }
    }
