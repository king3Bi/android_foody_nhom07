package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Food;

public interface FoodDAO {
    public List<Food> getAllFood();
    public List<Food> getFoodByRestaurantId(int id);
    public List<Food> findFoodByName(String name);
    public Food getFoodById(int id);
    public boolean insertFood(Food food);
}
