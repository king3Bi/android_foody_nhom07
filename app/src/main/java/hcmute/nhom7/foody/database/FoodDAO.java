package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.MonAn;

public interface FoodDAO {
    public List<MonAn> getAllFood();
    public List<MonAn> getFoodByRestaurantId(int id);
    public List<MonAn> findFoodByName(String name);
    public MonAn getFoodId(int id);
    public boolean insertFood(MonAn monAn);
}
