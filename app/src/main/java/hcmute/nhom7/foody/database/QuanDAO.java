package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Restaurant;

public interface QuanDAO {
    public List<Restaurant> getAllQuan();
    public Restaurant getQuanById(int id);
    public boolean insertQuan(Restaurant restaurant);
    public boolean updateQuan(Restaurant restaurant);
}
