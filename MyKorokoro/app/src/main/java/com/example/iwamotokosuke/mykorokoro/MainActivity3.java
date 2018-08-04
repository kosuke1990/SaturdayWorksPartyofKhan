package com.example.iwamotokosuke.mykorokoro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        // ドラッグ対象Viewとイベント処理クラスを紐付ける
        ImageView dragView = (ImageView) findViewById(R.id.imageView1);
        DragViewListener listener = new DragViewListener(dragView);
        dragView.setOnTouchListener(listener);

        TextView textView = (TextView) findViewById(R.id.DataX);
        textView.setText(String.valueOf(listener.oldx));

    }
}