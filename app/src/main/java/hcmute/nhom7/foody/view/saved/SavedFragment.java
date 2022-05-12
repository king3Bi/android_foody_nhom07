package hcmute.nhom7.foody.view.saved;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.SavedAdapter;
import hcmute.nhom7.foody.adapter.ViewPagerAdapterHome;
import hcmute.nhom7.foody.adapter.ViewPagerAdapterSaved;
import hcmute.nhom7.foody.database.SavedDAO;
import hcmute.nhom7.foody.model.Saved;
import hcmute.nhom7.foody.model.User;

public class SavedFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private View mView;
//    private ViewPagerAdapterSaved adapterSaved;
    private SavedAdapter adapterSaved;
    private SavedDAO savedDAO;
    private User user;
    private List<Saved> savedList;
    private ListView listViewSaved;

    public SavedFragment(SavedDAO savedDAO, User user) {
        this.savedDAO = savedDAO;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_saved, container, false);

        savedList = savedDAO.getAllFoodSavedOfUser(user.getId());

//        mTabLayout =mView.findViewById(R.id.tablayoutSaved);
//        mViewPager = mView.findViewById(R.id.viewpagerSaved);
//        adapterSaved = new ViewPagerAdapterSaved(this);
        adapterSaved = new SavedAdapter(getContext(), R.layout.custom_layout_menu, savedList, savedDAO, user, listViewSaved);
//        mViewPager.setAdapter(adapterSaved);
        listViewSaved = mView.findViewById(R.id.listViewSaved);
        listViewSaved.setAdapter(adapterSaved);

//        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
//            switch (position){
//                case 0:
//                    tab.setText(getString(R.string.all));
//                    break;
//                case 1:
//                    tab.setText(getString(R.string.places));
//                    break;
//                case 2:
//                    tab.setText(getString(R.string.photos));
//                    break;
//                case 3:
//                    tab.setText(getString(R.string.blogs));
//                    break;
//            }
//        }).attach();
        return mView;
    }

    public void loadDataListView() {
        listViewSaved.invalidateViews();
    }

}