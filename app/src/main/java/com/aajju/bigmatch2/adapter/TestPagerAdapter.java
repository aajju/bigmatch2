package com.aajju.bigmatch2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aajju.bigmatch2.fragment.BaseBallFragment;
import com.aajju.bigmatch2.fragment.ESportsFragment;
import com.aajju.bigmatch2.fragment.MainFragment;
import com.aajju.bigmatch2.fragment.OtherSportsFragment;

/**
 * Created by aajju on 2017-01-26.
 */

public class TestPagerAdapter extends FragmentPagerAdapter {

    private static final String [] PAGE_TITLE = new String[]{"축구","야구","e스포츠", "기타"};

    public TestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0 :
                fragment = MainFragment.newInstance("1번 프레그먼트 입니다.");
                break;
            case 1 :
                fragment = BaseBallFragment.newInstance("2번 프레그먼트 입니다.");
                break;
            case 2:
                fragment = ESportsFragment.newInstance("3번 프레그먼트 입니다.");
                break;
            case 3:
                fragment = OtherSportsFragment.newInstance("4번 프레그먼트 입니다.");
                break;
            default:
                fragment = MainFragment.newInstance("디폴트");
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLE[position];
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
