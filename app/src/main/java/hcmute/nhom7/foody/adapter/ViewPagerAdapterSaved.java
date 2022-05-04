package hcmute.nhom7.foody.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hcmute.nhom7.foody.view.home.HomeFragment;
import hcmute.nhom7.foody.view.fragment.MoreFragment;
import hcmute.nhom7.foody.view.saved.SavedFragment;
import hcmute.nhom7.foody.view.saved.fragment.AllFragment;
import hcmute.nhom7.foody.view.saved.fragment.BlogFragment;
import hcmute.nhom7.foody.view.saved.fragment.PhotoFragment;
import hcmute.nhom7.foody.view.saved.fragment.PlaceFragment;

public class ViewPagerAdapterSaved extends FragmentStateAdapter {


    public ViewPagerAdapterSaved(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new AllFragment();
            case 1:
                return new PlaceFragment();
            case 2:
                return new PhotoFragment();
            case 3:
                return new BlogFragment();

            default:
                return new AllFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
