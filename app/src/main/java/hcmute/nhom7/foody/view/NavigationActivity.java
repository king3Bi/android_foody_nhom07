package hcmute.nhom7.foody.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.database.QuanDAO;
import hcmute.nhom7.foody.databinding.LayoutNavigationBinding;
import hcmute.nhom7.foody.model.MonAn;
import hcmute.nhom7.foody.model.Quan;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ImageUtils;
import hcmute.nhom7.foody.view.fragment.HistoryFragment;
import hcmute.nhom7.foody.view.home.HomeFragment;
import hcmute.nhom7.foody.view.fragment.MoreFragment;
import hcmute.nhom7.foody.view.saved.SavedFragment;

public class NavigationActivity extends AppCompatActivity {

    private User user;
    LayoutNavigationBinding binding;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        db = new Database(this);

        user = (User) getIntent().getSerializableExtra("user");

        if (user == null) {
            Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(intent);
        }

//        insertQuanData();
//        insertMonAnData();

//        Quan pumpumtea = db.getQuanById(1);
//        Bitmap bm = ImageUtils.loadImage(this, pumpumtea.getImage());
//        String bmStr = ImageUtils.encodeImg(bm);
//        System.out.println("Name img: " + pumpumtea.getImage());
//        System.out.println("Endcode img: " + bmStr);
//
//        pumpumtea.setImage(bmStr);
//        System.out.println("Update pumpumtea: " + db.updateQuan(pumpumtea));

        binding = LayoutNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment(db, user));

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    replaceFragment(new HomeFragment(db, user));
                    break;
                case R.id.menu_saved:
                    replaceFragment(new SavedFragment(db, user));
                    break;
                case R.id.menu_history:
                    replaceFragment(new HistoryFragment());
                    break;
                case R.id.menu_more:
                    replaceFragment(new MoreFragment(db, user));
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

    private void insertQuanData() {
        String imgPumpum = ImageUtils.encodeImg(ImageUtils.loadImage(this, "milktea"));
        Quan pumpumtea = new Quan(imgPumpum, "Pum Pum Tea", "Comment comment", "Delivery");
        String imgKorean = ImageUtils.encodeImg(ImageUtils.loadImage(this, "korean"));
        Quan korean = new Quan(imgKorean, "Korean Food", "Great!!!", "Review");
        String imgGogi = ImageUtils.encodeImg(ImageUtils.loadImage(this, "gogi"));
        Quan gogi = new Quan(imgGogi, "Gogi House", "Great!!!", "Review");
        String imgkoithe = ImageUtils.encodeImg(ImageUtils.loadImage(this, "koi"));
        Quan koithe = new Quan(imgkoithe, "Koi Thé", "Great!!!", "Delivery");
        String imghadilao = ImageUtils.encodeImg(ImageUtils.loadImage(this, "hadilao"));
        Quan hadilao = new Quan(imghadilao, "Hadilao", "Great!!!", "Review");
        String imgquan1 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "japan"));
        Quan quan1 = new Quan(imgquan1, "Sushi House", "Great!!!", "Review");
        String imgquan2 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
        Quan quan2 = new Quan(imgquan2, "Snowee", "Great!!!", "Delivery");
        String imgquan3 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "drink"));
        Quan quan3 = new Quan(imgquan3, "Cocktail", "Great!!!", "Review");

        System.out.println(db.insertQuan(pumpumtea));
        db.insertQuan(korean);
        db.insertQuan(gogi);
        db.insertQuan(koithe);
        db.insertQuan(hadilao);
        db.insertQuan(quan1);
        db.insertQuan(quan2);
        db.insertQuan(quan3);
    }

    private void insertMonAnData() {
        String imgMon1 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
        MonAn monAn1 = new MonAn(imgMon1, "Tên món ăn", "Mô tả", "Giá");
        String imgMon2 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
        MonAn monAn2 = new MonAn(imgMon2, "Tên món ăn", "Mô tả", "Giá");
        String imgMon3 = ImageUtils.encodeImg(ImageUtils.loadImage(this, "snowee"));
        MonAn monAn3 = new MonAn(imgMon3, "Tên món ăn", "Mô tả", "Giá");

        System.out.println(db.insertFood(monAn1));
        db.insertFood(monAn2);
        db.insertFood(monAn3);
    }

}
