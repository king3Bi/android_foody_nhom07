package hcmute.nhom7.foody.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hcmute.nhom7.foody.view.home.fragment.NearByFragment;
import hcmute.nhom7.foody.view.home.fragment.RecentFragment;

public class ViewPagerAdapterHome extends FragmentStateAdapter {


    public ViewPagerAdapterHome(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new RecentFragment();
            case 1:
                return new NearByFragment();

            default:
                return new RecentFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
