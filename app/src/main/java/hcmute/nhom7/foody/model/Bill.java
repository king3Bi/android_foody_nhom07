package hcmute.nhom7.foody.model;

public class Bill {
    private int id;
    private int userId;
    private double totalPrice;
    private String address;
    private String phoneNumber;

    public Bill(int id, int userId, double totalPrice, String address, String phoneNumber) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Bill(int userId, double totalPrice, String address, String phoneNumber) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Bill(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
