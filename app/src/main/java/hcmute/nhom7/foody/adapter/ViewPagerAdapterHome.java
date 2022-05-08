package hcmute.nhom7.foody.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import hcmute.nhom7.foody.model.Quan;
import hcmute.nhom7.foody.view.home.fragment.NearByFragment;
import hcmute.nhom7.foody.view.home.fragment.RecentFragment;

public class ViewPagerAdapterHome extends FragmentStateAdapter {
    private List<Quan> quans;

    public ViewPagerAdapterHome(@NonNull Fragment fragment, List<Quan> quans) {
        super(fragment);
        this.quans = quans;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new RecentFragment(quans);
            case 1:
                return new NearByFragment();

            default:
                return new RecentFragment(quans);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
