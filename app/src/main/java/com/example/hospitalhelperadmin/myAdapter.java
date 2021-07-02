package com.example.hospitalhelperadmin;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class myAdapter extends FragmentPagerAdapter {

    int totalTabs;
    private Context myContext;

    public myAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RequestBlood requestBlood = new RequestBlood();
                return requestBlood;
            case 1:
                DonateBlood donateBlood = new DonateBlood();
                return donateBlood;
            case 2:
                Appoinments appoinments = new Appoinments();
                return appoinments;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
