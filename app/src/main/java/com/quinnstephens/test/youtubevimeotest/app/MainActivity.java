package com.quinnstephens.test.youtubevimeotest.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;


public class MainActivity extends ActionBarActivity {
  private static final int REQ_START_STANDALONE_PLAYER = 1;
  private static final int REQ_RESOLVE_SERVICE_MISSING = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      LinearLayout youTubeLink = (LinearLayout) findViewById(R.id.youtubelink);
      triggerYouTubePlayer(youTubeLink, this);

      LinearLayout vimeoLink = (LinearLayout) findViewById(R.id.vimeolink);
      triggerActivity(vimeoLink, VimeoActivity.class);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
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

  public void triggerYouTubePlayer(View view, final Activity thisActivity){
    View.OnClickListener handler = new View.OnClickListener() {
      public void onClick(View v) {
        String id = (String)v.getTag();
        Intent intent = null;

        if (id != null) {
          intent = YouTubeStandalonePlayer.createVideoIntent(thisActivity, DeveloperKey.DEVELOPER_KEY, id, 0, false, false);
        }

        if (intent != null) {
          if (canResolveIntent(intent)) {
            startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
          } else {
            // Could not resolve the intent - must need to install or update the YouTube API service.
            YouTubeInitializationResult.SERVICE_MISSING
                    .getErrorDialog(thisActivity, REQ_RESOLVE_SERVICE_MISSING).show();
          }
        }
      }
    };

    view.setOnClickListener(handler);
  }

  public void triggerActivity(View view, final Class activityClass) {
      View.OnClickListener handler = new View.OnClickListener() {
          public void onClick(View v) {
              Intent intent = new Intent(getBaseContext(), activityClass);
              intent.putExtra("tag", (String)v.getTag());

              startActivity(intent);
          }
      };

      view.setOnClickListener(handler);
  }

  private boolean canResolveIntent(Intent intent) {
    List<ResolveInfo> resolveInfo = getPackageManager().queryIntentActivities(intent, 0);
    return resolveInfo != null && !resolveInfo.isEmpty();
  }


}
