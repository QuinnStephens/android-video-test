package com.quinnstephens.test.youtubevimeotest.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;


public class VimeoAltActivity extends ActionBarActivity {

    private VideoEnabledWebView webView;
    private VideoEnabledWebChromeClient webChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vimeo_alt);

      // Save the web view
      webView = (VideoEnabledWebView) findViewById(R.id.webView);

      // Initialize the VideoEnabledWebChromeClient and set event handlers
      View nonVideoLayout = findViewById(R.id.nonVideoLayout); // Your own view, read class comments
      ViewGroup videoLayout = (ViewGroup) findViewById(R.id.videoLayout); // Your own view, read class comments
      View loadingView = getLayoutInflater().inflate(R.layout.video_loading_progress, null); // Your own view, read class comments
      webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView) // See all available constructors...
      {
        // Subscribe to standard events, such as onProgressChanged()...
        @Override
        public void onProgressChanged(WebView view, int progress)
        {
          // Your code...
        }
      };
      webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
      {
        @Override
        public void toggledFullscreen(boolean fullscreen)
        {
          // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
          if (fullscreen)
          {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            getWindow().setAttributes(attrs);
            if (android.os.Build.VERSION.SDK_INT >= 14)
            {
              getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
          }
          else
          {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            getWindow().setAttributes(attrs);
            if (android.os.Build.VERSION.SDK_INT >= 14)
            {
              getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
          }

        }
      });
      webView.setWebChromeClient(webChromeClient);

      // Navigate everywhere you want, this classes have only been tested on YouTube's mobile site
      String id = getIntent().getStringExtra("tag");
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
