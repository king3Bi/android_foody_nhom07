package hcmute.nhom7.foody.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import hcmute.nhom7.foody.database.HomeDAO;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.view.home.fragment.FoodFragment;
import hcmute.nhom7.foody.view.home.fragment.RestaurantFragment;

public class ViewPagerAdapterHome extends FragmentStateAdapter {
    private List<Restaurant> restaurants;
    private List<Food> foods;
    private User user;
    private HomeDAO homeDAO;

    public ViewPagerAdapterHome(@NonNull Fragment fragment, User user, List<Restaurant> restaurants, List<Food> foods, HomeDAO homeDAO) {
        super(fragment);
        this.restaurants = restaurants;
        this.user = user;
        this.foods = foods;
        this.homeDAO = homeDAO;
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
                return new FoodFragment(user, foods, homeDAO);

            default:
                return new RestaurantFragment(restaurants);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
