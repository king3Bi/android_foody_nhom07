package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.model.User;

public interface HomeDAO {
    public List<Food> searchMonAn(String keyWord);
    public List<Restaurant> getAllQuan();
    public List<Food> getAllFood();
    public Restaurant getQuanById(int id);
    public boolean insertQuan(Restaurant restaurant);
    public boolean updateQuan(Restaurant restaurant);
    public boolean bookFood(User user, Food food, int quantity);
    public boolean saveFood(User user, Food food);
    public boolean checkSavedFood(int userId, int foodId);
}
