package com.nerdos.android.commentpic.view.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import commentpic.android.nerdos.com.commentpic.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mToolbarLayout;
    private FloatingActionButton mFab;
    private CoordinatorLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(onCameraClicked);

        mToolbarLayout.setTitle("1 2 3 Probando");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO refresh grid
    }

    private View.OnClickListener onCameraClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //open the native camera app
            //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            //startActivity(intent);
            Snackbar.make(mLayout, "Snackbar!", Snackbar.LENGTH_LONG)
                    //.setAction(R.string.snackbar_action, myOnClickListener)
                    .show(); // Don’t forget to show!
        }
    };



    // ---- Menu ---- //

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
