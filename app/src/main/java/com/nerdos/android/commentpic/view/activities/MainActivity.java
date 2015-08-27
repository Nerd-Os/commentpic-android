package com.nerdos.android.commentpic.view.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import commentpic.android.nerdos.com.commentpic.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigationView();
        setupToolbar();
        setupFab();
        createDummyList();
    }

    private void setupFab() {
        mFab = (FloatingActionButton) findViewById(R.id.camera_fab);
        mFab.setOnClickListener(onCameraClicked);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO refresh grid

        if(requestCode==7676) {
            Snackbar.make(findViewById(R.id.coordinatorLayout), "Snackbar!", Snackbar.LENGTH_LONG)
                    //.setAction(R.string.snackbar_action, myOnClickListener)
                    .show(); // Don’t forget to show!
        }
    }

    // ---- Actions ---- //

    private View.OnClickListener onCameraClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //open the native camera app
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 7676);
        }
    };

    private static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public DataViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    // ---- Data ---- //

    private void createDummyList() {
        final ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<50; i++) {
            list.add("Item " + (i+1));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecyclerView.Adapter<DataViewHolder>() {


            @Override
            public DataViewHolder onCreateViewHolder(ViewGroup parent, int position) {
                View itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, null);
                return new DataViewHolder(itemLayoutView);
            }

            @Override
            public void onBindViewHolder(DataViewHolder viewHolder, int i) {
                viewHolder.textView.setText(list.get(i));
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });
    }


    // ---- Menu ---- //

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {
            mDrawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                mDrawer,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawerToggle.syncState();
    }
}
