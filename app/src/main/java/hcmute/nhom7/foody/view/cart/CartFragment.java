package hcmute.nhom7.foody.view.cart;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.CartAdapter;
import hcmute.nhom7.foody.database.CartDAO;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.model.Bill;
import hcmute.nhom7.foody.model.Booking;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.User;

public class CartFragment extends Fragment {

    private CartDAO cartDAO;
    private User user;
    private ListView listViewCart;
    private CartAdapter cartAdapter;
    private List<Booking> bookingList;
    private Button btnBook, btnDelete;
    private Hashtable<Integer, Boolean> mapCheckBox;

    public CartFragment(CartDAO cartDAO, User user) {
        // Required empty public constructor
        this.cartDAO = cartDAO;
        this.user = user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mapCheckBox = new Hashtable<>();

        btnBook = view.findViewById(R.id.btnBook);
        btnDelete = view.findViewById(R.id.btnDeleteCart);

        listViewCart = view.findViewById(R.id.listViewCart);
        bookingList = cartDAO.getBookingInCartOfUser(user);
        cartAdapter = new CartAdapter(getContext(), R.layout.custom_layout_booking, bookingList, cartDAO, user, mapCheckBox);
        listViewCart.setAdapter(cartAdapter);
        this.initMapCheckBox();

        handleEvent();

        return view;
    }

    private void handleEvent() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Booking> deleteList = getBookingChecked();

                if (cartDAO.deleteCart(deleteList)) {
                    Toast.makeText(getContext(), "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                    deleteList.forEach(booking -> {
                        bookingList.remove(booking);
                    });
                    cartAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "???? c?? l???i x???y ra", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Booking> bookings = getBookingChecked();
                DialogBooKFood(user, bookings);
            }
        });
    }

    private boolean checkInfo(String address, String phone) {
        return !(address.isEmpty() || address == null || phone.isEmpty() || phone == null);
    }

    private void DialogBooKFood(User user, List<Booking> bookings) {
        Double totalPrice = calcTotalPrice(bookings);

        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_book_food);

        EditText edtAddress = dialog.findViewById(R.id.edtAddress);
        EditText edtPhone = dialog.findViewById(R.id.edtPhone);

        TextView textTotalPrice = dialog.findViewById(R.id.textTongTienBookDia);
        textTotalPrice.setText("T???ng ti???n: " + Double.toString(totalPrice) + " VND");

        Button btnBookOK = dialog.findViewById(R.id.btnBookOK);
        Button btnHuy = dialog.findViewById(R.id.btnCancelBook);


        btnBookOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();

                if (checkInfo(address, phone)) {

                    Bill newBill = new Bill(user.getId(), totalPrice, address, phone);

                    if (cartDAO.createBill(newBill, bookings)) {
                        Toast.makeText(getContext(), "?????t m??n th??nh c??ng", Toast.LENGTH_SHORT).show();
                        bookings.forEach(booking -> {
                            bookingList.remove(booking);
                        });
                        cartAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "???? c?? l???i x???y ra", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(getContext(), "Nh???p ?????a ch??? v??o s??? ??i???n tho???i", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private List<Booking> getBookingChecked() {
        List<Booking> bookings = new ArrayList<>();
        mapCheckBox.forEach((k, v) -> {
            if (v) {
                bookings.add(bookingList.get(k));
            }
        });
        return bookings;
    }

    private double calcTotalPrice(List<Booking> bookings) {
        double totalPrice = 0;
        for (Booking booking : bookings) {
            Food food = cartDAO.getFoodById(booking.getFoodId());
            totalPrice += booking.getQuantity() * food.getPrice();
        }
        return totalPrice;
    }

    private void loadData() {
        bookingList.clear();
        bookingList = cartDAO.getBookingOfUser(user);
        cartAdapter.notifyDataSetChanged();
    }

    private void initMapCheckBox() {
        for (int i = 0; i < this.listViewCart.getCount(); i++) {
            this.mapCheckBox.put(i, false);
        }
    }
}