package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Quan;

public interface QuanDAO {
    public List<Quan> getAllQuan();
    public Quan getQuanById(int id);
    public boolean insertQuan(Quan quan);
    public boolean updateQuan(Quan quan);
}
