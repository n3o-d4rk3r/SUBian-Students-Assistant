package bd.edu.sub.subian.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import bd.edu.sub.subian.R;
import bd.edu.sub.subian.utils.CustomTabLayout;


public class ScrollableTabsFragment extends Fragment {

    public RelativeLayout tab_background;
    public CustomTabLayout smartTabLayout;
    public ViewPager viewPager;
    public int tab_count = 2;

    View view;

    public ScrollableTabsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scrollable_tabs, container, false);


        tab_background = view.findViewById(R.id.tab_background);
        smartTabLayout = view.findViewById(R.id.tabs);

        initViewPager();
        return view;
    }


    public void initViewPager() {

            viewPager = view.findViewById(R.id.view_pager);
            viewPager.setOffscreenPageLimit(tab_count);
            viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), tab_count));
            smartTabLayout.post(() -> smartTabLayout.setViewPager(viewPager));
        }


    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        int noOfItems;

        public ViewPagerAdapter(FragmentManager fm, int noOfItems) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.noOfItems = noOfItems;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return new AttendanceFragment();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new ScheduleFragment();
                default:
                    return null;
            }
        }


        // Returns total number of pages
        @Override
        public int getCount() {
            return noOfItems;
        }

        @Override
        public String getPageTitle(int position) {
            switch (position){
                //
                //Your tab titles
                //
                case 0:return "Attandance";
                case 1:return "Schedule";
                default:return null;
            }
        }
    }
    }