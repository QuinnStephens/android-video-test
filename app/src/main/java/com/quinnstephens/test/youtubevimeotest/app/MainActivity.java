package com.quinnstephens.test.youtubevimeotest.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout youTubeLink = (LinearLayout) findViewById(R.id.youtubelink);
        triggerActivity(youTubeLink, YouTubeActivity.class);

        LinearLayout vimeoLink = (LinearLayout) findViewById(R.id.vimeolink);
        triggerActivity(vimeoLink, VimeoActivity.class);

        LinearLayout youTubeLink2 = (LinearLayout) findViewById(R.id.youtubelink2);
        triggerActivity(youTubeLink2, YouTubeActivity.class);

        LinearLayout vimeoLink2 = (LinearLayout) findViewById(R.id.vimeolink2);
        triggerActivity(vimeoLink2, VimeoActivity.class);
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

}
