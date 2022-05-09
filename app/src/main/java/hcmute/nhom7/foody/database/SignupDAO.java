package hcmute.nhom7.foody.database;

public interface SignupDAO {
    public boolean checkExistsEmail(String email);
    public boolean signUp(String fullname, String email, String password);
}
