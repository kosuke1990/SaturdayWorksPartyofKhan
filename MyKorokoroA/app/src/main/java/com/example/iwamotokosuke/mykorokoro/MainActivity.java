package com.example.iwamotokosuke.mykorokoro;

import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.FrameLayout;
import java.util.Random;
import android.content.Intent;



public class MainActivity extends Activity implements Runnable, SensorEventListener {
    SensorManager manager;
    Ball ball, ball2;
    Hole hole;
    Handler handler;
    int width, height, time;
    float gx, gy, dpi;
    FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        framelayout = new FrameLayout(this);
        framelayout.setBackgroundColor(Color.GREEN);
        setContentView(framelayout);

        time = 10;
        handler = new Handler();
        handler.postDelayed(this, 1000);

        WindowManager windowManager =
                (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        dpi = getResources().getDisplayMetrics().densityDpi;

        ball = new Ball(this);
        ball.x = width / 2;
        ball.y = height / 2;

        ball2 = new Ball(this);
        ball2.x = width / 2;
        ball2.y = height - 10;
        
        hole = new Hole(this);
        Random rnd = new Random();
        hole.x = rnd.nextInt(
                width - (2 * (hole.r + ball.radius)))
                + hole.r + ball.radius;
        hole.y = rnd.nextInt(
                height - (2 * (hole.r + ball.radius)))
                + hole.r + ball.radius;

        framelayout.addView(hole);
        framelayout.addView(ball);
        framelayout.addView(ball2);
    }
    @Override
    public void run() {
        ball.vx = (float) (-gx * time / 1000);
        ball.vy = (float) (gy * time / 1000);
        ball.x += dpi * ball.vx * time / 25.4;
        ball.y += dpi * ball.vy * time / 25.4;

        ball2.vx = (float) (-gx * time / 500);
        ball2.vy = (float) (gy * time / 500);
        ball2.x += dpi * ball2.vx * time / 25.4;
        ball2.y += dpi * ball2.vy * time / 25.4;

        if (ball.x <= ball.radius) {
            ball.x = ball.radius;
            ball.vx = -ball.vx / 3;
        } else if (ball.x >= width - ball.radius) {
            ball.x = width - ball.radius;
            ball.vx = -ball.vx / 3;
        }

        if (ball.y <= ball.radius) {
            ball.y = ball.radius;
            ball.vy = -ball.vy / 3;
        } else if (ball.y >= height - ball.radius) {
            ball.y = height - ball.radius;
            ball.vy = -ball.vy / 3;
        }

        if (ball2.x <= ball2.radius) {
            ball2.x = ball2.radius;
            ball2.vx = -ball2.vx / 3;
        } else if (ball2.x >= width - ball2.radius) {
            ball2.x = width - ball2.radius;
            ball2.vx = -ball2.vx / 3;
        }

        if (ball2.y <= ball2.radius) {
            ball2.y = ball2.radius;
            ball2.vy = -ball2.vy / 3;
        } else if (ball2.y >= height - ball2.radius) {
            ball2.y = height - ball2.radius;
            ball2.vy = -ball2.vy / 3;
        }


        //ボールと穴の当たり判定の記述
        if ((hole.x - hole.r < ball.x &&
                ball.x < hole.x + hole.r) &&
                (hole.y - hole.r < ball.y &&
                        ball.y < hole.y + hole.r)) {
            Intent intent3 = new Intent(getApplication(), TitleActivity.class);
            startActivity(intent3);
            ball.invalidate();
            
        } else if((hole.x - hole.r < ball2.x &&
                ball2.x < hole.x + hole.r) &&
                (hole.y - hole.r < ball2.y &&
                        ball2.y < hole.y + hole.r)) {
            Intent intent3 = new Intent(getApplication(), TitleActivity.class);
            startActivity(intent3);
            ball2.invalidate();

        }
        else {
            ball.invalidate();
            ball2.invalidate();
            handler.postDelayed(this, time);
        }
    }
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        manager = (SensorManager)getSystemService(
                SENSOR_SERVICE);
        List<Sensor> sensors =
                manager.getSensorList(
                        Sensor.TYPE_ACCELEROMETER);
        if (0 < sensors.size()) {
            manager.registerListener(
                    this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        gx = event.values[0];
        gy = event.values[1];
    }
    @Override
    public void onAccuracyChanged(
            Sensor sensor, int accuracy) {
    }
}