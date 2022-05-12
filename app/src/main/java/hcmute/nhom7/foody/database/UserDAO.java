package hcmute.nhom7.foody.database;

import hcmute.nhom7.foody.model.User;

public interface UserDAO {
    public User getUserById(int id);
    public boolean updateFullInfo(User user);
    public boolean updateInfo(User user);
}
