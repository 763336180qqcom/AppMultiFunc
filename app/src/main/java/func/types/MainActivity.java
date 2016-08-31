package func.types;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import func.ndk_.NdkActivity;
import func.types.adapter.HomeListAdapter;
import func.types.browser.BrowserActivity;
import func.types.json.JsonActivity;
import func.types.socket_.SocketClientActivity;
import func.types.ui.ProgressActivity;
import func.types.ui.sf.SurfaceActivity;
import func.types.variable_.Filter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFloatButton;
    private RecyclerView mRecyclerView;
    private List<String> mLabelNames = new ArrayList<>();
    private HomeListAdapter mAdapter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));//Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
        setContentView(R.layout.activity_main);
        iniData();
        iniViews();
        Log.i("LOG_WATCHER", "onCreate:");

    }

    private void iniData() {
        mLabelNames.clear();
        int a = 0;
        while (a < 5) {
            mLabelNames.add(getString(R.string.pgs));
            ++a;
        }
        while (a < 10) {
            mLabelNames.add(getString(R.string.start_browser));
            ++a;
        }
        while (a < 15) {
            mLabelNames.add(getString(R.string.ndk));
            ++a;
        }
        while (a < 20) {
            mLabelNames.add(getString(R.string.socket));
            ++a;
        }
        while (a < 25) {
            mLabelNames.add(getString(R.string.socket));
            ++a;
        }
        while (a < 30) {
            mLabelNames.add(getString(R.string.json));
            ++a;
        }
        while (a < 40) {
            mLabelNames.add(getString(R.string.sf));
            ++a;
        }
        mAdapter = new HomeListAdapter(mContext, mLabelNames);
    }

    private HomeListAdapter.CustomItemClickListener mListener = new HomeListAdapter.CustomItemClickListener() {
        @Override
        public void _click(View v, int position) {
            for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
                Random random = new Random();
                mRecyclerView.getChildAt(i).findViewById(R.id.id_func).setBackgroundColor(Color.rgb(random.nextInt(255) + 1, random.nextInt(255) + 1, random.nextInt(255) + 1));
            }
        }

        @Override
        public void _longClick(View v, int position) {
            switch (((TextView) v).getText().toString()) {
                case "progressbar":
                    Intent intent = new Intent(mContext, ProgressActivity.class);
                    startActivity(intent);
                    break;
                case "browser":
                    Intent intent1 = new Intent(mContext, BrowserActivity.class);
                    startActivity(intent1);
                    break;
                case "ndk":
                    Intent intent2 = new Intent(mContext, NdkActivity.class);
                    startActivity(intent2);
                    break;
                case "socket":
                    Intent intent3 = new Intent(mContext, SocketClientActivity.class);
                    startActivity(intent3);
                    break;
                case "json":
                    Intent intent4 = new Intent(mContext, JsonActivity.class);
                    startActivity(intent4);
                    break;
                case "sf":
                    Intent intent5 = new Intent(mContext, SurfaceActivity.class);
                    startActivity(intent5);
                    break;
            }
        }
    };

    private void iniViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFloatButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatButton.setOnClickListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        mRecyclerView.setLayoutManager
                (new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL
                ));
        mAdapter.setCustomItemClickListener(mListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridItemDecoration(mContext, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Handle the camera action

        } else if (id == R.id.nav_pgs) {
            Intent intent = new Intent(mContext, ProgressActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, Filter.removeRedundantString("aabbbtqqdda"), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
/*
                Snackbar.make(view, Str_Ini.OutPuts(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
*/
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("LOG_WATCHER", "onConfigurationChanged:");

    }
}
