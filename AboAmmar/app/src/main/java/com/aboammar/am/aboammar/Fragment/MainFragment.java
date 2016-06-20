package com.aboammar.am.aboammar.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aboammar.am.aboammar.R;
import com.aboammar.am.aboammar.UI.MainActivity;

/**
 * Created by Mahmoud on 22-May-16.
 */
public class MainFragment extends Fragment {
    MainActivity activity;
    ViewPager viewPager;
    TabLayout tabLayout;
    private static HomeFragment FRAGMENT_HOME;
    private static FavouriteFragment FRAGMENT_FAV;
    String[] titles = new String[2];
    MainAdapter adapter;


    public MainFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapter = new MainAdapter(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#000000"));
        Typeface typeface = Typeface.DEFAULT.createFromAsset(activity.getAssets(),"fonts/Al Mawash Bold.ttf");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(Color.parseColor("#484949"));
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
                if (tabLayout.getSelectedTabPosition() == 0)
                    activity.setHomeTab();
                else
                    activity.setFavTab();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(Color.parseColor("#CBD4D9"));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        final TextView tab1_tv = new TextView(activity);
        tab1_tv.setTypeface(typeface);
        tab1_tv.setText(activity.getString(R.string.tab1));
        tab1_tv.setTextSize(20);
        tab1_tv.setGravity(Gravity.CENTER);
        tabLayout.getTabAt(0).setCustomView(tab1_tv);

        TextView tab2_tv = new TextView(activity);
        tab2_tv.setTypeface(typeface);
        tab2_tv.setText(activity.getString(R.string.tab2));
        tab2_tv.setTextSize(20);
        tab2_tv.setGravity(Gravity.CENTER);
        tab2_tv.setTextColor(Color.parseColor("#CBD4D9"));
        tabLayout.getTabAt(1).setCustomView(tab2_tv);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (tabLayout.getChildAt(position) != null)
                    tabLayout.getChildAt(position).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class MainAdapter extends FragmentStatePagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (FRAGMENT_HOME == null)
                    FRAGMENT_HOME = new HomeFragment();
                return FRAGMENT_HOME;
            } else {
                if (FRAGMENT_FAV == null)
                    FRAGMENT_FAV = new FavouriteFragment();
                return FRAGMENT_FAV;
            }
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
