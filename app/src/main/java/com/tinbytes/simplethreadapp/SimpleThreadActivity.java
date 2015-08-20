package com.tinbytes.simplethreadapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SimpleThreadActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_thread);

    final TextView tvCounter = (TextView) findViewById(R.id.tvCounter);
    new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while (i < 100) {
          SystemClock.sleep(250);
          i++;
          final int curCount = i;
          if (curCount % 5 == 0) { // update UI with progress every 5%
            tvCounter.post(new Runnable() {
              public void run() {
                tvCounter.setText(curCount + "% Complete!");
              }
            });
          }
        }
        tvCounter.post(new Runnable() {
          public void run() {
            tvCounter.setText("Count Complete!");
          }
        });
      }
    }).start();
  }
}


