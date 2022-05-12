package hcmute.nhom7.foody.database;

import java.util.List;

import hcmute.nhom7.foody.model.Bill;
import hcmute.nhom7.foody.model.Booking;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.User;

public interface CartDAO {
    public List<Booking> getBookingOfUser(User user);
    public List<Booking> getBookingInCartOfUser(User user);
    public Food getFoodById(int foodId);
    public boolean deleteCart(List<Booking> bookingList);
    public boolean createBill(Bill bill, List<Booking> bookingList);
}
