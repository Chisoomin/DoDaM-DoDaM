package com.example.dodamver2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class AdapterforTab extends FragmentPagerAdapter {

    int mNumOfTabs; //탭의 갯수

    public AdapterforTab(FragmentManager fm, int numTabs) {
        super(fm);
        this.mNumOfTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                CalenD tab1 = new CalenD();
                return tab1;
            case 1:
                HabitTracker tab2 = new HabitTracker();
                return tab2;
            case 2:
                MainScreen tab3 = new MainScreen();
                return tab3;
            case 3:
                Statistics tab4 = new Statistics();
                return tab4;
            case 4:
                ForYou tab5 = new ForYou();
                return tab5;
            default:
                return null;
        }
        //return null;


    }




    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}