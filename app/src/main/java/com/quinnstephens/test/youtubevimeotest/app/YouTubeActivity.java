package com.quinnstephens.test.youtubevimeotest.app;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;


public class YouTubeActivity extends VideoActivity {

  private static final int REQ_START_STANDALONE_PLAYER = 1;
  private static final int REQ_RESOLVE_SERVICE_MISSING = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      Intent intent = null;

      if (id != null) {
         intent = YouTubeStandalonePlayer.createVideoIntent(this, DeveloperKey.DEVELOPER_KEY, id + "a", 200, false, false);
      }

      if (intent != null) {
        if (canResolveIntent(intent)) {
          startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
        } else {
          // Could not resolve the intent - must need to install or update the YouTube API service.
          YouTubeInitializationResult.SERVICE_MISSING
                  .getErrorDialog(this, REQ_RESOLVE_SERVICE_MISSING).show();
        }
      }
    }

    private boolean canResolveIntent(Intent intent) {
      List<ResolveInfo> resolveInfo = getPackageManager().queryIntentActivities(intent, 0);
      return resolveInfo != null && !resolveInfo.isEmpty();
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
    public void onBackPressed(){
      super.onBackPressed();
    }

}
