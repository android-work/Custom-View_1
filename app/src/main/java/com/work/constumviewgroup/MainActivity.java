package com.work.constumviewgroup;

import android.graphics.Color;
import android.service.autofill.TextValueSanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] datas = new String[]{"美食","旅行","生你好久哦对您几可忽略可靠连接活","小技巧","健身","汽车","广告","动画","创意","灵感","当下乱码","一条日食记","视知TV"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutViewGroup lvg = findViewById(R.id.lvg);
        for (int i = 0;i<datas.length;i++){
            Random random = new Random();
            int r = random.nextInt(3);
            Button textView = new Button(this);
            textView.setPadding(5,5,5,5);
            Log.e("tag","r:"+r);
            textView.setBackgroundResource(R.drawable.text_bg);
//            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            textView.setLayoutParams(layoutParams);

            textView.setText(datas[i]);
            textView.setTextSize(15);
            textView.setId(i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case 0:
                            Log.e("tag","点击了:美食"+v.getId());
                            break;
                        case 1:
                            Log.e("tag","点击了:旅行"+v.getId());
                            break;
                        case 2:
                            Log.e("tag","点击了:生你好久哦对您几可忽略可靠连接活"+v.getId());
                            break;
                        case 3:
                            Log.e("tag","点击了:小技巧"+v.getId());
                            break;
                        case 4:
                            Log.e("tag","点击了:健身"+v.getId());
                            break;
                        case 5:
                            Log.e("tag","点击了:汽车"+v.getId());
                            break;
                        case 6:
                            Log.e("tag","点击了:广告"+v.getId());
                            break;
                        case 7:
                            Log.e("tag","点击了:动画"+v.getId());
                            break;
                        case 8:
                            Log.e("tag","点击了:创意"+v.getId());
                            break;
                        case 9:
                            Log.e("tag","点击了:灵感"+v.getId());
                            break;
                        case 10:
                            Log.e("tag","点击了:当下乱码"+v.getId());
                            break;
                        case 11:
                            Log.e("tag","点击了:一条日食记"+v.getId());
                            break;
                        case 12:
                            Log.e("tag","点击了:视知TV"+v.getId());
                            break;
                    }
                }
            });

            lvg.addView(textView);
        }
    }
}
