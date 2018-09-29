package com.example.iwamotokosuke.mykorokoro;

/**
 * Created by gjtuk on 2018/09/29.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class Kusa extends View {

    float x, y;
    int nexttime;
    Paint paint;

    public Kusa(Context context) {
        super(context);
        x = 100;
        y = 100;
//        nexttime = Math.random();

        nexttime = 40;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }


    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawCircle(x, y, nexttime, paint);
    }

}
