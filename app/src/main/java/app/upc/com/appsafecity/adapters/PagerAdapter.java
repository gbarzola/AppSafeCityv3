package app.upc.com.appsafecity.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.upc.com.appsafecity.fragments.PrimerFragment;
import app.upc.com.appsafecity.fragments.SegundoFragment;
import app.upc.com.appsafecity.fragments.TercerFragment;

public class PagerAdapter extends FragmentPagerAdapter{

    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PrimerFragment tab1 = new PrimerFragment();
                return tab1;
            case 1:
                SegundoFragment tab2 = new SegundoFragment();
                return tab2;
            case 2:
                TercerFragment tab3 = new TercerFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
