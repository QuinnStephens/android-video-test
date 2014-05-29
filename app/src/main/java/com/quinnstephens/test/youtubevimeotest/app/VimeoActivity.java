package com.quinnstephens.test.youtubevimeotest.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class VimeoActivity extends ActionBarActivity {

    HTML5WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();

        mWebView = new HTML5WebView(this);

        if (savedInstanceState != null) {
          mWebView.restoreState(savedInstanceState);

        } else {
          String id = intent.getStringExtra("tag");
          if (id != null) {
            mWebView.loadUrl("http://player.vimeo.com/video/" + id);
          }
        }

        setContentView(mWebView.getLayout());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      mWebView.saveState(outState);
    }

    @Override
    public void onStop() {
      super.onStop();
      mWebView.stopLoading();
      mWebView.hideCustomView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
