package me.everything.overscrolldemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.everything.overscrolldemo.view.GridViewDemoFragment;
import me.everything.overscrolldemo.view.ListViewDemoFragment;
import me.everything.overscrolldemo.view.RecyclerViewDemoFragment;

public class OverScrollDemo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overscroll_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_placeholder, new RecyclerViewDemoFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case R.id.drawer_item_recyclerview_demo:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_placeholder, new RecyclerViewDemoFragment())
                        .commit();
                break;
            case R.id.drawer_item_listview_demo:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_placeholder, new ListViewDemoFragment())
                        .commit();
                break;
            case R.id.drawer_item_gridview_demo:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_placeholder, new GridViewDemoFragment())
                        .commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
