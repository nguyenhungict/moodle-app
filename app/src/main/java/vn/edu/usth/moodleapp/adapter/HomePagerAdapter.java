package vn.edu.usth.moodleapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.moodleapp.SystemFragment;
import vn.edu.usth.moodleapp.DashboardFragment;

public class HomePagerAdapter extends FragmentStateAdapter {

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new SystemFragment();
        } else {
            return new DashboardFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
