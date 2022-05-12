package hcmute.nhom7.foody.view;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.databinding.LayoutNavigationBinding;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ImageUtils;
import hcmute.nhom7.foody.view.cart.CartFragment;
import hcmute.nhom7.foody.view.fragment.HistoryFragment;
import hcmute.nhom7.foody.view.home.HomeFragment;
import hcmute.nhom7.foody.view.fragment.MoreFragment;
import hcmute.nhom7.foody.view.profile.ProfileActivity;
import hcmute.nhom7.foody.view.saved.SavedFragment;

public class NavigationActivity extends AppCompatActivity {
    public static final int REQUEST_GET_PROFILE = 0;

    private User user;
    LayoutNavigationBinding binding;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(NavigationActivity.this);

        user = (User) getIntent().getSerializableExtra("user");

        if (user == null) {
            Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        binding = LayoutNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment(db, user), "homeFragment");

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    replaceFragment(new HomeFragment(db, user), "homeFragment");
                    break;
//                case R.id.menu_saved:
//                    replaceFragment(new SavedFragment(db, user), "savedFragment");
//                    break;
                case R.id.menu_cart:
                    replaceFragment(new CartFragment(db, user), "cartFragment");
                    break;
                case R.id.menu_history:
                    replaceFragment(new HistoryFragment(db, user), "historyFragment");
                    break;
                case R.id.menu_more:
                    replaceFragment(new MoreFragment(db, user), "moreFragment");
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, tag);
        fragmentTransaction.commit();
    }

    private void insertQuanData() {
        String imgPumpum = ImageUtils.encodeImg(ImageUtils.loadImage(this, "milktea"));
        Restaurant pumpumtea = new Restaurant(imgPumpum, "Pum Pum Tea", "Comment comment", "Delivery");
        String imgKorean = ImageUtils.encodeImg(ImageUtils.loadImage(this, "korean"));
        Restaurant korean = new Restaurant(imgKorean, "Korean Food", "Great!!!", "Review");
        String imgGogi = ImageUtils.encodeImg(ImageUtils.loadImage(this, "gogi"));
        Restaurant gogi = new Restaurant(imgGogi, "Gogi House", "Great!!!", "Review");
        String imgkoithe = ImageUtils.encodeImg(ImageUtils.loadImage(this, "koi"));
        Restaurant koithe = new Restaurant(imgkoithe, "Koi Thé", "Great!!!", "Delivery");
        String imghadilao = ImageUtils.encodeImg(ImageUtils.loadImage(this, "hadilao"));
        Restaurant hadilao = new Restaurant(imghadilao, "Hadilao", "Great!!!", "Review");
        String imgquan1 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "japan"));
        Restaurant restaurant1 = new Restaurant(imgquan1, "Sushi House", "Great!!!", "Review");
        String imgquan2 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
        Restaurant restaurant2 = new Restaurant(imgquan2, "Snowee", "Great!!!", "Delivery");
        String imgquan3 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "drink"));
        Restaurant restaurant3 = new Restaurant(imgquan3, "Cocktail", "Great!!!", "Review");

        System.out.println(db.insertQuan(pumpumtea));
        db.insertQuan(korean);
        db.insertQuan(gogi);
        db.insertQuan(koithe);
        db.insertQuan(hadilao);
        db.insertQuan(restaurant1);
        db.insertQuan(restaurant2);
        db.insertQuan(restaurant3);
    }

    private void insertMonAnData() {
//        String imgMon1 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
//        MonAn monAn1 = new MonAn(imgMon1, "Tên món ăn", "Mô tả", "Giá");
//        String imgMon2 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
//        MonAn monAn2 = new MonAn(imgMon2, "Tên món ăn", "Mô tả", "Giá");
//        String imgMon3 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
//        MonAn monAn3 = new MonAn(imgMon3, "Tên món ăn", "Mô tả", "Giá");
//
//        System.out.println(db.insertFood(monAn1));
//        db.insertFood(monAn2);
//        db.insertFood(monAn3);
    }

    public void startProfile() {
        Intent intent = new Intent(NavigationActivity.this, ProfileActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, REQUEST_GET_PROFILE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MoreFragment myFragment = (MoreFragment) getSupportFragmentManager().findFragmentByTag("moreFragment");
        if (myFragment != null && myFragment.isVisible()) {
            user = db.getUserById(user.getId());
            replaceFragment(new MoreFragment(db, user), "moreFragment");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GET_PROFILE && resultCode == RESULT_OK) {
            user = (User) data.getSerializableExtra("user");
            System.out.println("New name: " + user.getHoTen());
        }
    }

}
