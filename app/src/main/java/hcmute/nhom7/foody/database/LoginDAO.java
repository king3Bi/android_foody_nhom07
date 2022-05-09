package hcmute.nhom7.foody.database;

import hcmute.nhom7.foody.model.User;

public interface LoginDAO {
    public User login(String email, String password);
}
