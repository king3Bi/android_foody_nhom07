package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.MonAn;
import hcmute.nhom7.foody.model.Quan;
import hcmute.nhom7.foody.model.User;

public interface HomeDAO {
    public List<MonAn> searchMonAn(String keyWord);
    public List<Quan> getAllQuan();
    public Quan getQuanById(int id);
    public boolean insertQuan(Quan quan);
    public boolean updateQuan(Quan quan);
    public boolean bookFood(User user, MonAn monAn, int quantity);
}
