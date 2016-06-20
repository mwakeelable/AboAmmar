package com.aboammar.am.aboammar.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;

import com.aboammar.am.aboammar.Fragment.MainFragment;
import com.aboammar.am.aboammar.Model.CustomTypefaceSpan;
import com.aboammar.am.aboammar.R;
import com.aboammar.am.aboammar.SubFragment.AboutFragment;
import com.aboammar.am.aboammar.SubFragment.AccessoriesFragment;
import com.aboammar.am.aboammar.SubFragment.AccountFragment;
import com.aboammar.am.aboammar.SubFragment.ElectronicFragment;
import com.aboammar.am.aboammar.SubFragment.LaptopFragment;
import com.aboammar.am.aboammar.SubFragment.MobileFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    ActionBar mActionBar;
    public FrameLayout mFrameLayout;
    NavigationView mNavigationView;
    CoordinatorLayout mCoordinatorLayout;
    public int current_tab = -1;
    public static final int FRAGMENT_HOME = 1;
    public static final int FRAGMENT_FAV = 2;
    MainFragment FRAGMENT_MAIN;
    AboutFragment FRAGMENT_ABOUT;
    AccessoriesFragment FRAGMENT_ACCESSORIES;
    AccountFragment FRAGMENT_ACCOUNT;
    ElectronicFragment FRAGMENT_ELECTRONICS;
    LaptopFragment FRAGMENT_LAPTOP;
    MobileFragment FRAGMENT_MOBILE;
    Menu nav_logout, nav_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFrameLayout = (FrameLayout) findViewById(R.id.containerView);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu m = mNavigationView.getMenu();
        for (int i=0;i<m.size();i++){
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
        mNavigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {
                     @Override
                     public boolean onNavigationItemSelected(MenuItem item) {
                         switch (item.getItemId()) {
                             case R.id.nav_main:
                                 drawMainFragment();
                                 mDrawerLayout.closeDrawer(GravityCompat.START);
                                 break;
                             case R.id.nav_item_1:
                                 //Draw mobile fragment
                                 drawMobileFragment();
                                 mDrawerLayout.closeDrawer(GravityCompat.START);
                                 break;
                             case R.id.nav_item_2:
                                 //Draw laptop fragment
                                 drawLaptopFragment();
                                 mDrawerLayout.closeDrawer(GravityCompat.START);
                                 break;
                             case R.id.nav_item_3:
                                 //Draw electronics fragment
                                 drawElectronicsFragment();
                                 mDrawerLayout.closeDrawer(GravityCompat.START);
                                 break;
                             case R.id.nav_item_4:
                                 //Draw Accessories fragment
                                 drawAccessoriesFragment();
                                 mDrawerLayout.closeDrawer(GravityCompat.START);
                                 break;
                             case R.id.nav_item_5:
                                 //Draw About fragment
                                 drawAboutFragment();
                                 mDrawerLayout.closeDrawer(GravityCompat.START);
                                 break;
                             case R.id.nav_item_6:
                                 //Draw Account fragment
                                 drawAccountFragment();
                                 mDrawerLayout.closeDrawer(GravityCompat.START);
                                 break;
                             case R.id.nav_item_7:
                                 //logout
                                 break;
                         }
                         return true;
                     }
                 }
                );
        nav_logout = mNavigationView.getMenu();
        nav_account = mNavigationView.getMenu();
        nav_logout.findItem(R.id.nav_item_7).setVisible(false);
        nav_account.findItem(R.id.nav_item_6).setVisible(false);
        drawMainFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.search:
                //Search Action
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setHomeTab() {
        current_tab = FRAGMENT_HOME;
        Log.d("current_tab", FRAGMENT_HOME + "");
    }

    public void setFavTab() {
        current_tab = FRAGMENT_FAV;
        Log.d("current_tab", FRAGMENT_FAV + "");
    }

    public void drawMainFragment() {
        FRAGMENT_MAIN = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mFrameLayout.getChildCount() == 0) {
            transaction.replace(R.id.containerView, FRAGMENT_MAIN);
        } else {
            transaction.add(R.id.containerView, FRAGMENT_MAIN);
        }
        transaction.commit();
    }

    public void drawMobileFragment() {
        FRAGMENT_MOBILE = new MobileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerView, FRAGMENT_MOBILE);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void drawLaptopFragment() {
        FRAGMENT_LAPTOP = new LaptopFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerView, FRAGMENT_LAPTOP);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void drawElectronicsFragment() {
        FRAGMENT_ELECTRONICS = new ElectronicFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerView, FRAGMENT_ELECTRONICS);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void drawAccessoriesFragment() {
        FRAGMENT_ACCESSORIES = new AccessoriesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerView, FRAGMENT_ACCESSORIES);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void drawAboutFragment() {
        FRAGMENT_ABOUT = new AboutFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerView, FRAGMENT_ABOUT);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void drawAccountFragment() {
        FRAGMENT_ACCOUNT = new AccountFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerView, FRAGMENT_ACCOUNT);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void login(View v) {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        this.startActivity(loginIntent);
    }

    public void register(View v) {
        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
        this.startActivity(registerIntent);
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/MO_Nawel.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
