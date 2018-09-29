package com.example.iwamotokosuke.mykorokoro;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by gjtuk on 2018/09/29.
 */

public class ZassouActivity extends Activity implements Runnable{

    Kusa wara;
    FrameLayout framelayout;
    Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        wara = new Kusa(this);
        framelayout = new FrameLayout(this);

        handler = new Handler();
        handler.postDelayed(this, 1000);


        setContentView(framelayout);

        framelayout.addView(wara);

    }

    public void run(){

        wara.x += 10;

    }

}
