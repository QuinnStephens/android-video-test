package com.quinnstephens.test.youtubevimeotest.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class VimeoActivity extends VideoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      if (id != null) {
        webView.loadUrl("http://player.vimeo.com/video/" + id + "?badge=0&byline=0&portrait=0&title=0");
      }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vimeo_alt, menu);
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

    @Override
    public void onBackPressed()
    {
      // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
      if (!webChromeClient.onBackPressed())
      {
        if (webView.canGoBack())
        {
          webView.goBack();
        }
        else
        {
          // Close app (presumably)
          super.onBackPressed();
        }
      }
    }

}
