package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Saved;

public interface SavedDAO {
    public List<Saved> getAllSaved();
    public List<Saved> getAllFoodSaved();
    public List<Saved> getAllRestaurantSaved();
}
