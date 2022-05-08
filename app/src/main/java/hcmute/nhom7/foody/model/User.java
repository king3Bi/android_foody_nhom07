package hcmute.nhom7.foody.model;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;

    public User(int id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return fullName;
    }

    public void setHoTen(String hoTen) {
        this.fullName = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "id='" + id + '\'' +
                ", hoTen='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
