package com.example.viewpagerexample.viewmodel;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BaseObservable;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagerexample.MainActivity;
import com.example.viewpagerexample.R;
import com.example.viewpagerexample.adaper.ViewPagerAdapter;
import com.example.viewpagerexample.databinding.ActivityMainBinding;
import com.example.viewpagerexample.fragment.CamerFragment;
import com.example.viewpagerexample.fragment.ContactListFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivityVM extends BaseObservable {

    private MainActivity activity;
    private ActivityMainBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.mipmap.ic_camera,
            R.mipmap.ic_contactlist,
         //  R.mipmap.ic_chat_app
    };

    public MainActivityVM(MainActivity activity, ActivityMainBinding binding) {
        this.activity = activity;
        this.binding = binding;

        setTab();
    }

    private void setTab() {
        Toolbar toolbar = binding.toolbar;
        activity.setSupportActionBar(toolbar);
        viewPager = binding.viewPager;
        addTabs(viewPager);
        tabLayout = binding.tabs;
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
       // tabLayout.getTabAt(2).setIcon(tabIcons[2]);

    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        adapter.addFrag(new CamerFragment(), "CAMERA");
        adapter.addFrag(new ContactListFragment(), "CONTACT LIST");
    //   adapter.addFrag(new ChatFragment(), "CHAT");
        viewPager.setAdapter(adapter);

    }





}
