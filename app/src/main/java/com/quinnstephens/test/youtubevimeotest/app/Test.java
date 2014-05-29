package com.quinnstephens.test.youtubevimeotest.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Test extends Activity {

    HTML5WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new HTML5WebView(this);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {    
            mWebView.loadUrl("http://player.vimeo.com/video/27244727");
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
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.inCustomView()) {
                mWebView.hideCustomView();
            //  mWebView.goBack();
                //mWebView.goBack();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
