package hcmute.nhom7.foody.view.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.CustomRecyclerFoodViewAdapter;
import hcmute.nhom7.foody.database.HomeDAO;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.model.User;

public class FoodFragment extends Fragment {

    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView;
    private User user;
    private HomeDAO homeDAO;
    List<Food> itemList;

    public FoodFragment(User user, List<Food> foodList, HomeDAO homeDAO) {
        this.user = user;
        this.itemList = foodList;
        this.homeDAO = homeDAO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_by, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNearBy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new CustomRecyclerFoodViewAdapter(getContext(), this.itemList, user, homeDAO));

        return view;
    }

    private List<Restaurant> getListData() {
        List<Restaurant> list = new ArrayList<Restaurant>();

        return list;

    }
}