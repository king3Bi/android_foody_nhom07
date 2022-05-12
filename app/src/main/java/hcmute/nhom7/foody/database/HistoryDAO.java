package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Bill;
import hcmute.nhom7.foody.model.User;

public interface HistoryDAO {
    public List<Bill> getAllBillOfUser(User user);
}
