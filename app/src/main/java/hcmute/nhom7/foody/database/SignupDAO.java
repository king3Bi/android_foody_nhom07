package hcmute.nhom7.foody.database;

import hcmute.nhom7.foody.model.User;

public interface SignupDAO {
    public boolean checkExistsEmail(String email);
    public boolean signUp(User user);
}
