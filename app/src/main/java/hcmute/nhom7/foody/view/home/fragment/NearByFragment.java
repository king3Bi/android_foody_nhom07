package hcmute.nhom7.foody.view.home.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.CustomRecyclerViewAdapter;
import hcmute.nhom7.foody.model.Restaurant;

public class NearByFragment extends Fragment {

    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView;
    List<Restaurant> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_by, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNearBy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new CustomRecyclerViewAdapter(getContext(), getListData()));

        return view;
    }

    private List<Restaurant> getListData() {
        List<Restaurant> list = new ArrayList<Restaurant>();
        Restaurant pumpumtea = new Restaurant("milktea", "Pum Pum Tea", "Comment comment", "Delivery");
        Restaurant korean = new Restaurant("korean", "Korean Food", "Great!!!", "Review");
        Restaurant gogi = new Restaurant("gogi", "Gogi House", "Great!!!", "Review");
        Restaurant koithe = new Restaurant("koi", "Koi Thé", "Great!!!", "Review");
        Restaurant hadilao = new Restaurant("hadilao", "Hadilao", "Great!!!", "Review");
        Restaurant restaurant1 = new Restaurant("japan", "Sushi House", "Great!!!", "Review");
        Restaurant restaurant2 = new Restaurant("snowee", "Snowee", "Great!!!", "Review");
        Restaurant restaurant3 = new Restaurant("drink", "Cocktail", "Great!!!", "Review");

        list.add(pumpumtea);
        list.add(korean);
        list.add(gogi);
        list.add(koithe);
        list.add(hadilao);
        list.add(restaurant1);
        list.add(restaurant2);
        list.add(restaurant3);

        return list;

    }
}