package com.quinnstephens.test.youtubevimeotest.app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.VideoView;


public class VimeoAltActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vimeo_alt);

      WebView webView = (WebView) findViewById(R.id.vimeoWebView);
      WebSettings webSettings = webView.getSettings();
      webSettings.setPluginState(WebSettings.PluginState.ON);
      webSettings.setJavaScriptEnabled(true);
      webSettings.setUseWideViewPort(true);
      webSettings.setLoadWithOverviewMode(true);
      webView.setWebChromeClient(new chromeClient());
      webView.setWebViewClient(new WebViewClient());

      Intent intent = getIntent();
      String id = intent.getStringExtra("tag");
      if (id != null) {
        webView.loadUrl("http://player.vimeo.com/video/" + id);
      }
    }

    public class chromeClient extends WebChromeClient implements MediaPlayer.OnCompletionListener,
            MediaPlayer.OnErrorListener {
      private WebView wv;
      private VideoView mVideoView;
      private LinearLayout mContentView;
      private FrameLayout mCustomViewContainer;
      private WebChromeClient.CustomViewCallback mCustomViewCallback;
      FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new
              FrameLayout.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT,
              ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

      @Override
      public void onShowCustomView(View view, CustomViewCallback callback) {
        if (view instanceof FrameLayout) {
          wv = (WebView)findViewById(R.id.vimeoWebView);
          mCustomViewContainer = (FrameLayout) view;
          mCustomViewCallback = callback;
          mContentView = (LinearLayout)findViewById(R.id.videoContainer);

          if (mCustomViewContainer.getFocusedChild() instanceof VideoView) {
            mVideoView = (VideoView) mCustomViewContainer.getFocusedChild();
            // frame.removeView(video);
            mContentView.setVisibility(View.GONE);
            mCustomViewContainer.setVisibility(View.VISIBLE);
            setContentView(mCustomViewContainer);
            mVideoView.setOnCompletionListener(this);
            mVideoView.setOnErrorListener(this);
            mVideoView.start();

          }
        }
      }

      public void onHideCustomView() {
        if (mVideoView == null){
          return;
        }else{
          // Hide the custom view.
          mVideoView.setVisibility(View.GONE);
          // Remove the custom view from its container.
          mCustomViewContainer.removeView(mVideoView);
          mVideoView = null;
          mCustomViewContainer.setVisibility(View.GONE);
          mCustomViewCallback.onCustomViewHidden();
          // Show the content view.
          mContentView.setVisibility(View.VISIBLE);
        }
      }


      public void onCompletion(MediaPlayer mp) {
        mp.stop();
        mCustomViewContainer.setVisibility(View.GONE);
        onHideCustomView();
        setContentView(mContentView);
      }

      public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
        setContentView(mContentView);
        return true;
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

}
