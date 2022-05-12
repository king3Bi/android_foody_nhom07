package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Saved;
import hcmute.nhom7.foody.model.User;

public interface SavedDAO {
    public List<Saved> getAllSaved();
    public List<Saved> getAllSavedOfUser(int userId);
    public List<Saved> getAllFoodSavedOfUser(int userId);
    public List<Saved> getAllRestaurantSavedOfUser(int userId);
    public Food getFoodId(int id);
    public boolean removeSaved(int userId, int foodId);
    public boolean bookFood(User user, Food food, int quantity);
}
