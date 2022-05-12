package hcmute.nhom7.foody.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ImageUtils;

public class PrepareData {
    private Database db;
    private Context context;

    public PrepareData(Context context) {
        db = new Database(context);
    }

    public void insertUsers() {
        User hau = new User("Võ Văn Hậu", "hau@gmail.com", "1");
        User nhu = new User("Lâm Tâm Như", "nhu@gmail.com", "1");

        db.signUp(hau);
        db.signUp(nhu);
    }

    public void insertData() {
        List<Restaurant> restaurantList = new ArrayList<>();

        Restaurant pumpumtea = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "milktea")),
                "Pum Pum Tea", "Comment comment", "Delivery");
        restaurantList.add(pumpumtea);

        Restaurant korean = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "korean")),
                "Korean Food", "Great!!!", "Review");
        restaurantList.add(korean);

        Restaurant gogi = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "gogi")),
                "Gogi House", "Great!!!", "Review");
        restaurantList.add(gogi);

        Restaurant koithe = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "koi")),
                "Koi Thé", "Great!!!", "Delivery");
        restaurantList.add(koithe);

        Restaurant hadilao = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "hadilao")),
                "Hadilao", "Great!!!", "Review");
        restaurantList.add(hadilao);

        Restaurant restaurant1 = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "japan")),
                "Sushi House", "Great!!!", "Review");
        restaurantList.add(restaurant1);

        Restaurant restaurant2 = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "snowee")),
                "Snowee", "Great!!!", "Delivery");
        restaurantList.add(restaurant2);

        Restaurant restaurant3 = new Restaurant(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "drink")),
                "Cocktail", "Great!!!", "Review");
        restaurantList.add(restaurant3);

        restaurantList.forEach(quan -> {
            db.insertQuan(quan);
        });

        List<Food> foodList = new ArrayList<>();

        Food tomChienXu = new Food(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "tom_chien_xu")),
                "Tôm chiên xù", "", 50000, korean.getId());
        foodList.add(tomChienXu);

        Food goiTom = new Food(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "goi_xoai_xanh_tom_kho")),
                "Gỏi xoài xanh tôm khô", "", 60000, koithe.getId());
        foodList.add(goiTom);

        Food caChien = new Food(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "ca_chien_sot_chua_ngot")),
                "Cá chiên sốt chua ngọt", "", 55000, koithe.getId());
        foodList.add(caChien);

        Food canhGaChienGion = new Food(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "canh_ga_chien_gion")),
                "Cánh gà chiên giòn", "", 47000, hadilao.getId());
        foodList.add(canhGaChienGion);

        Food canhGaChienMam = new Food(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "canh_ga_chien_Mam")),
                "Cánh gà chiên mắm", "", 49000, restaurant1.getId());
        foodList.add(canhGaChienMam);

        Food traSuaTranChau = new Food(ImageUtils.encodeImg(ImageUtils.loadImage(this.context, "tra_sua_tran_chau")),
                "Trà sữa trân châu", "", 35000, restaurant1.getId());
        foodList.add(traSuaTranChau);

        foodList.forEach(monAn -> {
            db.insertFood(monAn);
        });
    }

}
