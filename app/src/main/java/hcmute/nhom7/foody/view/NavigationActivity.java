package hcmute.nhom7.foody.view;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.databinding.LayoutNavigationBinding;
import hcmute.nhom7.foody.view.fragment.HistoryFragment;
import hcmute.nhom7.foody.view.home.HomeFragment;
import hcmute.nhom7.foody.view.fragment.MoreFragment;
import hcmute.nhom7.foody.view.saved.SavedFragment;

public class NavigationActivity extends AppCompatActivity {

    LayoutNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.menu_saved:
                    replaceFragment(new SavedFragment());
                    break;
                case R.id.menu_history:
                    replaceFragment(new HistoryFragment());
                    break;
                case R.id.menu_more:
                    replaceFragment(new MoreFragment());
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
