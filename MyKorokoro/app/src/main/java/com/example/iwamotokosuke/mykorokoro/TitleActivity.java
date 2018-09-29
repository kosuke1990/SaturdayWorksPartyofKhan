package com.example.iwamotokosuke.mykorokoro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TitleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Button gyroButton = (Button) findViewById(R.id.main_button);
        gyroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplication(), MainActivity.class);
                startActivity(intent2);
            }
        });

        Button gyroButton2 = (Button) findViewById(R.id.main_button_2);
        gyroButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplication(), ZassouActivity.class);
                startActivity(intent3);
            }
        });

        Button tableButton = (Button) findViewById(R.id.main_button_3);
        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplication(), MainActivity3.class);
                startActivity(intent4);
            }
        });
    }
}