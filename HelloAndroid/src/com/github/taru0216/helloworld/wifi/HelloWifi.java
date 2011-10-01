package com.github.taru0216.helloworld.wifi;

import java.util.List;

import com.github.taru0216.helloworld.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewSwitcher;

public class HelloWifi extends Activity {
  private WifiManager mWifiManager;
  private ListView mScanResults;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.wifi_hello_wifi);

    mScanResults = (ListView) findViewById(R.id.WifiHelloWifi_Scanresults);
    mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
  }

  @Override
  protected void onResume() {
    super.onResume();
    scanResults();
  }


  private void showWifiNotFoundView() {
    ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.WifiHelloWifi_Switcher);
    switcher.setDisplayedChild(switcher.indexOfChild(switcher
      .findViewById(R.id.WifiHelloWifi_WifiNotFound)));
  }

  private void scanResults() {
    List<ScanResult> scanResults = mWifiManager.getScanResults();
    if (scanResults != null) {
      mScanResults.setAdapter(new WifiAdapter(this,
        R.layout.wifi_hello_wifi_scanresult, -1, scanResults));
    } else {
      showWifiNotFoundView();
    }
  }

  private class WifiAdapter extends ArrayAdapter<ScanResult> {

    public WifiAdapter(Context context, int resource, int textViewResourceId,
        List<ScanResult> objects) {
      super(context, resource, textViewResourceId, objects);
      // TODO Auto-generated constructor stub
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext()
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(
          R.layout.wifi_hello_wifi_scanresult, parent);
      }
      return convertView;
    }
  }

  public static void start(Context context) {
    Intent intent = new Intent();
    intent.setClass(context, HelloWifi.class);
    context.startActivity(intent);
  }
}