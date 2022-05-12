package hcmute.nhom7.foody.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.view.home.fragment.FoodFragment;
import hcmute.nhom7.foody.view.home.fragment.RestaurantFragment;

public class ViewPagerAdapterHome extends FragmentStateAdapter {
    private List<Restaurant> restaurants;
    private List<Food> foods;

    public ViewPagerAdapterHome(@NonNull Fragment fragment, List<Restaurant> restaurants, List<Food> foods) {
        super(fragment);
        this.restaurants = restaurants;
        this.foods = foods;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
//                return new RecentFragment(quans);
                return new RestaurantFragment(restaurants);
            case 1:
//                return new NearByFragment();
                return new FoodFragment(foods);

            default:
                return new RestaurantFragment(restaurants);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
