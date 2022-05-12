package hcmute.nhom7.foody.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.ResultSearchFoodAdapter;
import hcmute.nhom7.foody.adapter.ViewPagerAdapterHome;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.database.HomeDAO;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.model.User;

public class HomeFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private View mView;
    private EditText mEdtSearchText;
    private LinearLayout layoutImageViewSearch;
    private LinearLayout layoutContainer;
    private LinearLayout layoutContainResultSearch;
    private ViewPagerAdapterHome adapterHome;
    private HomeDAO homeDAO;
    private Button btnBackHome;
    private User user;
    private List<Restaurant> restaurants;
    private List<Food> foods;

    public HomeFragment(HomeDAO homeDAO, User user) {
        this.homeDAO = homeDAO;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        restaurants = homeDAO.getAllQuan();
        foods = homeDAO.getAllFood();

        layoutContainer = mView.findViewById(R.id.layoutContainer);
        layoutContainResultSearch = mView.findViewById(R.id.layoutContainResultSearch);
        mTabLayout = mView.findViewById(R.id.tablayoutHome);
        mViewPager = mView.findViewById(R.id.viewpagerHome);
        mEdtSearchText = mView.findViewById(R.id.textSearch);
        adapterHome = new ViewPagerAdapterHome(this, user, restaurants, foods, homeDAO);
        mViewPager.setAdapter(adapterHome);

        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText(getString(R.string.restautant));
                    break;
                case 1:
                    tab.setText(getString(R.string.food));
                    break;
            }
        }).attach();

        layoutImageViewSearch = mView.findViewById(R.id.layoutImageViewSearch);
        layoutImageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyWord = mEdtSearchText.getText().toString();
                List<Food> resultSearch = homeDAO.searchMonAn(keyWord);
                layoutContainResultSearch.setVisibility(View.VISIBLE);
                layoutContainer.setVisibility(View.GONE);
                ListView listViewMonAnResult = layoutContainResultSearch.findViewById(R.id.listViewMonAnResult);
                ResultSearchFoodAdapter adapter = new ResultSearchFoodAdapter(getContext(), R.layout.custom_layout_menu, resultSearch, homeDAO, user);
                listViewMonAnResult.setAdapter(adapter);
            }
        });

        btnBackHome = layoutContainResultSearch.findViewById(R.id.btnBackHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutContainResultSearch.setVisibility(View.GONE);
                layoutContainer.setVisibility(View.VISIBLE);
            }
        });

        return mView;
    }
}