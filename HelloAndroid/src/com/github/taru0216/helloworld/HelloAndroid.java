package com.github.taru0216.helloworld;

import com.github.taru0216.helloworld.wifi.HelloWifi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class HelloAndroid extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.hello_android);

    findViewById(R.id.HelloAndroid_ButtonHelloWifi).setOnClickListener(
      new OnClickListener() {
        @Override
        public void onClick(View v) {
          HelloWifi.start(HelloAndroid.this);
        }
      });

  }
}