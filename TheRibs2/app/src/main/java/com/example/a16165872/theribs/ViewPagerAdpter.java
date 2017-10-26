package com.example.a16165872.theribs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 16165872 on 19/10/2017.
 */

public class ViewPagerAdpter extends FragmentPagerAdapter {


    public ViewPagerAdpter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;
            case 1:
                LoginFragment loginFragment2 = new LoginFragment();
                return loginFragment2;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Informações";
            case 1:
                return "Avaliação";
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
