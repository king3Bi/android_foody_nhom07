package hcmute.nhom7.foody.view.home.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.CustomRecyclerViewAdapter;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.database.QuanDAO;
import hcmute.nhom7.foody.model.Quan;
import hcmute.nhom7.foody.view.MainActivity;
import hcmute.nhom7.foody.view.NavigationActivity;

public class RecentFragment extends Fragment {

    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView;
    private List<Quan> itemList;

    public RecentFragment(List<Quan> quans) {
        this.itemList = quans;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new CustomRecyclerViewAdapter(getContext(), this.itemList));

        return view;
    }

//    private List<Quan> getListData() {
//        List<Quan> list = new ArrayList<Quan>();
//        Quan pumpumtea = new Quan("milktea", "Pum Pum Tea", "Comment comment", "Delivery");
//        Quan korean = new Quan("korean", "Korean Food", "Great!!!", "Review");
//        Quan gogi = new Quan("gogi", "Gogi House", "Great!!!", "Review");
//        Quan koithe = new Quan("koi", "Koi Thé", "Great!!!", "Delivery");
//        Quan hadilao = new Quan("hadilao", "Hadilao", "Great!!!", "Review");
//        Quan quan1 = new Quan("japan", "Sushi House", "Great!!!", "Review");
//        Quan quan2 = new Quan("snowee", "Snowee", "Great!!!", "Delivery");
//        Quan quan3 = new Quan("drink", "Cocktail", "Great!!!", "Review");
//
//        list.add(pumpumtea);
//        list.add(korean);
//        list.add(gogi);
//        list.add(koithe);
//        list.add(hadilao);
//        list.add(quan1);
//        list.add(quan2);
//        list.add(quan3);
//
//        return list;
//
//    }
}